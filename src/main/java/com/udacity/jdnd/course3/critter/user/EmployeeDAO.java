package com.udacity.jdnd.course3.critter.user;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public interface EmployeeDAO {

    List<Employee> availableEmployeeForService(LocalDate date, LocalTime startTime, LocalTime endTime, Set<EmployeeSkill> skills);
}
