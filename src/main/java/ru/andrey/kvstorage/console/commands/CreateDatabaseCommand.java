package ru.andrey.kvstorage.console.commands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.logic.HashMapDatabase;

public class CreateDatabaseCommand implements DatabaseCommand {
    private final ExecutionEnvironment env;
    private final String databaseName;

    public CreateDatabaseCommand(ExecutionEnvironment env, String databaseName) {
        this.env = env;
        this.databaseName = databaseName;
    }

    @Override
    public DatabaseCommandResult execute() {
        var database = env.getDatabase(databaseName);
        if (database.isPresent()) {
            return DatabaseCommandResult.error("Database " + databaseName + " already exists");
        }

        try {
            env.addDatabase(new HashMapDatabase(databaseName));
            return DatabaseCommandResult.success("Created the database");
        } catch (Exception e){
            return DatabaseCommandResult.error(e.getMessage());
        }
    }
}
