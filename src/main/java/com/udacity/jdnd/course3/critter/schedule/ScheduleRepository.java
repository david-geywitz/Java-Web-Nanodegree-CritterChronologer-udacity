package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByPetsId(Long id);
    List<Schedule> findAllByEmployeesId(Long id);
    List<Schedule> findAllByPetsOwnerId(Long id);
}
