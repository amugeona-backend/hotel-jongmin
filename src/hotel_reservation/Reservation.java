package hotel_reservation;

import java.time.LocalDateTime;

public record Reservation(ProductRoom productRoom, String userName, String userPhoneNumber, LocalDateTime createdAt) {
}
