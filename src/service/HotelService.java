package service;

import hotel_reservation.Hotel;
import hotel_reservation.ProductRoom;
import constant.RoomType;
import hotel_reservation.Reservation;
import hotel_reservation.User;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HotelService {
    Hotel hotel = new Hotel();
    private static HotelService hotelService;

    public static HotelService getHotelService() {
        if (hotelService == null) {
            hotelService = new HotelService();
        }
        return hotelService;
    }

    public void initRoom() {
        LocalDate localDate = LocalDate.now();
        for (int i = 0; i < 7; i++) {
            hotel.getProductRooms().add(new ProductRoom(RoomType.STANDARD, 30000, 101, localDate.plusDays(i)));
            hotel.getProductRooms().add(new ProductRoom(RoomType.SUPERIOR, 40000, 102, localDate.plusDays(i)));
            hotel.getProductRooms().add(new ProductRoom(RoomType.DELUXE, 50000, 103, localDate.plusDays(i)));
            hotel.getProductRooms().add(new ProductRoom(RoomType.SUITE, 60000, 104, localDate.plusDays(i)));
        }
    }

    public String getHotelPassword() {
        return hotel.getPassword();
    }

    public List<LocalDate> findAvailableDays() {
        return hotel.getProductRooms().stream()
                .map(ProductRoom::getReservedDate)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<ProductRoom> findProductRoomByDate(LocalDate date) { // 날짜를 입력해 그날 예약가능한 방 조회?
        return hotel.getProductRooms().stream()
                .filter(room -> room.getReservedDate().equals(date))
                .collect(Collectors.toList());
    }

    public User findUserByPhoneNumber(String phoneNumber) { // 핸드폰번호로 고객 확인 (회원가입 여부)
        return hotel.getUsers().stream()
                .filter(u -> u.getPhoneNumber().equals(phoneNumber))
                .findFirst().orElse(null);
    }

    public List<Reservation> findReservationByDate(LocalDate date) { // 예약날짜로 예약 확인
        return hotel.getReservations().stream()
                .filter(reservation -> reservation.productRoom().getReservedDate().equals(date))
                .collect(Collectors.toList());
    }

    public List<Reservation> findReservationByName(String name) { // 예약자명으로 예약 확인
        return hotel.getReservations().stream()
                .filter(reservation -> reservation.userName().equals(name))
                .collect(Collectors.toList());
    }

    public List<Reservation> findReservationByPhoneNumber(String phoneNumber) { // 전화번호로 예약 확인
        return hotel.getReservations().stream()
                .filter(reservation -> reservation.userPhoneNumber().equals(phoneNumber))
                .collect(Collectors.toList());
    }

    public void addReservation(Reservation reservation) { // 예약 등록
        hotel.getReservations().add(reservation);
    }

    public void cancelReservation(Reservation reservation) { // 예약 취소
        hotel.getReservations().remove(reservation);
    }

    public void addUser(User user) { // 회원가입
        hotel.getUsers().add(user);
    }

    public boolean validatePhoneNumber(String phoneNumber) { // 전화번호 형식으로 입력했나 확인
        String pattern = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
        return Pattern.matches(pattern, phoneNumber);
    }

    public boolean existsPhoneNumber(String phoneNumber) { // 전화번호로 회원 여부 확인
        return hotel.getUsers().stream().anyMatch(u -> u.getPhoneNumber().equals(phoneNumber));
    }

    public void addAsset(int price) { // 호텔 자산 ++ (호텔 예약으로 인해)
        hotel.setAsset(hotel.getAsset() + price);
    }

    public void deductAsset(int price) { // 호텔 자산 -- (예약 취소로 인해)
        hotel.setAsset(hotel.getAsset() - price);
    }

    public int getAsset() { // 수익에 따른 호텔 자산 업데이트
        return hotel.getAsset();
    }

}
