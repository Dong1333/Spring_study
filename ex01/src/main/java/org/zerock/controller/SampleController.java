package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;


// 작성된 SampleController 클래스는 자동으로 스프링의 객체로 등록되게 된다.
// @Controller 어노테이션은 클래스를 Spring MVC 컨트롤러로 표시하는데 사용
@Controller

// SampleController 클래스의 모든 메서드들의 기본적인 URL경로
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
//	// 파라미터를 바인딩할 때 자동으로 호출되는 어노테이션
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(java.util.Date.class,
//				new CustomDateEditor(dateFormat, false));
//	}
//	
	@RequestMapping(value = "/basic", method = {RequestMethod.GET,
			RequestMethod.POST})
	public void basic() {
		log.info("basic  get..........");
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get.........");
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info("" + dto);
		
		return "ex01";
	}
	
	// 파라미터 변환 
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name,
			@RequestParam("age") int age) {
		log.info("name : " + name);
		log.info("age : " + age);
		
		return "ex02";
	}
	
	// 리스트 파라미터 변환 
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
		
		log.info("ids : " + ids);
		
		return "ex02List";
	}
	
	// 배열 파라미터 변환 
	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids) {
		
		log.info("array ids : " + Arrays.toString(ids));
		
		return "ex02Array";
	}
	
	// 객체 파라미터 변환
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		
		log.info("array dtos : " + list);
		
		return "ex02Bean";
	}
	
	// 날짜 형식의 데이터 파라미터 변환
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		
		log.info("todo : " + todo);
		
		return "ex03";
	}

//	// 모델 형식의 데이터 파라미터 변환(page 값 받기 불가한 예제 )
//	@GetMapping("/ex04")
//	public String ex04(SampleDTO dto, int page) {
//		
//		log.info("dto : " + dto);
//		log.info("page : " + page);
//		
//		return "/sample/ex04";
//	}
	
	// 모델 형식의 데이터 파라미터 변환(page 값 받기 가능한 예제) 
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page")int page) {
		
		log.info("dto : " + dto);
		log.info("page : " + page);
		
		return "/sample/ex04";
	}
}




