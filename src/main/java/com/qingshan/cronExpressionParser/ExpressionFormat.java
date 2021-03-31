package com.qingshan.cronExpressionParser;

import com.qingshan.cronExpressionParser.parsers.AllValueParser;
import com.qingshan.cronExpressionParser.parsers.Parser;
import com.qingshan.cronExpressionParser.parsers.IntervalParser;
import com.qingshan.cronExpressionParser.parsers.MultipleValuesParser;
import com.qingshan.cronExpressionParser.parsers.RangeParser;
import com.qingshan.cronExpressionParser.parsers.SingleValueParser;

import java.util.Arrays;
import java.util.regex.Pattern;

public enum ExpressionFormat {

    SINGLE_VALUE(Pattern.compile("([1-9]\\d+|\\d)"), new SingleValueParser()),
    MULTIPLE_VALUES(Pattern.compile("(\\d+,)+(\\d+)"), new MultipleValuesParser()),
    RANGE(Pattern.compile("([1-9]\\d+|\\d)-([1-9]\\d+|\\d)"), new RangeParser()),
    INTERVAL(Pattern.compile("(\\*|[1-9]\\d+|\\d)\\/([1-9]\\d+|\\d)"), new IntervalParser()),
    ALL(Pattern.compile("\\*"), new AllValueParser());

    private final Pattern pattern;
    private final Parser parser;

    ExpressionFormat(Pattern pattern, Parser parser) {
        this.pattern = pattern;
        this.parser = parser;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Parser getParser() {
        return parser;
    }

    public static ExpressionFormat getExpressionFormat(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        return Arrays.stream(ExpressionFormat.values())
                .filter(pat -> pat.getPattern().matcher(input).matches())
                .findAny()
                .orElse(null);
    }
}
