package org.socialsignin.spring.data.dynamodb.domain.sample;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.domain.sample.CustomerDocument;
import org.socialsignin.spring.data.dynamodb.domain.sample.CustomerDocumentId;
import org.springframework.data.repository.CrudRepository;


public interface CustomerDocumentRepository extends CrudRepository<CustomerDocument, CustomerDocumentId> {

  List<CustomerDocument> findByCustomerDocumentKey(String customerDocumentKey);

  default List<CustomerDocument> findByCustomerDocumentKey(String customerId, String downloadType) {
    return findByCustomerDocumentKey(CustomerDocumentId.buildCustomerDocumentKey(customerId, downloadType));
  }


}