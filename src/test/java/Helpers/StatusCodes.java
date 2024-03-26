package Helpers;

public enum StatusCodes {

    OK(200),
    CREATED(201),
    BAD_REQUEST(400),
    NOT_FOUND(404);

    private final int id;

    StatusCodes(int id) {this.id = id;}

    public int getId() {
        return id;
    }
}
