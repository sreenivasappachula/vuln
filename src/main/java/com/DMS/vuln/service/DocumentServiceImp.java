package com.DMS.vuln.service;

import org.springframework.stereotype.Service;

import com.DMS.vuln.dto.Document;
import com.DMS.vuln.repository.DocumentRepository;
import java.util.List;

@Service
public class DocumentServiceImp implements DocumentService {

    private DocumentRepository documentRepository;

    private DocumentServiceImp(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }
    
    @Override
    public Boolean createDocument(Document document) {
      
            if(documentRepository.createDocument(document) > 0) {
                return true;
            }
            return false;   
    }

    
    @Override
    public Boolean updateDocument(int id, String name, String content) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean deleteDocument(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDocument(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Document> getAllDocuments(String product) {
        // TODO Auto-generated method stub

        return documentRepository.getAllDocuments(product);
    }

    @Override
    public Boolean fileUpload(String name, String number, String description, String vaultPath) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Document getDocumentByReferenceID(int referenceID) {
        // TODO Auto-generated method stub
        return documentRepository.getDocumentByReferenceID(referenceID);
    }
    
}
