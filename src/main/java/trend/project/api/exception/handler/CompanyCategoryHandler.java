package trend.project.api.exception.handler;


import trend.project.api.code.BaseErrorCode;
import trend.project.api.exception.GeneralException;

public class CompanyCategoryHandler extends GeneralException {


    public CompanyCategoryHandler(BaseErrorCode baseErrorCode) {

        super(baseErrorCode);
    }
}
