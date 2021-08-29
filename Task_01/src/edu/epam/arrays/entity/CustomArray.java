package edu.epam.arrays.entity;

import edu.epam.arrays.exception.ArrayException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;


public class CustomArray {
    static Logger logger = LogManager.getLogger();
    private int[] array;

    public CustomArray() {
        logger.info("Array was created");
    }

    public CustomArray(int... array){
        this.array = array;
        logger.info("Array was created");
    }

    public int[] getArray(){
        return Arrays.copyOf(array, this.array.length);
    }

    public int getLength(){
        return array.length;
    }

    public int getElementByIndex(int index) throws ArrayException{
        if (index < 0 || index >= array.length){
            logger.error("Index is out of bounds");
            throw new ArrayException("Index is out of bounds");
        }
        return array[index];
    }
    public void setArray(int[] array){
        this.array = array;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for (int i: array){
            builder.append(i).append(" ");
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomArray that = (CustomArray) o;
        return Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }
}
