package edu.epam.arrays.service.impl;

import edu.epam.arrays.entity.CustomArray;
import edu.epam.arrays.exception.ArrayException;
import edu.epam.arrays.service.SearchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;


public class SearchServiceImpl implements SearchService {
    static Logger logger = LogManager.getLogger();
    @Override
    public int findMin(CustomArray array) throws ArrayException {
        if (array == null){
            logger.error("Array is null");
            throw new ArrayException("Array is null");
        }
        if (array.getLength() == 0){
            logger.error("Array is empty");
            throw new ArrayException("Array is empty");
        }
        int min = array.getElementByIndex(0);
        for (int i = 1; i < array.getLength(); i++) {
            if (min > array.getElementByIndex(i)) {
                min = array.getElementByIndex(i);
            }
        }
        logger.info("Minimal element: " + min);
        return min;
    }
    @Override
    public int findMinStream(CustomArray array) throws ArrayException {
        if (array == null){
            logger.error("Array is null");
            throw new ArrayException("Array is null");
        }
        if (array.getLength() == 0){
            logger.error("Array is empty");
            throw new ArrayException("Array is empty");
        }
        int min = Arrays.stream(array.getArray()).min().getAsInt();
        logger.info("Minimal element: " + min);
        return min;
    }
    @Override
    public int findMax(CustomArray array) throws ArrayException {
        if (array == null){
            logger.error("Array is null");
            throw new ArrayException("Array is null");
        }
        if (array.getLength() == 0){
            logger.error("Array is empty");
            throw new ArrayException("Array is empty");
        }
        int max = array.getElementByIndex(0);
        for (int i = 1; i < array.getLength(); i++){
             if(max < array.getElementByIndex(i)){
                max = array.getElementByIndex(i);
             }
        }
        logger.info("Maximal element: " + max);
        return max;
    }
    @Override
    public int findMaxStream(CustomArray array) throws ArrayException {
        if (array == null){
            logger.error("Array is null");
            throw new ArrayException("Array is null");
        }
        if (array.getLength() == 0){
            logger.error("Array is empty");
            throw new ArrayException("Array is empty");
        }
        int max = Arrays.stream(array.getArray()).max().getAsInt();
        logger.info("Maximal element: " + max);
        return max;
    }


}
