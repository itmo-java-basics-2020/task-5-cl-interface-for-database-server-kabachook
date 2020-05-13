package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    String getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    static DatabaseCommandResult success(String result) {
        return new DatabaseCommandResultImpl(result, null, DatabaseCommandStatus.SUCCESS);
    }

    static DatabaseCommandResult error(String error) {
        return new DatabaseCommandResultImpl(null, error, DatabaseCommandStatus.FAILED);
    }

     final class DatabaseCommandResultImpl implements DatabaseCommandResult {
         private String result;
         private String error;
         private DatabaseCommandStatus status;

         public DatabaseCommandResultImpl(String result, String error, DatabaseCommandStatus status) {
             this.result = result;
             this.error = error;
             this.status = status;
         }

        @Override
        public Optional<String> getResult() {
            return Optional.ofNullable(result);
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            return status;
        }

        @Override
        public boolean isSuccess() {
            return status == DatabaseCommandStatus.SUCCESS;
        }

        @Override
        public String getErrorMessage() {
            return error;
        }
    }
}