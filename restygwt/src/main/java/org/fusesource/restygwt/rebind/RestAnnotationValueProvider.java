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
package org.fusesource.restygwt.rebind;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.HasAnnotations;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JParameter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import static org.fusesource.restygwt.rebind.BaseSourceCreator.ERROR;
import static org.fusesource.restygwt.rebind.util.AnnotationUtils.getAnnotation;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author sj
 */
public class RestAnnotationValueProvider {

    private static final boolean SPRING_MVC_AVAILABLE;
    private static final boolean JAX_RS_AVAILABLE;

    static {
        boolean springMvcAvailable = false;
        try {
            Class.forName("org.springframework.web.bind.annotation.RequestMapping");
            springMvcAvailable = true;
        } catch (ClassNotFoundException ex) {
        }
        SPRING_MVC_AVAILABLE = springMvcAvailable;

        boolean jaxRsAvailable = false;
        try {
            Class.forName("javax.ws.rs.Path");
            jaxRsAvailable = true;
        } catch (ClassNotFoundException ex) {
        }
        JAX_RS_AVAILABLE = jaxRsAvailable;
    }

    public static String getRestMethod(JMethod method, TreeLogger logger) throws UnableToCompleteException {
        String restMethod = null;

        if (JAX_RS_AVAILABLE) {
            final Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                HttpMethod httpMethod = annotation.annotationType().getAnnotation(HttpMethod.class);
                // Check is HttpMethod
                if (httpMethod != null) {
                    if (restMethod != null) {
                        // Error, see description of HttpMethod
                        logger.log(ERROR, "Invalid method. It is an error for a method to be annotated with more than one annotation that is annotated with HttpMethod: " + method.getReadableDeclaration());
                        throw new UnableToCompleteException();
                    }
                    restMethod = httpMethod.value();
                }
            }
        }

        if (SPRING_MVC_AVAILABLE) {
            RequestMapping requestMapping = getAnnotation(method, RequestMapping.class);
            if (null != requestMapping) {
                RequestMethod[] requestMethods = requestMapping.method();
                if (requestMethods != null) {
                    if (requestMethods.length == 1) {
                        restMethod = requestMethods[0].name().toLowerCase();
                    } else if (requestMethods.length > 1) {
                        logger.log(ERROR, "Invalid method. It is an error for a method to be annotated "
                                + "with @RequestMapping and more than one request method specified: "
                                + method.getReadableDeclaration());
                        throw new UnableToCompleteException();
                    }
                }
            }
        }

