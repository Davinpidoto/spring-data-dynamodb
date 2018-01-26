package org.socialsignin.spring.data.dynamodb.domain.sample;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.socialsignin.spring.data.dynamodb.utils.DynamoDBLocalResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DynamoDBLocalResource.class, CustomerDocumentTest.TestAppConfig.class})
public class CustomerDocumentTest {

    @Configuration
    @EnableDynamoDBRepositories(basePackages = "org.socialsignin.spring.data.dynamodb.domain.sample")
    public static class TestAppConfig {

    }

    @Before
    public void setUp() {
        DynamoDBLocalResource.createTable(ddb, CustomerDocument.class);
    }

    @Autowired
    private CustomerDocumentRepository customerDocumentRepository;
    @Autowired
    private AmazonDynamoDB ddb;

    @Test
    public void runTest() {

        customerDocumentRepository.findByCustomerDocumentKey("a", "b");
    }

}
