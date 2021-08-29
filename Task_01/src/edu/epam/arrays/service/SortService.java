package edu.epam.arrays.service;

import edu.epam.arrays.entity.CustomArray;
import edu.epam.arrays.exception.ArrayException;

public interface SortService {
    void bubbleSort(CustomArray array) throws ArrayException;
    void insertSort(CustomArray array) throws ArrayException;
    void selectionSort(CustomArray array) throws ArrayException;
    void sortStream(CustomArray array) throws ArrayException;
}
