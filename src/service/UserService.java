package service;

import hotel_reservation.User;

public class UserService {
    public void chargePoint(User user, int point) {
        user.setCash(user.getCash() + point);
    }
    public void deductPoint(User user, int point) {
        user.setCash(user.getCash() - point);
    }
}
