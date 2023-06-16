package samta.example.samta.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ErrorRes {
    private ErrorCode errorCode;
    private String message;
    private String message1;


    public ErrorRes(ErrorCode errorCode, String s, String s1) {
        this.errorCode = errorCode;
        this.message = s;
        this.message1= s1;
    }
}
