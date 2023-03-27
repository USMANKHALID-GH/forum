package com.usman.forum.controller;

import com.usman.forum.dto.BaseResponseDto;
import com.usman.forum.dto.QuestionDto;
import com.usman.forum.dto.QuestionToShowDto;
import com.usman.forum.mapper.QuestionMapper;
import com.usman.forum.mapper.QuestionsToshowMapper;
import com.usman.forum.model.Question;
import com.usman.forum.service.QuestionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/questions/")
public class QuestionController {

    private final QuestionService service;
    private final QuestionMapper mapper;
    private final QuestionsToshowMapper detailQuestionMapper;

    @GetMapping("/")
    public  ResponseEntity<Page<QuestionToShowDto>> findAllQuestions(Pageable pageable,@RequestParam(name = "search",required = false )String  search){
        return  ResponseEntity.ok(new PageImpl<>(detailQuestionMapper.toDto(service.findAllQuestion(pageable, search).getContent())));
    }

    @PostMapping("/")
    public ResponseEntity<BaseResponseDto> saveQuestion(@RequestHeader(name = "userId") Long userId , @RequestBody QuestionDto questionDto){
           log.info(userId+ questionDto.toString());
          service.saveQuestion(userId,mapper.toEntity(questionDto));
         return  ResponseEntity.ok(new BaseResponseDto().builder().message("Question asked successfully").build());
    }




}
