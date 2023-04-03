package com.usman.forum.controller;

import com.usman.forum.dto.QuestionDto;
import com.usman.forum.mapper.QuestionMapper;
import com.usman.forum.service.CategoryService;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService service;
    private QuestionMapper mapper;

    @GetMapping("/question")
    public ResponseEntity<Page<QuestionDto>> findAllQuestions(Pageable pageable, @RequestParam(name = "search",required = false )String  search){
        return  ResponseEntity.ok(new PageImpl<>(mapper.toDto(service.findQuestionByCat( search,pageable).getContent())));
    }
}
