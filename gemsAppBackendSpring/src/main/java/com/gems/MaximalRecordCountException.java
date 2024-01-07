package com.gems;

public class MaximalRecordCountException extends RuntimeException {
    public MaximalRecordCountException() {
        super("Maximal count of records reached");
    }
}
