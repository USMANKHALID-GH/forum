package com.usman.forum.service.Implementation;

import com.usman.forum.exception.BusinessException;
import com.usman.forum.model.*;
import com.usman.forum.repository.AnswerRepository;
import com.usman.forum.repository.LikesReposiory;
import com.usman.forum.repository.SubAnswerRepository;
import com.usman.forum.repository.UserRepository;
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
public class SubAnswersImp implements SubAnswerService {
    private final SubAnswerRepository subAnswerRepository;
    private final  UserImp userImp;
    private  final  AnswerImp answerImp;


    @Override
    public void saveSubAnswer(SubAnswers subAnswers, Long userid, Long answerId) {
        User user=userImp.findUser(userid);

        Answers answer=answerImp.findAnswer(answerId);

        Optional<SubAnswers> alreadyAnswered= subAnswerRepository.findSubAnswerByQuestionAndUser(answerId,userid);

        if( alreadyAnswered.isPresent()){
            throw new BusinessException(HttpStatus.BAD_REQUEST, "You have already " +
                    "answered this question Update or delete the previous answer");
        }

        subAnswers.setUser(user);
        subAnswers.setAnswer(answer);
        subAnswerRepository.save(subAnswers);

    }

    @Override
    public void updateSubAnswer(Long userId,SubAnswers toEntity, Long id) {
        User user=userImp.findUser(userId);

        SubAnswers subAnswers= findSubAnswerById(id);
        if(user.equals(subAnswers.getUser())) {
            if (!(toEntity.getContent().isEmpty() || toEntity.getContent() == null)) {
                subAnswers.setContent(toEntity.getContent());
            }
            if (!(toEntity.getImage().isEmpty() || toEntity.getImage() == null)) {
                subAnswers.setImage(toEntity.getImage());
            }

            subAnswerRepository.save(subAnswers);
        }
        else{
            throw  new BusinessException(HttpStatus.FORBIDDEN, "You have no permission to update this");
        }
    }

    @Override
    public void deleteSubAnswer(Long userId,Long id) {
        User user=userImp.findUser(userId);
        SubAnswers subAnswers=findSubAnswer(id);
        if(user.equals(subAnswers.getUser())){
        subAnswerRepository.delete(subAnswers);}
        else{
            throw  new BusinessException(HttpStatus.FORBIDDEN, "You have no permission to delete this");
        }
    }

    @Override
    public Page<SubAnswers> findAllSubAnswer(Pageable pageable) {
        return subAnswerRepository.findAll(pageable);
    }

    @Override
    public SubAnswers findSubAnswer(Long id) {
        return subAnswerRepository.findById(id)
                .orElseThrow(()-> new BusinessException(HttpStatus.NOT_FOUND,"There is no such Id: "+id));
    }

    @Override
    public Page<Questions> searchAllInQuestionOrAnswersOrSubAnswer(String search, Pageable pageable) {
        return subAnswerRepository.searchAllInQuestionOrAnswersOrSubAnswers(search, pageable);
    }
    private SubAnswers findSubAnswerById(Long id){
        return subAnswerRepository.findById(id)
                .orElseThrow(()-> new BusinessException(HttpStatus.NOT_FOUND,"There is no such Id: "+id));
    }

    @Override
    public String likeUnlikeAnswer(Long userI, Long answerId) {
        return answerImp.likeAndUnLikeQuestionOrAnswer(userI,answerId,subAnswerRepository);

    }


}
