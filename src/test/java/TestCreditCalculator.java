import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;

public class TestCreditCalculator {

    final static double EXPECTED_CREDIT_RATE = 10;

    @ParameterizedTest
    @CsvSource({"0, 100 000, 2 000 000",
            "3, 2 000 000, 100 000",
            "1, 100 000, 2 000 000",
            "35, 500 000, 2 500 000"})
    @DisplayName("Тест работы dataVerification по проверке валидности входных параметров")
    public void test_validParam_method(int month, int sumObject, int firstSum) {
        // given:
        CreditCalculator calculator = new CreditCalculator();
        boolean expected = month == 0 || month > 30 || sumObject < firstSum;

        // when:
        var actual = calculator.dataVerification(month, sumObject, firstSum);

        // then:
        // Проверка результата работы метода
        assertThat(CreditCalculator.CreditRate, equals(EXPECTED_CREDIT_RATE));
        assertThat(actual, equals(expected));

    }

    @ParameterizedTest
    @CsvSource({"1, 100 000, 2 000 000, 174166.66",
            "30, 550 000, 2 000 000, 16 111.11"})
    @DisplayName("Тест работы calculate по вычислению ежемесячного платежа")
    public void test_validResult_method(int month, int sumObject, int firstSum, int expected) {
        // given:
        CreditCalculator calculator = new CreditCalculator();

        // when:
        var result = calculator.calculate(month, sumObject, firstSum);

        // then:
        // Проверка результата работы метода
        assertThat(result, equals(expected));
    }
}
