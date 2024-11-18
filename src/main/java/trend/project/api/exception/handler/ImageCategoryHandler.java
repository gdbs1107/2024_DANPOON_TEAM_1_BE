package trend.project.api.exception.handler;

import trend.project.api.code.BaseErrorCode;
import trend.project.api.exception.GeneralException;

public class ImageCategoryHandler extends GeneralException {

    public ImageCategoryHandler(BaseErrorCode baseErrorCode) {

        super(baseErrorCode);
    }
}
