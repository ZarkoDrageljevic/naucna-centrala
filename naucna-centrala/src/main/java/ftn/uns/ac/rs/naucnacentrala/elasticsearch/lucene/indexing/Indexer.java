package ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.indexing;


import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.indexing.handlers.DocumentHandler;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.indexing.handlers.PDFHandler;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.lucene.model.IndexUnit;
import ftn.uns.ac.rs.naucnacentrala.elasticsearch.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class Indexer {

    @Autowired
    private BookRepository repository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public Indexer() {
    }


    public boolean delete(String filename) {
        repository.deleteAll();
        return true;
//        if (repository.equals(filename)) {
//            repository.delete(filename);
//            return true;
//        } else
//            return false;

    }

    public boolean update(IndexUnit unit) {
        unit = repository.save(unit);
        return unit != null;
    }

    public boolean add(IndexUnit unit) {

        unit = repository.index(unit);
        return unit != null;
    }

    /**
     * @param file Direktorijum u kojem se nalaze dokumenti koje treba indeksirati
     */
    public int index(File file) {
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
                    if (add(handler.getIndexUnit(newFile)))
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
    }


    public DocumentHandler getHandler(String fileName) {
        if (fileName.endsWith(".pdf")) {
            return new PDFHandler();
        } else {
            return null;
        }
    }

}