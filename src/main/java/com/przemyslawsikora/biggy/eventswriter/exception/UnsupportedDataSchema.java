/*
 * Copyright (C) 2019 Przemyslaw Sikora
 */

package com.przemyslawsikora.biggy.eventswriter.exception;

public class UnsupportedDataSchema extends RuntimeException {
    public UnsupportedDataSchema() {
    }

    public UnsupportedDataSchema(String message) {
        super(message);
    }

    public UnsupportedDataSchema(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedDataSchema(Throwable cause) {
        super(cause);
    }
}
