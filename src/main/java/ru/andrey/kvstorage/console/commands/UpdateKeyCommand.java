package ru.andrey.kvstorage.console.commands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;

public class UpdateKeyCommand implements DatabaseCommand {
    private final ExecutionEnvironment env;
    private final String databaseName;
    private final String tableName;
    private final String key;
    private final String value;

    public UpdateKeyCommand(ExecutionEnvironment env, String databaseName, String tableName, String key, String value) {
        this.env = env;
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.key = key;
        this.value = value;
    }

    @Override
    public DatabaseCommandResult execute() {
        var database = env.getDatabase(databaseName);

        if (database.isEmpty()) {
            return DatabaseCommandResult.error("Database " + databaseName + " does not exist");
        }

        try {
            database.get().write(tableName, key, value);
            return DatabaseCommandResult.success(null);
        } catch (DatabaseException e){
            return DatabaseCommandResult.error(e.getMessage());
        }
    }
}
