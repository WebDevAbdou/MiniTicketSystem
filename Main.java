
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Event Ticket Booking System - Main Application Class
 *
 * ACADEMIC PROJECT FOR BIT 4043 OBJECT-ORIENTED PROGRAMMING COURSE
 *
 * This is the main application class that demonstrates comprehensive understanding of:
 * - Java Swing GUI development with professional design
 * - Object-Oriented Programming principles (Encapsulation, Inheritance, Polymorphism)
 * - Event-driven programming with ActionListeners
 * - MVC (Model-View-Controller) architecture pattern
 * - CardLayout for seamless navigation between application modules
 *
 * The application features three main modules as required:
 * 1. HOME MODULE: Event display with system information
 * 2. BOOKING FORM MODULE: Customer information and seat selection
 * 3. RECEIPT MODULE: Booking confirmation and PDF generation
 *
 * Additional PAYMENT MODULE added for enhanced user experience and realistic workflow
 *
 * @author BIT 4043 Student - Semester 2 IT
 * @version 3.0 - Final Academic Submission
 * @course BIT 4043 Object-Oriented Programming
 */
public class Main extends JFrame {

    // ========================================
    // PROFESSIONAL COLOR SCHEME CONSTANTS
    // ========================================
    // These static final constants demonstrate good programming practice
    // by centralizing design values and ensuring consistency across the application

    public static final Color PRIMARY_DARK = new Color(25, 42, 86);      // Deep navy blue for headers
    public static final Color PRIMARY_BLUE = new Color(52, 73, 94);      // Professional blue for main UI
    public static final Color ACCENT_BLUE = new Color(74, 144, 226);     // Bright blue for interactive elements
    public static final Color SUCCESS_GREEN = new Color(46, 204, 113);   // Success green for confirmations
    public static final Color TEXT_DARK = new Color(44, 62, 80);         // Dark text for readability
    public static final Color TEXT_LIGHT = new Color(127, 140, 141);     // Light text for secondary info
    public static final Color BACKGROUND = new Color(248, 249, 250);     // Light background for clean look
    public static final Color CARD_WHITE = Color.WHITE;                  // Pure white for content cards

    // ========================================
    // APPLICATION ARCHITECTURE COMPONENTS
    // ========================================
    // These private fields demonstrate ENCAPSULATION - hiding internal implementation
    // while providing controlled access through public methods

    private CardLayout cardLayout;              // Layout manager for switching between panels
    private JPanel contentPanel;                // Main container panel for all application screens
    private BookingController bookingController; // Business logic controller (MVC pattern)

    // ========================================
    // PANEL REFERENCES (VIEW COMPONENTS)
    // ========================================
    // Each panel represents a different module/screen of the application
    // This demonstrates COMPOSITION - Main class contains other objects

    private HomePanel homePanel;                // MODULE 1: Home screen with events and system info
    private BookingFormPanel bookingFormPanel; // MODULE 2: Booking form with customer details
    private PaymentPanel paymentPanel;         // ADDITIONAL: Payment processing for realistic workflow
    private ReceiptPanel receiptPanel;         // MODULE 3: Receipt display and PDF generation

    // ========================================
    // NAVIGATION CONSTANTS
    // ========================================
    // String constants for CardLayout navigation - demonstrates good coding practice
    // Using constants prevents typos and makes code more maintainable

    public static final String HOME_CARD = "HOME";         // Home page identifier
    public static final String BOOKING_CARD = "BOOKING";   // Booking form identifier
    public static final String PAYMENT_CARD = "PAYMENT";   // Payment page identifier
    public static final String RECEIPT_CARD = "RECEIPT";   // Receipt page identifier

    // ========================================
    // APPLICATION ENTRY POINT
    // ========================================
    /**
     * Main method - Application entry point
     *
     * This method demonstrates:
     * - Proper application initialization
     * - Exception handling with try-catch blocks
     * - SwingUtilities.invokeLater() for thread safety in GUI applications
     * - System Look and Feel for native appearance
     *
     * @param args Command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        // Set system look and feel for native appearance
        // This makes the application look consistent with the operating system
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // If system look and feel fails, use default Java look and feel
            System.out.println("Using default look and feel.");
        }

        // Use SwingUtilities.invokeLater() to ensure GUI creation happens on Event Dispatch Thread
        // This is a best practice for thread safety in Swing applications
        SwingUtilities.invokeLater(() -> {
            try {
                // Create new instance of Main class (which extends JFrame)
                Main app = new Main();

                // Make the application window visible to user
                app.setVisible(true);

                // Console output for debugging and confirmation
                System.out.println("Event Ticket Booking started successfully!");

            } catch (Exception e) {
                // Error handling - show user-friendly error dialog if startup fails
                JOptionPane.showMessageDialog(null,
                    "Error starting application: " + e.getMessage(),
                    "Startup Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // ========================================
    // CONSTRUCTOR - APPLICATION INITIALIZATION
    // ========================================
    /**
     * Main Constructor - Initializes the complete application
     *
     * This constructor demonstrates the proper order of initialization:
     * 1. Create business logic controller (Model in MVC)
     * 2. Initialize the main window frame
     * 3. Create all panel components (Views in MVC)
     * 4. Setup layout and navigation system
     *
     * This follows the CONSTRUCTOR CHAINING pattern for organized initialization
     */
    public Main() {
        // Step 1: Initialize business logic controller
        // This handles all data management and business rules
        bookingController = new BookingController();

        // Step 2: Initialize the main application window
        initializeFrame();

        // Step 3: Create all panel components for different screens
        initializePanels();

        // Step 4: Setup CardLayout navigation system
        setupLayout();
    }

