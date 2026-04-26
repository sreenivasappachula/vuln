package com.DMS.vuln.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import com.DMS.vuln.dto.Document;
import com.DMS.vuln.dto.Product;
import com.DMS.vuln.dto.User;
import com.DMS.vuln.dto.UserDTO;
import com.DMS.vuln.service.DocumentServiceImp;
import com.DMS.vuln.service.ProductServiceImp;
import com.DMS.vuln.service.UserServiceImp;


@Controller
public class DocumentController {
    
    private final DocumentServiceImp documentService;

    public DocumentController(DocumentServiceImp documentService) {
        this.documentService = documentService; }
    
          @GetMapping("/document-view")
          public String documentView(@RequestParam int ReferenceID, Model model) {
            
            System.out.println("ReferenceID: " + ReferenceID);
            //get the document details based on the reference id
            Document document = documentService.getDocumentByReferenceID(ReferenceID);
            model.addAttribute("document", document);

            return "documentView";
          }
            
        @GetMapping("/download/{id}")
        public ResponseEntity<Resource> downloadFile(@PathVariable int id) throws IOException {

                Document document = documentService.getDocumentByReferenceID(id);    

                System.out.println("Document details: " + document.getDoc_Name() + ", " + document.getLocalFilePath());

                if (document.getLocalFilePath() == null) {
                    throw new RuntimeException("file path is null");
                }

                Path path = Paths.get(document.getLocalFilePath());
        
                Resource resource = new UrlResource(path.toUri());

                if (!resource.exists()) {
                    throw new RuntimeException("File not found");
                }

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + path.getFileName().toString() + "\"")
                        .body(resource);
        }
}
