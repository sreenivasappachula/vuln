package com.DMS.vuln.dto;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "DOCUMENT")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ReferenceID ;

    @Column(name = "Doc_NUMBER")
    private String Doc_NUMBER ;
    @Column(name = "Doc_Name")
    private String Doc_Name ;
    @Column(name = "Revision")
    private String Revision ;
    @Column(name = "Description")
    private String Description ;
    @Column(name = "DocumentAuthor")
    private String DocumentAuthor ;
    @Column(name = "LocalFilePath")
    private String LocalFilePath ;
    @Column(name = "CreatedDate")
    private String CreatedDate ;
    @Column(name = "ModifiedDate")
    private String ModifiedDate ;
    @Column(name = "Product_name")
    private String Product_name ;
    public Document() {
        
    }   
    public Document(String doc_NUMBER, String doc_Name, String description, String documentAuthor,
            String localFilePath, String createdDate, String modifiedDate, String product_name) {
       
        this.Doc_NUMBER = doc_NUMBER;
        this.Doc_Name = doc_Name;
        this.Revision = "1.0";
        this.Description = description;
        this.DocumentAuthor = documentAuthor;
        this.LocalFilePath = localFilePath;
        this.CreatedDate = createdDate;
        this.ModifiedDate = modifiedDate;
        this.Product_name = product_name;
    }
    public int getReferenceID() {
        return ReferenceID;
    }
    public String getDoc_NUMBER() {
        return Doc_NUMBER;
    }
    public void setDoc_NUMBER(String doc_NUMBER) {
        Doc_NUMBER = doc_NUMBER;
    }   
    public String getDoc_Name() {
        return Doc_Name;
    }
    public void setDoc_Name(String doc_Name) {
        Doc_Name = doc_Name;
    }
    public String getRevision() {
        return Revision;
    }
    public void setRevision(String revision) {
        Revision = revision;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public String getDocumentAuthor() {
        return DocumentAuthor;
    }
    public void setDocumentAuthor(String documentAuthor) {
        DocumentAuthor = documentAuthor;
    }
    public String getLocalFilePath() {
        return LocalFilePath;
    }
    public void setLocalFilePath(String localFilePath) {
        LocalFilePath = localFilePath;
    }
    public String getCreatedDate() {
        return CreatedDate;
    }
    public void setCreatedDate(String now) {
        CreatedDate = now;
    }
    public String getModifiedDate() {
        return ModifiedDate;
    }
    public void setModifiedDate(String now) {
        ModifiedDate = now; }

    public String toString() {
        return "DOCUMENT [ReferenceID=" + ReferenceID + ", Doc_NUMBER=" + Doc_NUMBER + ", Doc_Name=" + Doc_Name + ", Revision=" + Revision
                + ", Description=" + Description + ", DocumentAuthor=" + DocumentAuthor + ", LocalFilePath="
                + LocalFilePath + ", CreatedDate=" + CreatedDate + ", ModifiedDate=" + ModifiedDate + "]";
    }
    public String getProduct_name() {
        return Product_name;
    }
    public void setProduct_name(String product_name) {
        Product_name = product_name;
    }
        
}
