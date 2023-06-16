package samta.example.samta.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAnswerResponse {
    private String correct_answer;
    private  ResponseQuestionWithQuestionId next_question;
}
