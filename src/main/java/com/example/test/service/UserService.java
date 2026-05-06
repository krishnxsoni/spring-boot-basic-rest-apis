package com.example.test.service;

import com.example.test.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<User> getAllUsers() {
        String query = "SELECT * FROM user";

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setUserName(rs.getString("username"));
            user.setEmailId(rs.getString("email_id"));
            user.setMobileNo(rs.getString("mobile_no"));
            user.setStatus(rs.getBoolean("status"));
            return user;
        });
    }

    public User getUserByUsername(String username) {
        String query = "SELECT * FROM user where username = ?";
        User u = null;

        try{
            u =  jdbcTemplate.queryForObject(
                    query,
                    new Object[]{username},
                    (rs, rowNum) -> {
                        User user = new User();
                        user.setId(rs.getLong("id"));
                        user.setFirstName(rs.getString("first_name"));
                        user.setLastName(rs.getString("last_name"));
                        user.setUserName(rs.getString("username"));
                        user.setEmailId(rs.getString("email_id"));
                        user.setMobileNo(rs.getString("mobile_no"));
                        user.setStatus(rs.getBoolean("status"));
                        log.info("User Found! user details :: "+user);
                        return user;
                    }
            );
        }catch (Exception e){
            log.error("USER NOT FOUND! with the provided username :: "+username);
        }
        return u;
    }

    public ResponseEntity addNewUsers(User user)
    {
        if(user!=null)
        {
            String query = "SELECT COUNT(*) FROM user WHERE username = ?";
            Integer count = jdbcTemplate.queryForObject(query, Integer.class, user.getUserName());

             if(count != null && count > 0){
                 return ResponseEntity
                         .badRequest()
                         .body(Map.of(
                                 "message", "User with this username already exists",
                                 "details", "Kindly check with another username"
                         ));
             }else{
                 String insertQuery = "INSERT INTO user (first_name,last_name,username,email_id,mobile_no,status) VALUES (?,?,?,?,?,?)";
                 int rows = jdbcTemplate.update(insertQuery,
                         user.getFirstName(),
                         user.getLastName(),
                         user.getUserName(),
                         user.getEmailId(),
                         user.getMobileNo(),
                         user.isStatus()
                 );
                 if(rows>0){
                     return ResponseEntity
                             .ok()
                             .body(Map.of(
                                     "message","User created successfully!",
                                     "user-details",user
                             ));
                 }
             }
        }
        return ResponseEntity
            .badRequest()
            .body(Map.of(
                    "message", "Oops!",
                    "details", "Something went wrong..."
            ));
    }

    public ResponseEntity removeUserByUsername(String username) {
        String query = "DELETE FROM user where username = ?";
        int result = jdbcTemplate.update(query, username);

        if(result>0){
            return ResponseEntity
                    .ok()
                    .body(Map.of(
                            "message","User deleted successfully!",
                            "user-name",username
                    ));
        }
        else {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of(
                            "message","User not found!"
                    ));
        }
    }

    public ResponseEntity updateUserById(int id,String responseBody) {
        if(responseBody!=null)
        {

            String query = "SELECT COUNT(*) FROM user WHERE id = ?";
            Integer count = jdbcTemplate.queryForObject(query, Integer.class, id);

            if(count != null && count > 0)
            {
                String updateUsername = "UPDATE USER set username = ? where id = ?";
                log.info("Updating username for id: {}", id);
                int result = jdbcTemplate.update(updateUsername, responseBody,id);
                if(result>0)
                {
                    return ResponseEntity
                            .ok()
                            .body(Map.of(
                                    "message","Username updated successfully!",
                                    "user-name",responseBody
                            ));
                }
                else {
                    return ResponseEntity
                            .badRequest()
                            .body(Map.of(
                                    "message","User not found!"
                            ));
                }
            }
        }
        return ResponseEntity
                .badRequest()
                .body(Map.of(
                        "message","Id not found!"
                ));
    }
}
