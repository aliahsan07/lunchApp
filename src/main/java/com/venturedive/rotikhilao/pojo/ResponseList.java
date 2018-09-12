package com.venturedive.rotikhilao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ResponseList<T> implements Serializable {


    private static final long serialVersionUID = 552616440196966489L;

    private List<T> data;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }


}
