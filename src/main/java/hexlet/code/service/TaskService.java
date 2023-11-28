package hexlet.code.service;

import hexlet.code.dto.TaskCreateDto;
import hexlet.code.dto.TaskDto;
import hexlet.code.dto.TaskParamsDto;
import hexlet.code.dto.TaskUpdateDto;
import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.mapper.TaskMapper;
import hexlet.code.repository.TaskStatusesRepository;
import hexlet.code.repository.TasksRepository;
import hexlet.code.specification.TaskSpecification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TasksRepository tasksRepository;
    private final TaskMapper taskMapper;
    private final TaskSpecification taskSpecification;
    private final TaskStatusesRepository taskStatusesRepository;

    public List<TaskDto> getAll(TaskParamsDto params) {
        var specification = taskSpecification.build(params);
        var tasks = tasksRepository.findAll(specification);
        var result = tasks.stream()
                .map(taskMapper::map)
                .toList();
        return result;
    }

    public TaskDto findById(Long id) {
        var task = tasksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));

        var result = taskMapper.map(task);
        return result;
    }

    public TaskDto create(TaskCreateDto dto) {
        var task = taskMapper.map(dto);
        var taskStatusSlug = dto.getStatus();
        var taskStatus = taskStatusesRepository.findBySlug(taskStatusSlug)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        task.setStatus(taskStatus);
        tasksRepository.save(task);
        var result = taskMapper.map(task);
        return result;
    }

    public TaskDto update(TaskUpdateDto dto, Long id) {
        var task = tasksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
        taskMapper.update(dto, task);
        tasksRepository.save(task);
        var result = taskMapper.map(task);
        return result;
    }

    public void delete(Long id) {
        tasksRepository.deleteById(id);
    }
}
