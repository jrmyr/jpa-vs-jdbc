package de.myrnet.jpavsjdbc.service;

import de.myrnet.jpavsjdbc.domain.model.*;
import de.myrnet.jpavsjdbc.domain.repo.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor_ = {@Autowired})
public class DbService {

    private ProductRepo productRepo;
    private InvoiceRepo invoiceRepo;
    private OrderRepo orderRepo;
    private OrderedProductRepo orderedProductSRepo;
    private ShopRepo shopRepo;

    public boolean createSimpleTestDataset() {
        try {
            doCreateSimpleTestDataset();
        } catch (Exception ex) {
            System.out.println("Creation failed/skipped: " + ex.getLocalizedMessage());
            return false;
        }
        return true;
    }

    @Transactional
    @SneakyThrows
    protected void doCreateSimpleTestDataset() {
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

    @Transactional
    public void createMassiveTestData() {
        ShopS shop = createAndSaveShop();

        createAndSaveOrders(shop, 50);
        createAndSaveOrders(shop, 180);
        createAndSaveOrders(shop, 2000);
    }

    @SneakyThrows
    private ShopS createAndSaveShop() {
        String name = RandomStringUtils.randomAlphanumeric(20);
        String urlStr = "http://" + name.replace(" ", "-") + ".com";
        ShopS shop = new ShopS(name, new URL(urlStr));
        shopRepo.save(shop);
        return shop;
    }

    private Triple<OrderS, InvoiceS, List<OrderedProductS>> createAndSaveOrders(ShopS shop, int orderedProductsCount) {
        InvoiceS invoice = invoiceRepo.save(createInvoice());
        OrderS order = orderRepo.save(new OrderS(LocalDateTime.now(), shop, invoice));
        List<OrderedProductS> orderedProducts = createOrderedProductList(order, orderedProductsCount);
        orderedProducts.forEach(op -> {
            productRepo.save(op.getProduct());
            orderedProductSRepo.save(op);
        });
        return Triple.of(order, invoice, orderedProducts);
    }

    private List<OrderedProductS> createOrderedProductList(OrderS order, int count) {
        if (count < 1) {
            throw new IllegalArgumentException(
                    "Cannot create a products list with " + count + " elements");
        }
        return IntStream.range(0, count).mapToObj(i -> {
            ProductS product = createProduct();
            return new OrderedProductS(order, product, product.getDefaultPrice());
        }).collect(Collectors.toList());
    }

    private ProductS createProduct() {
        String name = getArbitraryName();
        String desc = getArbitraryDescription(name);
        BigDecimal price = new BigDecimal((Math.random() * 100.0) + "");
        return new ProductS(name, desc, price);
    }

    private String getArbitraryName() {
        int length = Math.max(4, (int) (Math.random() * 10.0));
        return RandomStringUtils.randomAlphanumeric(length);
    }

    private String getArbitraryDescription(String name) {
        return name + ":::" + RandomStringUtils.randomAlphabetic(1000);
    }

    private InvoiceS createInvoice() {
        String number = ((int) (Math.random() * 1000000.0)) + "";
        return new InvoiceS(
                "NAME_" + number,
                "ADDRESS__" + number,
                RandomStringUtils.randomAlphabetic(10),
                new BigDecimal((Math.random() * 1000.0) + ""));
    }

}
