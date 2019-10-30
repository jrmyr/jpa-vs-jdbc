package de.myrnet.jpavsjdbc.domain.model;

import de.myrnet.jpavsjdbc.domain.DbBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.net.URL;
import java.util.List;

@Entity
@Table(name="shops")
@Getter
@Setter
@NoArgsConstructor
public class ShopS extends DbBase {

    private String name;

    private URL webAddress;

    // "Code-only" relation
    @OneToMany(mappedBy = "shop")
    private List<OrderS> orders;

    public ShopS(String name, URL webAddress) {
        this.name = name;
        this.webAddress = webAddress;
    }
}
