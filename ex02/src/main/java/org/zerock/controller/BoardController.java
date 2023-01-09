package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

// 스프링 빈으로 인식
@Controller
// 로그문의 출력을 다양한 대상으로 할 수 있도록 도와주는 도구
@Log4j
// ‘/board’로 시작하는 모든 처리를 BoardController가 하도록 지정
@RequestMapping("/board/*")

// BoardService에 대해서 의존적이므로 
// @AllArgsConstructor를 이용해 생성자를 만들고 자동으로 주입하도록 한다
@AllArgsConstructor
public class BoardController {

	private BoardService service;
	
	// 나중에 게시물의 목록을 전달해야 하므로 Model을 파리미터로 지정
	@GetMapping("/list")
	public void list(Model model) {
		
		// 이를 통해서 BoardServiceImpl 객체의 getList() 결과를 담아 전달한다.
		log.info("list");
		model.addAttribute("list", service.getList());
	}
}