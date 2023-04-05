package com.usman.forum.service.Implementation;

import com.usman.forum.exception.BusinessException;
import com.usman.forum.model.*;

import com.usman.forum.repository.AnswerRepository;
import com.usman.forum.repository.AnswerLikeRepository;
import com.usman.forum.repository.QuestionRepository;

import com.usman.forum.service.AnswersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class AnswerImp  implements AnswersService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    private  final UserImp userImp;
    private  final  QuestionImpl questionImpl;
    private  final AnswerLikeRepository answerLikeRepository;

    @Override
    public void saveAnswer(@Valid Answer answers , Long uid, Long questionid) {


        User user=userImp.findUser(uid);

        Question question =questionImpl.findAQuestion( questionid);

        Optional<Answer> alreadyAnswered= answerRepository.findAnswerByQuestionAndUser(questionid,uid);
        if( alreadyAnswered.isPresent()){
            throw new BusinessException(HttpStatus.BAD_REQUEST, "You have already " +
                    "answered this question Update or delete the previous answer");
        }

        question.setAnswered(true);
         answers.setUser(user);

        answers.setQuestion(question);

        answerRepository.save(answers);

    }

    @Override
    public Page<Answer> findAllAnswer(Pageable pageable) {
        return  answerRepository.findAll(pageable);
    }

    @Override
    public Answer findAnswer(Long id) {
      return answerRepository.findById(id).
                orElseThrow(()-> new BusinessException(HttpStatus.NOT_FOUND, "There is  not such Id in our System: "+id));
    }

    @Override
    public List<Answer> searchForAnswersByQuestion(Pageable pageable, String saerch) {
        return answerRepository.findAll(pageable)

                .stream().parallel()
                .filter(s->s.getQuestion().getContent().contains(saerch))
                .collect(Collectors.toList());
    }


    @Override
    public void deleteAnswer(Long userID,Long id, Long questionId) {
//        islem
        User user=userImp.findUser(userID);
        Answer answer=findAnswer(id);
       if(user.equals(answer.getUser())){

        Long isAnswered=answerRepository.numberOfAnsweredQuestion(questionId);
        if(isAnswered==1){
            Question question=questionImpl.findAQuestion(questionId);
            answerRepository.delete(answer);
            question.setAnswered(false);
            questionRepository.save(question);
            return;
        }
        else
         answerRepository.delete(answer);}
       else {
           throw  new BusinessException(HttpStatus.FORBIDDEN, "You have no permission to delete this");
       }

    }

    @Override
    public void bestAnswer(Long userID,Long id, Long questionId) {
        User user=userImp.findUser(userID);
        Answer answer=findAnswer(id);
        List<Answer> answersByQuestionID =answerRepository.findAllAnswersByQuestionID(questionId)
                .stream()
                .filter(s-> s.isBestAnswer()).collect(Collectors.toList());
        log.equals(answersByQuestionID);
        for(Answer a: answersByQuestionID){
            a.setBestAnswer(false);
            answerRepository.save(a);
        }
        answer.setUser(user);
        answer.setBestAnswer(true);
        answerRepository.save(answer);
    }



    @Override
    public String  likeUnlikeAnswer(Long userId, Long answerId) {
        User user= userImp.findUser(userId);
        Answer subAnswer=findAnswer(answerId);
        Optional<AnswerLike> likes= answerLikeRepository.findAnswerLikedByUser(answerId,userId);
        if( likes.isPresent()){
            answerLikeRepository.delete(likes.get());
            return "unliked";
        }
        AnswerLike answerLike= new AnswerLike();
        answerLike.setAnswer(subAnswer);
        answerLike.setUser(user);
        answerLikeRepository.save(answerLike);
        return "Liked";
    }

    @Override
    public Integer answerLikeCount(long id) {
        return answerLikeRepository.findAnswerLikeCount(id);
    }


}

