package com.usman.forum.controller;


import com.usman.forum.dto.AnswerDto;
import com.usman.forum.dto.BaseResponseDto;
import com.usman.forum.dto.SubAnswerDto;
import com.usman.forum.dto.UserDto;
import com.usman.forum.mapper.SubAnswerMapper;
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
        Long userid=Long.parseLong(params.get("userid"));
        Long questionId=Long.parseLong(params.get("qid"));
        service.saveSubAnswer(mapper.toEntity(subAnswerDtoDto),userid,questionId);
        return ResponseEntity.ok(BaseResponseDto.builder().message("SubAnswer saved successfully").build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponseDto> updateSubAnswer(@PathVariable("id")Long id, @RequestBody SubAnswerDto subAnswerDtoDto){

        service.updateSubAnswer(mapper.toEntity(subAnswerDtoDto),id);
        return ResponseEntity.ok(BaseResponseDto.builder().message("SubAnswer updated successfully").build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseDto> unSelectSubAnswer(@PathVariable("id") Long id){

        service.deleteSubAnswer(id);
        return ResponseEntity.ok(BaseResponseDto.builder().message("subAnswer deleted successfully").build());

    }




}
