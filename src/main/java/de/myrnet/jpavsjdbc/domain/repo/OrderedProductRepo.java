package de.myrnet.jpavsjdbc.domain.repo;

import de.myrnet.jpavsjdbc.domain.model.OrderedProductS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderedProductRepo extends JpaRepository<OrderedProductS, UUID>{
}
