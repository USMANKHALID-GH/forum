package com.usman.forum.controller;


import com.usman.forum.dto.*;
import com.usman.forum.mapper.QuestionMapper;
import com.usman.forum.mapper.SubAnswerMapper;
import com.usman.forum.model.Questions;
import com.usman.forum.service.SubAnswerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/subanswer/")
public class SubAnswerController {

            private final SubAnswerService service;
            private final SubAnswerMapper mapper;
            private  final QuestionMapper questionMapper;

            @GetMapping("/")

            public  ResponseEntity<Page<SubAnswerDto>>  findAllSubAnswer(Pageable pageable){
                return  ResponseEntity.ok(new PageImpl<>( mapper.toDto(service.findAllSubAnswer(pageable).getContent())));
            }


    @GetMapping("/{id}")
    public  ResponseEntity<SubAnswerDto>  findSubAnswer(@PathVariable("id") Long id){
        return ResponseEntity.ok(mapper.toDto(service.findSubAnswer(id)));
    }


    @PostMapping("/")
    public ResponseEntity<BaseResponseDto> saveSubAnswer(@RequestHeader Map<String, String> params, @RequestBody SubAnswerDto subAnswerDtoDto){
        log.info(params.get("userid")+"======================");
        log.info(params.get("answerid")+"111111111111111111");
        Long userid=Long.parseLong(params.get("userid"));
        Long questionId=Long.parseLong(params.get("answerid"));
        service.saveSubAnswer(mapper.toEntity(subAnswerDtoDto),userid,questionId);
        return ResponseEntity.ok(BaseResponseDto.builder().message("SubAnswer saved successfully").build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponseDto> updateSubAnswer(@RequestHeader("userId") Long userId,@PathVariable("id")Long id, @RequestBody SubAnswerDto subAnswerDtoDto){

        service.updateSubAnswer(userId,mapper.toEntity(subAnswerDtoDto),id);
        return ResponseEntity.ok(BaseResponseDto.builder().message("SubAnswer updated successfully").build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseDto> unSelectSubAnswer(@RequestHeader("userId") Long userId,@PathVariable("id") Long id){

        service.deleteSubAnswer(userId,id);
        return ResponseEntity.ok(BaseResponseDto.builder().message("subAnswer deleted successfully").build());

    }

    @GetMapping("/{search}/searching" )
            public  ResponseEntity<Page<QuestionDto>>  findAllSubAnswer(@PathVariable("search") String search, Pageable pageable){
        return  ResponseEntity.ok(new PageImpl<>( questionMapper.toDto(service.searchAllInQuestionOrAnswersOrSubAnswer(search,pageable).getContent())));

    }
    @PostMapping("{id}/like")
    public ResponseEntity<BaseResponseDto> likeUnlikeAnAnswer(@RequestHeader("userID") Long userI ,@PathVariable("id") Long id){
        String liked_unliked=service.likeUnlikeAnswer(userI,id);
        return ResponseEntity.ok(BaseResponseDto.builder().message("Answer is "+liked_unliked+"  successfully").build());
    }




}
