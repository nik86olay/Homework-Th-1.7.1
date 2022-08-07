import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCreditCalculator {

    static final BigDecimal EXPECTED_CREDIT_RATE = new BigDecimal(10);

    @ParameterizedTest
    @CsvSource({"0, 100 000, 2 000 000, 1",
            "ноль, 100 000, 2 000 000, 1",
            "0, сто, 2 000 000, 1",
            "0, 100 000, миллион, 1",
            "3, 2 000 000, 100 000, один",
            "1, 100 000, 2 000 000, 2",
            "35, 500 000, 2 500 000, 3",
            "3, 2 000 000, 100 000, 1000"})
    @DisplayName("The text of the data Verification work on verifying the validity of input parameters")
    public void test_validParam_method(String years, String sumObject, String firstSum, String operation) {
        // given:
        CreditCalculator calculator = new CreditCalculator();
        // Если operation.equals("один"), month.equals("ноль"),
        //      sumObject.equals("миллион"), firstSum.equals("сто"),
        //      Integer.parseInt(month) == 0, Integer.parseInt(month) > 30,
        //      Integer.parseInt(sumObject) < Integer.parseInt(firstSum)
        // возвращаем FALSE;
        final boolean EXPECTED = false;

        // when:
        var actual = calculator.dataVerification(years, sumObject, firstSum, operation);

        // then:
        // Проверка результата работы метода
        assertEquals(CreditCalculator.CREDIT_RATE.compareTo(EXPECTED_CREDIT_RATE),0);
        assertEquals(actual, EXPECTED);

    }

    @ParameterizedTest
    @CsvSource({"1, 100000, 2000000, 2090000.00, 2",
            "30, 550000, 2000000, 16111.11, 1",
            "15, 550000, 2000000, 2175000.00, 3"})
    @DisplayName("A test of calculateMonthPay's work on calculating the monthly payment")
    public void test_validResult_method(BigDecimal years, BigDecimal firstSum, BigDecimal sumObject,
                                        BigDecimal expected, int operation) {
        // given:
        CreditCalculator calculator = new CreditCalculator();

        // when:
        var result = calculator.calculate(years, sumObject, firstSum, operation);

        // then:
        // Проверка результата работы метода
        assertEquals(result, expected);
    }

}
