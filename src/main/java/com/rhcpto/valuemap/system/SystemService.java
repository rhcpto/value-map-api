package com.rhcpto.valuemap.system;

import com.rhcpto.valuemap.ServiceException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@ApplicationScoped
@AllArgsConstructor
@Slf4j
public class SystemService {

    private final SystemRepository systemRepository;
    private final SystemMapper systemMapper;

    public List<System> findAll() {
        return this.systemMapper.toDomainList(systemRepository.findAll().list());
    }

    public Optional<System> findById(@NonNull Integer systemId) {
        return systemRepository.findByIdOptional(systemId)
                .map(systemMapper::toDomain);
    }

    @Transactional
    public void save(@Valid System system) {
        log.debug("Saving System: {}", system);
        SystemEntity entity = systemMapper.toEntity(system);
        systemRepository.persist(entity);
        systemMapper.updateDomainFromEntity(entity, system);
    }

    @Transactional
    public void update(@Valid System system) {
        log.debug("Updating System: {}", system);
        if (Objects.isNull(system.getSystemId())) {
            throw new ServiceException("System does not have a systemId");
        }
        SystemEntity entity = systemRepository.findByIdOptional(system.getSystemId())
                .orElseThrow(() -> new ServiceException("No System found for systemId[%s]", system.getSystemId()));
        systemMapper.updateEntityFromDomain(system, entity);
        systemRepository.persist(entity);
        systemMapper.updateDomainFromEntity(entity, system);
    }

}
