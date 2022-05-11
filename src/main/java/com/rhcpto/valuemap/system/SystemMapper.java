package com.rhcpto.valuemap.system;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface SystemMapper {

    List<System> toDomainList(List<SystemEntity> entities);

    System toDomain(SystemEntity entity);

    @InheritInverseConfiguration(name = "toDomain")
    SystemEntity toEntity(System domain);

    void updateEntityFromDomain(System domain, @MappingTarget SystemEntity entity);

    void updateDomainFromEntity(SystemEntity entity, @MappingTarget System domain);

}