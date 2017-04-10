/**
 * Copyright (C) 2009-2011 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
