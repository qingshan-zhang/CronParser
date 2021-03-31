import com.qingshan.cronExpressionParser.CronExpressionProcessor;
import com.qingshan.cronExpressionParser.Exceptions.InvalidCronExpressionException;
import static org.assertj.core.api.Assertions.assertThat;

import com.qingshan.cronExpressionParser.models.CronParsedOutput;
import com.qingshan.cronExpressionParser.models.ValueRange;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class CronExpressionProcessorTest {

    @Test
    public void invalidInput() {
        assertThrows(InvalidCronExpressionException.class,
                () -> new CronExpressionProcessor(" "),
                "Invalid Cron expression");
    }

    @Test
    public void expressionHasNotEnoughentries() {
        assertThrows(InvalidCronExpressionException.class,
                () -> new CronExpressionProcessor("*/15 /bin/find"),
                "Invalid Cron expression");
    }

    @Test
    public void cronExpressionContainsInvalidFormat() throws InvalidCronExpressionException {
        CronExpressionProcessor processor = new CronExpressionProcessor("*/15 0 0 0 1-2-3 /bin/find");
        assertThrows(InvalidCronExpressionException.class,
                processor::getParsedResult,
                "Invalid cron expression");
    }

    @Test
    public void extraSpaceInExpression() throws InvalidCronExpressionException {
        CronExpressionProcessor processor = new CronExpressionProcessor("*/15 0  0  0");
        assertThrows(InvalidCronExpressionException.class,
                processor::getParsedResult,
                "Invalid cron expression");
    }

    @Test
    public void parseExpressionContainsAllFormat() throws InvalidCronExpressionException {
        CronExpressionProcessor processor = new CronExpressionProcessor("*/15 0 1,15 * 1-5 /usr/bin/find");
        CronParsedOutput expected = new CronParsedOutput();
        expected.setMinute(generateAllValuesForInterval(new ValueRange(0, 45), 15));
        expected.setHour(List.of("0"));
        expected.setDayOfMonth(List.of("1", "15"));
        expected.setMonth(generateAllValuesForInterval(new ValueRange(1, 12), 1));
        expected.setDayOfWeek(generateAllValuesForInterval(new ValueRange(1, 5), 1));
        expected.setCommand("/usr/bin/find");
        assertThat(processor.getParsedResult()).isEqualTo(expected.toString());
    }

    @Test
    public void parseExpressionWithAllStars() throws InvalidCronExpressionException {
        CronExpressionProcessor processor = new CronExpressionProcessor("* * * * * /usr/bin/find");
        CronParsedOutput expected = new CronParsedOutput();
        expected.setMinute(generateAllValuesForInterval(new ValueRange(0, 59), 1));
        expected.setHour(generateAllValuesForInterval(new ValueRange(0, 23), 1));
        expected.setDayOfMonth(generateAllValuesForInterval(new ValueRange(1, 31), 1));
        expected.setMonth(generateAllValuesForInterval(new ValueRange(1, 12), 1));
        expected.setDayOfWeek(generateAllValuesForInterval(new ValueRange(1, 7), 1));
        expected.setCommand("/usr/bin/find");
        assertThat(processor.getParsedResult()).isEqualTo(expected.toString());
    }

    @Test
    public void parseExpressionsWithIntervals() throws InvalidCronExpressionException {
        CronExpressionProcessor processor = new CronExpressionProcessor("5/25 1/6 */15 * 1/3 /usr/bin/find");
        CronParsedOutput expected = new CronParsedOutput();
        expected.setMinute(generateAllValuesForInterval(new ValueRange(5, 59), 25));
        expected.setHour(generateAllValuesForInterval(new ValueRange(1, 23), 6));
        expected.setDayOfMonth(generateAllValuesForInterval(new ValueRange(1, 31), 15));
        expected.setMonth(generateAllValuesForInterval(new ValueRange(1, 12), 1));
        expected.setDayOfWeek(generateAllValuesForInterval(new ValueRange(1, 7), 3));
        expected.setCommand("/usr/bin/find");
        assertThat(processor.getParsedResult()).isEqualTo(expected.toString());
    }

    private List<String> generateAllValuesForInterval(ValueRange range, int interval) {
        return IntStream.iterate(range.getStart(), i -> i <= range.getEnd(), i -> i + interval)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());
    }
}
