package trend.project.api.exception.handler;


import trend.project.api.code.BaseErrorCode;
import trend.project.api.exception.GeneralException;

public class MemberCategoryHandler extends GeneralException {

    public MemberCategoryHandler(BaseErrorCode baseErrorCode) {

        super(baseErrorCode);
    }
}