    /**
     * Initialize the main frame
     */
    private void initializeFrame() {
        setTitle("Event Ticket Booking - BIT 4043 OOP Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setResizable(true);

        // Set beautiful background
        getContentPane().setBackground(BACKGROUND);

        // Set application icon
        try {
            File iconFile = new File("images/icon.png");
            if (iconFile.exists()) {
                setIconImage(Toolkit.getDefaultToolkit().createImage("images/icon.png"));
            }
        } catch (Exception e) {
            // Ignore if no icon available
        }
    }

    /**
     * Initialize all panels
     */
    private void initializePanels() {
        homePanel = new HomePanel(this, bookingController);
        bookingFormPanel = new BookingFormPanel(this, bookingController);
        paymentPanel = new PaymentPanel(this, bookingController);  // Initialize payment panel
        receiptPanel = new ReceiptPanel(this, bookingController);
    }

    /**
     * Setup the layout with CardLayout
     */
    private void setupLayout() {
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(BACKGROUND);

        // Add panels to card layout
        contentPanel.add(homePanel, HOME_CARD);
        contentPanel.add(bookingFormPanel, BOOKING_CARD);
        contentPanel.add(paymentPanel, PAYMENT_CARD);      // Add payment panel
        contentPanel.add(receiptPanel, RECEIPT_CARD);

        // Add content panel to frame
        add(contentPanel, BorderLayout.CENTER);

        // Show home panel initially
        showCard(HOME_CARD);
    }

    // Navigation methods
    public void showCard(String cardName) {
        cardLayout.show(contentPanel, cardName);
    }

    public void showHome() {
        homePanel.refreshEventList();
        showCard(HOME_CARD);
    }

    public void showBookingForm(int eventId) {
        bookingFormPanel.setSelectedEvent(eventId);
        showCard(BOOKING_CARD);
    }

    /**
     * Shows the payment panel with booking information
     * This method demonstrates data transfer between panels
     *
     * @param customerName Customer's full name
     * @param customerEmail Customer's email address
     * @param customerPhone Customer's phone number
     * @param event Selected event for booking
     * @param seatType Type of seat selected
     * @param quantity Number of tickets
     * @param totalAmount Total payment amount
     */
    public void showPayment(String customerName, String customerEmail, String customerPhone,
                           Event event, String seatType, int quantity, java.math.BigDecimal totalAmount) {
        paymentPanel.setBookingInfo(customerName, customerEmail, customerPhone,
                                   event, seatType, quantity, totalAmount);
        showCard(PAYMENT_CARD);
    }

    public void showReceipt(Booking booking) {
        receiptPanel.displayBooking(booking);
        showCard(RECEIPT_CARD);
    }

    public BookingController getBookingController() {
        return bookingController;
    }

    /**
     * Create a header panel with consistent background image across all pages
     * @param title Main title text
     * @param subtitle Secondary text (can be null)
     * @param backgroundColor Fallback color if no background image
     * @return JPanel with background image and text
     */
    public static JPanel createHeaderWithBackground(String title, String subtitle, Color backgroundColor) {
        // Create custom panel with background image support
        JPanel headerPanel = new JPanel(new BorderLayout()) {
            private Image backgroundImage;

            {
                // Try to load background image
                try {
                    File bgFile = new File("images/header_background.jpg");
                    if (bgFile.exists()) {
                        backgroundImage = Toolkit.getDefaultToolkit().createImage("images/header_background.jpg");
                    }
                } catch (Exception e) {
                    backgroundImage = null;
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    // Draw background image scaled to fit
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

                    // Add semi-transparent overlay for better text readability
                    g2d.setColor(new Color(backgroundColor.getRed(), backgroundColor.getGreen(),
                                         backgroundColor.getBlue(), 180)); // 180/255 = ~70% opacity
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                    g2d.dispose();
                } else {
                    // Fallback to solid color background
                    setBackground(backgroundColor);
                }
            }
        };

        // Set transparent background so custom painting shows
        boolean hasBackgroundImage = new File("images/header_background.jpg").exists();
        headerPanel.setOpaque(!hasBackgroundImage);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Create title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(subtitle != null ? new GridLayout(2, 1, 10, 10) : new GridLayout(1, 1));
        titlePanel.setOpaque(false); // Make transparent for background image

        // Main title
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        // Subtitle (if provided)
        if (subtitle != null) {
            JLabel subtitleLabel = new JLabel(subtitle, JLabel.CENTER);
            subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            subtitleLabel.setForeground(new Color(220, 220, 220));
            titlePanel.add(subtitleLabel);
        }

        headerPanel.add(titlePanel, BorderLayout.CENTER);
        return headerPanel;
    }
}
