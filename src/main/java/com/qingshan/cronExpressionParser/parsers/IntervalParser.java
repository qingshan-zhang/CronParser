package com.qingshan.cronExpressionParser.parsers;

import com.qingshan.cronExpressionParser.models.ValueRange;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntervalParser implements Parser {

    @Override
    public List<String> getParsedString(String input, ValueRange range) {
        String[] values = input.split("\\/");
        int st = "*".equals(values[0])? range.getStart() : Integer.parseInt(values[0]);
        int incr = Integer.parseInt(values[1]);
        return IntStream.iterate(st, i -> i <= range.getEnd(), i -> i + incr).mapToObj(String::valueOf).collect(Collectors.toList());
    }
}
