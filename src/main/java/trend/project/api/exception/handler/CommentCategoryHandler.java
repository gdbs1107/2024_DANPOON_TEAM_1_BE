package trend.project.api.exception.handler;

import trend.project.api.code.BaseErrorCode;
import trend.project.api.exception.GeneralException;

public class CommentCategoryHandler extends GeneralException {
    
    public CommentCategoryHandler(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
