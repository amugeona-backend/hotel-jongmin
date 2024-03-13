package hotel_reservation;

import constant.RoomType;
import java.time.LocalDate;

public class ProductRoom extends Room {
    private final int roomNumber; // 객실 호수
    private final LocalDate reservedDate; // 예약 날짜
    private boolean isReserved = false; // 예약 확인 여부

    public ProductRoom(RoomType roomType, int cost, int roomNuber, LocalDate reservedDate) {
        super(roomType, cost);
        this.roomNumber = roomNuber;
        this.reservedDate = reservedDate;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public LocalDate getReservedDate() {
        return reservedDate;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }
}
