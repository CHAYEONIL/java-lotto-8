package lotto.utill;

import java.util.*;

public class YieldResult {
    private final Map<Rank, Integer> rankCounts;
    private final int purchaseAmount;

    public YieldResult(List<Lotto> lottos, WinningLotto winningLotto, int purchaseAmount) {
        this.rankCounts = new EnumMap<>(Rank.class);
        this.purchaseAmount = purchaseAmount;
        initializeRankCounts();
        calculateRanks(lottos, winningLotto);
    }

    private void initializeRankCounts() {
        for (Rank rank : Rank.values()) {
            if (rank != Rank.NONE) {
                rankCounts.put(rank, 0);
            }
        }
    }

    private void calculateRanks(List<Lotto> lottos, WinningLotto winningLotto) {
        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.getRank(lotto);
            if (rank != Rank.NONE) {
                rankCounts.put(rank, rankCounts.get(rank) + 1);
            }
        }
    }

    public Map<Rank, Integer> getRankCounts() {
        return new EnumMap<>(rankCounts);
    }

    public double calculateProfitRate() {
        int totalPrize = calculateTotalPrize();
        return (double) totalPrize / purchaseAmount * 100;
    }

    private int calculateTotalPrize() {
        return rankCounts.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
    }
}