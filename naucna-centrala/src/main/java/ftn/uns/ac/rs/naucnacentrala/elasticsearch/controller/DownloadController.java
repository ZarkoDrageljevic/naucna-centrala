package ftn.uns.ac.rs.naucnacentrala.elasticsearch.controller;

import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.search.StorageService;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.model.DownloadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController(value = "/download")
public class DownloadController {

    @Autowired
    private StorageService storageService;

    @PostMapping
    public ResponseEntity<Resource> downloadFile(@RequestBody DownloadFile downloadFile, HttpServletRequest request) throws Exception {
        Resource resource = storageService.loadFileAsResource(downloadFile.getFileName());
        String contentType;
        contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        System.out.println("OVDE SAM");
        contentType = (contentType == null) ? contentType : "application/octet-stream";
        System.out.println("OVDE SAM");

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
