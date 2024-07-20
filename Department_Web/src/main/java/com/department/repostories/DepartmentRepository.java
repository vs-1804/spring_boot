package com.department.repostories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.department.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
	

}
