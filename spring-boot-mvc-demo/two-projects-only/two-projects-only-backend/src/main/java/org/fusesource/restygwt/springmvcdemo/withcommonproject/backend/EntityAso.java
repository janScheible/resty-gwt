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
