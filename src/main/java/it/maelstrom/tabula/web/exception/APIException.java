package it.maelstrom.tabula.web.exception;

import java.util.Map;

import it.maelstrom.tabula.common.constant.ErrorConstants;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class APIException extends RuntimeException {
    private final String reason;
    private final Map<String, Object> metadata;

    protected APIException(String message, Throwable cause, String reason, Map<String, Object> metadata) {
        super(message, cause);
        this.reason = reason;
        this.metadata = metadata;
    }

    public String getMessage() {
        return super.getMessage() == null ? this.getDefaultMessage() : super.getMessage();
    }

    public String getReason() {
        return this.reason == null ? this.getDefaultReason() : this.reason;
    }

    protected String getDefaultMessage() {
        return ErrorConstants.DEFAULT_EXCEPTION_MESSAGE;
    }

    protected String getDefaultReason() {
        return ErrorConstants.DEFAULT_EXCEPTION_REASON;
    }

}
