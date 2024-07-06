package service.backend_spring3.repository;

import org.hibernate.sql.Update;
import service.backend_spring3.domain.User;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;


import javax.sql.DataSource;
import java.util.*;

public class JdbcTemplateUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public void save(User user) {
//        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
//
//        jdbcTemplate.update("insert into ");
//        Map<String, Object> parameters1 = new HashMap<>();
//        parameters1.put("name",user.getName());
//        Map<String, Object> parameters2= new HashMap<>();
//        parameters2.put("password",user.getPassword());
//        User key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters1));
//        jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters2));
//        user.setId(key.longValue());
//        return user;

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", user.getName());
        parameters.put("password", user.getPassword());
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("user").usingGeneratedKeyColumns("id");
        jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
//        Number newId = jdbcInsert.executeAndReturnKey(parameters);
//        User key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
//
//        return
    }

    @Override
    public Optional<User> findById(Long id) {
        List<User> result = jdbcTemplate.query("SELECT * FROM user WHERE id = ?", userRowMapper(),id);
        return result.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM user",userRowMapper());
    }

    @Override
    public Optional<User> findByName(String name) {
        List<User> result = jdbcTemplate.query("SELECT * FROM user WHERE name = ?", userRowMapper(), name);
        return result.stream().findAny();
    }

    private RowMapper<User> userRowMapper(){

        return (rs,rowNum) -> {
            User  user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            return user;
        };


    }
}
