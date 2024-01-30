package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.schedule.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeDAOImplementation employeeDAOImplementation;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private EmployeeRepository employeeRepository;

public List<Employee> list() {
    return employeeRepository.findAll();
}

public Employee save(Employee employee) {
    return employeeRepository.save(employee);
}

public Employee findById(Long id) {
    return employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
}

public List<Employee> availableEmployeeForService(LocalDate date, LocalTime startTime, LocalTime endTime, Set<EmployeeSkill> skills) {
    return employeeDAOImplementation.availableEmployeeForService(date, startTime, endTime, skills)
            .stream()
            .map(employee -> findById(employee.getId()))
            .collect(Collectors.toList());
}
}
