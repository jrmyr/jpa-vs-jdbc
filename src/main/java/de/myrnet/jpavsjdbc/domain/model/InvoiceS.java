package de.myrnet.jpavsjdbc.domain.model;

import de.myrnet.jpavsjdbc.domain.DbBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="invoices")
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class InvoiceS extends DbBase {

    private String recipientName;

    private String recipientAddress;

    private String reference;

    private BigDecimal amountEur;

}
