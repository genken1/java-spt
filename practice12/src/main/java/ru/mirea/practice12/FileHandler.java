package ru.mirea.practice12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;

@Service
public class FileHandler {
    private final FileHelper fileHelper = new FileHelper();
    private final Logger logger = LoggerFactory.getLogger(FileHandler.class);
    private FileInfo fileInfo;
    private File file;


    @PostConstruct
    private void init() {
        fileInfo.setInputName("file.txt");
        fileInfo.setOutputName("output.txt");
        String inputFilename = fileInfo.getInputName();
        String outputFilename = fileInfo.getOutputName();

        try {
            file = new File(inputFilename);
            if (!file.exists()) {
                fileHelper.writeToFile(outputFilename, "null");
                return;
            }

            fileHelper.writeToFile(outputFilename, fileHelper.getMessageDigest(fileHelper.readFromFile(inputFilename)));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @PreDestroy
    private void destroy() {
        logger.info("Destroying...");
        if (file.exists()) {
            logger.info("File {} deleted: {}", file.getName(), file.delete());
        } else {
            logger.info("File {} does not exist", file.getName());
        }
    }

    @Autowired
    public void setFileInfo(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }
}
