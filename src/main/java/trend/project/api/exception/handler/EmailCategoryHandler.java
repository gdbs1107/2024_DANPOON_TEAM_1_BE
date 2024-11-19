package trend.project.api.exception.handler;


import trend.project.api.code.BaseErrorCode;
import trend.project.api.exception.GeneralException;

public class EmailCategoryHandler extends GeneralException {

    public EmailCategoryHandler(BaseErrorCode baseErrorCode) {

        super(baseErrorCode);
    }
}
