package com.placement.util;

public class ActiveApplicationsExistException extends Exception {
    public ActiveApplicationsExistException(String msg) {
        super(msg);
    }
    public String toString() {
        return getMessage();
    }
}
