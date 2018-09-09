package com.major.project.travel.exception;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by HUY on 9/9/2018
 */
public class DataNotFoundException extends Exception {

    private int code;

    public DataNotFoundException(String message) {
        super(message);
        this.code = HttpServletResponse.SC_NOT_FOUND;
    }

    public DataNotFoundException(String message, int code) {
        super(message);
        this.code = code;
    }

    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
        this.code = HttpServletResponse.SC_NOT_FOUND;
    }

    public DataNotFoundException(String message, int code, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
