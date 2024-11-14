package umc.spring.apiPaylaod.exception.handler;

import umc.spring.apiPaylaod.code.BaseErrorCode;
import umc.spring.apiPaylaod.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
