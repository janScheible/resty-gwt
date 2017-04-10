package org.fusesource.restygwt.springmvcdemo.twoprojectsonly.frontend.shared;

/**
 *
 * @author sj
 */
public class EntityDto {
    
    private String id;
    private String name;

    public EntityDto() {
    }
    
    public EntityDto(String id, String name) {
        this.id = id;
        this.name = name;
    }    

    public EntityDto(String name) {
        this.id = null;
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
