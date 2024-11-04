package dat.exceptions;

import java.time.LocalDateTime;

public class ApiException extends Exception{

    private final int statusCode;
    private LocalDateTime timestamp;

    public ApiException(int statusCode, String message, LocalDateTime timestamp) {
        super(message);
        this.timestamp = timestamp;
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
