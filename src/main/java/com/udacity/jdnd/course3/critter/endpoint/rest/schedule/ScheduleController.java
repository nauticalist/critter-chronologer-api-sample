package com.udacity.jdnd.course3.critter.endpoint.rest.schedule;

import com.udacity.jdnd.course3.critter.application.ScheduleService;
import com.udacity.jdnd.course3.critter.domain.model.Employee;
import com.udacity.jdnd.course3.critter.domain.model.Pet;
import com.udacity.jdnd.course3.critter.domain.model.Schedule;
import com.udacity.jdnd.course3.critter.endpoint.dto.ScheduleDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return this.mapScheduleToScheduleDTO(
                scheduleService.saveSchedule(
                        this.mapScheduleDTOToSchedule(scheduleDTO),
                        scheduleDTO.getEmployeeIds(),
                        scheduleDTO.getPetIds()));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.getAll().stream().map(this::mapScheduleToScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return scheduleService.getSchedulesForPet(petId).stream()
                .map(this::mapScheduleToScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleService.getScheduleForEmployee(employeeId).stream()
                .map(this::mapScheduleToScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService.getScheduleForCustomer(customerId).stream()
                .map(this::mapScheduleToScheduleDTO).collect(Collectors.toList());
    }

    private ScheduleDTO mapScheduleToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        scheduleDTO.setEmployeeIds(schedule.getEmployees().stream()
                .map(Employee::getId).collect(Collectors.toList()));
        scheduleDTO.setPetIds(schedule.getPets().stream()
                .map(Pet::getId).collect(Collectors.toList()));
        return scheduleDTO;
    }

    private Schedule mapScheduleDTOToSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        return schedule;
    }
}
