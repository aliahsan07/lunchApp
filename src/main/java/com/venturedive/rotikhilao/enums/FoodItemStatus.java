package com.venturedive.rotikhilao.enums;

public enum FoodItemStatus {

    ACTIVE((short) 1),
    DISCONTINUED((short) 2),
    LIMITED_TIME((short) 3);

    private Short value;

    FoodItemStatus(short value) {
        this.value = value;
    }

    public Short value() {
        return this.value;
    }
}
