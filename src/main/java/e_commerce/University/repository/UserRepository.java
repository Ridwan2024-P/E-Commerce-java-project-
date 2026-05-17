package e_commerce.University.repository;

import e_commerce.University.entity.User;
import e_commerce.University.Enum.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public int save(User user) {

        String sql = "INSERT INTO users(name, email, password, role, created_at) VALUES (?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().toString(),
                user.getCreatedAt()
        );
    }


    public List<User> findAll() {

        String sql = "SELECT * FROM users";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {

            User user = new User();

            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));

            user.setRole(UserRole.valueOf(rs.getString("role")));

            user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

            return user;
        });
    }


    public Optional<User> findById(Long id) {

        String sql = "SELECT * FROM users WHERE id = ?";

        List<User> users = jdbcTemplate.query(sql, new Object[]{id},
                (rs, rowNum) -> {

                    User user = new User();

                    user.setId(rs.getLong("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(UserRole.valueOf(rs.getString("role")));
                    user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

                    return user;
                });

        return users.stream().findFirst();
    }


    public int update(User user) {

        String sql = "UPDATE users SET name = ?, email = ?, password = ?, role = ? WHERE id = ?";

        return jdbcTemplate.update(sql,
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().toString(),
                user.getId()
        );
    }


    public int deleteById(Long id) {

        String sql = "DELETE FROM users WHERE id = ?";

        return jdbcTemplate.update(sql, id);
    }


    public Optional<User> findByEmail(String email) {

        String sql = "SELECT * FROM users WHERE email = ?";

        List<User> users = jdbcTemplate.query(sql, new Object[]{email},
                (rs, rowNum) -> {

                    User user = new User();

                    user.setId(rs.getLong("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(UserRole.valueOf(rs.getString("role")));
                    user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

                    return user;
                });

        return users.stream().findFirst();
    }
}