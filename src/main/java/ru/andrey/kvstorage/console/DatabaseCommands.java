package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.console.commands.CreateDatabaseCommand;
import ru.andrey.kvstorage.console.commands.CreateTableCommand;
import ru.andrey.kvstorage.console.commands.ReadKeyCommand;
import ru.andrey.kvstorage.console.commands.UpdateKeyCommand;

import java.util.Arrays;

public enum DatabaseCommands {
    CREATE_DATABASE() {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            if (args.length != argsCount) throw new IllegalArgumentException("Expected " + argsCount + " arguments");
            return new CreateDatabaseCommand(env, args[0]);
        }

        private static final int argsCount = 1;
    },
    CREATE_TABLE() {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            if (args.length != argsCount) throw new IllegalArgumentException("Expected " + argsCount + " arguments");
            return new CreateTableCommand(env, args[0], args[1]);
        }

        private static final int argsCount = 2;
    },
    UPDATE_KEY() {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            if (args.length != argsCount) throw new IllegalArgumentException("Expected " + argsCount + " arguments");
            return new UpdateKeyCommand(env, args[0], args[1], args[2], args[3]);
        }

        private static final int argsCount = 4;
    },
    READ_KEY() {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            if (args.length != argsCount) throw new IllegalArgumentException("Expected " + argsCount + " arguments");
            return new ReadKeyCommand(env, args[0], args[1], args[2]);
        }

        private static final int argsCount = 3;
    };

    public abstract DatabaseCommand getCommand(
            ExecutionEnvironment environment,
            String... options
    );

    public static DatabaseCommand get(ExecutionEnvironment env, String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("input should not be null or empty");
        }

        var args = input.split(" ");
        for (var command : DatabaseCommands.values()) {
            if (args[0].equals(command.name())) {
                return command.getCommand(env, Arrays.copyOfRange(args, 1, args.length));
            }
        }

        throw new IllegalArgumentException("No such command");
    }
}
