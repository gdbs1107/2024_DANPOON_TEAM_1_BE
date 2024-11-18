package trend.project.api.exception.handler;

import trend.project.api.code.BaseErrorCode;
import trend.project.api.exception.GeneralException;

public class PlanCategoryHandler extends GeneralException {
    
    public PlanCategoryHandler(BaseErrorCode baseErrorCode) {
        
        super(baseErrorCode);
    }
}
