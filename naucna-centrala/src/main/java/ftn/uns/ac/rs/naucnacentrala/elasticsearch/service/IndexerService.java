package ftn.uns.ac.rs.naucnacentrala.elasticsearch.service;


import ftn.uns.ac.rs.naucnacentrala.configuration.StorageProperties;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.indexing.handlers.DocumentHandler;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.indexing.handlers.PDFHandler;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.model.IndexUnit;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.model.UploadModel;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.repository.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class IndexerService {

    @Value("${dataDir}")
    private static String DATA_DIR_PATH;

    private StorageProperties storageProperties;
    private PaperRepository paperRepository;
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    public IndexerService(PaperRepository paperRepository, StorageProperties storageProperties,
                          ElasticsearchTemplate elasticsearchTemplate) {
        this.paperRepository = paperRepository;
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.storageProperties = storageProperties;
    }

    public void indexUploadedFile(UploadModel model) throws IOException {
        for (MultipartFile file : model.getFiles()) {
            if (file.isEmpty()) {
                continue; //next please
            }
            String fileName = saveUploadedFile(file);
            if (fileName != null) {
                IndexUnit indexUnit = getHandler(fileName).getIndexUnit(new File(fileName));
                indexUnit.setKeywords(model.getKeywords());
                indexUnit.setAuthorName(model.getAuthorName());
                indexUnit.setAuthorLastName(model.getAuthorLastName());
                indexUnit.setMagazine(model.getMagazine());
                indexUnit.setScienceField(model.getScientificField());
                indexUnit.setFilename(fileName);
                indexUnit.setApstract(model.getApstrakt());
                add(indexUnit);
            }
        }
    }

    //save file

    private String saveUploadedFile(MultipartFile file) throws IOException {
        String retVal = null;
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(storageProperties.getLocation() + File.separator + file.getOriginalFilename());
            Files.write(path, bytes);
            retVal = path.toString();
        }
        return retVal;
    }


    public boolean delete(String filename) {
        paperRepository.deleteAll();
        return true;
    }

    public DocumentHandler getHandler(String fileName) {
        if (fileName.endsWith(".pdf")) {
            return new PDFHandler();
        } else {
            return null;
        }
    }

    public boolean update(IndexUnit unit) {
        unit = paperRepository.save(unit);
        return unit != null;
    }

    public boolean add(IndexUnit unit) {
        unit = paperRepository.index(unit);
        return unit != null;
    }

    private File getResourceFilePath(String path) {
        URL url = this.getClass().getClassLoader().getResource(path);
        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        }
        return file;
    }
    /*public int index(File file) {
        DocumentHandler handler = null;
        String fileName = null;
        int retVal = 0;
        try {
            File[] files;
            if (file.isDirectory()) {
                files = file.listFiles();
            } else {
                files = new File[1];
                files[0] = file;
            }
            for (File newFile : files) {
                if (newFile.isFile()) {
                    fileName = newFile.getName();
                    handler = getHandler(fileName);
                    if (handler == null) {
                        System.out.println("Nije moguce indeksirati dokument sa nazivom: " + fileName);
                        continue;
                    }
                    IndexUnit unit = handler.getIndexUnit(newFile);
                    unit.setAuthorName("Pera");
                    unit.setAuthorLastName("Peric");
                    unit.setMagazine("Casopis1");
                    unit.setScienceField("Naucna Oblast 1");
                    unit.setFilename(fileName);
                    if (add(unit))
                        retVal++;
                } else if (newFile.isDirectory()) {
                    retVal += index(newFile);
                }
            }
            System.out.println("indexing done");
        } catch (Exception e) {
            System.out.println("indexing NOT done");
        }
        return retVal;
    }*/


}