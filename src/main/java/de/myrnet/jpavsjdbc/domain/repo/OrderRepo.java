package de.myrnet.jpavsjdbc.domain.repo;

import de.myrnet.jpavsjdbc.domain.model.OrderS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepo extends JpaRepository<OrderS, UUID>{
}
