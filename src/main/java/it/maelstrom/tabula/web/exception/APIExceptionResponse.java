package it.maelstrom.tabula.web.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

import java.util.Map;


@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class APIExceptionResponse {
    private final String message;
    private final String reason;
    private final Map<String, Object> metadata;
}
