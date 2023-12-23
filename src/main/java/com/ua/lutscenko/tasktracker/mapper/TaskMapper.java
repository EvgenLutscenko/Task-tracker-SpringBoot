package com.ua.lutscenko.tasktracker.mapper;

import com.ua.lutscenko.tasktracker.config.MapperConfig;
import com.ua.lutscenko.tasktracker.dto.task.TaskCreateRequestDto;
import com.ua.lutscenko.tasktracker.dto.task.TaskRespDto;
import com.ua.lutscenko.tasktracker.dto.task.TaskUpdateRequestDto;
import com.ua.lutscenko.tasktracker.model.Priority;
import com.ua.lutscenko.tasktracker.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface TaskMapper{
    @Mapping(source = "priority", target = "priority", qualifiedByName = "toPriority")
    @Mapping(source = "isCompleted", target = "completed", qualifiedByName = "toBoolean")
    Task toModel(TaskCreateRequestDto dto);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "completed", target = "isCompleted", qualifiedByName = "fromBoolean")
    TaskRespDto toDto(Task task);

    @Mapping(source = "priority", target = "priority", qualifiedByName = "toPriority")
    @Mapping(source = "isCompleted", target = "completed", qualifiedByName = "toBoolean")
    Task toModel(TaskUpdateRequestDto dto);
    @Named("toPriority")
    default Priority toPriority(String priority) {
        return Priority.value(priority);
    }

    @Named("toBoolean")
    default Boolean toBoolean(String string){
        return Boolean.valueOf(string);
    }

    @Named("fromBoolean")
    default Boolean fromBoolean(boolean bool) {
        return bool;
    }
}
