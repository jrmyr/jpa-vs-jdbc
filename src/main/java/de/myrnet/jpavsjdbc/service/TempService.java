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
public class TempService {

    private ProductRepo productRepo;
    private InvoiceRepo invoiceRepo;
    private OrderRepo orderRepo;
    private OrderedProductRepo orderedProductSRepo;
    private ShopRepo shopRepo;


    public String getProductName(String uuidStr) {
        return productRepo.getOne(UUID.fromString(uuidStr)).getName();
    }

    public boolean createTestDataset() {
        try {
            doCreateTestDataset();
        } catch (Exception ex) {
            System.out.println("Creation failed/skipped: " + ex.getLocalizedMessage());
            return false;
        }
        return true;
    }

    @Transactional
    @SneakyThrows
    protected void doCreateTestDataset() {

        ShopS shop = new ShopS("Footie's", new URL("http://www.footies.com"));
        shopRepo.save(shop);

        ProductS ball = new ProductS("Ball", "A real leather football", new BigDecimal("29.99"));
        ProductS goals = new ProductS("Goal set", "2 set of 2 little football goals", new BigDecimal("35.49"));
        productRepo.saveAll(List.of(ball, goals));

        InvoiceS invoice = new InvoiceS("Hans Wurst", "Kirchplatz 6", "co-142412asa", new BigDecimal("30.00"));
        invoiceRepo.save(invoice);

        OrderS order = new OrderS(LocalDateTime.now(), shop, invoice);
        orderRepo.save(order);

        OrderedProductS oBall = new OrderedProductS(order, ball, new BigDecimal("10"));
        OrderedProductS oGoals = new OrderedProductS(order, goals, new BigDecimal("20"));
        orderedProductSRepo.saveAll(List.of(oBall, oGoals));

    }

    @SneakyThrows
    @Transactional
    public void testThings() {
        orderRepo.findAll().forEach(o -> System.out.println(o.getOrderedProducts()));
    }

}
