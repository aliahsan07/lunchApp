package com.venturedive.rotikhilao.exception;



public class ApplicationException extends Exception {

    private static final long serialVersionUID = 1L;

    private Integer code;

    public ApplicationException(Integer code) {
        super();
        this.code = code;
    }

    public ApplicationException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public ApplicationException(String message) {
        super(message);
    }
}
