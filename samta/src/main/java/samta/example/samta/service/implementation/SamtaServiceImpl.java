package samta.example.samta.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import samta.example.samta.entity.Root;
import samta.example.samta.exception.ErrorCode;
import samta.example.samta.exception.ErrorRes;
import samta.example.samta.models.QuestionAnswerResponse;
import samta.example.samta.models.QuestionRequest;
import samta.example.samta.models.ResponseQuestionWithQuestionId;
import samta.example.samta.repository.SamtaRepository;
import samta.example.samta.service.ISamtaService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SamtaServiceImpl implements ISamtaService {
    @Autowired
    SamtaRepository samtaRepository;
    @Autowired
    RestTemplate restTemplate;
    @Override
    public ResponseEntity<?> addRendomQuestionService() {
        List<Root> responseList = new ArrayList<>();
        for(int i=0; i<5; i++){
            Root[] root=restTemplate.getForObject("https://jservice.io/api/random", Root[].class);
            Root root1 = root[0];
            Root root2 = new Root(root1.getId(),root1.getAnswer(),root1.getQuestion(),root1.getValue(),root1.getAirdate(),root1.getCreated_at(),
                    root1.getUpdated_at(),root1.getCategory_id(),root1.getGame_id(),root1.getInvalid_count(),root1.getCategory());
            Root rootObj = samtaRepository.save(root2);
            responseList.add(rootObj);
        }

        return ResponseEntity.ok(responseList);
    }

    @Override
    public ResponseEntity<?> getQuestionService() {
        try {
            List<Root> root = samtaRepository.findAll();
            List<ResponseQuestionWithQuestionId> responseList = new ArrayList<>();
            for(Root obj:root){
                ResponseQuestionWithQuestionId responseQuestionWithQuestionId = new ResponseQuestionWithQuestionId();
                responseQuestionWithQuestionId.setQuestion(obj.getQuestion());
                responseQuestionWithQuestionId.setQuestionId(obj.getId());
                responseList.add(responseQuestionWithQuestionId);
                if(responseList.size() == 5){
                    break;
                }

            }

            return ResponseEntity.ok(responseList);
        }catch (NoSuchElementException e) {
            return new ResponseEntity<>(
                    new ErrorRes(
                            ErrorCode.NOT_FOUND,
                            "Questions not found !: ",
                            "Questions are not available in Database "
                    ),
                    HttpStatus.NOT_FOUND
            );
        }


    }

    @Override
    public ResponseEntity<?> nextQuestionService( QuestionRequest questionRequest) {
        try{
            Root rootObj = samtaRepository.findById(questionRequest.getQuestionId()).get();
            QuestionAnswerResponse questionAnswerResponse = new QuestionAnswerResponse();
            questionAnswerResponse.setCorrect_answer(rootObj.getAnswer());
            ResponseQuestionWithQuestionId responseQuestionWithQuestionId = new ResponseQuestionWithQuestionId();
            Root[] rootob = restTemplate.getForObject("https://jservice.io/api/random", Root[].class);
            Root obj= rootob[0];
            responseQuestionWithQuestionId.setQuestionId(obj.getId());
            responseQuestionWithQuestionId.setQuestion(obj.getQuestion());
            Root dbObj = samtaRepository.save(obj);
            questionAnswerResponse.setNext_question(responseQuestionWithQuestionId);
            return ResponseEntity.ok(questionAnswerResponse);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(
                    new ErrorRes(
                            ErrorCode.NOT_FOUND,
                            "Question not found with QuestionId!: "+ questionRequest.getQuestionId(),
                            "This QuestionId is not available in Database "
                    ),
                    HttpStatus.NOT_FOUND
            );
        }

    }
}
