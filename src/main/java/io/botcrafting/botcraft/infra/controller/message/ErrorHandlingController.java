package io.botcrafting.botcraft.infra.controller.message;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import lombok.extern.java.Log;

@ControllerAdvice
@Log
public class ErrorHandlingController {
	  @ExceptionHandler(HttpClientErrorException.class)
	  @ResponseStatus(HttpStatus.OK)
	  @ResponseBody
	  public ResponseEntity<HttpClientErrorException> onHttpClientErrorException(HttpClientErrorException characterNameAlreadyExists) {
		  log.info("Caught Http client error exception, returning 200 to telegram so that won't block any posterior messages");
		  return new ResponseEntity<>(HttpStatus.OK);
	  }
}
