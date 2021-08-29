package edu.epam;

import com.google.gson.Gson;
import edu.epam.entity.Truck;
import edu.epam.exception.ThreadException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static final Logger logger = LogManager.getLogger();
    private static final String FILE_NAME = "src/resources/trucks.json";

    public static void main(String[] args) throws ThreadException {
        Gson gson = new Gson();
        try {
            Truck[] trucks = gson.fromJson(new FileReader(FILE_NAME), Truck[].class);
            ExecutorService service = Executors.newFixedThreadPool(trucks.length);
            Arrays.stream(trucks).forEach(service::execute);
            service.shutdown();
        } catch (FileNotFoundException e) {
            logger.error("File not found");
            throw new ThreadException("File not found");
        }
    }
}
