package next.jwp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import next.jwp.model.User;

@Repository
public class UserDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public User insert(User user) {
        String sql = "INSERT INTO USERS (userId, name, password, pic) VALUES (:userId, :name, :password, :pic)";
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, namedParameters, holder);

        Integer workImageId = holder.getKey().intValue();
        user.setId(workImageId);
        return user;
    }

    public User findById(int id) {
        String sql = "SELECT * FROM USERS WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        return DataAccessUtils.singleResult(jdbcTemplate.query(sql, namedParameters, new BeanPropertyRowMapper<User>(User.class)));
    }

    public User findByUserId(String userId) {
        String sql = "SELECT * FROM USERS WHERE userId = :userId";
        SqlParameterSource namedParameters = new MapSqlParameterSource("userId", userId);
        return DataAccessUtils.singleResult(jdbcTemplate.query(sql, namedParameters, new BeanPropertyRowMapper<User>(User.class)));
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM USERS";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
    }

    public void delete(int id) {
        String sql = "DELETE FROM USERS WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(sql, namedParameters);
    }


    public void update(User user) {
        String sql = "UPDATE USERS SET userId = :userId, name = :name, password = :password WHERE id = :id";
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
        jdbcTemplate.update(sql, namedParameters);
    }

}
