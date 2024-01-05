package com.devcoding.Employee.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcoding.Employee.Entity.EmployeeEntity;
import com.devcoding.Employee.model.Employee;
import com.devcoding.Employee.repository.EmployeeRepository;


@Service
public class EmployeeServiceImple implements EmployeeService{

	
	@Autowired
    private EmployeeRepository employeeRepository;
    
    public EmployeeServiceImple(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
        return employee;
    }

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
	       List<EmployeeEntity> employeeEntities
           = employeeRepository.findAll();
	       List<Employee> employees = employeeEntities.stream()
	               .map(employeeEntity -> {
	                   Employee employee = new Employee();
	                   employee.setId(employeeEntity.getId());
	                   employee.setFirstName(employeeEntity.getFirstName());
	                   employee.setLastName(employeeEntity.getLastName());
	                   employee.setEmailId(employeeEntity.getEmailId());
	                   return employee;
	               })
	               .collect(Collectors.toList());
   return employees;
	}
	
	
    @Override
    public boolean deleteEmployee(Long id) {
        EmployeeEntity employee = employeeRepository.findById(id).get();
        employeeRepository.delete(employee);
        return true;
    }
    
    
    

    @Override
    public Employee getEmployeeById(Long id) {
        EmployeeEntity employeeEntity
                = employeeRepository.findById(id).get();
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity, employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        EmployeeEntity employeeEntity
                = employeeRepository.findById(id).get();
        employeeEntity.setEmailId(employee.getEmailId());
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());

        employeeRepository.save(employeeEntity);
        return employee;
    }
    
 
       
    
	
}

