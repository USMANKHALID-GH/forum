package com.usman.forum.controller;

import com.usman.forum.dto.AnswerDto;
import com.usman.forum.dto.BaseResponseDto;

import com.usman.forum.dto.SubAnswerDto;
import com.usman.forum.mapper.AnswerMapper;
import com.usman.forum.model.Answers;
import com.usman.forum.service.AnswersService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/answer/")
public class AnswerController {
    private  final AnswersService service;
    private final AnswerMapper mapper;



    @GetMapping("/")
    public  ResponseEntity<Page<AnswerDto>>  findAllAnswers(Pageable pageable){
        return  ResponseEntity.ok(new PageImpl<>( mapper.toDto(service.findAllAnswer(pageable).getContent())));

    }




    @GetMapping("/{id}")
    public  ResponseEntity<AnswerDto>  findAnswer(@PathVariable("id") Long id){
        return ResponseEntity.ok(mapper.toDto(service.findAnswer(id)));
    }

    @GetMapping("/{search}/searching")
    public  ResponseEntity<Page<AnswerDto>>  searchForAnswersByQuestion(Pageable pageable , @PathVariable("search") String search){
       return  ResponseEntity.ok(new PageImpl<>( mapper.toDto(service.searchForAnswersByQuestion(pageable, search))));

    }



    @PostMapping("/")
    public ResponseEntity<BaseResponseDto> saveAnswer(@RequestHeader Map<String, String> params, @RequestBody AnswerDto answerDtoDto){
         Long userid= Long.parseLong(params.get("userid"));
         Long questionId=Long.parseLong(params.get("questionid"));
         service.saveAnswer(mapper.toEntity(answerDtoDto),userid,questionId);
         return ResponseEntity.ok(BaseResponseDto.builder().message("Answer saved successfully").build());

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseDto> deleteAnswer(@PathVariable("id") Long id ,@RequestHeader(name = "questionId") Long questionId){

        service.deleteAnswer(id,questionId);
        return ResponseEntity.ok(BaseResponseDto.builder().message("Answer deleted successfully").build());

    }


    @PostMapping("/{id}/best_answer")
    public ResponseEntity<BaseResponseDto> bestAnswer(@PathVariable("id") Long id,@RequestHeader(name = "questionId") Long questionId ){

        service.bestAnswer(id,questionId);
        return ResponseEntity.ok(BaseResponseDto.builder().message("Answer deleted successfully").build());

    }



}
