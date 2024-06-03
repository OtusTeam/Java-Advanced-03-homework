package ru.otus.db.sessionmanager;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class TransactionManagerSpring implements TransactionManager {

    @Override
    @Transactional
    public <T> T doInTransaction(TransactionAction<T> action) {
        return action.get();
    }
}
