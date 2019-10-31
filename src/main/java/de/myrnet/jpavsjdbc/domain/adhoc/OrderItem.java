package de.myrnet.jpavsjdbc.domain.adhoc;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderItem {

    private String shopName;
    private LocalDateTime orderDate;
    private String productName;
    private String productDescription;
    private BigDecimal appliedPrice;

}
