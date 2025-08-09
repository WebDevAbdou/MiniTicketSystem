

import java.math.BigDecimal;

/**
 * Simple Booking model for Mini Ticket System
 * Academic project for BIT 4043 OOP course
 */
public class Booking {
    private int id;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private Event event;
    private String seatType;
    private int quantity;
    private BigDecimal totalPrice;
    private String bookingDate;

    // Seat type constants
    public static final String SEAT_TYPE_STANDARD = "Standard";
    public static final String SEAT_TYPE_VIP = "VIP";
    public static final String SEAT_TYPE_PREMIUM = "Premium";

    // Price multipliers
    public static final BigDecimal STANDARD_MULTIPLIER = new BigDecimal("1.0");
    public static final BigDecimal VIP_MULTIPLIER = new BigDecimal("1.5");
    public static final BigDecimal PREMIUM_MULTIPLIER = new BigDecimal("2.0");

    // Constructor
    public Booking(int id, String customerName, String customerEmail, String customerPhone,
                   Event event, String seatType, int quantity, String bookingDate) {
        this.id = id;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.event = event;
        this.seatType = seatType;
        this.quantity = quantity;
        this.bookingDate = bookingDate;
        this.totalPrice = calculateTotalPrice(event.getBasePrice(), seatType, quantity);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    // Business methods
    public static BigDecimal calculateTotalPrice(BigDecimal basePrice, String seatType, int quantity) {
        BigDecimal multiplier;

        switch (seatType) {
            case SEAT_TYPE_VIP:
                multiplier = VIP_MULTIPLIER;
                break;
            case SEAT_TYPE_PREMIUM:
                multiplier = PREMIUM_MULTIPLIER;
                break;
            case SEAT_TYPE_STANDARD:
            default:
                multiplier = STANDARD_MULTIPLIER;
                break;
        }

        return basePrice.multiply(multiplier).multiply(new BigDecimal(quantity));
    }

    @Override
    public String toString() {
        return "Booking #" + id + " - " + customerName + " - " + event.getName();
    }
}
