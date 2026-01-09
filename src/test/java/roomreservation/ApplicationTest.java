package roomreservation;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 이 프로젝트는 "문제 레포"이므로, 아래 테스트들은 구현 후 활성화하여 사용한다.
 * (현재는 예시/가이드용으로 @Disabled 처리)
 */
class ApplicationTest extends NsTest {

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }

    @Test
    void 초기_메뉴가_출력된다() {
        run("Q");
        assertThat(output()).contains("기능을 선택해 주세요.");
        assertThat(output()).contains("1. 예약 등록");
        assertThat(output()).contains("2. 체크인");
        assertThat(output()).contains("3. 룸 스케줄 조회");
        assertThat(output()).contains("4. 패널티/예약불가 크루 조회");
        assertThat(output()).contains("Q. 종료");
    }

    @Test
    void 예약등록_정상흐름_출력형식을_확인한다() {
        run(
                "1",
                "짱수",
                "A",
                "18:00",
                "19:00",
                "Q"
        );

        String out = output();
        assertThat(out).contains("예약할 크루의 닉네임을 입력해 주세요.");
        assertThat(out).contains("룸을 입력해 주세요. (A/B/C)");
        assertThat(out).contains("예약 시작 시각을 입력해 주세요. (HH:mm)");
        assertThat(out).contains("예약 종료 시각을 입력해 주세요. (HH:mm)");
        assertThat(out).contains("예약 완료!");
        assertThat(out).contains("- 룸: A");
        assertThat(out).contains("- 크루: 짱수");
        assertThat(out).contains("- 시간: 18:00~19:00");
    }

    @Test
    void 예약등록_시간이_겹치면_에러가_발생한다() {
        // 초기 데이터: 빙티 A 19:00~20:00 (2026-01-06)
        run(
                "1",
                "빙티",
                "A",
                "19:30",
                "20:30"
        );

        assertThat(output()).contains("[ERROR]");
        assertThat(output()).contains("해당 시간에는 이미 예약이 존재합니다.");
    }

    @Test
    void 체크인할_예약이_없으면_에러가_발생한다() {
        run(
                "2",
                "쿠키",
                "A"
        );

        assertThat(output()).contains("[ERROR]");
        assertThat(output()).contains("오늘 해당 룸에 예약이 없습니다.");
    }

    @Test
    void 시간_형식이_잘못되면_에러가_발생한다() {
        run(
                "1",
                "이든",
                "B",
                "18:7a"
        );

        assertThat(output()).contains("[ERROR]");
        assertThat(output()).contains("잘못된 형식을 입력하였습니다.");
    }
}
