package lotto.view;

import java.util.List;
import lotto.dto.LottoResponse;
import lotto.dto.WinningStatisticResponse;

public class OutputView {

    private static final String OUTPUT_PURCHASED_LOTTO_HEADER = "개를 구매했습니다.";

    private static final String OUTPUT_WINNING_STATISTICS_HEADER = "당첨 통계\n---";
    private static final String OUTPUT_RANK_5_RESULT_FORMAT = "3개 일치 (5,000원) - %d개\n";
    private static final String OUTPUT_RANK_4_RESULT_FORMAT = "4개 일치 (50,000원) - %d개\n";
    private static final String OUTPUT_RANK_3_RESULT_FORMAT = "5개 일치 (1,500,000원) - %d개\n";
    private static final String OUTPUT_RANK_2_RESULT_FORMAT = "5개 일치, 보너스 볼 일치 (30,000,000원) - %d개\n";
    private static final String OUTPUT_RANK_1_RESULT_FORMAT = "6개 일치 (2,000,000,000원) - %d개\n";


    public void printLottos(List<LottoResponse> lottoResponses) {
        System.out.printf(lottoResponses.size() + OUTPUT_PURCHASED_LOTTO_HEADER);
        for (LottoResponse lottoResponse : lottoResponses) {
            System.out.println(lottoResponse.formattedNumbers());
        }
    }

    public void printWinningStatistics(WinningStatisticResponse response) {
        System.out.println(OUTPUT_WINNING_STATISTICS_HEADER);
        System.out.printf(OUTPUT_RANK_5_RESULT_FORMAT, response.fifthCount());
        System.out.printf(OUTPUT_RANK_4_RESULT_FORMAT, response.fourthCount());
        System.out.printf(OUTPUT_RANK_3_RESULT_FORMAT, response.thirdCount());
        System.out.printf(OUTPUT_RANK_2_RESULT_FORMAT, response.secondCount());
        System.out.printf(OUTPUT_RANK_1_RESULT_FORMAT, response.firstCount());
    }
}
