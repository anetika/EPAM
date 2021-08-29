package edu.epam.arrays.service.impl;

import edu.epam.arrays.entity.CustomArray;
import edu.epam.arrays.exception.ArrayException;
import edu.epam.arrays.service.ChangeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;


public class ChangeServiceImpl implements ChangeService {
    static Logger logger = LogManager.getLogger();
    @Override
    public void changeElements(CustomArray array) throws ArrayException {
        if (array == null){
            logger.error("Array is null");
            throw new ArrayException("Array is null");
        }
        if (array.getLength() == 0){
            logger.error("Array is empty");
            throw new ArrayException("Array is empty");
        }
        int[] tmpArray = array.getArray();
        for(int i = 0; i < array.getLength(); i++){
            if(tmpArray[i] < 0){
                tmpArray[i] = 0;
            }
        }
        logger.info("All changes were made");
        array.setArray(tmpArray);
    }
    @Override
    public void changeElementsStream(CustomArray array) throws ArrayException {
        if (array == null){
            logger.error("Array is null");
            throw new ArrayException("Array is null");
        }
        if (array.getLength() == 0){
            logger.error("Array is empty");
            throw new ArrayException("Array is empty");
        }
        array.setArray(Arrays.stream(array.getArray()).map(value -> {
            if (value < 0){
                value = 0;
            }
            return value;
        }).toArray());
    }
}
