package lotto.Messages;

public class Message {
    // 로또 관련 상수
    public static final int LOTTO_SIZE = 6;
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;

    // 에러 메시지
    public static final String ERROR_LOTTO_SIZE = "[ERROR] 로또 번호는 6개여야 합니다.";
    public static final String ERROR_LOTTO_RANGE = "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.";
    public static final String ERROR_LOTTO_DUPLICATE = "[ERROR] 로또 번호는 중복되지 않아야 합니다.";
}