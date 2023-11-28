package hexlet.code.service;

import hexlet.code.dto.TaskStatusCreateDto;
import hexlet.code.dto.TaskStatusDto;
import hexlet.code.dto.TaskStatusUpdateDto;
import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.mapper.TaskStatusMapper;
import hexlet.code.repository.TaskStatusesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskStatusService {
    private final TaskStatusesRepository taskStatusesRepository;

    private final TaskStatusMapper taskStatusMapper;

    public List<TaskStatusDto> getAll() {
        var statuses = taskStatusesRepository.findAll();
        var result = statuses.stream()
                .map(taskStatusMapper::map)
                .toList();
        return result;
    }

    public TaskStatusDto findById(Long id) {
        var status = taskStatusesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Status with id " + id + " not found"));

        var result = taskStatusMapper.map(status);
        return result;
    }
    public TaskStatusDto create(TaskStatusCreateDto dto) {
        var status = taskStatusMapper.map(dto);
        taskStatusesRepository.save(status);
        var result = taskStatusMapper.map(status);
        return result;
    }
    public TaskStatusDto update(TaskStatusUpdateDto dto, Long id) {
        var status = taskStatusesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Status with id " + id + " not found"));

        taskStatusMapper.update(dto, status);
        taskStatusesRepository.save(status);
        var result = taskStatusMapper.map(status);
        return result;
    }
    public void delete(Long id) {
        var status = taskStatusesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Status with id " + id + " not found"));
        taskStatusesRepository.deleteById(id);
    }
}
