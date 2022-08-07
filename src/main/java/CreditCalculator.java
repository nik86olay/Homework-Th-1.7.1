import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;


public class CreditCalculator {
    static final BigDecimal CREDIT_RATE = new BigDecimal("10");
    BigDecimal years, sumObject, firstSum;
    int operation;
    Scanner scanner = new Scanner(System.in);

    void communicationWithClient() {
        while (true) {
            System.out.println("Для выполнения расчета введите следующие данные (значения вводить арабскими цифрами):");
            System.out.println("Сумму объекта покупки:");
            String sumObjectStr = scanner.nextLine();
            System.out.println("Сумму первоначального взноса:");
            String firstSumStr = scanner.nextLine();
            System.out.println("Срок кредита (не более 30 лет):");
            String yearsStr = scanner.nextLine();
            System.out.println(" Введите номер необходимой операции:");
            System.out.println("1 - Рассчет месячного платежа; \n" +
                    "2 - Рассчет общей суммы к возврату в банк;\n" +
                    "3 - Рассчет переплаты за весь период\nДля выхода введите End");
            String operationStr = scanner.nextLine();
            if(operationStr.equals("End")) break;

            if(dataVerification(yearsStr, sumObjectStr, firstSumStr, operationStr)){
                System.out.println("Результат по Вашему запросу: " + calculate(years, sumObject, firstSum, operation));
            }
        }
    }

    protected BigDecimal calculate(BigDecimal years, BigDecimal sumObject, BigDecimal firstSum, int operation) {
        BigDecimal result = BigDecimal.ZERO;
        BigDecimal sunCredit = sumObject.subtract(firstSum);
        BigDecimal constCalculation = sunCredit.divide(CREDIT_RATE, 2, RoundingMode.HALF_UP).multiply(years);
        BigDecimal countMonth = BigDecimal.valueOf(12);
        switch (operation) {
            case 1 -> result = constCalculation.add(sunCredit).divide(countMonth.multiply(years), 2, RoundingMode.HALF_UP);
            case 2 -> result = constCalculation.add(sunCredit);
            case 3 -> result = constCalculation;
        }
        return result.setScale(2, RoundingMode.HALF_UP);
    }

    protected boolean dataVerification(String yearsStr, String sumObjectStr, String firstSumStr, String operationStr) {
        boolean resultVerification = true;
        try {
            years = new BigDecimal(yearsStr);
            sumObject = new BigDecimal(sumObjectStr);
            firstSum = new BigDecimal(firstSumStr);
            operation = Integer.parseInt(operationStr);
        } catch (Exception e) {
            System.out.println("Неккорректные значения ввоода.\nВведите значения арабскими цифрами (1, 2, 3 и т.д.)");
            return false;
        }

        if (years.compareTo(BigDecimal.ZERO) == 0 | years.compareTo(new BigDecimal("30")) == 0
                | sumObject.compareTo(firstSum) < 0 | operation < 1 | operation > 3) {
            System.out.println("Неккорректные значения ввоода.\nВведите значения в указанном диапазоне");
            resultVerification = false;
        }
        return resultVerification;
    }
}
