package org.fusesource.restygwt.springmvcdemo.withcommonproject.backend.gwtadapter;

import org.fusesource.restygwt.springmvcdemo.twoprojectsonly.frontend.shared.DirectSpringMvcExampleService;
import org.fusesource.restygwt.springmvcdemo.twoprojectsonly.frontend.shared.EntityDto;
import org.fusesource.restygwt.springmvcdemo.withcommonproject.backend.EntityApplicationService;
import org.fusesource.restygwt.springmvcdemo.withcommonproject.backend.EntityAso;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author scheible
 */
@RestController
public class DirectSpringMvcExampleServiceImpl implements DirectSpringMvcExampleService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final EntityApplicationService entityApplicationService;

    public DirectSpringMvcExampleServiceImpl(EntityApplicationService entityApplicationService) {
        this.entityApplicationService = entityApplicationService;
    }

    @Override
    public EntityDto getExampleDtos(@PathVariable("id") String id) {
        logger.info("getExampleDtos(id = '" + id + "')");
        EntityAso entityAso = entityApplicationService.getById(id);
        return new EntityDto(entityAso.getId().orElse(null), entityAso.getName());
    }

    @Override
    public EntityDto storeDto(@RequestBody EntityDto entity) {
        logger.info("storeDto(name = '" + entity.getName() + "')");
        EntityAso entityAso = entityApplicationService.create(EntityAso.create(entity.getName()));
        return new EntityDto(entityAso.getId().orElse(null), entityAso.getName());
    }
}
