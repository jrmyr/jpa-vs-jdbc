package de.myrnet.jpavsjdbc;

import de.myrnet.jpavsjdbc.service.TempService;
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
    private TempService tempService;

    @Override
    public void run(String... args) throws Exception {

        tempService.createTestDataset();

        tempService.testThings();

        System.out.println("ENDE");
    }
}
