package de.myrnet.jpavsjdbc.web;

import de.myrnet.jpavsjdbc.service.DbService;
import de.myrnet.jpavsjdbc.service.TestService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor_ = {@Autowired})
public class TempController {

    private DbService dbService;
    private TestService testService;

    @GetMapping(path="temp/{uuid}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getProduct(@PathVariable("uuid") String uuidStr) {
        return ResponseEntity.ok("Product name: " + testService.getProductName(uuidStr));
    }

    //Should be POST!!
    @GetMapping(path="addTestData/{count}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> addTestData(@PathVariable("count") int count) {

        dbService.createSimpleTestDataset();

        return ResponseEntity.ok("Seems to have worked");
    }

}
