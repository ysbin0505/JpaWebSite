package com.example.demo.api;

import com.example.demo.domain.Counsel;
import com.example.demo.service.CounselService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequiredArgsConstructor
public class CounselApiController {
  private final CounselService counselService;

  @PostMapping("/api/v1/counsels")  //email을 다른이름으로 바꾸면 API스펙 자체가 변경되어버림
  public CreateCounselResponse saveCounselV1(@RequestBody @Valid Counsel counsel){
    Long id = counselService.join(counsel);
    return new CreateCounselResponse(id);
  }

  @PostMapping("/api/v2/counsels")  //email을 다른이름으로 바꾸면 오류남 -> DTO(CreateCounselRequest)로 받으면 더 안정적! 이거 추천
  public CreateCounselResponse saveCounselV2(@RequestBody @Valid CreateCounselRequest request){
    Counsel counsel = new Counsel();
    counsel.setEmail(request.getEmail());
    counsel.setContext(request.getContext());

    Long id = counselService.join(counsel);
    return new CreateCounselResponse(id);
  }

  @Data //v2
  static class CreateCounselRequest{
    @NotEmpty
    private String email;

    private String context;
  }

  @Data //v1
  static class CreateCounselResponse{
    private Long id;

    public CreateCounselResponse(Long id) {
      this.id = id;
    }
  }
}
