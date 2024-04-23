package domain.utils;

public class StringUtils {

  private StringUtils() {
    // Utility class
  }

  public static boolean isBlank(final CharSequence cs) {
    final int strLen = length(cs);
    if (strLen == 0) {
      return true;
    }
    for (int i = 0; i < strLen; i++) {
      if (!Character.isWhitespace(cs.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  private static int length(final CharSequence cs) {
    return cs == null ? 0 : cs.length();
  }

}
