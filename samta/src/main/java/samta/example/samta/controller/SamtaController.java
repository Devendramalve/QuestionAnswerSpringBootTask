package samta.example.samta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import samta.example.samta.models.QuestionRequest;
import samta.example.samta.service.implementation.SamtaServiceImpl;

@RestController
@RequestMapping("api/v1/task")
public class SamtaController {
    @Autowired
    SamtaServiceImpl samtaServiceImpl;
    @PostMapping("/addQuestion")
    public ResponseEntity<?> addRendomQuestion(){
        return samtaServiceImpl.addRendomQuestionService();
    }
    @GetMapping("/play")
    public ResponseEntity<?> getQuestion(){
        return samtaServiceImpl.getQuestionService();
    }
    @PostMapping("/next")
    public ResponseEntity<?> nextQuestion(@RequestBody QuestionRequest questionRequest){
        return samtaServiceImpl.nextQuestionService(questionRequest);
    }


}
