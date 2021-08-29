package edu.epam.arrays.reader;

import edu.epam.arrays.exception.ArrayException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomArrayReader {
    static Logger logger = LogManager.getLogger();
    public List<String> readArray(String path) throws ArrayException {
        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            Stream<String> lines = reader.lines();
            List<String> list = lines.collect(Collectors.toList());
            logger.info("File was read");
            return list;
        } catch (FileNotFoundException e){
            logger.error("File not found: " + path);
            throw new ArrayException("File not found");
        }catch (IOException e){
            logger.error("Something is wrong with file: " + path);
            throw new ArrayException("Something is wrong with file");
        }
    }
}
