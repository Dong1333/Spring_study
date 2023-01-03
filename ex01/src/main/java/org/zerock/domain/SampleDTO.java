package org.zerock.domain;

import lombok.Data;
//SampleController의 메서드가 SampleDTO를 파라미터로 사용하게 되면
// 자동으로 setter 메서드가 동작하면서 파라미터를 수집하게 된다.


// getter/setter, equals(), toString() 자동 생성을 위한 어노테이션
@Data
public class SampleDTO {
	private String name;
	private int age;

}
