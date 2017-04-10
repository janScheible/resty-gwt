package org.fusesource.restygwt.springmvcdemo.twoprojectsonly.frontend.shared;

import org.fusesource.restygwt.shared.DirectRestService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 *
 * @author sj
 */
public interface DirectSpringMvcExampleService extends DirectRestService {

    @RequestMapping(path = "/entities/{id}", method = GET)
    EntityDto getExampleDtos(@PathVariable("id") String id);

    @RequestMapping(path = "/entities", method = POST, consumes = {"application/json"})
    EntityDto storeDto(EntityDto entity);
}
