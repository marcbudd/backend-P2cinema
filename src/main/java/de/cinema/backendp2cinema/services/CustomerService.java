package de.cinema.backendp2cinema.services;

import de.cinema.backendp2cinema.entities.Customer;
import de.cinema.backendp2cinema.exceptions.CustomerNotFoundException;
import de.cinema.backendp2cinema.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository CustomerRepository;

    @Autowired
    public CustomerService(CustomerRepository CustomerRepository) {
        this.CustomerRepository = CustomerRepository;
    }

    public Customer addCustomer(Customer Customer) {
        return CustomerRepository.save(Customer);

    }

    public List<Customer> getAllCustomers() {
        return (List<Customer>) CustomerRepository.findAll();
    }

    public Customer findCustomerById(UUID id) {
        return CustomerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer with id " + id + " not found"));
    }

    public void delete(UUID id) {
        CustomerRepository.deleteById(id);
    }

}