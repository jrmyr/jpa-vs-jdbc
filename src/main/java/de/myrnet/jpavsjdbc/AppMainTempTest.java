package de.myrnet.jpavsjdbc;

import de.myrnet.jpavsjdbc.service.DbService;
import de.myrnet.jpavsjdbc.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppMainTempTest implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AppMain.class, args);
    }

    @Autowired
    private TestService testService;

    @Autowired
    private DbService dbService;

    @Override
    public void run(String... args) throws Exception {

//        dbService.createSimpleTestDataset();
//
//        tempService.testThings();

        dbService.createMassiveTestData();

        System.out.println("ENDE");
    }
}
