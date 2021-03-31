import com.qingshan.cronExpressionParser.models.ValueRange;
import com.qingshan.cronExpressionParser.parsers.AllValueParser;
import com.qingshan.cronExpressionParser.parsers.Parser;
import com.qingshan.cronExpressionParser.parsers.IntervalParser;
import com.qingshan.cronExpressionParser.parsers.MultipleValuesParser;
import com.qingshan.cronExpressionParser.parsers.RangeParser;
import com.qingshan.cronExpressionParser.parsers.SingleValueParser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserTest {

    private final Parser intervalParser = new IntervalParser();
    private final Parser rangeParser = new RangeParser();
    private final Parser multipleValueParser = new MultipleValuesParser();
    private final Parser allValueParser = new AllValueParser();
    private final Parser singleValueParser = new SingleValueParser();

    //region IntervalParserTest

    @Test
    public void intervalStartingAtAValue() {
        assertThat(intervalParser.getParsedString("5/15", new ValueRange(0, 59)))
                .containsExactly("5", "20", "35","50");
    }

    @Test
    public void intervalStartingAtBegining() {
        assertThat(intervalParser.getParsedString("*/20", new ValueRange(0, 59)))
                .containsExactly("0", "20", "40");
    }

    @Test
    public void intervalCoversRange() {
        assertThat(intervalParser.getParsedString("*/5", new ValueRange(5, 20)))
                .containsExactly("5", "10", "15","20");
    }

    //endregion

    //region RangeParserTest

    @Test
    public void getParsedValueForRange() {
        assertThat(rangeParser.getParsedString("2-5", new ValueRange(1,6)))
                .containsExactly("2", "3", "4", "5");
    }

    //endregion

    //region MultipleValuesParserTest

    @Test
    public void getParsedValueForMultipleValueParser() {
        assertThat(multipleValueParser.getParsedString("1,2,3", new ValueRange(1,7)))
                .containsExactly("1", "2", "3");
    }

    //endregion

    //region AllValueParserTest

    @Test
    public void getParsedValuesForAllValueParser() {
        assertThat(allValueParser.getParsedString("", new ValueRange(1,5))).containsExactly("1", "2", "3", "4", "5");
    }

    //endregion

    //region SingleValueParserTest

    @Test
    public void getParsedValuesForSingleValuedRange() {
        assertThat(singleValueParser.getParsedString("59", new ValueRange(0, 59))).containsExactly("59");
    }

    //endregion

}
