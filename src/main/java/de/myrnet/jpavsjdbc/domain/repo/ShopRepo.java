package de.myrnet.jpavsjdbc.domain.repo;

import de.myrnet.jpavsjdbc.domain.model.ShopS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShopRepo extends JpaRepository<ShopS, UUID>{

    ShopS findByName(String name);

}
