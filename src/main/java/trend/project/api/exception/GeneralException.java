package trend.project.api.exception;



import lombok.AllArgsConstructor;
import lombok.Getter;
import trend.project.api.code.BaseErrorCode;
import trend.project.api.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private BaseErrorCode code;

    public ErrorReasonDTO getErrorReason(){
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }
}
