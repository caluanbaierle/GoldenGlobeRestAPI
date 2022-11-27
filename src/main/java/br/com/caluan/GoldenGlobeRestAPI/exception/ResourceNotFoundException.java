package br.com.caluan.GoldenGlobeRestAPI.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
