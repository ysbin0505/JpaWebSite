package com.example.demo.order;

import com.example.demo.discount.DiscountPolicy;
import com.example.demo.discount.FixDiscountPolicy;
import com.example.demo.discount.RateDiscountPolicy;
import com.example.demo.member.Member;
import com.example.demo.member.MemberRepository;
import com.example.demo.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  /*private final MemberRepository memberRepository = new MemoryMemberRepository();
  //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
  //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
  private final DiscountPolicy discountPolicy;    //인터페이스에만 의존하도록 구현 but NPE 발생, 이 문제를 해결하려면
                                            //OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입
  */
  @Autowired private final MemberRepository memberRepository;
  @Autowired private final DiscountPolicy discountPolicy;

  /*@Autowired(required = false)
  public void setMemberRepository(MemberRepository memberRepository){
    System.out.println("memberRepository = " + memberRepository);
    this.memberRepository = memberRepository;
  }
  @Autowired
  public void setDiscountPolicy(DiscountPolicy discountPolicy){
    System.out.println("discountPolicy = " + discountPolicy);
    this.discountPolicy = discountPolicy;
  }*/

  //new OrderServiceImpl();
  /*@Autowired
  public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    System.out.println("memberRepository = " + memberRepository);
    System.out.println("discountPolicy = " + discountPolicy);
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }zsdsv*/


  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int discountPrice = discountPolicy.discount(member, itemPrice);

    return new Order(memberId, itemName, itemPrice, discountPrice);
  }

  //테스트 용도
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }
}
