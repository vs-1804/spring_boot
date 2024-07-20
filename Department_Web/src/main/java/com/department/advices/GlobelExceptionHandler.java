package com.department.advices;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.department.exception.ResourceNotFoundException;

@RestControllerAdvice
public class GlobelExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<?>> resourceNotFound(ResourceNotFoundException exception){
		ApiError apiError =ApiError.builder().status(HttpStatus.NOT_FOUND).message(exception.getMessage()).build();
		return  new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<?>> methodArgumentValidation(MethodArgumentNotValidException exception){
		
		List<String> errors=exception.getBindingResult().getAllErrors().stream()
				.map(error-> error.getDefaultMessage()).collect(Collectors.toList());
		ApiError apiError=ApiError.builder().status(HttpStatus.BAD_REQUEST).message("Input Validation Failed")
				.error(errors).build();
		return new ResponseEntity<ApiResponse<?>>(new ApiResponse<>(apiError), apiError.getStatus());
		
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<?>> internalServerError(Exception exception) {
		ApiError apiError = ApiError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(exception.getMessage())
				.build();
		return  new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
	}

}
