package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e where :day member of e.daysAvailable")
    List<Employee> availableEmployeeForServiceByDay(DayOfWeek day);
}
