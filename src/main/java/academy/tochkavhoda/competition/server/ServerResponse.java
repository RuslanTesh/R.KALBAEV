package academy.tochkavhoda.competition.server;

import java.util.Objects;

public class ServerResponse {
    private final int responseCode;
    private final String responseData;

    public ServerResponse(int responseCode, String responseData) {
        this.responseCode = responseCode;
        this.responseData = responseData;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseData() {
        return responseData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServerResponse that = (ServerResponse) o;
        return responseCode == that.responseCode && Objects.equals(responseData, that.responseData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseCode, responseData);
    }
}
