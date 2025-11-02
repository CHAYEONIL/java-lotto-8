package lotto.utill;

import lotto.Messages.Message;
import java.util.*;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != Message.LOTTO_SIZE) {
            throw new IllegalArgumentException(Message.ERROR_LOTTO_SIZE);
        }
        validateRange(numbers);
        validateDuplicate(numbers);
    }

    private void validateRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number < Message.MIN_LOTTO_NUMBER || number > Message.MAX_LOTTO_NUMBER) {
                throw new IllegalArgumentException(Message.ERROR_LOTTO_RANGE);
            }
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(Message.ERROR_LOTTO_DUPLICATE);
        }
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public List<Integer> getSortedNumbers() {
        List<Integer> sorted = new ArrayList<>(numbers);
        Collections.sort(sorted);
        return sorted;
    }

    public int countMatch(Lotto other) {
        return (int) numbers.stream()
                .filter(other.getNumbers()::contains)
                .count();
    }

    public boolean containsBonus(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }
}