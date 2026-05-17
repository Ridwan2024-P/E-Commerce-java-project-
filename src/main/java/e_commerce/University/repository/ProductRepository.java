package e_commerce.University.repository;

import e_commerce.University.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Product save(Product product) {

        String sql = "INSERT INTO products(name, price, stock) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {


            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getStock());

            return ps;
        }, keyHolder);


        product.setId(keyHolder.getKey().longValue());

        return product;
    }


    public List<Product> findAll() {

        String sql = "SELECT * FROM products";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {

            Product product = new Product();

            product.setId(rs.getLong("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setStock(rs.getInt("stock"));

            return product;
        });
    }


    public Optional<Product> findById(Long id) {

        String sql = "SELECT * FROM products WHERE id = ?";

        List<Product> products = jdbcTemplate.query(sql, new Object[]{id},
                (rs, rowNum) -> {

                    Product product = new Product();

                    product.setId(rs.getLong("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setStock(rs.getInt("stock"));

                    return product;
                });

        return products.stream().findFirst();
    }


    public int update(Product product) {

        String sql = "UPDATE products SET name = ?, price = ?, stock = ? WHERE id = ?";

        return jdbcTemplate.update(sql,
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getId()
        );
    }


    public int deleteById(Long id) {

        String sql = "DELETE FROM products WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}