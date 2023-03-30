package com.usman.forum.service.Implementation;

import com.usman.forum.exception.BusinessException;
import com.usman.forum.model.Answers;

import com.usman.forum.model.Questions;
import com.usman.forum.model.User;
import com.usman.forum.repository.AnswerRepository;
import com.usman.forum.repository.QuestionRepository;
import com.usman.forum.repository.UserRepository;
import com.usman.forum.service.AnswersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class AnswerImp  implements AnswersService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private  final UserRepository userRepository;

    @Override
    public void saveAnswer(@Valid Answers answers , Long uid, Long questionid) {


        User user=userRepository.findById(uid).get();
        Questions question =questionRepository.findById( questionid).get();

        Optional<Answers> alreadyAnswered= answerRepository.findAnswerByQuestionAndUser(questionid,uid);
        if( alreadyAnswered.isPresent()){
            throw new BusinessException(HttpStatus.BAD_REQUEST, "You have already " +
                    "answered this question Update or delete the previous answer");
        }
        question.setAnswered(true);
        answers.setUser(user);
        answers.setQuestion(question);
        questionRepository.save(question);
        answerRepository.save(answers);

    }

    @Override
    public Page<Answers> findAllAnswer(Pageable pageable) {
        return  answerRepository.findAll(pageable);
    }

    @Override
    public Answers findAnswer(Long id) {
        return findAnswers(id);
    }

    @Override
    public List<Answers> searchForAnswersByQuestion(Pageable pageable, String saerch) {
        return answerRepository.findAll(pageable)

                .stream().parallel()
                .filter(s->s.getQuestion().getContent().contains(saerch))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAnswer(Long id, Long questionId) {
        Answers answer=findAnswer(id);
        Long isAnswered=answerRepository.numberOfAnsweredQuestion(questionId);
        log.info(isAnswered+"numberofAnswered==========================");
        if(isAnswered==1){
            Questions question=questionRepository.findById(questionId).get();
            answerRepository.delete(answer);
            question.setAnswered(false);
            questionRepository.save(question);
            log.info("inside if statement--------------------");
            return;
        }
        else
         answerRepository.delete(answer);

    }

    @Override
    public void bestAnswer(Long id, Long questionId) {
        Answers answer=findAnswers(id);
        List<Answers> answersByQuestionID =answerRepository.findAllAnswersByQuestionID(questionId)
                .stream()
                .filter(s-> s.isBestAnswer()).collect(Collectors.toList());
        for(Answers a: answersByQuestionID){
            a.setBestAnswer(false);
            answerRepository.save(a);
        }
        answer.setBestAnswer(true);
        answerRepository.save(answer);
    }




    private final Answers findAnswers(Long id)
    {
        Answers answers= answerRepository.findById(id).
                orElseThrow(()-> new BusinessException(HttpStatus.NOT_FOUND, "There is  not such Id in our System: "+id));
        return answers;
    }
}
