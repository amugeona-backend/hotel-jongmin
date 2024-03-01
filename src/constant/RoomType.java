package constant;

public enum RoomType {
    STANDARD("standard room"),
    SUPERIOR("superior room"),
    DELUXE("deluxe room"),
    SUITE("suite room");

    String name;

    RoomType(String name) {
        this.name = name;
    }
}
