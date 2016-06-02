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

import next.jwp.model.Label;

@Repository
public class LabelDao {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    
    public Label insert(Label label){
       String query = "INSERT INTO LABEL (labelText, color) VALUES(:labelText, :color)";
       
       SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(label);
       KeyHolder holder = new GeneratedKeyHolder();
       jdbcTemplate.update(query, namedParameters, holder);
       
       label.setId(holder.getKey().intValue());
       return label;
    }

    public Label findById(int id) {
        String sql = "SELECT * FROM LABEL WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        return DataAccessUtils.singleResult(jdbcTemplate.query(sql, namedParameters, new BeanPropertyRowMapper<Label>(Label.class)));
    }

    public List<Label> findAll() {
        String sql = "SELECT * FROM LABEL";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Label>(Label.class));
    }

    public void delete(int id) {
        String sql = "DELETE FROM LABEL WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(sql, namedParameters);
    }

    public void update(Label label) {
        String sql = "UPDATE LABEL SET labelText = :labelText, color = :color WHERE id = :id";
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(label);
        jdbcTemplate.update(sql, namedParameters);
        
    }

}
