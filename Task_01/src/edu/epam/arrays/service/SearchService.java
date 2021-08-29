package edu.epam.arrays.service;

import edu.epam.arrays.entity.CustomArray;
import edu.epam.arrays.exception.ArrayException;

public interface SearchService {
    int findMin(CustomArray array) throws ArrayException;
    int findMinStream(CustomArray array) throws ArrayException;
    int findMax(CustomArray array) throws ArrayException;
    int findMaxStream(CustomArray array) throws ArrayException;
}
