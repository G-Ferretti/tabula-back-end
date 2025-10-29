

package it.maelstrom.tabula.web.exception;

import java.util.Map;

import it.maelstrom.tabula.common.constant.ErrorConstants;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MissingResourceException extends APIException {

    @Builder
    protected MissingResourceException(String message, Throwable cause, String reason, Map<String, Object> metadata) {
        super(message, cause, reason, metadata);
    }

    protected String getDefaultMessage() {
        return ErrorConstants.MISSING_RESOURCE_MESSAGE;
    }

    protected String getDefaultReason() {
        return ErrorConstants.MISSING_RESOURCE_REASON;
    }

}
