package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class EmployeeDAOImplementation implements EmployeeDAO {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    private static final String EMPLOYEE_AVAILABLE_FOR_SERVICE =
    "SELECT e.* " +
    "FROM employee e " +
    "JOIN employee_days_available a " +
    "ON e.id = a.employee_id " +
    "JOIN employee_skills s " +
    "ON e.id = s.employee_id " +
    "WHERE a.days_available = :dayOfWeek " +
    "AND s.skills IN (:requiredSkills) " +
    "AND e.id NOT IN ( SELECT es.employee_id " +
    "FROM employee_schedule es " +
    "JOIN schedule sc " +
    "ON es.schedule_id = sc.id " +
    "WHERE es.employee_id = e.id " +
    "AND sc.date = :date " +
    "AND ((sc.start_time <= :startTime AND sc.end_time >= :startTime) " +
    "OR (sc.start_time <= :endTime AND sc.end_time >= :endTime))) " +
    "GROUP BY e.id " +
    "HAVING COUNT(e.id) = :numberOfRequiredSkills";

    private static final BeanPropertyRowMapper<Employee> employeeRowMapper = new BeanPropertyRowMapper<>(Employee.class);

    @Override
    public List<Employee> availableEmployeeForService(LocalDate date, LocalTime startTime, LocalTime endTime, Set<EmployeeSkill> skills) {
        List<Employee> employeeList = jdbcTemplate.query(EMPLOYEE_AVAILABLE_FOR_SERVICE,
                new MapSqlParameterSource()
                        .addValue("dayOfWeek", date.getDayOfWeek().name())
                        .addValue("requiredSkills", skills.stream().map(employeeSkill -> employeeSkill.name()).collect(Collectors.toSet()))
                        .addValue("date", date)
                        .addValue("startTime", startTime)
                        .addValue("endTime", endTime)
                        .addValue("numberOfRequiredSkills", skills.size()),
                employeeRowMapper);

        return employeeList;
    }
}
