package com.venturedive.rotikhilao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BooleanResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean success;
    private String message;



    public static BooleanResponse success() {
        return success("Operation Successful");
    }

    public static BooleanResponse success(String message) {
        return new BooleanResponse(Boolean.TRUE, message);
    }

    public static BooleanResponse failure(String message) {
        return new BooleanResponse(Boolean.FALSE, message);
    }

    public static BooleanResponse failure() {
        return failure("Operation Failed");
    }

    public Boolean getSuccess() {
        return this.success;
    }


}
