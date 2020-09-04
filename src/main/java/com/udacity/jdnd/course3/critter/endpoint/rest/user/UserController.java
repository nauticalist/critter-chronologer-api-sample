package com.udacity.jdnd.course3.critter.endpoint.rest.user;

import com.udacity.jdnd.course3.critter.application.CustomerService;
import com.udacity.jdnd.course3.critter.application.EmployeeService;
import com.udacity.jdnd.course3.critter.application.PetService;
import com.udacity.jdnd.course3.critter.domain.model.Customer;
import com.udacity.jdnd.course3.critter.domain.model.Employee;
import com.udacity.jdnd.course3.critter.domain.model.Pet;
import com.udacity.jdnd.course3.critter.endpoint.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.endpoint.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.endpoint.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.infrastructure.exception.handler.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final static Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final PetService petService;

    @Autowired
    public UserController(CustomerService customerService, EmployeeService employeeService, PetService petService) {
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.petService = petService;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer newCustomer = customerService.saveCustomer(this.mapCustomerDTOtoCustomer(customerDTO), customerDTO.getPetIds());
        return this.mapCustomerToCustomerDTO(newCustomer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        return customers.stream().map(this::mapCustomerToCustomerDTO).collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) throws ResourceNotFoundException {
        Customer customer = petService.getPetById(petId).getOwner();
        return this.mapCustomerToCustomerDTO(customer);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee newEmployee = employeeService.saveEmployee(this.mapEmployeeDTOToEmployee(employeeDTO));
        return this.mapEmployeeToEmployeeDTO(newEmployee);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) throws ResourceNotFoundException {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return this.mapEmployeeToEmployeeDTO(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) throws ResourceNotFoundException{
        Employee employee = employeeService.getEmployeeById(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeService.saveEmployee(employee);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> employees = employeeService.getAvailableEmployees(employeeDTO.getSkills(), employeeDTO.getDate());
        return employees.stream().map(this::mapEmployeeToEmployeeDTO).collect(Collectors.toList());
    }

    private CustomerDTO mapCustomerToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        List<Long> petIds = customer.getPets().stream().map(Pet::getId).collect(Collectors.toList());
        customerDTO.setPetIds(petIds);
        return customerDTO;
    }

    private Customer mapCustomerDTOtoCustomer(CustomerDTO customerDTO) {
        LOG.info(customerDTO.toString());
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        customer.setPets(new ArrayList<>());
        LOG.info(customer.toString());
        return customer;
    }

    private EmployeeDTO mapEmployeeToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    private Employee mapEmployeeDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

}
