package umc.spring.apiPaylaod.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.spring.apiPaylaod.code.BaseErrorCode;
import umc.spring.apiPaylaod.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private BaseErrorCode code;

    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }
}