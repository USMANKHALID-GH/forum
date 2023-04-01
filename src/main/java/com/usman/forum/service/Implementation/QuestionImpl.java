package com.usman.forum.service.Implementation;

import com.usman.forum.exception.BusinessException;
import com.usman.forum.model.Questions;
import com.usman.forum.model.User;
import com.usman.forum.repository.QuestionRepository;
import com.usman.forum.repository.UserRepository;
import com.usman.forum.service.QuestionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
@Slf4j
@AllArgsConstructor
public class QuestionImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private  final UserRepository userRepository;
    @Override
    public void saveQuestion(Long userId, Questions questions) {
        User user = findUser(userId);
        questions.setAnswered(false);
        questions.setUser(user);
        questionRepository.save(questions);
    }

    @Override
    public Page<Questions> findAllQuestion(Pageable pageable, String search) {
       if(search==null){

           return questionRepository.findAll(pageable);

       }
        return questionRepository.findQuestionByTitleContaining(search,pageable);

    }

    @Override
    public Page<Questions> findAllQuestion1(Pageable pageable, String search) {
        return questionRepository.findQuestionByTitleContaining(search,pageable);
    }

//    to be checked
    @Override
    public void deleteQuestion(Long userID ,Long questionId) {
        findUser(userID);
        Questions questions =findQuestion(questionId);
        questionRepository.delete(questions);
    }

    @Override
    public Questions findAQuestion(Long id) {
        return findQuestion(id);
    }

    //    to be checked
    @Override
    public void updateQuestion(Long questionId ,Long userID ,  Questions toEntity) {
        findUser(userID);
        Questions questions =findQuestion(questionId);

        if(!(toEntity.getTitle().isEmpty()  ||toEntity.getTitle()==null)){
            questions.setTitle(toEntity.getTitle());
        }
        if(!(toEntity.getImage().isEmpty()  || toEntity.getImage()==null)){
            questions.setImage(toEntity.getImage());
        }
        if(!(toEntity.getContent().isEmpty()  || toEntity.getContent()==null)){
            questions.setContent(toEntity.getContent());
        }
       questionRepository.save(questions);
    }


    private  final User findUser(Long id){
        User user= userRepository.findById(id).
                orElseThrow(()-> new BusinessException(HttpStatus.NOT_FOUND, "There is  not such Id in our System: "+id));
        return user;
    }

    private final Questions findQuestion(Long id)
    {
        Questions questions = questionRepository.findById(id).
                orElseThrow(()-> new BusinessException(HttpStatus.NOT_FOUND, "There is  not such Id in our System: "+id));
        return questions;
    }
}
