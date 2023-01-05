package org.zerock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

// 해당 객체가 스프링의 컨트롤러에서 발생하는 예외를 처리하는 존재임을 명시
@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {
	
	// 해당 메서드가 '()'안에 들어가는 예외 타입을 처리한다는 것을 의미.
	@ExceptionHandler(Exception.class)
	// 모든 예외 처리를 except()만을 이용해서 처리할 수 있다.
	public String except(Exception ex, Model model) {
		
		log.error("Exception ......" + ex.getMessage());
		model.addAttribute("exception", ex);
		log.error(model);
		return "error_page";
	}
	
	// 해당 메서드가 '()'안에 들어가는 예외 타입을 처리한다는 것을 의미.
	@ExceptionHandler(NoHandlerFoundException.class)
	// 404 에러 
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex) {

		return "custom404";
	}	
}
