package com.example.demo;

import com.example.demo.member.MemberRepository;
import com.example.demo.member.MemberServiceImpl;
import com.example.demo.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
 /* @Bean(name = "memoryMemberRepository")
  MemberRepository memberRepository(){
    return new MemoryMemberRepository();
  }
*/
}
