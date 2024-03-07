package service;

import hotel_reservation.ProductRoom;

public class ProductRoomService {
    public void changeReservationState(ProductRoom productRoom, boolean reservation) {
        productRoom.setReserved(reservation);
    }
}
