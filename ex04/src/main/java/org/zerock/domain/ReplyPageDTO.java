package org.zerock.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
// 댓글 목록과 함께 전체 댓글의 수를 같이 전달
public class ReplyPageDTO {
	public int replyCnt;
	public List<ReplyVO> list;
	

}
