package com.qingshan.cronExpressionParser;

import com.qingshan.cronExpressionParser.Exceptions.InvalidCronExpressionException;
import com.qingshan.cronExpressionParser.models.CronParsedOutput;
import com.qingshan.cronExpressionParser.models.ValueRange;

import java.util.List;

public class CronExpressionProcessor {

    private static final ValueRange SEC_MINUTE_RANGE = new ValueRange(0, 59);
    private static final ValueRange HOUR_RANGE = new ValueRange(0, 23);
    private static final ValueRange DAY_RANGE = new ValueRange(1, 31);
    private static final ValueRange MONTH_RANGE = new ValueRange(1, 12);
    private static final ValueRange WEEKDAY_RANGE = new ValueRange(1, 7);

    private final String minute;
    private final String hour;
    private final String dayOfMonth;
    private final String month;
    private final String dayOfWeek;
    private final String command;

    public CronExpressionProcessor(String expression) throws InvalidCronExpressionException {
        if (expression == null || expression.isEmpty()) {
            throw new InvalidCronExpressionException("Invalid Cron expression");
        }
        String[] expressions = expression.split("\\s");
        if (expressions.length != 6) {
            throw new InvalidCronExpressionException("Invalid Cron expression");
        }

        this.minute = expressions[0];
        this.hour = expressions[1];
        this.dayOfMonth = expressions[2];
        this.month = expressions[3];
        this.dayOfWeek = expressions[4];
        this.command = expressions[5];
    }

    public String getParsedResult() throws InvalidCronExpressionException {
        CronParsedOutput output = new CronParsedOutput();
        output.setMinute(getParsedValues(minute, SEC_MINUTE_RANGE));
        output.setHour(getParsedValues(hour, HOUR_RANGE));
        output.setDayOfMonth(getParsedValues(dayOfMonth, DAY_RANGE));
        output.setMonth(getParsedValues(month, MONTH_RANGE));
        output.setDayOfWeek(getParsedValues(dayOfWeek, WEEKDAY_RANGE));
        output.setCommand(command);
        return output.toString();
    }

    private List<String> getParsedValues(String input, ValueRange range) throws InvalidCronExpressionException {
        ExpressionFormat format = ExpressionFormat.getExpressionFormat(input);
        if (format == null) {
            throw new InvalidCronExpressionException("Invalid cron expression");
        }
        return format.getParser().getParsedString(input, range);
    }
}
