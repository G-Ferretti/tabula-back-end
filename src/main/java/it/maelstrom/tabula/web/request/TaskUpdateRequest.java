package it.maelstrom.tabula.web.request;

import it.maelstrom.tabula.common.annotation.NullOrNotBlank;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class TaskUpdateRequest {
    private String description;
    private LocalDate endDate;
    private Boolean completed;
}
