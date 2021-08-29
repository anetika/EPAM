package edu.epam.handling.reader;

import edu.epam.handling.exception.CompositeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.stream.Stream;

public class CompositeReader {
    private static Logger logger = LogManager.getLogger();
    public String read(String filename) throws CompositeException {
        if (filename == null) {
            logger.error("File is null");
            throw new CompositeException("File is null");
        }

        File file = new File(filename);

        if (!file.exists()) {
            logger.error("File doesn't exist");
            throw new CompositeException("File doesn't exist");
        }

        if (!file.canRead()) {
            logger.error("File can't be read");
            throw new CompositeException("File can't be read");
        }

        if (file.length() == 0) {
            logger.error("File is empty");
            throw new CompositeException("File is empty");
        }

        StringBuilder builder = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Stream<String> lines = reader.lines();
            lines.forEach(s -> builder.append(s).append('\n'));
        } catch (FileNotFoundException e) {
            logger.error("File not found");
            throw new CompositeException("File not found");
        } catch (IOException e) {
            logger.error("Something is wrong with file");
            throw new CompositeException("Something is wrong with file");
        }
        builder.deleteCharAt(builder.length() - 1);
        logger.info("The whole text was read");
        return builder.toString();
    }
}
