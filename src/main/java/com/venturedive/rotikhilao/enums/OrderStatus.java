package com.venturedive.rotikhilao.enums;

public enum OrderStatus {
    PREPARING((short) 1),
    DELIVERED((short) 2),
    CANCELLED((short) 3),
    LOCKED((short) 4);

    private Short value;

    OrderStatus(short value) {
        this.value = value;
    }

    public Short value() {
        return this.value;
    }
}
