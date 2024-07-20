package com.department.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.department.dto.DepartmentDto;
import com.department.exception.ResourceNotFoundException;
import com.department.services.DepartmentService;

import jakarta.validation.Valid;


@RestController
@RequestMapping(path = "/api")
public class DepartmentController {
	
	private final DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
	
		this.departmentService = departmentService;
	}
	
	@PostMapping(path="/add")
	public ResponseEntity<DepartmentDto> addDepartment( @RequestBody   @Valid DepartmentDto departmentDto) {
		
		return ResponseEntity.ok(departmentService.saveDepartment(departmentDto));		
		
	}
	@GetMapping("/department/{deptId}")
	ResponseEntity<DepartmentDto> getById(@PathVariable Long deptId){
		 return departmentService.getByDepartmentId(deptId)
				 .map(dept-> ResponseEntity.ok(dept))
				 .orElseThrow(()->new ResourceNotFoundException("No department with id:"+deptId));
				 
	}
	
	@GetMapping("/department")
	ResponseEntity<List<DepartmentDto>> getAll(){
		
		return ResponseEntity.ok(  departmentService.getAllDepartment());
	}
	
	@PutMapping("/department/{deptId}")
	ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long deptId ,@RequestBody DepartmentDto departmentDto){
		
		return ResponseEntity.ok(departmentService.updateDepartment(deptId, departmentDto));
	}
	@DeleteMapping("/department/{deptId}")
	ResponseEntity<String> deleteById(@PathVariable Long deptId){
		if(departmentService.deleteDepartment(deptId)){
		 return	ResponseEntity.ok("department deleted");
		}
		throw new ResourceNotFoundException("No Department exist with id:"+deptId);
	}
}
	


