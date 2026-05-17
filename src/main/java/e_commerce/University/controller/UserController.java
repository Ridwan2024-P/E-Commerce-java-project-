package e_commerce.University.controller;

import e_commerce.University.dto.UserDTO;
import e_commerce.University.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public Object createUser(@RequestBody UserDTO userDTO) {

        try {
            return userService.createUser(userDTO);
        } catch (Exception e) {
            return "Error creating user: " + e.getMessage();
        }
    }


    @GetMapping
    public Object getAllUsers() {

        try {
            return userService.getAllUsers();
        } catch (Exception e) {
            return "Error fetching users: " + e.getMessage();
        }
    }


    @GetMapping("/{id}")
    public Object getUserById(@PathVariable Long id) {

        try {
            return userService.getUserById(id);
        } catch (Exception e) {
            return "User not found: " + e.getMessage();
        }
    }


    @PutMapping("/{id}")
    public Object updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {

        try {
            return userService.updateUser(id, userDTO);
        } catch (Exception e) {
            return "Error updating user: " + e.getMessage();
        }
    }


    @DeleteMapping("/{id}")
    public Object deleteUser(@PathVariable Long id) {

        try {
            userService.deleteUser(id);
            return "User deleted successfully";
        } catch (Exception e) {
            return "Error deleting user: " + e.getMessage();
        }
    }
}