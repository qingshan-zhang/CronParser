package com.qingshan.cronExpressionParser.parsers;

import com.qingshan.cronExpressionParser.models.ValueRange;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RangeParser implements Parser {
    @Override
    public List<String> getParsedString(String input, ValueRange valueRange) {
        String[] ranges = input.split("-");
        return IntStream.rangeClosed(Integer.parseInt(ranges[0]), Integer.parseInt(ranges[1]))
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());
    }
}
