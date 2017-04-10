package org.fusesource.restygwt.springmvcdemo.twoprojectsonly.frontend.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.REST;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;
import org.fusesource.restygwt.springmvcdemo.twoprojectsonly.frontend.shared.DirectSpringMvcExampleService;
import org.fusesource.restygwt.springmvcdemo.twoprojectsonly.frontend.shared.EntityDto;

/**
 * @author scheible
 */
public class SpringMvcDemoModule implements EntryPoint {

    @Override
    public void onModuleLoad() {
        final DirectSpringMvcExampleService service = GWT.create(DirectSpringMvcExampleService.class);
        ((RestServiceProxy) service).setResource(new Resource(""));

        REST.withCallback(new MethodCallback<EntityDto>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert(exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, EntityDto entity) {
                Window.alert("entity name: " + entity.getName());
            }
        }).call(service).getExampleDtos("my-id");

        Button redButton = new Button("Red");
        redButton.setWidth("100px");
        redButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                EntityDto entity = new EntityDto("From Client");

                REST.withCallback(new MethodCallback<EntityDto>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        Window.alert("POST onFailure");
                    }

                    @Override
                    public void onSuccess(Method method, EntityDto entity) {
                        Window.alert("POST onSuccess (new id = '" + entity.getId() + "')");
                    }
                }).call(service).storeDto(entity);
            }
        });

        VerticalPanel panel = new VerticalPanel();
        panel.add(redButton);

        RootPanel.get("main").add(panel);
    }
}
