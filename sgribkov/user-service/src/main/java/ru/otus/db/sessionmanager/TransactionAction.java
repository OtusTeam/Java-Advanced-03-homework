package ru.otus.db.sessionmanager;

import java.util.function.Supplier;

public interface TransactionAction<T>  extends Supplier<T> {}
