package rayen.rejeb.tennis.domain.exception;

public class DomainException extends RuntimeException {

  private final String rejectedObjectName;

  public DomainException(String message, String rejectedObjectName) {
    super(message);
    this.rejectedObjectName = rejectedObjectName;
  }

  public String getRejectedObjectName() {
    return rejectedObjectName;
  }
}
