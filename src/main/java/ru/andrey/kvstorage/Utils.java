package ru.andrey.kvstorage;

import ru.andrey.kvstorage.exception.DatabaseException;

public class Utils {
    public static void checkEmptyOrNull(String arg, String argName) throws DatabaseException {
        if (arg == null) {
            throw new DatabaseException(argName + " is null"); // I would rather throw IllegalArgumentException
        }
        if (arg.isEmpty()) {
            throw new DatabaseException(argName + " is empty");
        }
    }
}
