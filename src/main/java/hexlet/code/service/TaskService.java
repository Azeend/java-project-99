package hexlet.code.service;

import hexlet.code.dto.TaskCreateDto;
import hexlet.code.dto.TaskDto;
import hexlet.code.dto.TaskParamsDto;
import hexlet.code.dto.TaskUpdateDto;
import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.mapper.TaskMapper;
import hexlet.code.repository.TaskStatusRepository;
import hexlet.code.repository.TaskRepository;
import hexlet.code.specification.TaskSpecification;
import lombok.AllArgsConstructor;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TaskSpecification taskSpecification;
    private final TaskStatusRepository taskStatusRepository;

    public List<TaskDto> getAll(TaskParamsDto params) {
        var specification = taskSpecification.build(params);
        var tasks = taskRepository.findAll(specification);
        var result = tasks.stream()
                .map(taskMapper::map)
                .toList();
        return result;
    }

    public TaskDto findById(Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));

        var result = taskMapper.map(task);
        return result;
    }

    public TaskDto create(TaskCreateDto dto) {
        var task = taskMapper.map(dto);
        var taskStatusSlug = dto.getStatus();
        var taskStatus = taskStatusRepository.findBySlug(taskStatusSlug)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        taskStatus.setSlug(taskStatusSlug);
        task.setTaskStatus(taskStatus);
        taskRepository.save(task);
        var result = taskMapper.map(task);
        return result;
    }

    public TaskDto update(TaskUpdateDto dto, Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
        taskMapper.update(dto, task);
        taskRepository.save(task);
        var result = taskMapper.map(task);
        return result;
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
