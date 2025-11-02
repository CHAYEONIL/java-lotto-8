package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.Messages.Message;
import lotto.utill.Lotto;
import lotto.utill.Rank;
import lotto.utill.YieldResult;

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
    public void printPurchasedLottos(List<Lotto> lottos) {
        System.out.println();
        System.out.printf(Message.PURCHASE_COUNT + "%n", lottos.size());
        for (Lotto lotto : lottos) {
            System.out.println(formatLottoNumbers(lotto));
        }
    }

    private String formatLottoNumbers(Lotto lotto) {
        return lotto.getSortedNumbers().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
    }

    public void printResult(YieldResult result) {
        System.out.println();
        System.out.println(Message.WINNING_STATISTICS);
        System.out.println(Message.STATISTICS_SEPARATOR);
        printRankStatistics(result);
        printProfitRate(result);
    }

    private void printRankStatistics(YieldResult result) {
        Map<Rank, Integer> rankCounts = result.getRankCounts();
        printRankCount(Rank.FIFTH, rankCounts.get(Rank.FIFTH));
        printRankCount(Rank.FOURTH, rankCounts.get(Rank.FOURTH));
        printRankCount(Rank.THIRD, rankCounts.get(Rank.THIRD));
        printRankCount(Rank.SECOND, rankCounts.get(Rank.SECOND));
        printRankCount(Rank.FIRST, rankCounts.get(Rank.FIRST));
    }

    private void printRankCount(Rank rank, int count) {
        System.out.printf(Message.RANK_FORMAT + "%n", rank.getFormattedDescription(), count);
    }

    private void printProfitRate(YieldResult result) {
        System.out.printf(Message.PROFIT_RATE_FORMAT + "%n", result.calculateProfitRate());
    }
}