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
package org.fusesource.restygwt.springmvcdemo.withcommonproject.backend;

import java.util.ArrayList;
import java.util.List;
import org.fusesource.restygwt.springmvcdemo.withcommonproject.common.shared.DirectSpringMvcExampleService;
import org.fusesource.restygwt.springmvcdemo.withcommonproject.common.shared.ExampleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author scheible
 */
@RestController
public class DirectSpringMvcExampleServiceImpl implements DirectSpringMvcExampleService {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<ExampleDto> getExampleDtos(String id) {
        List<ExampleDto> dtos = new ArrayList<>();
        ExampleDto dto = new ExampleDto();
        dto.setName("hi");
        dtos.add(dto);
        return dtos;
    }

    @Override
    public void storeDto(@RequestBody ExampleDto exampleDto) {
        logger.info(exampleDto.getName());
    }

    @Override
    public Integer getRegex(Integer i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getRegexMultiParams(Integer i, Integer t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
