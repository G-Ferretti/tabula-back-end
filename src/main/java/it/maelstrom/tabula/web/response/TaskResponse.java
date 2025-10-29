package it.maelstrom.tabula.web.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskResponse {
    private Long categoryId;
    private Long taskId;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean completed;
}
