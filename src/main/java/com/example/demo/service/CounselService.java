package com.example.demo.service;

import com.example.demo.DTO.CounselForm;
import com.example.demo.domain.Counsel;
import com.example.demo.repository.CounselRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CounselService {
  private final CounselRepository counselRepository;

  @Transactional
  public void saveCounsel(CounselForm counselForm) {
    Counsel counsel = new Counsel();
    counsel.setEmail(counselForm.getEmail());
    counsel.setContext(counselForm.getContext());
    counselRepository.save(counsel);
  }

  @Transactional //변경 + readonly false
  public Long join(Counsel counsel){
    validateDuplicateMember(counsel);    //중복 회원 검증
    counselRepository.save(counsel);
    return counsel.getId();   //em.persist 아직 db에 들어간 시점이 아니라도 값을 넣어줌
  }

  private void validateDuplicateMember(Counsel counsel) {
    //EXCEPTION
    List<Counsel> findCounsels = counselRepository.findByemail(counsel.getEmail()); //멀티쓰레드 예방으로 DB에서 member의 name을 unique로 지정
    if (!findCounsels.isEmpty()){
      throw new IllegalStateException("이미 존재하는 회원입니다.");   //member수를 카운트해서 0보다 크다 라고 하면 더 최적화 되긴함
    }
  }

  public Counsel findOne(Long counselId){
    return counselRepository.findOne(counselId);
  }


  @Transactional
  public void update(Long id, String email, String context){
    Counsel counsel = counselRepository.findOne(id);
    counsel.setEmail(email);
    counsel.setContext(context);
  }



}
