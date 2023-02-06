package org.zerock.mapper;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	// 현재 실제 있는 데이터의 bno 값인지 확인필수
	private Long[] bnoArr = {450L, 449L, 448L, 447L, 446L, 445L};
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	
	// 댓글 등록 테스트
	@Test
	public void testCreate() {
		IntStream.rangeClosed(1,10).
		forEach(i -> {
			ReplyVO vo = new ReplyVO();
			
			// 게시물의 번호
			vo.setBno(bnoArr[i % 5]);
			vo.setReply("댓글 테스트 " + i);
			vo.setReplyer("replyer" + i);
			
			mapper.insert(vo);
		});
		
	}
	
	@Test
	public void testMapper() {
		
		log.info(mapper);
	}
	
}
