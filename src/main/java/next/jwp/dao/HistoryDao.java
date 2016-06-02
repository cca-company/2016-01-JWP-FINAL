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

import next.jwp.model.History;

@Repository
public class HistoryDao {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    
    public History insert(History history){
       String query = "INSERT INTO HISTORY (writerId, issueId, type, content, date) VALUES(:writerId, :issueId, :type, :content, CURRENT_TIMESTAMP)";
       
       SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(history);
       KeyHolder holder = new GeneratedKeyHolder();
       jdbcTemplate.update(query, namedParameters, holder);
       
       history.setId(holder.getKey().intValue());
       return history;
    }

    public History findById(int id) {
        String sql = "SELECT * FROM HISTORY WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        return DataAccessUtils.singleResult(jdbcTemplate.query(sql, namedParameters, new BeanPropertyRowMapper<History>(History.class)));
    }
    
    public List<History> findAllByIssueId(int id) {
        String sql = "SELECT * FROM HISTORY WHERE issueId = :issueId";
        SqlParameterSource namedParameters = new MapSqlParameterSource("issueId", id);
        return jdbcTemplate.query(sql, namedParameters, new BeanPropertyRowMapper<History>(History.class));
    }

    public void delete(int id) {
        String sql = "DELETE FROM HISTORY WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(sql, namedParameters);
    }

    public void update(History history) {
        String sql = "UPDATE HISTORY SET content = :content WHERE id = :id";
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(history);
        jdbcTemplate.update(sql, namedParameters);
        
    }

}
