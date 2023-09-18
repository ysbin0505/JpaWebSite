package com.example.demo.api;

import com.example.demo.domain.Counsel;
import com.example.demo.service.CounselService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
@RestController
@RequiredArgsConstructor
public class CounselApiController {
  private final CounselService counselService;

  @PostMapping("/api/v1/counsels")  //email을 다른이름으로 바꾸면 오류남 -> DTO(CreateCounselRequest)로 받으면 더 안정적! 이거 추천
  public CreateCounselResponse saveCounselV1(@RequestBody @Valid CreateCounselRequest request){
    Counsel counsel = new Counsel();
    counsel.setEmail(request.getEmail());
    counsel.setContext(request.getContext());

    Long id = counselService.join(counsel);
    return new CreateCounselResponse(id);
  }

  @Data
  static class CreateCounselRequest{
    @NotEmpty
    private String email;

    private String context;
  }

  @Data
  static class CreateCounselResponse{
    private Long id;

    public CreateCounselResponse(Long id) {
      this.id = id;
    }
  }

  @PutMapping("/api/v1/counsels/{id}")
  public UpdateCounselResponse updateCounselV1(@PathVariable("id") Long id,
                                               @RequestBody @Valid UpdateCounselRequest request){
    counselService.update(id, request.getEmail(), request.getContext());
    Counsel findCounsel = counselService.findOne(id);
    return new UpdateCounselResponse(findCounsel.getId(), findCounsel.getEmail(), findCounsel.getContext());
  }

  @Data
  static class UpdateCounselRequest{
    private String email;
    private String context;
  }

  @Data
  @AllArgsConstructor
  static class UpdateCounselResponse{
    private Long id;
    private String email;
    private String context;
  }
}


