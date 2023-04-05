package com.usman.forum.service.Implementation;

import com.usman.forum.model.Question;
import com.usman.forum.repository.QuestionRepository;
import com.usman.forum.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryImp implements CategoryService {
   private final  QuestionRepository questionRepository;

    @Override
    public Page<Question> findQuestionByCat(String search, Pageable pageable) {
        return questionRepository.findQuestionByCat(search,pageable);
    }
}
