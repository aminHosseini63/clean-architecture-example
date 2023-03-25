package com.link_intersystems.jdbc.tx;

public abstract class Transaction {

    private static final ThreadLocal<Transaction> TRANSACTION_THREAD_LOCAL = new ThreadLocal<>();

    public static Transaction getCurrent() {
        return TRANSACTION_THREAD_LOCAL.get();
    }

    static void setCurrent(Transaction transaction) {
        if (transaction == null) {
            TRANSACTION_THREAD_LOCAL.remove();
        } else {
            TRANSACTION_THREAD_LOCAL.set(transaction);
        }
    }

    abstract void rollback() throws Exception;

    abstract void commit() throws Exception;

    public <T> T unwrap(Class<T> type) {
        return null;
    }
}
