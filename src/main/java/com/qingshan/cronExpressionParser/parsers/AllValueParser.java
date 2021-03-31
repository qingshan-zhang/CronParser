package com.qingshan.cronExpressionParser.parsers;

import com.qingshan.cronExpressionParser.models.ValueRange;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AllValueParser implements Parser {

    @Override
    public List<String> getParsedString(String input, ValueRange range) {
        return IntStream.rangeClosed(range.getStart(), range.getEnd()).mapToObj(String::valueOf).collect(Collectors.toList());
    }
}
