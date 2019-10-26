package de.myrnet.jpavsjdbc.domain.repo;

import de.myrnet.jpavsjdbc.domain.model.InvoiceS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvoiceRepo extends JpaRepository<InvoiceS, UUID>{
}
