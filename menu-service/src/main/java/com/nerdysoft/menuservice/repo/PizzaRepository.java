package com.nerdysoft.menuservice.repo;
import com.nerdysoft.menuservice.model.Pizza;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PizzaRepository extends ReactiveCassandraRepository<Pizza, UUID> {

}