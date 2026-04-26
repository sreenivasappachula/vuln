package com.DMS.vuln.service;

import java.util.List;

import com.DMS.vuln.dto.Document;

public interface DocumentService {

        public Boolean   createDocument(Document document);
        public Boolean   updateDocument(int id, String name, String content);
        public Boolean   deleteDocument(int id);
        public String getDocument(int id);
        public List<Document> getAllDocuments(String product);
        public Document getDocumentByReferenceID(int referenceID);
        public Boolean fileUpload(String name, String number, String description, String vaultPath);
}
