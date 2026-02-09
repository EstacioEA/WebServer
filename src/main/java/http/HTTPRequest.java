package http;

public class HTTPRequest {
    private String method;
    private String resource;
    private String version;

    public HTTPRequest(String method, String resource, String version) {
        this.method = method;
        this.resource = resource;
        this.version = version;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return method + " " + resource + " " + version;
    }
}
