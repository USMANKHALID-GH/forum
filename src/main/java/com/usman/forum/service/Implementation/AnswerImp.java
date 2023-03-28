package com.usman.forum.service.Implementation;

import com.usman.forum.exception.BusinessException;
import com.usman.forum.model.Answers;

import com.usman.forum.model.Questions;
import com.usman.forum.model.User;
import com.usman.forum.repository.AnswerRepository;
import com.usman.forum.repository.QuestionRepository;
import com.usman.forum.repository.UserRepository;
import com.usman.forum.service.AnswersService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AnswerImp  implements AnswersService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private  final UserRepository userRepository;

    @Override
    public void saveAnswer(Answers answers ,Long uid, Long aid) {
        User user=userRepository.findById(uid).get();
        Questions question =questionRepository.findById(aid).get();
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

    private final Answers findAnswers(Long id)
    {
        Answers answers= answerRepository.findById(id).
                orElseThrow(()-> new BusinessException(HttpStatus.NOT_FOUND, "There is  not such Id in our System: "+id));
        return answers;
    }
}
