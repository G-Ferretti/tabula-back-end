package it.maelstrom.tabula.controller;

import it.maelstrom.tabula.common.constant.EndpointConstants;
import it.maelstrom.tabula.service.TaskService;
import it.maelstrom.tabula.web.request.TaskCreateRequest;
import it.maelstrom.tabula.web.request.TaskUpdateRequest;
import it.maelstrom.tabula.web.response.TaskResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(EndpointConstants.TASK_BASE_URL)
@Validated
@RequiredArgsConstructor
public class TaskController {
    private final TaskService service;

    @PostMapping
    public void createTask(@RequestBody @Valid TaskCreateRequest request) {
        this.service.createTask(request);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        return ResponseEntity.ok().body(this.service.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable(name = "id") @NotNull(message = "Task id is absent") Long id){
        return ResponseEntity.ok().body(this.service.getTask(id));
    }

    @PatchMapping("/{id}")
    public void updateTask(
            @RequestBody @Valid TaskUpdateRequest request, @PathVariable(name = "id") @NotNull(message = "Task id is absent") Long id
    ) {
        this.service.updateTask(request, id);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable(name = "id") @NotNull(message = "Task id is absent") Long id) {
        this.service.deleteTask(id);
    }
}
