package de.myrnet.jpavsjdbc.domain.model;

import de.myrnet.jpavsjdbc.domain.DbBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.net.URL;
import java.util.List;

@Entity
@Table(name="shops")
@Getter
@Setter
public class ShopS extends DbBase {

    private String name;

    private URL webAddress;

    @OneToMany
    @JoinColumn(name = "id")
    private List<OrderS> orders;

}
