package com.DMS.vuln.dto;

public class DocumentDTO {

    private String docNumber;
    private String docName;
    private String description;
    private String productName;

    public DocumentDTO() {}

    public DocumentDTO(String docNumber, String docName, String description) {
        this.docNumber = docNumber;
        this.docName = docName;
        this.description = description;
        this.productName = productName;
    }

    public String getDocNumber() { return docNumber; }
    public void setDocNumber(String docNumber) { this.docNumber = docNumber; }

    public String getDocName() { return docName; }
    public void setDocName(String docName) { this.docName = docName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }


    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

}