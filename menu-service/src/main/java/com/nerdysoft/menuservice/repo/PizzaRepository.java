package com.nerdysoft.menuservice.repo;

import com.nerdysoft.menuservice.model.Pizza;
import com.nerdysoft.menuservice.model.PizzaKey;
import com.nerdysoft.menuservice.model.PizzaType;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface PizzaRepository extends ReactiveCassandraRepository<Pizza, PizzaKey> {
    Flux<Pizza> findAllByKeyType(PizzaType type);
    Mono<Pizza> findByKeyTypeAndKeyName(PizzaType type, String name);
    Mono<Boolean> existsByKeyTypeAndKeyName(@NotNull PizzaType type, @NotNull String name);
}

