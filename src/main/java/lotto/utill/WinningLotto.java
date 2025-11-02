package lotto.utill;

import lotto.Messages.Message;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final int bonusNumber;

    public WinningLotto(Lotto winningNumbers, int bonusNumber) {
        validateBonus(bonusNumber, winningNumbers);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonus(int bonusNumber, Lotto winningNumbers) {
        if (bonusNumber < Message.MIN_LOTTO_NUMBER || bonusNumber > Message.MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(Message.ERROR_BONUS_RANGE);
        }
        if (winningNumbers.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException(Message.ERROR_BONUS_DUPLICATE);
        }
    }

    public Rank getRank(Lotto lotto) {
        int matchCount = winningNumbers.countMatch(lotto);
        boolean hasBonus = lotto.containsBonus(bonusNumber);
        return Rank.valueOf(matchCount, hasBonus);
    }
}