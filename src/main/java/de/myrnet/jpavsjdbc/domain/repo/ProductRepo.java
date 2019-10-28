package de.myrnet.jpavsjdbc.domain.repo;

import de.myrnet.jpavsjdbc.domain.model.ProductS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepo extends JpaRepository<ProductS, UUID>{

}