        return restMethod;
    }

    public static String getPathValue(HasAnnotations annotatedType, TreeLogger logger) throws UnableToCompleteException {
        String path = null;
        String[] pathValues = null;

        if (JAX_RS_AVAILABLE) {
            Path pathAnnotation = getAnnotation(annotatedType, Path.class);
            if (pathAnnotation != null) {
                path = pathAnnotation.value();
            }
        }

        if (SPRING_MVC_AVAILABLE) {
            RequestMapping requestMappingAnnotation = getAnnotation(annotatedType, RequestMapping.class);
            if (requestMappingAnnotation != null) {
                String[] paths = requestMappingAnnotation.path();
                if (paths != null) {
                    if (paths.length == 1) {
                        path = paths[0];
                    } else if (paths.length > 1) {
                        if (annotatedType instanceof JMethod) {
                            logger.log(ERROR, "Invalid method. It is an error for a method to be annotated "
                                    + "with @RequestMapping and more than one paths specified: "
                                    + ((JMethod) annotatedType).getReadableDeclaration());
                        } else if (annotatedType instanceof JClassType) {
                            logger.log(ERROR, "Invalid method. It is an error for a class to be annotated "
                                    + "with @RequestMapping and more than one paths specified: "
                                    + ((JClassType) annotatedType).getName());
                        }
                        throw new UnableToCompleteException();
                    }
                }
            }
        }

        return path;
    }

    public static boolean isPathValue(Annotation annotation, Method method) {
        if (annotation.annotationType().getName().equals("javax.ws.rs.Path")) {
            return true;
        } else if (annotation.annotationType().getName().equals("org.springframework.web.bind.annotation.RequestMapping")
                && method.getName().equals("path")) {
            return true;
        }

        return false;
    }

    public static String getPathValue(HasAnnotations annotatedType) {
        try {
            return getPathValue(annotatedType, TreeLogger.NULL);
        } catch (UnableToCompleteException ex) {
            return null;
        }
    }

    public static String getParamPathValue(JParameter arg) {
        String paramPathValue = null;

        if (JAX_RS_AVAILABLE) {
            PathParam paramPath = getAnnotation(arg, PathParam.class);
            if (paramPath != null) {
                paramPathValue = paramPath.value();
            }
        }

        if (SPRING_MVC_AVAILABLE) {
            PathVariable pathVariable = getAnnotation(arg, PathVariable.class);
            if (pathVariable != null) {
                paramPathValue = pathVariable.value();
            }
        }

        return paramPathValue;
    }

    public static String getHeaderParamValue(JParameter arg) {
        String headerParamValue = null;

        if (JAX_RS_AVAILABLE) {
            HeaderParam headerParam = getAnnotation(arg, HeaderParam.class);
            if (headerParam != null) {
                headerParamValue = headerParam.value();
            }
        }

        if (SPRING_MVC_AVAILABLE) {
            RequestHeader requestHeader = getAnnotation(arg, RequestHeader.class);
            if (requestHeader != null) {
                headerParamValue = requestHeader.value();
            }
        }

        return headerParamValue;
    }

    public static String getFormParamValue(JParameter arg) {
        String formParamValue = null;

        if (JAX_RS_AVAILABLE) {
            FormParam formParam = getAnnotation(arg, FormParam.class);
            if (formParam != null) {
                formParamValue = formParam.value();
            }
        }

        if (SPRING_MVC_AVAILABLE) {
            RequestParam requestParam = getAnnotation(arg, RequestParam.class);
            if (requestParam != null) {
                formParamValue = requestParam.value();
            }
        }

        return formParamValue;
    }

    public static String getQueryParamValue(JParameter arg) {
        String queryParamValue = null;

        if (JAX_RS_AVAILABLE) {
            QueryParam queryParam = getAnnotation(arg, QueryParam.class);
            if (queryParam != null) {
                queryParamValue = queryParam.value();
            }
        }

        if (SPRING_MVC_AVAILABLE) {
            RequestParam requestParam = getAnnotation(arg, RequestParam.class);
            if (requestParam != null) {
                queryParamValue = requestParam.value();
            }
        }

        return queryParamValue;
    }

    public static String getProducesFirstValue(JMethod method) {
        String producesValue = null;

        if (JAX_RS_AVAILABLE) {
            Produces producesAnnotation = getAnnotation(method, Produces.class);
            if (producesAnnotation != null) {
                producesValue = producesAnnotation.value()[0];
            } else {
                producesAnnotation = getAnnotation(method.getEnclosingType(), Produces.class);
                if (producesAnnotation != null) {
                    producesValue = producesAnnotation.value()[0];
                }
            }
        }

        if (SPRING_MVC_AVAILABLE) {
            RequestMapping requestMapping = getAnnotation(method, RequestMapping.class);
            if (requestMapping != null && requestMapping.produces() != null && requestMapping.produces().length > 1) {
                producesValue = requestMapping.produces()[0];
            } else {
                requestMapping = getAnnotation(method.getEnclosingType(), RequestMapping.class);
                if (requestMapping != null && requestMapping.produces() != null && requestMapping.produces().length > 1) {
                    producesValue = requestMapping.produces()[0];
                }
            }
        }

        return producesValue;
    }

    public static String getConsumesFirstValue(JMethod method) {
        String consumesValue = null;

        if (JAX_RS_AVAILABLE) {
            Consumes consumesAnnotation = getAnnotation(method, Consumes.class);
            if (consumesAnnotation != null) {
                consumesValue = consumesAnnotation.value()[0];
            } else {
                consumesAnnotation = getAnnotation(method.getEnclosingType(), Consumes.class);
                if (consumesAnnotation != null) {
                    consumesValue = consumesAnnotation.value()[0];
                }
            }
        }

        if (SPRING_MVC_AVAILABLE) {
            RequestMapping requestMapping = getAnnotation(method, RequestMapping.class);
            if (requestMapping != null && requestMapping.produces() != null && requestMapping.produces().length > 1) {
                consumesValue = requestMapping.consumes()[0];
            } else {
                requestMapping = getAnnotation(method.getEnclosingType(), RequestMapping.class);
                if (requestMapping != null && requestMapping.produces() != null && requestMapping.produces().length > 1) {
                    consumesValue = requestMapping.consumes()[0];
                }
            }
        }

        return consumesValue;
    }
}
