package org.fusesource.restygwt.springmvcdemo.withcommonproject.frontend.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.List;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.REST;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;
import org.fusesource.restygwt.springmvcdemo.withcommonproject.common.shared.DirectSpringMvcExampleService;
import org.fusesource.restygwt.springmvcdemo.withcommonproject.common.shared.ExampleDto;

/**
 * @author scheible
 */
public class SpringMvcDemoModule implements EntryPoint {

    @Override
    public void onModuleLoad() {
        final DirectSpringMvcExampleService service = GWT.create(DirectSpringMvcExampleService.class);
        ((RestServiceProxy) service).setResource(new Resource(""));

        REST.withCallback(new MethodCallback<List<ExampleDto>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert(exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<ExampleDto> dtos) {
                Window.alert("number of dtos: " + dtos.get(0).getName());
            }
        }).call(service).getExampleDtos("my-id");

        Button redButton = new Button("Red");
        redButton.setWidth("100px");
        redButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                ExampleDto dto = new ExampleDto();
                dto.setName("posty");

                REST.withCallback(new MethodCallback<Void>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        Window.alert("POST onFailure");
                    }

                    @Override
                    public void onSuccess(Method method, Void response) {
                        Window.alert("POST onSuccess");
                    }
                }).call(service).storeDto(dto);
            }
        });

        VerticalPanel panel = new VerticalPanel();
        panel.add(redButton);

        RootPanel.get("main").add(panel);
    }
}
