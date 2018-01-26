package org.socialsignin.spring.data.dynamodb.domain.sample;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomerDocumentId implements Serializable {

  private static final long serialVersionUID = 1L;

  private static final Joiner PIPE_JOINER = Joiner.on("|").skipNulls();
  private static final Splitter PIPE_SPLITTER = Splitter.on('|').trimResults().omitEmptyStrings();

  private String customerId;
  private String documentType;

  public CustomerDocumentId() {

  }

  public CustomerDocumentId(String customerId, String documentType, String version) {
    this.customerId = customerId;
    this.documentType = documentType;
    this.version = version;
  }

  @DynamoDBRangeKey
  private String version;

  @DynamoDBHashKey(attributeName = "customerId|documentType")
  public String getCustomerDocumentKey() {
    return buildCustomerDocumentKey(customerId, documentType);
  }

  public void setCustomerDocumentKey(String customerDocumentKey) {

    List<String> keyPartList = new ArrayList<>();
    PIPE_SPLITTER.split(customerDocumentKey).forEach(keyPartList::add);

    if (keyPartList.size() != 2) {
      throw new IllegalStateException(String.format("An CustomerDocumentId was found to have the following mal-formed key: '%s'.", customerDocumentKey));
    }

    this.customerId = keyPartList.get(0);
    this.documentType = keyPartList.get(1);

  }

  static String buildCustomerDocumentKey(String customerId, String documentType) {
    return PIPE_JOINER.join(customerId, documentType);
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getDocumentType() {
    return documentType;
  }

  public void setDocumentType(String documentType) {
    this.documentType = documentType;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }
}