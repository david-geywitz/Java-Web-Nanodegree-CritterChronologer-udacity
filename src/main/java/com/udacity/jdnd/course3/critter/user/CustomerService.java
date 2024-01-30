package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.user.CustomerNotFoundException;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> list() {
        return customerRepository.findAll();
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }

    public Customer findByPetId(Long id) {
        Customer customer = customerRepository.findByPetsId(id);

        return customer;
        }
    }
