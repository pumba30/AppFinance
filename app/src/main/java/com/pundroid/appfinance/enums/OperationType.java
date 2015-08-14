package com.pundroid.appfinance.enums;

/**
 * Created by pumba30 on 12.08.2015.
 */
public enum OperationType {

    ALL("0"),
    INCOME("1"),
    SPENDING("2");

    private String id;

    private OperationType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }


}
