package com.usman.forum.service.Implementation;

import com.usman.forum.exception.BusinessException;
import com.usman.forum.model.Answers;

import com.usman.forum.model.Likes;
import com.usman.forum.model.Questions;
import com.usman.forum.model.User;
import com.usman.forum.repository.AnswerRepository;
import com.usman.forum.repository.LikesReposiory;
import com.usman.forum.repository.QuestionRepository;

import com.usman.forum.service.AnswersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
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
    private  final LikesReposiory likesReposiory;

    @Override
    public void saveAnswer(@Valid Answers answers , Long uid, Long questionid) {


        User user=userImp.findUser(uid);

        Questions question =questionImpl.findAQuestion( questionid);

        Optional<Answers> alreadyAnswered= answerRepository.findAnswerByQuestionAndUser(questionid,uid);
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
    public Page<Answers> findAllAnswer(Pageable pageable) {
        return  answerRepository.findAll(pageable);
    }

    @Override
    public Answers findAnswer(Long id) {
      return answerRepository.findById(id).
                orElseThrow(()-> new BusinessException(HttpStatus.NOT_FOUND, "There is  not such Id in our System: "+id));
    }

    @Override
    public List<Answers> searchForAnswersByQuestion(Pageable pageable, String saerch) {
        return answerRepository.findAll(pageable)

                .stream().parallel()
                .filter(s->s.getQuestion().getContent().contains(saerch))
                .collect(Collectors.toList());
    }


    @Override
    public void deleteAnswer(Long userID,Long id, Long questionId) {
        User user=userImp.findUser(userID);
        Answers answer=findAnswer(id);
       if(user.equals(answer.getUser())){
        Long isAnswered=answerRepository.numberOfAnsweredQuestion(questionId);
        if(isAnswered==1){
            Questions question=questionImpl.findAQuestion(questionId);
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
        Answers answer=findAnswer(id);
        List<Answers> answersByQuestionID =answerRepository.findAllAnswersByQuestionID(questionId)
                .stream()
                .filter(s-> s.isBestAnswer()).collect(Collectors.toList());
        log.equals(answersByQuestionID);
        for(Answers a: answersByQuestionID){
            a.setBestAnswer(false);
            answerRepository.save(a);
        }
        answer.setUser(user);
        answer.setBestAnswer(true);
        answerRepository.save(answer);
    }

    @Override
    public String likeAnswer(Long userI, Long answerId)

 {
     return  likeAndUnLikeQuestionOrAnswer(userI,answerId,answerRepository);


    }

    public  <T extends JpaRepository> String likeAndUnLikeQuestionOrAnswer(Long userid, Long id, T repository)  {
        Answers answers= findAnswer(id);
        log.info("===========================================1");
        User user=userImp.findUser(userid);
        Optional<Likes> likes=likesReposiory.findLikeByAnmswerAndUser(id,userid);
        if( likes.isPresent()){
            likes.get().setUser(user.getId());
            likes.get().setAnswer(answers.getId());

            int increaseLike = answers.getLikeCount() - 1;
            answers.setLikeCount(increaseLike);
            likesReposiory.delete(likes.get());
            repository.save(answers);
            return "unliked";
        }

        Likes newLike = new Likes();
        newLike.setUser(user.getId());
        newLike.setAnswer(answers.getId());

        int increaseLike = answers.getLikeCount() + 1;
        answers.setLikeCount(increaseLike);
        likesReposiory.save(newLike);
        repository.save(answers);

        return  "liked";
    }




}
