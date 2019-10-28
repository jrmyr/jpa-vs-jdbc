package de.myrnet.jpavsjdbc.domain.model;

import de.myrnet.jpavsjdbc.domain.DbBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="ordered_products")
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class OrderedProductS extends DbBase {

    @ManyToOne
    private OrderS order;

    @OneToOne
    private ProductS product;

    private BigDecimal appliedPrice;

}
