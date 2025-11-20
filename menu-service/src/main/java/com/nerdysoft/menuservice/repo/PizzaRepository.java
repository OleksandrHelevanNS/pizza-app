package com.nerdysoft.menuservice.repo;
import com.nerdysoft.menuservice.model.Pizza;
import com.nerdysoft.menuservice.model.PizzaKey;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface PizzaRepository extends ReactiveCassandraRepository<Pizza, PizzaKey> {
    Flux<Pizza> findAllBy(Pageable pageable);
}
