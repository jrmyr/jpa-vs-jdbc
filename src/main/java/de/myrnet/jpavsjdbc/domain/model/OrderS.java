package de.myrnet.jpavsjdbc.domain.model;

import de.myrnet.jpavsjdbc.domain.DbBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
@Setter
public class OrderS extends DbBase {

    private LocalDateTime date;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<OrderedProductS> orderedProducts;

    @OneToOne
    private InvoiceS invoice;

}
