package service;

public class GusetService {
    private final HotelService hotelService;
    private final UserService userService = new UserService();
    private final ProductRoomService productRoomService = new ProductRoomService();


    public GusetService(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    public void displayGuestMode() {
        while (true) {

        }
    }
}
