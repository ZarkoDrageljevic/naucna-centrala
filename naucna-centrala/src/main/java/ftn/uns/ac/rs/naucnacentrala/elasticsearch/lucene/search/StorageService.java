package ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.search;

import ftn.uns.ac.rs.naucnacentrala.configuration.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {

    @Autowired
    StorageProperties storageProperties;

    public Resource loadFileAsResource(String fileName) throws Exception {
        try {
            Path filePath = Paths.get(fileName);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new Exception("File not found " + fileName);
            }
        } catch (Exception ex) {
            throw new Exception("File not found " + fileName, ex);
        }
    }
}
