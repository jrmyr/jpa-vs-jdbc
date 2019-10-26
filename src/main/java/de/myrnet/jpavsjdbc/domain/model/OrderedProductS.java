package de.myrnet.jpavsjdbc.domain.model;

import de.myrnet.jpavsjdbc.domain.DbBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="ordered_products")
@Getter
@Setter
public class OrderedProductS extends DbBase {

    @ManyToOne
    private OrderS order;

    @OneToOne
    private ProductS product;

    private BigDecimal appliedPrice;

}
