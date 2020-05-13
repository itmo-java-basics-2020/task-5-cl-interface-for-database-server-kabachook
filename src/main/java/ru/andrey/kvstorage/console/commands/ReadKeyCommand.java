package ru.andrey.kvstorage.console.commands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;

public class ReadKeyCommand implements DatabaseCommand {
    private final ExecutionEnvironment env;
    private final String databaseName;
    private final String tableName;
    private final String key;

    public ReadKeyCommand(ExecutionEnvironment env, String databaseName, String tableName, String key) {
        this.env = env;
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.key = key;
    }

    @Override
    public DatabaseCommandResult execute() {
        var database = env.getDatabase(databaseName);

        if (database.isEmpty()) {
            return DatabaseCommandResult.error("Database " + databaseName + " does not exist");
        }

        try {
            var value = database.get().read(tableName, key);
            return DatabaseCommandResult.success(value);
        } catch (DatabaseException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
    }
}
