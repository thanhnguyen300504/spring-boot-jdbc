package com.example.springboot.jdbc.repository;

import com.example.springboot.jdbc.model.Person;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PeopleRepository {
    private static final String SELECT_ALL_SQL_QUERY = "SELECT * FROM people";
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Person> personRowMapper;
    private List<Person> peopleList;

    @Autowired
    public PeopleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setPersonRowMapper(RowMapper<Person> personRowMapper) {
        this.personRowMapper = personRowMapper;
    }

    @PostConstruct
    private void init() {
        peopleList = this.findAll(SELECT_ALL_SQL_QUERY);
    }

    public List<Person> findAll(String sql) {
        List<Person> lst = jdbcTemplate.query(sql, personRowMapper);
        return lst;

    }

    public void save(String sql, int id, String firstName) {
        jdbcTemplate.update(sql, id, firstName);
        peopleList.add(new Person(id, firstName));
    }


    public void update(String sql, String newFirstName, int newId, int oldId) {
        jdbcTemplate.update(sql, newFirstName, newId, oldId);
    }

    public void delete(String sql, int id) {
        jdbcTemplate.update(sql, id);
    }


    public List<Person> getPeopleList() {
        return peopleList;
    }

    public void refresh() {
        peopleList = jdbcTemplate.query(SELECT_ALL_SQL_QUERY, personRowMapper);
    }
}


