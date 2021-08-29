package edu.epam.shapes.reader;

import edu.epam.shapes.exception.ShapeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ShapeReader {
    private static final Logger logger = LogManager.getLogger();
    public List<String> readStrings(String path) throws ShapeException {
        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            List<String> list = reader.lines().collect(Collectors.toList());
            logger.info("All strings were read");
            return list;
        } catch (FileNotFoundException e){
            logger.error("File not found");
            throw new ShapeException("File not found");
        }catch (IOException e){
            logger.error("Something is wrong with file");
            throw new ShapeException("Something is wrong with file");
        }
    }
}
