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

import next.jwp.model.Issue;

@Repository
public class IssueDao {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public Issue insert(Issue issue) {
        String query = "INSERT INTO ISSUE (writerId, milestoneId, subject, isOpen, comment, date) VALUES(:writerId, :milestoneId, :subject, :isOpen, :comment, CURRENT_TIMESTAMP)";

        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(issue);
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(query, namedParameters, holder);

        issue.setId(holder.getKey().intValue());
        return issue;
    }

    public Issue findById(int id) {
        String sql = "SELECT * FROM ISSUE WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        return DataAccessUtils
                .singleResult(jdbcTemplate.query(sql, namedParameters, new BeanPropertyRowMapper<Issue>(Issue.class)));
    }

    public List<Issue> findAll() {
        String sql = "SELECT * FROM ISSUE";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Issue>(Issue.class));
    }

    public List<Issue> findAllByMilestoneId(int id) {
        String sql = "SELECT * FROM ISSUE WHERE milestoneId = :milestoneId";
        SqlParameterSource namedParameters = new MapSqlParameterSource("milestoneId", id);
        return jdbcTemplate.query(sql, namedParameters, new BeanPropertyRowMapper<Issue>(Issue.class));
    }

    public void delete(int id) {
        String sql = "DELETE FROM ISSUE WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(sql, namedParameters);
    }

    public void update(Issue issue) {
        String sql = "UPDATE ISSUE SET milestoneId = :milestoneId, subject = :subject, isOpen = :isOpen, comment = :comment, assigneeId = :assigneeId, labelId = :labelId WHERE id = :id";
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(issue);
        jdbcTemplate.update(sql, namedParameters);

    }

}
