package de.myrnet.jpavsjdbc.web;

import de.myrnet.jpavsjdbc.service.TempService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor_ = {@Autowired})
public class TempController {

    private TempService tempService;

    @GetMapping(path="temp/{uuid}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getProduct(@PathVariable("uuid") String uuidStr) {
        return ResponseEntity.ok("Product name: " + tempService.getProductName(uuidStr));
    }

}
