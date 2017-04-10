package org.fusesource.restygwt.springmvcdemo.withcommonproject.backend;

import com.google.auto.value.AutoValue;
import java.util.Optional;

/**
 *
 * @author sj
 */
@AutoValue
public abstract class EntityAso {

    public static EntityAso create(String name) {
        return new AutoValue_EntityAso(Optional.empty(), name);
    }

    public static EntityAso create(String id, String name) {
        return new AutoValue_EntityAso(Optional.of(id), name);
    }

    public abstract Optional<String> getId();

    public abstract String getName();
}
