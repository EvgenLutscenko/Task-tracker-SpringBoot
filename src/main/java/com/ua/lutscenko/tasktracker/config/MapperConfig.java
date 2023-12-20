package com.ua.lutscenko.tasktracker.config;

import com.ua.lutscenko.tasktracker.model.Priority;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;

@org.mapstruct.MapperConfig(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.impl")
public class MapperConfig {

}
