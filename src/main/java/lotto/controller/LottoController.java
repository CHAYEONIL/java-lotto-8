package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Messages.Message;
import lotto.utill.*;
import lotto.view.UserInterface;

import java.util.*;

public class LottoController {
    private final UserInterface userInterface = new UserInterface();

    public void run() {
        int purchaseAmount = getPurchaseAmount();
        List<Lotto> lottos = generateLottos(purchaseAmount);
        userInterface.printPurchasedLottos(lottos);

        WinningLotto winningLotto = getWinningLotto();
        YieldResult result = new YieldResult(lottos, winningLotto, purchaseAmount);
        userInterface.printResult(result);
    }

    private int getPurchaseAmount() {
        while (true) {
            try {
                String input = userInterface.inputPurchaseAmount();
                return userInterface.validateAndParsePurchaseAmount(input);
            } catch (IllegalArgumentException e) {
                userInterface.printError(e.getMessage());
            }
        }
    }

    private List<Lotto> generateLottos(int purchaseAmount) {
        int count = purchaseAmount / Message.LOTTO_PRICE;
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            lottos.add(generateLotto());
        }

        return lottos;
    }

    private Lotto generateLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(
                Message.MIN_LOTTO_NUMBER,
                Message.MAX_LOTTO_NUMBER,
                Message.LOTTO_SIZE
        );
        return new Lotto(numbers);
    }

    private WinningLotto getWinningLotto() {
        Lotto winningNumbers = getWinningNumbers();
        int bonusNumber = getBonusNumber(winningNumbers);
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private Lotto getWinningNumbers() {
        while (true) {
            try {
                String input = userInterface.inputWinningNumbers();
                List<Integer> numbers = userInterface.validateAndParseWinningNumbers(input);
                return new Lotto(numbers);
            } catch (IllegalArgumentException e) {
                userInterface.printError(e.getMessage());
            }
        }
    }

    private int getBonusNumber(Lotto winningNumbers) {
        while (true) {
            try {
                String input = userInterface.inputBonusNumber();
                int bonusNumber = userInterface.validateAndParseBonusNumber(input);
                validateBonusNumber(bonusNumber, winningNumbers);
                return bonusNumber;
            } catch (IllegalArgumentException e) {
                userInterface.printError(e.getMessage());
            }
        }
    }

    private void validateBonusNumber(int bonusNumber, Lotto winningNumbers) {
        new WinningLotto(winningNumbers, bonusNumber);
    }
}