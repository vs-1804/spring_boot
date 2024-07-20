package com.department.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.department.dto.DepartmentDto;
import com.department.entities.Department;
import com.department.repostories.DepartmentRepository;

@Service
public class DepartmentService {
	
	private final DepartmentRepository departmentRepository;
	private final ModelMapper mapper;
	
	
	public DepartmentService(DepartmentRepository departmentRepository, ModelMapper mapper) {
		
		this.departmentRepository = departmentRepository;
		this.mapper = mapper;
	}
	public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
		  
		Department dept=mapper.map(departmentDto, Department.class);
		
		return mapper.map(departmentRepository.save(dept), DepartmentDto.class);
		
	}
	public Optional<DepartmentDto> getByDepartmentId(Long id) {
		
		return  departmentRepository.findById(id)
				.map(dept-> mapper.map(dept, DepartmentDto.class));
		
	}
    public List<DepartmentDto> getAllDepartment(){
    	
    	List<Department> dept=departmentRepository.findAll();
    	
    	return dept.stream().map(dep->mapper.map(dep, DepartmentDto.class)).toList();
    }
    public DepartmentDto updateDepartment(Long id ,DepartmentDto deptDto) {
    	   Department dept= departmentRepository.findById(id).orElse(null);
    	   deptDto.setDeptId(id);
    		Department updatedDepatemnt=departmentRepository.save(mapper.map(deptDto, Department.class));
    		return mapper.map(updatedDepatemnt, DepartmentDto.class);
    	}
    public boolean deleteDepartment(Long id) {
    	if(!departmentRepository.existsById(id)) {
    		return false;
    	}
    	departmentRepository.deleteById(id);
    	return true;
    	
    }
}
