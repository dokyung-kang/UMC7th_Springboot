package umc.spring.apiPaylaod.exception.handler;

import umc.spring.apiPaylaod.code.BaseErrorCode;
import umc.spring.apiPaylaod.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}