package com.example.demo.repository;

import com.example.demo.domain.Counsel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CounselRepository {

  private final EntityManager em;

  public void save(Counsel counsel){
    em.persist(counsel);
  }

  public Counsel findOne(Long id){
    return em.find(Counsel.class, id);
  }
  public List<Counsel> findAll(){  //조회된 결과를 반환
    List<Counsel> result = em.createQuery("select c from Counsel c", Counsel.class).getResultList();
    return result;
  }


  public List<Counsel> findByemail(String email){
    return em.createQuery("select c from Counsel c where c.email = :email", Counsel.class)
        .setParameter("email",  email).getResultList();
  }

}
