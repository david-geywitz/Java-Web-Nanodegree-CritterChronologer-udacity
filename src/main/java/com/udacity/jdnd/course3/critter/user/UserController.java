package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.DayOfWeek;
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

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PetService petService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        return entityToCustomerDTO(customerService.save(customerDTOToEntity(customerDTO)));

    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        return customerService.list()
                .stream()
                .map(this::entityToCustomerDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){

        return entityToCustomerDTO(customerService.findByPetId(petId));
    }

    @GetMapping("/employee")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.list()
                .stream()
                .map(this::entityToEmployeeDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return entityToEmployeeDTO(employeeService.save(employeeDTOToEntity(employeeDTO)));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {

        return entityToEmployeeDTO(employeeService.findById(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {

        Employee employee = employeeService.findById(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeService.save(employee);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@Valid @RequestBody EmployeeRequestDTO employeeDTO) {
        List<EmployeeDTO> employeeDTOS = employeeService.availableEmployeeForService(employeeDTO.getDate(), employeeDTO.getStartTime(), employeeDTO.getEndTime(), employeeDTO.getSkills())
                .stream()
                .map(this::entityToEmployeeDTO)
                .collect(Collectors.toList());
        return employeeDTOS;
    }

    private Customer customerDTOToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);

        return customer;
    }

    private CustomerDTO entityToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);

        if(customer.getPets().size() > 0) {
            customerDTO.setPetIds(customer.getPets()
            .stream()
            .map(pet -> pet.getId())
            .collect(Collectors.toList()));
        }

        return customerDTO;
    }

    private Employee employeeDTOToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);

        return employee;
    }

    private EmployeeDTO entityToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);

        return employeeDTO;
    }

}
