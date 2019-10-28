package de.myrnet.jpavsjdbc.domain.model;

import de.myrnet.jpavsjdbc.domain.DbBase;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
@Setter(value = AccessLevel.PACKAGE)
@NoArgsConstructor
public class OrderS extends DbBase {

    private LocalDateTime date;

    @OneToOne
    private ShopS shop;

    // "Code-only" relation
    @OneToMany
    @JoinColumn(name = "order_id")
    private List<OrderedProductS> orderedProducts;

    @OneToOne
    private InvoiceS invoice;

    public OrderS(LocalDateTime date, ShopS shop, InvoiceS invoice) {
        this.date = date;
        this.shop = shop;
        this.invoice = invoice;
    }
}
