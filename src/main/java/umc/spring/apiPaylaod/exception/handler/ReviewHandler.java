package umc.spring.apiPaylaod.exception.handler;

import umc.spring.apiPaylaod.code.BaseErrorCode;
import umc.spring.apiPaylaod.exception.GeneralException;

public class ReviewHandler extends GeneralException {
    public ReviewHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
