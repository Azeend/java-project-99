package hexlet.code.mapper;

import hexlet.code.dto.TaskCreateDto;
import hexlet.code.dto.TaskDto;
import hexlet.code.dto.TaskUpdateDto;
import hexlet.code.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        uses = {JsonNullableMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class TaskMapper {

    @Mapping(target = "assignee.id", source = "assignee_id")
    @Mapping(target = "taskStatus.slug", source = "status")
    @Mapping(target = "name", source = "title")
    public abstract Task map(TaskCreateDto dto);
    @Mapping(target = "status", source = "taskStatus.slug")
    @Mapping(target = "assignee_id", source = "assignee.id")
    @Mapping(target = "title", source = "name")
    @Mapping(target = "content", source = "description")
    public abstract TaskDto map(Task model);

    @Mapping(target = "name", source = "title")
    @Mapping(target = "assignee.id", source = "assignee_id")
    public abstract void update(TaskUpdateDto dto, @MappingTarget Task model);

    @Mapping(target = "status", source = "taskStatus.slug")
    @Mapping(target = "assignee_id", source = "assignee.id")
    @Mapping(target = "title", source = "name")
    @Mapping(target = "content", source = "description")
    public abstract TaskCreateDto mapToCreateDto(Task model);
}

