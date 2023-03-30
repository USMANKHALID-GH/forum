package com.usman.forum.service.Implementation;

import com.usman.forum.exception.BusinessException;
import com.usman.forum.model.Answers;
import com.usman.forum.model.SubAnswers;
import com.usman.forum.model.User;
import com.usman.forum.repository.AnswerRepository;
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

@Validated
@Service
@AllArgsConstructor
@Slf4j
public class SubAnswersImp implements SubAnswerService {
    private final SubAnswerRepository subAnswerRepository;
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;

    @Override
    public void saveSubAnswer(SubAnswers subAnswers, Long uid, Long aid) {
        User user=userRepository.findById(uid).get();
        Answers answer=answerRepository.findById(aid).get();
        subAnswers.setUser(user);
        subAnswers.setAnswer(answer);
        subAnswerRepository.save(subAnswers);

    }

    @Override
    public void updateSubAnswer(SubAnswers toEntity, Long id) {
        SubAnswers subAnswers=findSubAswer(id);
        if(!(toEntity.getContent().isEmpty()  ||toEntity.getContent()==null)){
            subAnswers.setContent(toEntity.getContent());
        }
        if(!(toEntity.getImage().isEmpty()  || toEntity.getImage()==null)){
            subAnswers.setImage(toEntity.getImage());
        }

        subAnswerRepository.save(subAnswers);

    }

    @Override
    public void deleteSubAnswer(Long id) {
        subAnswerRepository.delete(findSubAswer(id));
    }

    @Override
    public Page<SubAnswers> findAllSubAnswer(Pageable pageable) {
        return subAnswerRepository.findAll(pageable);
    }

    @Override
    public SubAnswers findSubAnswer(Long id) {
        return findSubAswer(id);
    }

    private SubAnswers findSubAswer(Long id){
        return subAnswerRepository.findById(id)
                .orElseThrow(()-> new BusinessException(HttpStatus.NOT_FOUND,"There is no such Id: "+id));
    }
}
