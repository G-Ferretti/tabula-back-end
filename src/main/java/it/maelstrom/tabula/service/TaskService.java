package it.maelstrom.tabula.service;

import it.maelstrom.tabula.model.Category;
import it.maelstrom.tabula.model.Task;
import it.maelstrom.tabula.repository.CategoryRepository;
import it.maelstrom.tabula.repository.TaskRepository;
import it.maelstrom.tabula.web.exception.MissingResourceException;
import it.maelstrom.tabula.web.request.TaskCreateRequest;
import it.maelstrom.tabula.web.request.TaskUpdateRequest;
import it.maelstrom.tabula.web.response.TaskResponse;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepo;
    private final CategoryRepository categoryRepo;

    public void createTask(TaskCreateRequest request) {
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .startDate(request.getStartDate())
                .category(this.fetchCategory(request.getCategoryId()))
                .completed(false)
                .build();

        this.taskRepo.save(task);
    }

    @Transactional
    public void updateTask(TaskUpdateRequest request, Long taskId) {
        Task task = taskRepo.findById(taskId).orElseThrow(() -> MissingResourceException
                .builder()
                .reason("No task found with id:" + taskId)
                .build()
        );

        task.setDescription(request.getDescription());
        task.setStartDate(request.getStartDate());
        task.setEndDate(request.getEndDate());
        task.setCompleted(request.getCompleted());
        task.setCategory(this.fetchCategory(request.getCategoryId()));

        this.taskRepo.save(task);
    }

    public List<TaskResponse> getAllTasks() {
        List<Task> tasks = this.taskRepo.findAll();
        if (tasks.isEmpty())
            throw MissingResourceException.builder().reason("No task found").build();

        return tasks
                .stream()
                .map((task) -> TaskResponse.builder()
                        .categoryId(task.getCategory() != null ? task.getCategory().getId() : null)
                        .taskId(task.getId())
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .startDate(task.getStartDate())
                        .endDate(task.getEndDate())
                        .completed(task.getCompleted())
                        .build()
                ).toList();
    }

    @Transactional
    public void deleteTask(Long taskId) {
        Task task = taskRepo.findById(taskId).orElseThrow(() ->
                MissingResourceException
                        .builder()
                        .reason("No task found with id: " + taskId)
                        .build()
        );
        taskRepo.delete(task);
    }

    public Category fetchCategory(Long categoryId) {
        return categoryId == null
                ? null
                : categoryRepo.findById(categoryId).orElseThrow(() ->
                MissingResourceException
                        .builder()
                        .reason("No category found with id: " + categoryId)
                        .build()
        );
    }

}
