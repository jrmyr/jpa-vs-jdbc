package de.myrnet.jpavsjdbc.service;

import de.myrnet.jpavsjdbc.domain.repo.ProductRepo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor_ = {@Autowired})
public class TempService {

    private ProductRepo productRepo;

    public String getProductName(String uuidStr) {
        return productRepo.getOne(UUID.fromString(uuidStr)).getName();
    }

}
