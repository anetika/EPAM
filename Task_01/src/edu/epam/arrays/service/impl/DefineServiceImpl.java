package edu.epam.arrays.service.impl;

import edu.epam.arrays.entity.CustomArray;
import edu.epam.arrays.exception.ArrayException;
import edu.epam.arrays.service.DefineService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.stream.IntStream;


public class DefineServiceImpl implements DefineService {
    static Logger logger = LogManager.getLogger();
    @Override
    public int findSum(CustomArray array) throws ArrayException {
        if (array == null){
            logger.error("Array is null");
            throw new ArrayException("Array is null");
        }
        if (array.getLength() == 0){
            logger.error("Array is empty");
            throw new ArrayException("Array is empty");
        }
        int sum = 0;
        for(int i = 0; i < array.getLength(); i++){
            sum += array.getElementByIndex(i);
        }
        logger.info("Sum of array elements is " + sum);
        return sum;
    }
    @Override
    public int findSumStream(CustomArray array) throws ArrayException {
        if (array == null){
            logger.error("Array is null");
            throw new ArrayException("Array is null");
        }
        if (array.getLength() == 0){
            logger.error("Array is empty");
            throw new ArrayException("Array is empty");
        }
        int sum = Arrays.stream(array.getArray()).sum();
        logger.info("Sum of array elements is " + sum);
        return sum;
    }
    @Override
    public double findAverage(CustomArray array) throws ArrayException {
        if (array == null){
            logger.error("Array is null");
            throw new ArrayException("Array is null");
        }
        if (array.getLength() == 0){
            logger.error("Array is empty");
            throw new ArrayException("Array is empty");
        }
        double value = (double)findSum(array) / array.getLength();
        logger.info("Average element is " + value);
        return value;
    }
    @Override
    public double findAverageStream(CustomArray array) throws ArrayException {
        if (array == null){
            logger.error("Array is null");
            throw new ArrayException("Array is null");
        }
        if (array.getLength() == 0){
            logger.error("Array is empty");
            throw new ArrayException("Array is empty");
        }
        double value = Arrays.stream(array.getArray()).average().getAsDouble();
        logger.info("Average element is " + value);
        return value;
    }
    @Override
    public int calculatePositiveElements(CustomArray array) throws ArrayException {
        if (array == null){
            logger.error("Array is null");
            throw new ArrayException("Array is null");
        }
        if (array.getLength() == 0){
            logger.error("Array is empty");
            throw new ArrayException("Array is empty");
        }
        int counter = 0;
        for(int i = 0; i < array.getLength(); i++){
            if(array.getElementByIndex(i) >= 0){
                counter++;
            }
        }
        logger.info("The number of positive elements is " + counter);
        return counter;
    }
    @Override
    public long calculatePositiveElementsStream(CustomArray array) throws ArrayException {
        if (array == null){
            logger.error("Array is null");
            throw new ArrayException("Array is null");
        }
        if (array.getLength() == 0){
            logger.error("Array is empty");
            throw new ArrayException("Array is empty");
        }
        long counter = Arrays.stream(array.getArray()).filter(value ->  value > 0).count();
        logger.info("The number of positive elements is " + counter);
        return counter;
    }
    @Override
    public int calculateNegativeElements(CustomArray array) throws ArrayException {
        if (array == null){
            logger.error("Array is null");
            throw new ArrayException("Array is null");
        }
        if (array.getLength() == 0){
            logger.error("Array is empty");
            throw new ArrayException("Array is empty");
        }
        int counter = 0;
        for(int i = 0; i < array.getLength(); i++){
            if(array.getElementByIndex(i) < 0){
                counter++;
            }
        }
        logger.info("The number of negative elements is " + counter);
        return counter;
    }
    @Override
    public long calculateNegativeElementsStream(CustomArray array) throws ArrayException {
        if (array == null){
            logger.error("Array is null");
            throw new ArrayException("Array is null");
        }
        if (array.getLength() == 0){
            logger.error("Array is empty");
            throw new ArrayException("Array is empty");
        }
        long counter = Arrays.stream(array.getArray()).filter(value -> value < 0).count();
        logger.info("The number of negative elements is " + counter);
        return counter;
    }
}
