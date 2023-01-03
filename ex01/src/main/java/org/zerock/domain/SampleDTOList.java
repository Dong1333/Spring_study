package org.zerock.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

// Sample Controllerd에 전달할 객체 타입 만들기 

@Data
public class SampleDTOList {
	private List<SampleDTO> list;
	
	public SampleDTOList() {
		list = new ArrayList<> () ;
		
	}

}
