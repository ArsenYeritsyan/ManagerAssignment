package com.cafe.managerassignment;

import com.cafe.managerassignment.model.Product;
import com.cafe.managerassignment.repo.ProductRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ManagerAssignmentApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        productRepository.save(new Product("Cola", "rtrtretert", 12.3456));
        productRepository.save(new Product("Pepsi", "ggfgdfgd", 23.4567));
        productRepository.save(new Product("Lemonad", "asdsdasd", 34.5678));
        productRepository.save(new Product("Bounty", "asdsa", 45.6789));
    }
}
