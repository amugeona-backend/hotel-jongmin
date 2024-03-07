package hotel_reservation;

import static constant.UserConstant.USER_DEFAULT_CASH;
public class User {
    private final String name; // 예약자명
    private final String phoneNumber; // 예약자 전화번호
    int Cash = USER_DEFAULT_CASH; // 예약자 소지금

    public User(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getCash() {
        return Cash;
    }

    public void setCash(int Cash) {
        this.Cash = Cash;
    }
}
