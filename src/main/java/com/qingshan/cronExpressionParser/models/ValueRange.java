package com.qingshan.cronExpressionParser.models;

public class ValueRange {

    private int start;
    private int end;

    public ValueRange(int st, int end) {
        this.start = st;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
