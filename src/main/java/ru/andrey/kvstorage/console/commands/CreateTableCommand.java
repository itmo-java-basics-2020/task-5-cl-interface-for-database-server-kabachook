package ru.andrey.kvstorage.console.commands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;

public class CreateTableCommand implements DatabaseCommand {
    private final ExecutionEnvironment env;
    private final String databaseName;
    private final String tableName;

    public CreateTableCommand(ExecutionEnvironment env, String databaseName, String tableName) {
        this.env = env;
        this.databaseName = databaseName;
        this.tableName = tableName;
    }

    @Override
    public DatabaseCommandResult execute() {
        var database = env.getDatabase(databaseName);

        if (database.isEmpty()) {
            return DatabaseCommandResult.error("Database " + databaseName + " does not exist");
        }

        try {
            database.get().createTableIfNotExists(tableName);
        } catch (DatabaseException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
        return DatabaseCommandResult.success("Created table" + tableName);
    }
}
