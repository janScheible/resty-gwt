Purpose of this fork
--------------------

The purpose of this fork is to allow usage of Spring MVC annotations with RestyGWT instead of JAX-RS annotations.
It is targeted to a Spring only environment where GWT is used *just* as the frontend.
The backend is pure Spring MVC without any GWT dependencies (see the sample projects [with-common-project](https://github.com/janScheible/resty-gwt-with-spring-mvc/tree/master/spring-boot-mvc-demo/with-common-project)  and [two-projects-only](https://github.com/janScheible/resty-gwt-with-spring-mvc/tree/master/spring-boot-mvc-demo/two-projects-only)).

```java
public interface DirectSpringMvcExampleService extends DirectRestService {

    @RequestMapping(path = "/entities/{id}", method = GET)
    EntityDto getExampleDtos(@PathVariable("id") String id);

    @RequestMapping(path = "/entities", method = POST, consumes = {"application/json"})
    EntityDto storeDto(EntityDto entity);
}
```

<dl>
  <dt>NOTE</dt>
  <dd>It looks like Spring MVC annotations on method arguments must be placed both on the interface and on the implementation class (on the interface for RestyGWT and on the implementation for Spring itself).</dd>
</dl>

![RestyGWT](http://resty-gwt.github.io/images/restygwt-logo.png)
==============

* [![Build Status](https://secure.travis-ci.org/resty-gwt/resty-gwt.png)](http://travis-ci.org/resty-gwt/resty-gwt)

To know more have a look to [RestyGWT web site](http://resty-gwt.github.io/)

Description
-----------

RestyGWT is a GWT generator for REST services and JSON encoded data transfer objects.

Features
--------

* Generates Async Restful JSON based service proxies
* Java Object to JSON encoding/decoding
* Easy to use REST API


