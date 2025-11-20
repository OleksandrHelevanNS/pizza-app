package com.nerdysoft.menuservice.model;

import jakarta.validation.constraints.DecimalMax;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.util.Set;

@Table("pizzas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Pizza {

    @PrimaryKey
    private PizzaKey key;

    @Column("price")
    private BigDecimal price;

    @Column("sizes")
    private Set<Integer> sizes;

    @Column("ingredients")
    private Set<String> ingredients;

    @Column("rating")
    @DecimalMax("10")
    private BigDecimal rating;

    @Column("image")
    private String image;
}
