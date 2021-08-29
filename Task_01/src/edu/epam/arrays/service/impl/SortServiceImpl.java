package edu.epam.arrays.service.impl;

import edu.epam.arrays.entity.CustomArray;
import edu.epam.arrays.exception.ArrayException;
import edu.epam.arrays.service.SortService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class SortServiceImpl implements SortService {
    static Logger logger = LogManager.getLogger();
    @Override
    public void bubbleSort(CustomArray array) throws ArrayException {
        if (array == null){
            logger.error("Array is null");
            throw new ArrayException("Array is null");
        }
        if (array.getLength() == 0){
            logger.error("Array is empty");
            throw new ArrayException("Array is empty");
        }
        int[] tmpArray = array.getArray();
        for(int i = 0; i < array.getLength() - 1; i++){
            for(int j = 0; j < array.getLength() - i - 1; j++){
                if(tmpArray[j] > tmpArray[j + 1]){
                    int tmp = tmpArray[j];
                    tmpArray[j] = tmpArray[j + 1];
                    tmpArray[j + 1] = tmp;
                }
            }
        }
        logger.info("Array was sorted");
        array.setArray(tmpArray);
    }
    @Override
    public void insertSort(CustomArray array) throws ArrayException {
        if (array == null){
            logger.error("Array is null");
            throw new ArrayException("Array is null");
        }
        if (array.getLength() == 0){
            logger.error("Array is empty");
            throw new ArrayException("Array is empty");
        }
        int[] tmpArray = array.getArray();
        for(int left = 0; left < array.getLength(); left++){
            int value = tmpArray[left];
            int i = left -1;
            for(; i >= 0; i--){
                if (value < tmpArray[i]){
                    tmpArray[i + 1] = tmpArray[i];
                }
                else{
                    break;
                }
            }
            tmpArray[i + 1] = value;
        }
        logger.info("Array was sorted");
        array.setArray(tmpArray);
    }
    @Override
    public void selectionSort(CustomArray array) throws ArrayException {
        if (array == null){
            logger.error("Array is null");
            throw new ArrayException("Array is null");
        }
        if (array.getLength() == 0){
            logger.error("Array is empty");
            throw new ArrayException("Array is empty");
        }
        int[] tmpArray = array.getArray();
        for(int i = 0; i < array.getLength() - 1; i++){
            int index = i;
            for(int j = i + 1; j < array.getLength(); j++){
                if(tmpArray[j] < tmpArray[index]){
                    index = j;
                }
            }
            int tmp = tmpArray[index];
            tmpArray[index] = tmpArray[i];
            tmpArray[i] = tmp;
        }
        logger.info("Array was sorted");
        array.setArray(tmpArray);
    }
    @Override
    public void sortStream(CustomArray array) throws ArrayException {
        if (array == null){
            logger.error("Array is null");
            throw new ArrayException("Array is null");
        }
        if (array.getLength() == 0){
            logger.error("Array is empty");
            throw new ArrayException("Array is empty");
        }
        array.setArray(Arrays.stream(array.getArray()).sorted().toArray());
        logger.info("Array was sorted");
    }
}
