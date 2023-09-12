package com.example.demo.discount;

import com.example.demo.member.Member;
import org.springframework.stereotype.Component;

public interface DiscountPolicy {

  /**
   * @return
   */
  int discount(Member member, int price);

}
