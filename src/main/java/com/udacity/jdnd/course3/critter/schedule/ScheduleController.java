package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PetService petService;

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@Valid @RequestBody ScheduleDTO scheduleDTO) {
        return entityToScheduleDTO(scheduleService.save(scheduleDTOToEntity(scheduleDTO)));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.list()
                .stream()
                .map(this::entityToScheduleDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return scheduleService.allWithPet(petId)
                .stream()
                .map(this::entityToScheduleDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleService.allWithEmployee(employeeId)
                .stream()
                .map(this::entityToScheduleDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService.allWithCustomer(customerId)
                .stream()
                .map(this::entityToScheduleDTO)
                .collect(Collectors.toList());
    }

    private Schedule scheduleDTOToEntity(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);

        if(scheduleDTO.getEmployeeIds().size() > 0) {
            schedule.setEmployees(scheduleDTO.getEmployeeIds()
                    .stream()
                    .map(id -> employeeService.findById(id))
                    .collect(Collectors.toList())
            );
        }

        if(scheduleDTO.getPetIds().size() > 0) {
            schedule.setPets(scheduleDTO.getPetIds()
                    .stream()
                    .map(id -> petService.findById(id))
                    .collect(Collectors.toList())
            );
        }

        return schedule;
    }

    private ScheduleDTO entityToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);

        if(schedule.getEmployees().size() > 0) {
            scheduleDTO.setEmployeeIds(schedule.getEmployees()
            .stream()
            .map(Employee::getId)
            .collect(Collectors.toList())
            );
        }

        if(schedule.getPets().size() > 0) {
            scheduleDTO.setPetIds(schedule.getPets()
            .stream()
            .map(Pet::getId)
            .collect(Collectors.toList())
            );
        }

        return scheduleDTO;
    }

}
