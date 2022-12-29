package org.zerock.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


// java 설정을 이용하는 의존성 주입 
@Configuration
@ComponentScan(basePackages= {"org.zerock.sample"})
public class RootConfig {

}
