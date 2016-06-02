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

import next.jwp.model.Milestone;

@Repository
public class MilestoneDao {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    
    public Milestone insert(Milestone milestone){
       String query = "INSERT INTO MILESTONE (subject, startDate, endDate) VALUES(:subject, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
       
       SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(milestone);
       KeyHolder holder = new GeneratedKeyHolder();
       jdbcTemplate.update(query, namedParameters, holder);
       
       milestone.setId(holder.getKey().intValue());
       return milestone;
    }

    public Milestone findById(int id) {
        String sql = "SELECT * FROM MILESTONE WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        return DataAccessUtils.singleResult(jdbcTemplate.query(sql, namedParameters, new BeanPropertyRowMapper<Milestone>(Milestone.class)));
    }
    
    public List<Milestone> findAll() {
        String sql = "SELECT * FROM MILESTONE";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Milestone>(Milestone.class));
    }

    public void delete(int id) {
        String sql = "DELETE FROM MILESTONE WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(sql, namedParameters);
    }

    public void update(Milestone milestone) {
        String sql = "UPDATE MILESTONE SET subject = :subject, startDate = :startDate, endDate = :endDate WHERE id = :id";
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(milestone);
        jdbcTemplate.update(sql, namedParameters);
        
    }

}
