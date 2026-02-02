package com.placement.util;

public class EligibilityViolationException extends Exception {
    public EligibilityViolationException(String msg) {
        super(msg);
    }
    public String toString() {
        return getMessage();
    }
}
