package ftn.uns.ac.rs.naucnacentrala.elasticsearch.controller;

import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.model.UploadModel;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.service.IndexerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class IndexerController {

    @Value("${dataDir}")
    private static String DATA_DIR_PATH;

    @Autowired
    private IndexerService indexerService;

    @PostMapping("/index/add")
        public ResponseEntity<String> multiUploadFileModel(@ModelAttribute UploadModel model) {
        try {
            System.out.println("OVDI SAM");
            indexerService.indexUploadedFile(model);
        } catch (IOException e) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Successfully uploaded!", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody String filename) {
        indexerService.delete(filename);
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    @GetMapping("/reindex")
//    public ResponseEntity<String> index() throws IOException {
//        File dataDir = getResourceFilePath(DATA_DIR_PATH);
//        long start = new Date().getTime();
//        int numIndexed = indexerService.index(dataDir);
//        long end = new Date().getTime();
//        String text = "Indexing " + numIndexed + " files took "
//                + (end - start) + " milliseconds";
//        return new ResponseEntity<String>(text, HttpStatus.OK);
//    }

}
