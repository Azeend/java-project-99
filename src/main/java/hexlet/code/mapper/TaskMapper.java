package hexlet.code.mapper;

import hexlet.code.dto.TaskCreateDto;
import hexlet.code.dto.TaskDto;
import hexlet.code.dto.TaskUpdateDto;
import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.model.Task;
import hexlet.code.model.TaskStatus;
import hexlet.code.model.User;
import hexlet.code.repository.TaskStatusRepository;
import hexlet.code.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        uses = {JsonNullableMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class TaskMapper {
    @Autowired
    private TaskStatusRepository statusRepository;
    @Autowired
    private UserRepository userRepository;

    @Mapping(source = "title", target = "name")
    @Mapping(source = "status", target = "taskStatus")
    @Mapping(source = "assignee_id", target = "assignee")
    public abstract Task map(TaskCreateDto dto);
    @Mapping(source = "name", target = "title")
    @Mapping(source = "description", target = "content")
    @Mapping(source = "taskStatus.slug", target = "status")
    @Mapping(source = "assignee.id", target = "assignee_id")
    public abstract TaskDto map(Task model);

    @Mapping(source = "title", target = "name")
    @Mapping(source = "status", target = "taskStatus")
    @Mapping(source = "assignee_id", target = "assignee")
    public abstract void update(TaskUpdateDto dto, @MappingTarget Task model);

    @Mapping(target = "status", source = "taskStatus.slug")
    @Mapping(target = "assignee_id", source = "assignee.id")
    @Mapping(target = "title", source = "name")
    @Mapping(target = "content", source = "description")
    public abstract TaskCreateDto mapToCreateDto(Task model);

    public TaskStatus toTaskStatus(String statusSlug) {
        return statusRepository.findBySlug(statusSlug)
                .orElseThrow(() -> new ResourceNotFoundException("TaskStatus with slug " + statusSlug + " not found"));
    }
    public User toUser(Long assigneeId) {
        return userRepository.findById(assigneeId)
                .orElseThrow();
    }
}

