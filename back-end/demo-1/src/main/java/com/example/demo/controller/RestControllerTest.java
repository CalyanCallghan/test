package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class RestControllerTest {
	@Autowired
	public EmployeeRepository employeeRepository;
	@Autowired
	public AdminRepository adminRepository;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}
	
	@PostMapping(path = "/employees", consumes = "application/json")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
         @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }
	
	@DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
         throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
       .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
	@GetMapping("/employees/{email}/{userId}")
	public ResponseEntity<Login> isAuthToLogin(@PathVariable(value = "email") String emailId, @PathVariable(value = "userId") String userId) {
		Admin employee = adminRepository.findByEmailAddress(emailId,userId);

		if (employee != null) {
			return ResponseEntity.ok(new Login("Y", "Login successful", "256klkj78"));
		}else {
			System.err.println("=====not presemt=====");
			return ResponseEntity.ok(new Login("N", "Username or password is incorrect", "0"));
		}
	}
	@PostMapping(path = "/admin", consumes = "application/json")
	public Admin createEmployee(@RequestBody Admin admin) {
		return adminRepository.save(admin);
	}
	
	@GetMapping("/resetPassword/{pwd}/{code}")
	public ResponseEntity<Login> resetPassword(@PathVariable(value = "pwd") String pwd, @PathVariable(value = "code") long code) {
		
		Admin employee = adminRepository.findByAdminCode(code);

		if (employee != null) {
			adminRepository.resetPasswrd(pwd, code);
			return ResponseEntity.ok(new Login("Y", "Login successful", "256klkj78"));
		}else {
			System.err.println("=====not presemt=====");
			return ResponseEntity.ok(new Login("N", "Unable to find Admin, please try with diff Admin code", "0"));
		}
	}
	
	
	
}
