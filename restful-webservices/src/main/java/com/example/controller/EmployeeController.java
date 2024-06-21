package com.example.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.beans.Employee;
import com.example.service.EmployeeService;

// Run application as Spring boot and use the below link to test one of the Rest Api
//http://localhost:8080/emp/201
@Controller
@RequestMapping("/emp")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	// Fetch all user records
	//  http://localhost:8080/emp/empList
	@GetMapping(value = "/empList")
	public  String getAllEmployees(Model model) {
		System.out.println("inside getAllEmployees this will get the all employees:::");
		List<Employee> empList = employeeService.getAllEmployees();
		model.addAttribute("employeesList", empList);

		return "empList";
	}
	
	// Delete user record http://localhost:8080/emp/deleteEmp/2
		@GetMapping("/deleteEmp/{id}")
		public String deleteEmployee(Model model,@PathVariable int id) {
				System.out.println("inside deleteEmployee id:::"+id);
				employeeService.deleteEmployeeById(id);
				List<Employee> empList = employeeService.getAllEmployees();
				model.addAttribute("employeesList", empList);

			return "empList";
		}
		// Fetch single emp
		@GetMapping("/editEmp/{id}")
		public String getEmployeeById(Model model,@PathVariable("id") int empId) {
			Employee objEmployee = employeeService.getEmployeeById(empId);
			System.out.println("inside getEmployeeById id is:::"+objEmployee.getEmpId());
			model.addAttribute("objEmployee", objEmployee);
			model.addAttribute("empId",empId);
			return "employee";
		}

	// Insert emp record
		@RequestMapping(value = "/saveEmp", method = RequestMethod.POST)
	public String addEmployee(Model model,@ModelAttribute(value = "objEmployee") Employee objEmployee) {
		 System.out.println("inside deleteEmployee id:::"+objEmployee.getEmpId());
		 	if(objEmployee.getEmpId() == 0) {// new record
		 		int Random = (int)(Math.random()*90);
		 		 System.out.println("new record:::"+objEmployee.getEmpId());
		 		objEmployee.setEmpId(Random);
		 		 System.out.println("new record: set id::"+objEmployee.getEmpId());
		 		employeeService.addEmployee(objEmployee);
		 	} else {
		 		employeeService.updateEmployee(objEmployee);
		 	}
		 	List<Employee> empList = employeeService.getAllEmployees();
		 	model.addAttribute("employeesList", empList);
			return "empList";
	}

		@RequestMapping(value = "/addEmp", method = RequestMethod.GET)
		public String newEmployee(Model model,
				@ModelAttribute(value = "objEmployee") Employee objEmployee) {
			
			model.addAttribute("objEmployee", objEmployee);
			return "employee";
		}
}
