package http;

public class HTTPResponse {
    private int statusCode;
    private String statusMessage;
    private String contentType;
    private byte[] body;

    public HTTPResponse(int statusCode, String statusMessage, String contentType, byte[] body) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.contentType = contentType;
        this.body = body;
    }

    public static HTTPResponse error(int statusCode, String statusMessage, String errorMessage) {
        String body = String.format(
                "<html><body><h1>Error %d: %s</h1><p>%s</p></body></html>",
                statusCode, statusMessage, errorMessage
        );
        return new HTTPResponse(statusCode, statusMessage, "text/html", body.getBytes());
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public int getContentLength() {
        if (body != null) {
            return body.length;
        } else {
            return 0;
        }
    }
}
