package com.demo.dto.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorResponseDto {
	 private String message;
	    private int statusCode;
	    private LocalDateTime timeStamp;
	    private String path;

}
