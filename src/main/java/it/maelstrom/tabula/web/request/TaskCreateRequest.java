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
    @NotNull(message = "Category is absent")
    private Long categoryId;

    @NotBlank(message = "Title is blank")
    private String title;
    
    @NotNull(message = "Start date is absent")
    private LocalDate startDate;
    
    private String description;

    private LocalDate endDate;

}
