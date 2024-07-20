package com.department.advices;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ApiResponse<T> {
	
	@JsonFormat(pattern = "hh:mm:ss dd:MM:yy")
	private LocalDateTime dateTime;
	
	private T data;
	
	private ApiError error;
	
	public ApiResponse() {
		this.dateTime=LocalDateTime.now();
	}

	public ApiResponse(T data) {
		this();
		this.data = data;
	}

	public ApiResponse( ApiError error) {
		this();
		this.error= error;
	}
	
	
}
