package com.DMS.vuln.repository;


import java.util.List;
import org.springframework.stereotype.Repository;

import com.DMS.vuln.dto.Document;
import com.DMS.vuln.dto.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@Repository
public class DocumentRepository {
    
     @PersistenceContext
    private  EntityManager entityManager;

    public DocumentRepository() {

    }
    
    @Transactional
    public int   createDocument(Document document) {

        //local file vault path
          
        System.out.println("Document name in repo..."+document.getLocalFilePath());

        // Code to create a new document
         String sql = "INSERT INTO DOCUMENT (Doc_Name, Doc_NUMBER, Revision,Description, DocumentAuthor,LocalFilePath,CreatedDate,ModifiedDate,Product_name) VALUES ('"
                + document.getDoc_Name() + "', ' "
                + document.getDoc_NUMBER() + "', '"
                + document.getRevision() + "', '"
                + document.getDescription() + "', '"
                + document.getDocumentAuthor() + "', '"
                + addExtraSlash(document.getLocalFilePath()) + "', '"
                + document.getCreatedDate() + "', '"
                + document.getModifiedDate() + "', '"
                + document.getProduct_name() + "')";
      
         return entityManager.createNativeQuery(sql).executeUpdate();
        
      

    }

    public String addExtraSlash(String path) {
        
        return path.replace("\\", "\\\\");
    }

    public void updateDocument(int id, String name, String content) {
        // Code to update an existing document
    }

    public void deleteDocument(int id) {
        // Code to delete a document
    }

    public String getDocument(int id) {
        // Code to retrieve a document by its ID
        return "Document content";
    }


    @Transactional
    public  List<Document>  getAllDocuments(String product) {
        
        
        List<Document> obj = entityManager
        .createNativeQuery(
            "SELECT * FROM DOCUMENT WHERE Product_name = :product",
            Document.class
        )
        .setParameter("product", product)
        .getResultList();


        return obj;

    }

        public Document getDocumentByReferenceID(int referenceID) {
            // TODO Auto-generated method stub
        
            
            String sql = "SELECT * FROM DOCUMENT WHERE ReferenceID = '" + referenceID + "'";
            return (Document) entityManager.createNativeQuery(sql, Document.class).getSingleResult();

    }

}