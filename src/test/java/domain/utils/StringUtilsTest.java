package domain.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import rayen.rejeb.tennis.domain.utils.StringUtils;

class StringUtilsTest {

  @ParameterizedTest
  @MethodSource("inputs")
  void should_validate_input(String ignore, String input, boolean expected) {
    // Given
    // When
    var res = StringUtils.isBlank(input);
    // Then
    assertEquals(expected, res);
  }

  public static Stream<Arguments> inputs() {
    return Stream.of(
        Arguments.of("should not validate empty string", "", true),
        Arguments.of("should not validate space", " ", true),
        Arguments.of("should not validate string with tab", "\t\t", true),
        Arguments.of("should not validate string with new line", "\n", true),
        Arguments.of("should not validate null string", null, true),
        Arguments.of("should validate string", "AB", false)
    );
  }

}
