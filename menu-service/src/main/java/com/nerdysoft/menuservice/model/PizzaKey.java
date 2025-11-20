package com.nerdysoft.menuservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@PrimaryKeyClass
public class PizzaKey {

    @PrimaryKeyColumn(name = "type", type = PrimaryKeyType.PARTITIONED)
    private PizzaType type;

    @PrimaryKeyColumn(name = "name", type = PrimaryKeyType.CLUSTERED)
    private String name;
}
