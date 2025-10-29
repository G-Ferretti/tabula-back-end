package it.maelstrom.tabula.web.request;

import it.maelstrom.tabula.common.annotation.NullOrNotBlank;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class TaskUpdateRequest {
    @NullOrNotBlank(message = "New description is blank")
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean completed;
    private Long categoryId;
}
