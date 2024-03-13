package service;

import hotel_reservation.ProductRoom;
import hotel_reservation.Reservation;

import java.time.LocalDate;
import java.util.Scanner;

public class ManagerService {
    private final HotelService hotelService;

    public ManagerService(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    public void displayManagerMode() {
        while (true) {
            System.out.println("---------Manager Mode---------");
            System.out.println("0. 회원/관리자 모드 선택");
            System.out.println("1. 예약 현황");
            System.out.println("2. 자산 현황");
            System.out.println("9. 시스템 종료");
            System.out.println("-----------------------------");

            Scanner sc = new Scanner(System.in);
            int command = sc.nextInt();

            if (command == 0) {
                break;
            } else if (command == 1) {
                reservationStatus();
            } else if (command == 2) {
                assertStatus();
            } else if (command == 9) {
                System.exit(0);
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }

    public void reservationStatus() {
        System.out.println();
        System.out.println("매뉴를 선택해 주세요.");
        System.out.println("1. 빈객실 찾기");
        System.out.println("2. 예약 찾기");
        System.out.println("3. 오늘 객실 현황");
        System.out.println("-----------------------------");

        Scanner sc = new Scanner(System.in);
        int command = sc.nextInt();

        switch (command) {
            case 1 -> findProductRoomByDate();
            case 2 -> findReservation();
            case 3 -> findReservationByToday();
            case 0 -> {
            }
            default -> {
                System.out.println("잘못된 입력입니다.");
                reservationStatus();
            }
        }
    }

    public void findProductRoomByDate() {
        showHotelDate();

        Scanner sc = new Scanner(System.in);
        int command = sc.nextInt();

        if (command == 0) {
            reservationStatus();
        } else if (0 < command && command <= hotelService.findAvailableDays().size()) {
            findProductRoomIsReservedByDate(hotelService.findAvailableDays().get(command - 1));
        } else {
            System.out.println("잘못된 입력입니다.");
            findProductRoomByDate();
        }
    }

    public void findProductRoomIsReservedByDate(LocalDate day) {
        System.out.println();
        System.out.println("선택하신 날짜는 " + day + " 입니다");
        for (ProductRoom room : hotelService.findEmptyProductRoomByDate(day)) {
            System.out.println(room.getRoomType());
        }
        System.out.println("-----------------------------");

        Scanner sc = new Scanner(System.in);
        int command = sc.nextInt();
        if (command == 0) {
            reservationStatus();
        } else {
            System.out.println("잘못된 입력입니다.");
            findProductRoomIsReservedByDate(day);
        }
    }

    public void findReservation() {
        System.out.println();
        System.out.println("방법을 선택해 주세요.");
        System.out.println("1. 이름으로 찾기");
        System.out.println("2. 번호로 찾기");
        System.out.println("3. 날짜로 찾기");
        System.out.println("-----------------------------");

        Scanner sc = new Scanner(System.in);
        int command = sc.nextInt();

        switch (command) {
            case 1 -> findReservationByExistingName();
            case 2 -> findReservationByValidatePhoneNumber();
            case 3 -> findReservationByDayChoice();
            case 0 -> reservationStatus();
            default -> {
                System.out.println("잘못된 입력입니다.");
                reservationStatus();
            }
        }
    }

    public void findReservationByExistingName() {
        System.out.println();
        System.out.println("이름을 입력해 주세요.");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        if (hotelService.findReservationByExistingName(name)) {
            findReservationByName(name);
        } else {
            System.out.println("예약 내역이 없습니다.");
            findReservation();
        }
    }

    public void findReservationByName(String name) {
        for (Reservation reservation : hotelService.findReservationByName(name)) {
            System.out.println(reservation.productRoom().getReservedDate() + "\t" + reservation.productRoom().getRoomType() + '\t'
                    + reservation.userName() + "\t" + reservation.userPhoneNumber());
        }
        System.out.println("-----------------------------");
    }

    public void findReservationByValidatePhoneNumber() {
        System.out.println();
        System.out.println("번호를 입력해 주세요.");
        System.out.println("ex) 010-0000-0000");

        Scanner sc = new Scanner(System.in);
        String phoneNumber = sc.next();

        if (hotelService.validatePhoneNumber(phoneNumber)) {
            findReservationByExistingPhoneNumber(phoneNumber);
        } else {
            System.out.println("\n핸드폰 번호의 입력이 올 바르지 않습니다!");
            findReservationByValidatePhoneNumber();
        }
    }

    private void findReservationByExistingPhoneNumber(String phoneNumber) {
        if (hotelService.findReservationByExistingPhoneNumber(phoneNumber)) {
            findReservationByValidatePhoneNumber();
        } else {
            System.out.println("입력하신 핸드폰 번호로 예약된 객실은 없습니다.");
        }
    }

    public void findReservationByDayChoice() {
        showHotelDate();
        System.out.println();
        System.out.println("날짜를 입력해 주세요.");

        Scanner sc = new Scanner(System.in);
        int date = sc.nextInt();

        if (date == 0) {
            findReservation();
        } else if (0 < date && date <= hotelService.findAvailableDays().size()) {
            findReservationByExistingDate(hotelService.findAvailableDays().get(date - 1));
        } else {
            System.out.println("잘못된 입력입니다.");
            findReservation();
        }
    }

    public void findReservationByExistingDate(LocalDate date) {
        System.out.println();
        System.out.println("선택하신 날짜는 " + date + " 입니다.");
        if (hotelService.findReservationByExistingDate(date)) {
            findReservationByDate(date);
        } else {
            System.out.println("예약된 정보가 없습니다!");
            reservationStatus();
        }
    }

    public void findReservationByDate(LocalDate date) {
        for (Reservation reservation : hotelService.findReservationByDate(date)) {
            System.out.println(reservation.productRoom().getRoomType() + "\t" + reservation.userName() + "\t" + reservation.userPhoneNumber());
        }
        System.out.println("-----------------------------");

        Scanner sc = new Scanner(System.in);
        int command = sc.nextInt();

        if (command == 0) {
            reservationStatus();
        } else {
            System.out.println("-----------------------------");
            findReservation();
        }
    }

    public void findReservationByToday() {
        LocalDate date = LocalDate.now();
        findReservationByExistingDate(date);
    }


    public void assertStatus() {
        System.out.println("총 금액은 " + hotelService.getAsset() + "원 입니다.");
    }

    private void showHotelDate() {
        int i = 1;
        System.out.println();
        System.out.println("날짜를 선택해 주세요!");
        for (LocalDate day : hotelService.findAvailableDays()) {
            System.out.printf("%d. %10s", i++, day + "\n");
        }
    }
}
