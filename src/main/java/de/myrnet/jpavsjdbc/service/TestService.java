package de.myrnet.jpavsjdbc.service;

import de.myrnet.jpavsjdbc.domain.model.*;
import de.myrnet.jpavsjdbc.domain.repo.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor_ = {@Autowired})
public class TestService {

    private ProductRepo productRepo;
    private InvoiceRepo invoiceRepo;
    private OrderRepo orderRepo;
    private OrderedProductRepo orderedProductSRepo;
    private ShopRepo shopRepo;


    public String getProductName(String uuidStr) {
        return productRepo.getOne(UUID.fromString(uuidStr)).getName();
    }

    @SneakyThrows
    @Transactional
    public void testThings() {
        orderRepo.findAll().forEach(o -> System.out.println(o.getOrderedProducts()));
    }

}
