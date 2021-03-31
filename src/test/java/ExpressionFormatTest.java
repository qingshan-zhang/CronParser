import com.qingshan.cronExpressionParser.ExpressionFormat;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class ExpressionFormatTest {

    @Test
    public void nullInput() {
        assertThat(ExpressionFormat.getExpressionFormat(null)).isNull();
    }

    @Test
    public void emptyInput() {
        assertThat(ExpressionFormat.getExpressionFormat("")).isNull();
    }

    @Test
    public void invalidInput() {
        assertThat(ExpressionFormat.getExpressionFormat("001")).isNull();
    }

    @Test
    public void decimalInInput() {
        assertThat(ExpressionFormat.getExpressionFormat("0.5-2")).isNull();
    }

    @Test
    public void multipleAllowedChars() {
        assertThat(ExpressionFormat.getExpressionFormat("3-4-5")).isNull();
    }

    @Test
    public void extraSeparate() {
        assertThat(ExpressionFormat.getExpressionFormat("1,2,")).isNull();
    }

    @Test
    public void multipleStars() {
        assertThat(ExpressionFormat.getExpressionFormat("**")).isNull();
    }

    @Test
    public void mixedFormat() {
        assertThat(ExpressionFormat.getExpressionFormat("1-2,4-5")).isNull();
    }

    @Test
    public void singleDigitValueFormat() {
        assertThat(ExpressionFormat.getExpressionFormat("1")).isEqualTo(ExpressionFormat.SINGLE_VALUE);
    }

    @Test
    public void singleValueFormat() {
        assertThat(ExpressionFormat.getExpressionFormat("234")).isEqualTo(ExpressionFormat.SINGLE_VALUE);
    }

    @Test
    public void multipleValuesFormat() {
        assertThat(ExpressionFormat.getExpressionFormat("1,22,33")).isEqualTo(ExpressionFormat.MULTIPLE_VALUES);
    }

    @Test
    public void rangeValuesFormat() {
        assertThat(ExpressionFormat.getExpressionFormat("2-345")).isEqualTo(ExpressionFormat.RANGE);
    }

    @Test
    public void intervalValuesWithStarFormat() {
        assertThat(ExpressionFormat.getExpressionFormat("*/1")).isEqualTo(ExpressionFormat.INTERVAL);
    }

    @Test
    public void intervalValuesFormat() {
        assertThat(ExpressionFormat.getExpressionFormat("2/15")).isEqualTo(ExpressionFormat.INTERVAL);
    }

    @Test
    public void allValuesFormat() {
        assertThat(ExpressionFormat.getExpressionFormat("*")).isEqualTo(ExpressionFormat.ALL);
    }
}
