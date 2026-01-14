package kz.eldar.microservice.delivery_service.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String resource, Object id) {
        super(resource + " with id=" + id + " not found");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
