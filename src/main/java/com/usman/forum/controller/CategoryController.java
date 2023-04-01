package com.usman.forum.controller;

import com.usman.forum.mapper.CategoryMapper;
import com.usman.forum.service.CategoryService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService service;
    private CategoryMapper mapper;
}
