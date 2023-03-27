package com.usman.forum.service.Implementation;

import com.usman.forum.dto.QuestionToShowDto;
import com.usman.forum.exception.BusinessException;
import com.usman.forum.model.Question;
import com.usman.forum.model.User;
import com.usman.forum.repository.QuestionRepository;
import com.usman.forum.repository.UserRepository;
import com.usman.forum.service.QuestionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class QuestionImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private  final UserRepository userRepository;
    @Override
    public void saveQuestion(Long userId, Question question) {
        User user = findUser(userId);
        question.setUser(user);
        questionRepository.save(question);
    }

    @Override
    public Page<Question> findAllQuestion(Pageable pageable, String search) {
       if(search==null){
           log.info(search+ " -------------------------");
           return questionRepository.findAll(pageable);

       }
        return questionRepository.findQuestionByQuestionTitleContaining(search,pageable);

    }

    @Override
    public Page<Question> findAllQuestion1(Pageable pageable, String search) {
        return questionRepository.findQuestionByQuestionTitleContaining(search,pageable);
    }


    private User findUser(Long id){
        User user= userRepository.findById(id).
                orElseThrow(()-> new BusinessException(HttpStatus.NOT_FOUND, "There is  not such Id in our System: "+id));
        return user;
    }
}
