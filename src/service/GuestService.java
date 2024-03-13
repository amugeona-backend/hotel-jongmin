package service;

import hotel_reservation.User;

import java.util.Scanner;

public class GuestService {
    private final HotelService hotelService;
    private final UserService userService = new UserService();
    private final ProductRoomService productRoomService = new ProductRoomService();


    public GuestService(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    public void displayGuestMode() {
        while (true) {
            System.out.println("---------Guest Mode---------");
            System.out.println("0. 회원/관리자 모드 선택");
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("9. 프로그램 종료");
            System.out.println("-----------------------------");

            Scanner sc = new Scanner(System.in);
            int command = sc.nextInt();

            if (command == 0) {
                break;
            } else if (command == 1) {
                signIn();
            } else if (command == 2) {
                signUp();
            } else if (command == 9) {
                System.exit(0);
            } else {
                System.out.println("잘못된 입력입니다.");
            }
            
        }
    }

    private static void phoneNumberContext() {
        System.out.println("ex)000-0000-0000");
        System.out.println("자신의 핸드폰 번호를 입력해주세요.");
        System.out.print("핸드폰 번호: ");
    }

    private void signIn() {
        System.out.println("---------JAVA호텔 로그인---------");
        phoneNumberContext();
        Scanner sc = new Scanner(System.in);
        String phoneNumber = sc.next();

        if (hotelService.validatePhoneNumber(phoneNumber)) {
            if (hotelService.existsPhoneNumber(phoneNumber)) {
                User user = hotelService.findUserByPhoneNumber(phoneNumber);
                displayUserService(user);
            } else {
                System.out.println("존재하지 않는 아이디입니다.");
            }
        } else {
            System.out.println("핸드폰 번호 양식이 올바르지 않습니다.");
        }
    }

    private void signUp() {
        System.out.println("---------JAVA호텔 회원가입---------");
        System.out.println("자신의 정보를 등록해주세요.");
        System.out.print("이름: ");

        Scanner sc = new Scanner(System.in);

        String name = sc.next();
        phoneNumberContext();
        String phoneNumber = sc.next();

        if (hotelService.validatePhoneNumber(phoneNumber)) {
            if (!hotelService.existsPhoneNumber(phoneNumber)) {
                hotelService.addUser(new User(name, phoneNumber));
                System.out.println("\n회원 가입이 완료되었습니다.");
                displayGuestMode();
            } else {
                System.out.println("\n이미 존재하는 핸드폰 번호입니다.");
                System.out.println("로그인 화면으로 돌아갑니다.");
                signIn();
            }
        } else {
            System.out.println("핸드폰 번호 양식이 올바르지 않습니다.");
        }
    }

    private void displayUserService(User user) { // 로그인 후 나타나는 창
        System.out.println();
        System.out.println("반갑습니다. " + user.getName() + "님");
        System.out.println("---------회원 모드---------");
        System.out.println("1. 호텔 예약하기");
        System.out.println("2. 예약한 호텔 조회하기");
        System.out.println("3. 포인트 충전하기");
        System.out.println("4. 포인트 조회하기");
        System.out.println("5. 예약 취소하기");
        System.out.println("6. 포인트 환전하기");
        System.out.println("7. 로그아웃");
        serviceInputHandling(user);
    }

    private void serviceInputHandling(User user) {
        System.out.println();
        System.out.print("입력: ");

        Scanner sc = new Scanner(System.in);
        int command = sc.nextInt();
        switch (command) {
            case 1 -> {
                break;
            }

            default -> {
                System.out.println("잘못된 입력입니다.");
                displayUserService(user);
            }
        }
    }
}

