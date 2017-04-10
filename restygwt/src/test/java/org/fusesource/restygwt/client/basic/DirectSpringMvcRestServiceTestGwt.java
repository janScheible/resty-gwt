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
package org.fusesource.restygwt.client.basic;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.REST;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;

/**
 *
 * @author sj
 */
public class DirectSpringMvcRestServiceTestGwt extends GWTTestCase {

    @Override
    public String getModuleName() {
        return "org.fusesource.restygwt.DirectRestServiceTestGwt";
    }

    public void testCallingMethodsDirectlyShouldFail() {
        delayTestFinish(10000);
        DirectSpringMvcExampleService directExampleService = GWT.create(DirectSpringMvcExampleService.class);

        try {
            directExampleService.getExampleDtos("3");
            fail("This code is unreachable, since the proxy can not be called directly.");
        } catch (Exception e) {
            finishTest();
        }
    }

    public void testQueryCall() {
        delayTestFinish(10000);
        DirectSpringMvcExampleService directExampleService = GWT.create(DirectSpringMvcExampleService.class);

        Resource resource = new Resource(GWT.getModuleBaseURL() + "api");
        ((RestServiceProxy) directExampleService).setResource(resource);

        REST.withCallback(new MethodCallback<List<ExampleDto>>() {
            @Override
            public void onSuccess(Method method, List<ExampleDto> response) {
                assertEquals(3, response.size());
                finishTest();
            }

            @Override
            public void onFailure(Method method, Throwable e) {
                fail(e.getMessage());
            }
        }).call(directExampleService).getExampleDtos("3");
    }

    public void testRegexPathParamCall() {
        delayTestFinish(10000);
        DirectSpringMvcExampleService directExampleService = GWT.create(DirectSpringMvcExampleService.class);

        Resource resource = new Resource(GWT.getModuleBaseURL() + "api");
        ((RestServiceProxy) directExampleService).setResource(resource);

        REST.withCallback(new MethodCallback<Integer>() {
            @Override
            public void onSuccess(Method method, Integer response) {
                assertEquals((Integer) 456, response);
                finishTest();
            }

            @Override
            public void onFailure(Method method, Throwable e) {
                fail(e.getMessage());
            }
        }).call(directExampleService).getRegexMultiParams(123, 456);
    }

    public void testVoidCall() {
        delayTestFinish(10000);
        DirectSpringMvcExampleService directExampleService = GWT.create(DirectSpringMvcExampleService.class);

        Resource resource = new Resource(GWT.getModuleBaseURL() + "api");
        ((RestServiceProxy) directExampleService).setResource(resource);

        REST.withCallback(new MethodCallback<List<ExampleDto>>() {
            @Override
            public void onSuccess(Method method, List<ExampleDto> response) {
                finishTest();
            }

            @Override
            public void onFailure(Method method, Throwable e) {
                fail(e.getMessage());
            }
        }).call(directExampleService).storeDto(new ExampleDto());
    }

    public void testPrimitiveDirectRestService() {
        PrimitiveDirectRestService primitiveDirectService = GWT.create(PrimitiveDirectRestService.class);
        assertNotNull(primitiveDirectService);
    }
}
