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
