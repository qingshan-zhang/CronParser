package com.qingshan.cronExpressionParser.models;

import java.util.List;

public class CronParsedOutput {

    private List<String> minute = null;
    private List<String> hour = null;
    private List<String> dayOfMonth = null;
    private List<String> month = null;
    private List<String> dayOfWeek = null;
    private String command = null;

    public void setCommand(String command) {
        this.command = command;
    }

    public void setDayOfMonth(List<String> dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public void setDayOfWeek(List<String> dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setHour(List<String> hour) {
        this.hour = hour;
    }

    public void setMinute(List<String> minute) {
        this.minute = minute;
    }

    public void setMonth(List<String> month) {
        this.month = month;
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("minute        ");
        strBuilder.append(String.join(" ", minute));
        strBuilder.append("\n");
        strBuilder.append("hour          ");
        strBuilder.append(String.join(" ", hour));
        strBuilder.append("\n");
        strBuilder.append("day of month  ");
        strBuilder.append(String.join(" ", dayOfMonth));
        strBuilder.append("\n");
        strBuilder.append("month         ");
        strBuilder.append(String.join(" ", month));
        strBuilder.append("\n");
        strBuilder.append("day of week   ");
        strBuilder.append(String.join(" ", dayOfWeek));
        strBuilder.append("\n");
        strBuilder.append("command       ");
        strBuilder.append(command);
        return strBuilder.toString();
    }
}
