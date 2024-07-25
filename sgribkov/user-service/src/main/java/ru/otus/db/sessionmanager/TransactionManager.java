package ru.otus.db.sessionmanager;

public interface TransactionManager {
    <T> T doInTransaction(TransactionAction<T> action);
}
