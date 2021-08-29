package edu.epam.arrays.service;

import edu.epam.arrays.entity.CustomArray;
import edu.epam.arrays.exception.ArrayException;

public interface DefineService {
    int findSum(CustomArray array) throws ArrayException;
    int findSumStream(CustomArray array) throws ArrayException;
    double findAverage(CustomArray array) throws ArrayException;
    double findAverageStream(CustomArray array) throws ArrayException;
    int calculatePositiveElements(CustomArray array) throws ArrayException;
    long calculatePositiveElementsStream(CustomArray array) throws ArrayException;
    int calculateNegativeElements(CustomArray array) throws ArrayException;
    long calculateNegativeElementsStream(CustomArray array) throws ArrayException;
}
