package hotel_reservation;

import constant.RoomType;

public class Room {
    private final RoomType roomtype; // 룸 타입
    private final int cost; // 룸 가격

    public Room(RoomType roomtype, int cost) {
        this.roomtype = roomtype;
        this.cost = cost;
    }

    public RoomType getroomtype() {
        return roomtype;
    }

    public int getCost() {
        return cost;
    }
}
