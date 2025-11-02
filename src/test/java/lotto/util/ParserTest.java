package lotto.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ParserTest {

    @Nested
    @DisplayName("parseInput() 테스트")
    class ParseInputTest {

        @Test
        @DisplayName("공백이 포함된 입력을 쉼표 기준으로 정상 분리")
        void 공백_포함된_입력을_정상적으로_파싱() {
            // given
            String input = "   park, pobi,jun    ";
            String[] expected = {"park", "pobi", "jun"};

            // when
            String[] actual = Parser.parseInput(input);

            // then
            Assertions.assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("빈 값(공백 포함 쉼표)을 정상적으로 분리")
        void 빈_값들을_정상적으로_파싱() {
            // given
            String input = " , , ";
            String[] expected = {"", "", ""};

            // when
            String[] actual = Parser.parseInput(input);

            // then
            Assertions.assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("공백을 정상적으로 파싱")
        void 공백을_정상적으로_파싱() {
            // given
            String input = "  ";
            String[] expected = {""};

            // when
            String[] actual = Parser.parseInput(input);

            // then
            Assertions.assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    @DisplayName("removeAllSpaces() 테스트")
    class RemoveAllSpacesTest {

        @Test
        @DisplayName("공백이 있는 입력과 쉼표만 남기고 제거")
        void 공백이_있는_입력과_쉼표만_남기고_정제() {
            // given
            String input = " a    ,  b,    c";
            String expected = "a,b,c";

            // when
            String actual = Parser.removeAllSpaces(input);

            // then
            Assertions.assertThat(actual).isEqualTo(expected);
        }
    }
}