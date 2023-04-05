package com.usman.forum.service.Implementation;

import com.usman.forum.exception.BusinessException;
import com.usman.forum.model.*;
import com.usman.forum.repository.AnswerLikeRepository;
import com.usman.forum.repository.SubAnswerLikeRepository;
import com.usman.forum.repository.SubAnswerRepository;
import com.usman.forum.service.SubAnswerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import java.util.Optional;


@Validated
@Service
@AllArgsConstructor
@Slf4j
public class SubAnswersImp implements SubAnswerService{
    private final SubAnswerRepository subAnswerRepository;
    private final  UserImp userImp;
    private  final  AnswerImp answerImp;

    private final SubAnswerLikeRepository subAnswerLikeRepository;


    @Override
    public void saveSubAnswer(SubAnswer subAnswer, Long userid, Long answerId) {
        User user=userImp.findUser(userid);

        Answer answer=answerImp.findAnswer(answerId);

        Optional<SubAnswer> alreadyAnswered= subAnswerRepository.findSubAnswerByQuestionAndUser(answerId,userid);

        if( alreadyAnswered.isPresent()){
            throw new BusinessException(HttpStatus.BAD_REQUEST, "You have already " +
                    "answered this question Update or delete the previous answer");
        }

        subAnswer.setUser(user);
        subAnswer.setAnswer(answer);
        subAnswerRepository.save(subAnswer);

    }

    @Override
    public void updateSubAnswer(Long userId, SubAnswer toEntity, Long id) {
        User user=userImp.findUser(userId);

        SubAnswer subAnswer = findSubAnswerById(id);
        if(user.equals(subAnswer.getUser())) {
            if (!(toEntity.getContent().isEmpty() || toEntity.getContent() == null)) {
                subAnswer.setContent(toEntity.getContent());
            }
            if (!(toEntity.getImage().isEmpty() || toEntity.getImage() == null)) {
                subAnswer.setImage(toEntity.getImage());
            }

            subAnswerRepository.save(subAnswer);
        }
        else{
            throw  new BusinessException(HttpStatus.FORBIDDEN, "You have no permission to update this");
        }
    }

    @Override
    public void deleteSubAnswer(Long userId,Long id) {
        User user=userImp.findUser(userId);
        SubAnswer subAnswer =findSubAnswer(id);
        if(user.equals(subAnswer.getUser())){
        subAnswerRepository.delete(subAnswer);}
        else{
            throw  new BusinessException(HttpStatus.FORBIDDEN, "You have no permission to delete this");
        }
    }

    @Override
    public Page<SubAnswer> findAllSubAnswer(Pageable pageable) {
        return subAnswerRepository.findAll(pageable);
    }

    @Override
    public SubAnswer findSubAnswer(Long id) {
        return subAnswerRepository.findById(id)
                .orElseThrow(()-> new BusinessException(HttpStatus.NOT_FOUND,"There is no such Id: "+id));
    }

    @Override
    public Page<Question> searchAllInQuestionOrAnswersOrSubAnswer(String search, Pageable pageable) {
        return subAnswerRepository.searchAllInQuestionOrAnswersOrSubAnswers(search, pageable);
    }
    private SubAnswer findSubAnswerById(Long id){
        return subAnswerRepository.findById(id)
                .orElseThrow(()-> new BusinessException(HttpStatus.NOT_FOUND,"There is no such Id: "+id));
    }



//    islem var
//    public  <T extends JpaRepository> String likeAndUnLikeQuestionOrAnswer(Long userid, Long id, T repository)  {
//
//
//        SubAnswers answers=findSubAnswer(id);
//
//
//        User user=userImp.findUser(userid);
//        Optional<AnswerLike> likes= answerLikeRepository.findLikeByAnmswerAndUser(id,userid);
//        if( likes.isPresent()){
//            likes.get().setUser(user.getId());
//            likes.get().setAnswer(answers.getId());
//
//            int increaseLike = answers.getLikeCount() - 1;
//            answers.setLikeCount(increaseLike);
//            answerLikeRepository.delete(likes.get());
//            repository.save(answers);
//            return "unliked";
//        }
//
//        AnswerLike newLike = new AnswerLike();
//        newLike.setUser(user.getId());
//        newLike.setAnswer(answers.getId());
//
//        int increaseLike = answers.getLikeCount() + 1;
//        answers.setLikeCount(increaseLike);
//        answerLikeRepository.save(newLike);
//        repository.save(answers);
//
//        return  "liked";
//    }

    @Override
    public String likeUnlikeSubAnswer(Long userId, Long answerId) {
        User user= userImp.findUser(userId);
        SubAnswer subAnswer=findSubAnswer(answerId);
        Optional<SubAnswerLike> likes= subAnswerLikeRepository.likeUnlikeSubAnswer(answerId,userId);
        if( likes.isPresent()){
            subAnswerLikeRepository.delete(likes.get());
            return "unliked";
        }
        SubAnswerLike subAnswerLike= new SubAnswerLike();
        subAnswerLike.setSubAnswer(subAnswer);
        subAnswerLike.setUser(user);
        subAnswerLikeRepository.save(subAnswerLike);
        return "Liked";
    }

    @Override
    public Integer subAnswerLikeCount(long id) {
        return subAnswerLikeRepository.findSubAnswerLikeCount(id);
    }


}
