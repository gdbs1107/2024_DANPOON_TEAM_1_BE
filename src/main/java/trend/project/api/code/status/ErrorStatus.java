package trend.project.api.code.status;



import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import trend.project.api.code.BaseErrorCode;
import trend.project.api.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"COMMON1001","서버에러, 관리자에게 문의 바랍니다"),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON1002","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON1003","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON1004", "금지된 요청입니다."),


    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER2001", "사용자가 없습니다."),
    COMPANY_NOT_FOUND(HttpStatus.NOT_FOUND, "COMPANY3001", "사용자가 없습니다."),
    
    //기획서 관련 오류
    PLAN_NOT_FOUND(HttpStatus.NOT_FOUND, "PLAN4001", "기획서를 찾을 수 없습니다."),
    PLAN_POSTER_NOT_FOUND(HttpStatus.NOT_FOUND, "PLAN4002", "포스터를 찾을 수 없습니다."),
    PLAN_BANNER_NOT_FOUND(HttpStatus.NOT_FOUND, "PLAN4003", "배너를 찾을 수 없습니다."),
    PLAN_LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND, "PLAN4004", "주소를 찾을 수 없습니다."),
    
    
    // 기업 관련 에러
    COMPANY_USERNAME_DUPLICATE(HttpStatus.MULTI_STATUS,"COMPANY4001","중복된 기업 username입니다"),
    COMPANY_NOT_FOUND(HttpStatus.NOT_FOUND, "COMPANY4002", "사용자가 없습니다."),
    COMPANY_VALID_PASSWORD(HttpStatus.MULTI_STATUS,"COMPANY4003","비밀번호가 변경되지 않았습니다");

    
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
