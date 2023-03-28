package com.usman.forum.controller;

import com.usman.forum.dto.BaseResponseDto;
import com.usman.forum.dto.QuestionDto;
import com.usman.forum.mapper.QuestionMapper;
import com.usman.forum.model.Questions;
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


    @GetMapping("/")
    public  ResponseEntity<Page<QuestionDto>> findAllQuestions(Pageable pageable,@RequestParam(name = "search",required = false )String  search){
        return  ResponseEntity.ok(new PageImpl<>(mapper.toDto(service.findAllQuestion(pageable, search).getContent())));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<QuestionDto> findQuestion(@PathVariable("id") Long id){
        return  ResponseEntity.ok(mapper.toDto(service.findAQuestion(id)));
    }




    @PostMapping("/")
    public ResponseEntity<BaseResponseDto> saveQuestion(@RequestHeader(name = "userId") Long userId , @RequestBody QuestionDto questionDto){
          log.info("before mapping "+questionDto);

        Questions o=mapper.toEntity(questionDto);
        log.info("after mapping repo" + o);
          service.saveQuestion(userId,mapper.toEntity(questionDto));

         return  ResponseEntity.ok(new BaseResponseDto().builder().message("Question asked successfully").build());
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<BaseResponseDto> deleteQuestion(@PathVariable("questionId") Long questionId ){

        service.deleteQuestion(questionId);
        return  ResponseEntity.ok(new BaseResponseDto().builder().message("Question deleted successfully").build());
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<BaseResponseDto> updateQuestion(@PathVariable("questionId") Long questionId, @RequestBody QuestionDto questionDto){

        service.updateQuestion(questionId,mapper.toEntity(questionDto));
        return  ResponseEntity.ok(new BaseResponseDto().builder().message("Question updated successfully").build());
    }





}