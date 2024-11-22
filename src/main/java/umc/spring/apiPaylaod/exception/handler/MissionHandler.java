package umc.spring.apiPaylaod.exception.handler;

import umc.spring.apiPaylaod.code.BaseErrorCode;
import umc.spring.apiPaylaod.exception.GeneralException;

public class MissionHandler extends GeneralException {
    public MissionHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
