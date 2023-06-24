package com.crudapplication.usermanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<User> getAll() {
        return jdbcTemplate.query("SELECT id,firstname, lastname, age FROM user",
                BeanPropertyRowMapper.newInstance(User.class));
    }

    public User getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM user WHERE" +
                " id = ?", BeanPropertyRowMapper.newInstance(User.class), id);
    }

    public int save(List<User> users) {
        users.forEach(user -> jdbcTemplate
                .update("INSERT INTO user(firstname, lastname, age) VALUES(?, ?, ?)",
                        user.getFirstname(), user.getLastname(), user.getAge()));
        return 1;
    }

    public int update(User user) {
        return jdbcTemplate.update("UPDATE user SET firstname=?, lastname=?, age=? WHERE id=?",
                user.getFirstname(), user.getLastname(), user.getAge(), user.getId());
    }
}
