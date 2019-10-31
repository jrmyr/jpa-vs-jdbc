package de.myrnet.jpavsjdbc;

import de.myrnet.jpavsjdbc.service.DbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class AppMain implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AppMain.class, args);
    }

    @Autowired
    private DbService dbService;

    @Override
    public void run(String... args) throws Exception {

//        dbService.createSimpleTestDataset();

        String exampleShopName = "buymesomething";

        try {
            dbService.createMassiveTestData(exampleShopName);
        } catch (Exception ex) {
            log.info("Data not created - might be there already");
        }

        long sortTimeJpa = dbService.analyseDescriptionsJpa(exampleShopName);
        log.info("Sorting JPA lasted " + sortTimeJpa + "ms");

        long sortTimeJdbc = dbService.analyseDescriptionsJdbc(exampleShopName);
        log.info("Sorting JDBC lasted " + sortTimeJdbc + "ms");

    }

}
