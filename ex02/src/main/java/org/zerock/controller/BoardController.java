package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
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
	
	// BoardService 대한 객체 생성
	// 즉 service로 BoardService 메소드를 사용할 수 있음
	private BoardService service;
	
	// 게시물의 목록을 전달해야 하므로 Model을 파리미터로 지정
	@GetMapping("/list")
	public void list(Model model) {
		
		// 이를 통해서 BoardServiceImpl 객체의 getList() 결과를 담아 전달한다.
		log.info("list");
		model.addAttribute("list", service.getList());
	}
	
	@GetMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
ㄴㄴ
		log.info("register: " + board);

		service.register(board);

		rttr.addFlashAttribute("result", board.getBno());
		
		// 스프링 MVC가 내부적으로 response.sendRedirect()를 처리
		return "redirect:/board/list";
	}
	
	// 등록 작업이 끝나면 다시 목록화면으로 이동하는 메소드
	@PostMapping("/register")
	public void register() {

	}
	
	// 특정한 게시물을 가져오는 메소드
	// bno 값을 좀 더 명시적으로 처리하는 *@RequestParam을 이용해서 지정
	// 요청에 따라 get, modify 둘 다 처리 수 있게 설정한다.
	// void 이기 때문에 요청된 값에 따라 다른 페이지를 보여준다(get.jsp, modify.jsp)
	@GetMapping({ "/get", "/modify" })
	public void get(@RequestParam("bno") Long bno, Model model) {
		
		log.info("/get or modify ");
		model.addAttribute("board", service.get(bno));
	}
	
	// 수정 / 변경된 내용을 수집해서 BoardVO 파라미터로 처리하고, BoardService를 호출
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {

		log.info("modify: " + board);

		// service.modify()는 boolean으로 처리한다.
		// 따라서 성공한 경우에만 RedirectAtrributes에 추가
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		// 스프링 MVC가 내부적으로 response.sendRedirect()를 처리
		return "redirect:/board/list";
	}
	
	
	// 삭제
	// bno 값을 좀 더 명시적으로 처리하는 *@RequestParam을 이용해서 지정
	// 삭제 후 페이지의 이동이 필요하여 RedirectAttributes를 파라미터로 사용
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		log.info("remove...." + bno);
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:/board/list";
	}
}