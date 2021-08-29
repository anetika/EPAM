package edu.epam.arrays.service;

import edu.epam.arrays.entity.CustomArray;
import edu.epam.arrays.exception.ArrayException;

public interface ChangeService {
    void changeElements(CustomArray array) throws ArrayException;
    void changeElementsStream(CustomArray array) throws ArrayException;
}
