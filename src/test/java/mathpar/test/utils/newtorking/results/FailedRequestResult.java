package mathpar.test.utils.newtorking.results;

import lombok.Getter;

public class FailedRequestResult extends RequestResult {
    @Getter
    private final String responseStatus;
    @Getter
    private final String body;

    public FailedRequestResult(int status, String responseStatus, String body) {
        super(status);
        this.responseStatus = responseStatus;
        this.body = body;
    }
}
