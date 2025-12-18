    package com.nerdysoft.orderservice.model;

    import jakarta.persistence.*;
    import lombok.*;
    import org.hibernate.annotations.UuidGenerator;

    import java.math.BigDecimal;
    import java.util.Set;
    import java.util.UUID;

    @Entity
    @Table(name = "orders")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Order {

        @Id
        @GeneratedValue
        @UuidGenerator
        private UUID id;

        @Column(name = "pizza_name", nullable = false)
        private String pizzaName;

        @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
        private BigDecimal totalPrice;

        @Column(name = "pizza_size", nullable = false)
        private Integer pizzaSize;

        @Column(nullable = false)
        private Integer amount;

        @ManyToMany
        @JoinTable(
                name = "order_ingredients",
                joinColumns = @JoinColumn(name = "order_id"),
                inverseJoinColumns = @JoinColumn(name = "ingredient_id")
        )
        private Set<Ingredient> ingredients;

        @Column(name = "need_delivery")
        private Boolean needDelivery;

        @Column
        private String phoneNumber;

        @Enumerated(EnumType.STRING)
        @Column(name = "order_status", nullable = false)
        private OrderStaus orderStaus;
    }
