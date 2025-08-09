
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Simple Booking Controller for Mini Ticket System
 * Academic project for BIT 4043 OOP course
 * Handles business logic and data storage in memory
 */
public class BookingController {
    private ArrayList<Event> events;
    private ArrayList<Booking> bookings;
    private int nextBookingId;

    public BookingController() {
        this.events = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.nextBookingId = 1;
        initializeSampleEvents();
    }

    /**
     * Initialize sample events for demonstration
     */
    private void initializeSampleEvents() {
        // Music Events
        events.add(new Event(1, "Rock Concert 2025", "2025-07-15", "City Arena",
                           "Amazing rock concert with top artists featuring electric guitars and powerful vocals", new BigDecimal("50.00"), 500, "images/rock_concert.jpg"));
        events.add(new Event(2, "Classical Music Evening", "2025-07-20", "Symphony Hall",
                           "Beautiful classical music performance by the National Orchestra", new BigDecimal("75.00"), 300, "images/classical_music.jpg"));
        events.add(new Event(3, "Jazz Festival", "2025-08-01", "Jazz Lounge",
                           "Smooth jazz music festival with renowned international artists", new BigDecimal("60.00"), 250, "images/jazz_festival.jpg"));
        events.add(new Event(4, "Pop Music Concert", "2025-08-10", "Stadium Arena",
                           "Chart-topping pop artists performing their greatest hits", new BigDecimal("85.00"), 800, "images/pop_concert.jpg"));

        // Entertainment Events
        events.add(new Event(5, "Comedy Show", "2025-07-25", "Comedy Club",
                           "Hilarious stand-up comedy night with award-winning comedians", new BigDecimal("30.00"), 200, "images/comedy_show.jpg"));
        events.add(new Event(6, "Theater Play", "2025-08-05", "Grand Theater",
                           "Classic Shakespeare drama performed by professional theater company", new BigDecimal("40.00"), 400, "images/theater_play.jpg"));
        events.add(new Event(7, "Magic Show", "2025-08-12", "Entertainment Center",
                           "Mind-blowing magic and illusion show for the whole family", new BigDecimal("35.00"), 350, "images/magic_show.jpg"));

        // Sports Events
        events.add(new Event(8, "Basketball Championship", "2025-09-18", "Sports Arena",
                           "Exciting championship game between top professional teams", new BigDecimal("65.00"), 1200, "images/basketball.jpg"));
        events.add(new Event(9, "Football Match", "2025-09-25", "National Stadium",
                           "Premier league football match with international star players", new BigDecimal("70.00"), 2000, "images/football.jpg"));

        // Cultural Events
        events.add(new Event(10, "Art Exhibition Opening", "2025-10-01", "Modern Art Gallery",
                           "Contemporary art exhibition featuring works by emerging artists", new BigDecimal("25.00"), 150, "images/art_exhibition.jpg"));
        events.add(new Event(11, "Food Festival", "2025-10-05", "Central Park",
                           "International food festival with cuisines from around the world", new BigDecimal("20.00"), 500, "images/food_festival.jpg"));
        events.add(new Event(12, "Technology Conference", "2025-10-10", "Convention Center",
                           "Latest innovations in technology and artificial intelligence", new BigDecimal("120.00"), 600, "images/tech_conference.jpg"));
    }

    /**
     * Get all available events
     */
    public ArrayList<Event> getAllEvents() {
        return new ArrayList<>(events);
    }

    /**
     * Get event by ID
     */
    public Event getEventById(int eventId) {
        for (Event event : events) {
            if (event.getId() == eventId) {
                return event;
            }
        }
        return null;
    }

    /**
     * Create a new booking
     */
    public Booking createBooking(String customerName, String customerEmail, String customerPhone,
                               int eventId, String seatType, int quantity) {
        Event event = getEventById(eventId);
        if (event == null) {
            return null;
        }

        if (!event.hasAvailableSeats(quantity)) {
            return null;
        }

        // Create booking date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String bookingDate = sdf.format(new Date());

        // Create booking
        Booking booking = new Booking(nextBookingId++, customerName, customerEmail, customerPhone,
                                    event, seatType, quantity, bookingDate);

        // Update available seats
        event.bookSeats(quantity);

        // Store booking
        bookings.add(booking);

        return booking;
    }

    /**
     * Get all bookings
     */
    public ArrayList<Booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }

    /**
     * Calculate price for given parameters
     */
    public BigDecimal calculatePrice(int eventId, String seatType, int quantity) {
        Event event = getEventById(eventId);
        if (event == null) {
            return BigDecimal.ZERO;
        }
        return Booking.calculateTotalPrice(event.getBasePrice(), seatType, quantity);
    }

    /**
     * Validate seat type
     */
    public boolean isValidSeatType(String seatType) {
        return Booking.SEAT_TYPE_STANDARD.equals(seatType) ||
               Booking.SEAT_TYPE_VIP.equals(seatType) ||
               Booking.SEAT_TYPE_PREMIUM.equals(seatType);
    }

    /**
     * Get available seat types
     */
    public String[] getSeatTypes() {
        return new String[]{
            Booking.SEAT_TYPE_STANDARD,
            Booking.SEAT_TYPE_VIP,
            Booking.SEAT_TYPE_PREMIUM
        };
    }
}
