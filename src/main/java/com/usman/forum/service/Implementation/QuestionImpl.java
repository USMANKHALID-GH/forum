package com.usman.forum.service.Implementation;

import com.usman.forum.exception.BusinessException;
import com.usman.forum.model.Questions;
import com.usman.forum.model.User;
import com.usman.forum.repository.QuestionRepository;

import com.usman.forum.service.QuestionService;

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

    private final  UserImp userImp;
    @Override
    public void saveQuestion(Long userId, Questions questions) {
        User user = userImp.findUser(userId);
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
        User user=userImp.findUser(userID);
        Questions questions =findAQuestion(questionId);
        if(user.equals(questions.getUser()))
        {
        questionRepository.delete(questions);}
        else {
            throw  new BusinessException(HttpStatus.FORBIDDEN, "You have no permission to delete this");
        }
    }

    @Override
    public Questions findAQuestion(Long id) {
        return questionRepository.findById(id).
                orElseThrow(()-> new BusinessException(HttpStatus.NOT_FOUND, "There is  not such Id in our System: "+id));
    }


    @Override
    public void updateQuestion(Long questionId ,Long userID ,  Questions toEntity) {
        User user=userImp.findUser(userID);
        Questions questions =findAQuestion(questionId);
        if(user.equals(questions.getUser())) {
            if (!(toEntity.getTitle().isEmpty() || toEntity.getTitle() == null)) {
                questions.setTitle(toEntity.getTitle());
            }
            if (!(toEntity.getImage().isEmpty() || toEntity.getImage() == null)) {
                questions.setImage(toEntity.getImage());
            }
            if (!(toEntity.getContent().isEmpty() || toEntity.getContent() == null)) {
                questions.setContent(toEntity.getContent());
            }
            questionRepository.save(questions);
        }
        else {
            throw  new BusinessException(HttpStatus.FORBIDDEN, "You have no permission to delete this");
        }
    }





}
