package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.Messages.Message;

import java.util.*;
import java.util.stream.Collectors;

public class UserInterface {

    // 입력 메서드들
    public String inputPurchaseAmount() {
        System.out.println(Message.INPUT_PURCHASE_AMOUNT);
        return Console.readLine();
    }

    public String inputWinningNumbers() {
        System.out.println();
        System.out.println(Message.INPUT_WINNING_NUMBERS);
        return Console.readLine();
    }

    public String inputBonusNumber() {
        System.out.println();
        System.out.println(Message.INPUT_BONUS_NUMBER);
        return Console.readLine();
    }

    // 검증 및 파싱 메서드들
    public int validateAndParsePurchaseAmount(String input) {
        int amount = parseInteger(input, Message.ERROR_PURCHASE_NUMBER);
        validatePurchaseAmount(amount);
        return amount;
    }

    private void validatePurchaseAmount(int amount) {
        if (amount % Message.LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(Message.ERROR_PURCHASE_UNIT);
        }
        if (amount <= 0) {
            throw new IllegalArgumentException(Message.ERROR_PURCHASE_POSITIVE);
        }
    }

    public List<Integer> validateAndParseWinningNumbers(String input) {
        return parseNumbers(input);
    }

    private List<Integer> parseNumbers(String input) {
        try {
            return Arrays.stream(input.split(Message.NUMBER_DELIMITER))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Message.ERROR_WINNING_NUMBER);
        }
    }

    public int validateAndParseBonusNumber(String input) {
        return parseInteger(input, Message.ERROR_BONUS_NUMBER);
    }

    private int parseInteger(String input, String errorMessage) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public void printError(String message) {
        System.out.println(message);
    }
}