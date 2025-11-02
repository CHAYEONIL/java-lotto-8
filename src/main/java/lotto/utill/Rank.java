package lotto.utill;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000, false, "6개 일치"),
    SECOND(5, 30_000_000, true, "5개 일치, 보너스 볼 일치"),
    THIRD(5, 1_500_000, false, "5개 일치"),
    FOURTH(4, 50_000, false, "4개 일치"),
    FIFTH(3, 5_000, false, "3개 일치"),
    NONE(0, 0, false, "");

    private final int matchCount;
    private final int prize;
    private final boolean requireBonus;
    private final String description;

    Rank(int matchCount, int prize, boolean requireBonus, String description) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.requireBonus = requireBonus;
        this.description = description;
    }

    public static Rank valueOf(int matchCount, boolean hasBonus) {
        return Arrays.stream(values())
                .filter(rank -> rank != NONE)
                .filter(rank -> rank.matchCount == matchCount)
                .filter(rank -> !rank.requireBonus || hasBonus)
                .findFirst()
                .orElse(NONE);
    }

    public int getPrize() {
        return prize;
    }

    public String getDescription() {
        return description;
    }

    public String getFormattedDescription() {
        return String.format("%s (%,d원)", description, prize);
    }
}