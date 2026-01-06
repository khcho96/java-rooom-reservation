package roomreservation;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 예시 테스트입니다. 구현 후 @Disabled를 제거하고 사용하세요.
 */
class ApplicationTest extends NsTest {

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }

    @Disabled
    @Test
    void 메뉴가_출력된다() {
        run("Q");
        assertThat(output()).contains("기능을 선택해 주세요.");
    }
}
