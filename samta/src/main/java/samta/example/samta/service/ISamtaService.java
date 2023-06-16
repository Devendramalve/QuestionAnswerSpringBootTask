package samta.example.samta.service;

import org.springframework.http.ResponseEntity;
import samta.example.samta.models.QuestionRequest;

public interface ISamtaService {
    ResponseEntity<?> addRendomQuestionService();
    ResponseEntity<?> getQuestionService();
    ResponseEntity<?> nextQuestionService( QuestionRequest questionRequest);
}
