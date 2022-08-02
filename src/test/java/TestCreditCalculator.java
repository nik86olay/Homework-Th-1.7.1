import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCreditCalculator {

    static final double EXPECTED_CREDIT_RATE = 10;

    @ParameterizedTest
    @CsvSource({"0, 100 000, 2 000 000, 1",
            "3, 2 000 000, 100 000, один",
            "1, 100 000, 2 000 000, 2",
            "35, 500 000, 2 500 000, 3"})
    @DisplayName("Тест работы dataVerification по проверке валидности входных параметров")
    public void test_validParam_method(int month, int sumObject, int firstSum, String operation) {
        // given:
        CreditCalculator calculator = new CreditCalculator();
        boolean expected = month == 0 || month > 30 || sumObject < firstSum || operation.equals("один");

        // when:
        var actual = calculator.dataVerification(month, sumObject, firstSum, operation);

        // then:
        // Проверка результата работы метода
        assertTrue(CreditCalculator.CREDIT_RATE == EXPECTED_CREDIT_RATE);
        assertThat(actual, equals(expected));

    }

    @ParameterizedTest
    @CsvSource({"1, 100_000, 2_000_000, 2_090_000, 2",
            "30, 550_000, 2_000_000, 16_111.11, 1",
            "15, 550_000, 2_000_000, 3_625_000, 3"})
    @DisplayName("Тест работы calculateMonthPay по вычислению ежемесячного платежа")
    public void test_validResult_method(int month, int sumObject, int firstSum, String operation, int expected) {
        // given:
        CreditCalculator calculator = new CreditCalculator();

        // when:
        var result = calculator.calculate(month, sumObject, firstSum, operation);

        // then:
        // Проверка результата работы метода
        assertThat(result, equals(expected));
    }

}
