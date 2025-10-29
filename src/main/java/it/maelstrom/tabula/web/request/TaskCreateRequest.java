package it.maelstrom.tabula.web.request;

import it.maelstrom.tabula.common.annotation.NullOrNotBlank;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class TaskCreateRequest {
    @NotBlank(message = "Title is blank")
    private String title;
    
    @NotNull(message = "Start date is absent")
    private LocalDate startDate;

    @NullOrNotBlank(message = "Description is blank")
    private String description;

    private LocalDate endDate;
    private Long categoryId;
}
