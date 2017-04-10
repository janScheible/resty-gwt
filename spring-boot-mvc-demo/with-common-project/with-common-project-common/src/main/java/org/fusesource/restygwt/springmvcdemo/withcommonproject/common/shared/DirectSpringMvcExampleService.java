package org.fusesource.restygwt.springmvcdemo.withcommonproject.common.shared;

import java.util.List;
import org.fusesource.restygwt.shared.DirectRestService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author sj
 */
public interface DirectSpringMvcExampleService extends DirectRestService {

    @RequestMapping(path = "/list", method = GET)
    List<ExampleDto> getExampleDtos(@RequestParam("id") String id);

    @RequestMapping(path = "/store", method = POST, consumes = {"application/json"})
    void storeDto(ExampleDto exampleDto);

    @RequestMapping(path = "/get/{id : \\d+}", method = GET)
    Integer getRegex(@PathVariable(value = "id") Integer i);

    @RequestMapping(path = "/get/{id : \\d+}/things/{thing: \\d+}", method = GET)
    Integer getRegexMultiParams(@PathVariable(value = "id") Integer i, @PathVariable(value = "thing") Integer t);
}
