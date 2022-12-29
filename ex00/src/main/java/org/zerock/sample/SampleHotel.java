package org.zerock.sample;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


// 기존 코드
//@Component
//@ToString
//@Getter
//public class SampleHotel {
//	private Chef chef;
//	// 생성자는 정의하였지만 @Autowired와 같은
//	// 어노테이션은 추가하지 않았다.
//	public SampleHotel(Chef chef) {
//		this.chef = chef;
//	}
//}

//// 생성자의 자동 주입과 Lombok을 결합하여 수정된 코드 
//@Component
//@ToString
//@Getter
//@AllArgsConstructor
//public class SampleHotel {
//	private Chef chef;
//}

// 생성자의 자동 주입과 Lombok을 결합하여 수정된 코드
// 여러 개의 인스턴스 변수들 중에서 특정한 변수에 대해서만
// 생성자를 작성하고 싶을때 사용하는 어노테이션 수정본
@Component
@ToString
@Getter
@RequiredArgsConstructor
public class SampleHotel {
	@NonNull
	private Chef chef;
}