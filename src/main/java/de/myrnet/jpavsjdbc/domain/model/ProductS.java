package de.myrnet.jpavsjdbc.domain.model;

import de.myrnet.jpavsjdbc.domain.DbBase;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="products")
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class ProductS extends DbBase {

    private String name;

    private String description;

    private BigDecimal defaultPrice;

}
