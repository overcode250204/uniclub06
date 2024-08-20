package com.cybersoft.uniclub06.service.imp;

import com.cybersoft.uniclub06.exception.SaveFileException;
import com.cybersoft.uniclub06.service.FilesStorageService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;
@Service
public class FilesStorageServiceImpl implements FilesStorageService {
    @Value("${root.path}")
    private String root;


    @Override
    public void init() {

    }

    @Override
    public void save(MultipartFile file) {
        Path path = Paths.get(root);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                Files.copy(file.getInputStream(), path.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new SaveFileException(e.getMessage());
            }

        }

    }

    @Override
    public UrlResource load(String filename) {
        return null;
    }


    @Override
    public void deleteAll() {

    }

    @Override
    public Stream<Path> loadAll() {
        return Stream.empty();
    }


}
