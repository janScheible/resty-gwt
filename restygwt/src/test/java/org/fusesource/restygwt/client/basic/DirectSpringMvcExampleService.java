/*
 * Copyright 2017 sj.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fusesource.restygwt.client.basic;

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

    @RequestMapping(path = "/store", method = POST)
    void storeDto(ExampleDto exampleDto);

    @RequestMapping(path = "/get/{id : \\d+}", method = GET)
    Integer getRegex(@PathVariable(value = "id") Integer i);

    @RequestMapping(path = "/get/{id : \\d+}/things/{thing: \\d+}", method = GET)
    Integer getRegexMultiParams(@PathVariable(value = "id") Integer i, @PathVariable(value = "thing") Integer t);
}
