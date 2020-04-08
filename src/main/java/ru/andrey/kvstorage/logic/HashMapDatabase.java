package ru.andrey.kvstorage.logic;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.Utils;
import java.util.HashMap;

public class HashMapDatabase implements Database {
    private final String name;
    private final HashMap<String, HashMap<String, String>> tables;

    public HashMapDatabase(String name) {
        this.name = name;
        this.tables = new HashMap<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void createTableIfNotExists(String tableName) throws DatabaseException {
        Utils.checkEmptyOrNull(tableName, "tableName");

        if (!tables.containsKey(tableName)) {
            tables.put(tableName, new HashMap<>());
        }
    }

    @Override
    public void createTableIfNotExists(String tableName, int segmentSizeInBytes) throws DatabaseException {
        throw new DatabaseException("Not implemented");
    }

    @Override
    public void write(String tableName, String objectKey, String objectValue) throws DatabaseException {
        Utils.checkEmptyOrNull(tableName, "tableName");
        Utils.checkEmptyOrNull(objectKey, "objectKey");
        Utils.checkEmptyOrNull(objectValue, "objectValue");

        if (!tables.containsKey(tableName)){
            throw new DatabaseException("Table " + tableName + "does not exists");
        }

        tables.get(tableName).put(objectKey, objectValue);
    }

    @Override
    public String read(String tableName, String objectKey) throws DatabaseException {
        Utils.checkEmptyOrNull(tableName, "tableName");
        Utils.checkEmptyOrNull(objectKey, "objectKey");

        if (tables.containsKey(tableName)){
            var table = tables.get(tableName);
            if (table != null && table.containsKey(objectKey)){
                return table.get(objectKey);
            }
        }
        return null;
    }
}
