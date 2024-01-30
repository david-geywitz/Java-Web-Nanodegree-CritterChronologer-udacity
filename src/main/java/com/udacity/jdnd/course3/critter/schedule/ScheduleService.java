package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.user.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Schedule> list() {
        return scheduleRepository.findAll();
    }

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public Optional<Schedule> findById(Long id) {
        return scheduleRepository.findById(id);
    }

    public List<Schedule> allWithCustomer(Long customerId) {
        return scheduleRepository.findAllByPetsOwnerId(customerId);
    }

    public List<Schedule> allWithEmployee(Long employeeId) {
        return scheduleRepository.findAllByEmployeesId(employeeId);
    }

    public List<Schedule> allWithPet(Long petId) {
        return scheduleRepository.findAllByPetsId(petId);
    }

    public void deleteSchedule(Schedule schedule) {
        scheduleRepository.findById(schedule.getId()).orElseThrow(ScheduleNotFoundException::new);
        scheduleRepository.delete(schedule);
    }
}
