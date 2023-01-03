package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jdk.internal.org.jline.utils.Log;

// 작성된 SampleController 클래스는 자동으로 스프링의 객체로 등록되게 된다.
// @Controller 어노테이션은 클래스를 Spring MVC 컨트롤러로 표시하는데 사용
@Controller

// SampleController 클래스의 모든 메서드들의 기본적인 URL경로
@RequestMapping("/sample/*")
public class SampleController {
	
	@RequestMapping(value = "/basic", method = {RequestMethod.GET,
			RequestMethod.POST})
	public void basic() {
		Log.info("basic  get..........");
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		Log.info("basic get only get.........");
	}

}




