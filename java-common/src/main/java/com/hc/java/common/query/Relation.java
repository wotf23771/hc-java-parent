package com.hc.java.common.query;

public enum Relation {

    AND(0),
    OR(1);

    private int index;

    Relation(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
