package umc.spring.apiPaylaod.exception.handler;

import umc.spring.apiPaylaod.code.BaseErrorCode;
import umc.spring.apiPaylaod.exception.GeneralException;

public class StoreHandler extends GeneralException {
    public StoreHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
