package org.fusesource.restygwt.springmvcdemo.withcommonproject.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author sj
 */
@Component
public class EntityApplicationService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public EntityAso getById(String id) {
        EntityAso entity = EntityAso.create(id, "From Server");
        logger.info("getById(" + entity.toString() + ")");
        return entity;
    }

    public EntityAso create(EntityAso entityValues) {
        logger.info("create(" + entityValues.toString() + ")");
        return EntityAso.create("42", entityValues.getName());
    }
}
