import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * Payment Panel for Event Ticket Booking System
 * Academic project for BIT 4043 Object-Oriented Programming course
 *
 * This panel demonstrates:
 * - Form validation techniques
 * - Input formatting and validation
 * - User interface design for payment processing
 * - Business process simulation
 *
 * @author BIT 4043 Student
 * @version 1.0
 */
public class PaymentPanel extends JPanel {
    // Main application components for navigation and data management
    private Main mainFrame;
    private BookingController bookingController;

    // Payment form components - demonstrates various input field types
    private JTextField cardNumberField;      // Credit card number input
    private JTextField cardHolderField;      // Cardholder name input
    private JComboBox<String> expiryMonthCombo;  // Expiry month dropdown
    private JComboBox<String> expiryYearCombo;   // Expiry year dropdown
    private JTextField cvvField;             // CVV security code input

    // Action buttons for user interaction
    private JButton processPaymentButton;    // Process payment simulation
    private JButton backButton;              // Return to booking form

    // Labels for displaying booking and payment information
    private JLabel bookingInfoLabel;         // Shows booking details
    private JLabel totalAmountLabel;         // Shows total payment amount

    // Current booking data for payment processing
    private String customerName;             // Customer information
    private String customerEmail;
    private String customerPhone;
    private Event selectedEvent;             // Event being booked
    private String seatType;                 // Selected seat type
    private int quantity;                    // Number of tickets
    private BigDecimal totalAmount;          // Total payment amount

    /**
     * Constructor - Initialize the payment panel
     * This demonstrates proper component initialization and layout setup
     *
     * @param mainFrame Reference to main application window
     * @param bookingController Reference to booking management system
     */
    public PaymentPanel(Main mainFrame, BookingController bookingController) {
        this.mainFrame = mainFrame;
        this.bookingController = bookingController;
        initializePanel();
    }

    /**
     * Initialize the panel layout and components
     * This method demonstrates proper GUI initialization patterns
     */
    private void initializePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Create header with consistent background image
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        // Create payment form in center
        JPanel formPanel = createPaymentForm();
        add(formPanel, BorderLayout.CENTER);

        // Create button panel at bottom
        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Creates the header panel with consistent branding
     * Uses the same background image system as other pages
     *
     * @return JPanel containing the header with background image
     */
    private JPanel createHeaderPanel() {
        // Use consistent header background across all pages
        JPanel headerPanel = Main.createHeaderWithBackground(
            "Secure Payment",
            "Complete your booking with secure payment processing",
            Main.PRIMARY_DARK
        );

        return headerPanel;
    }

    /**
     * Creates the main payment form with professional styling
     * This method demonstrates advanced form layout and input validation setup
     *
     * @return JPanel containing the complete payment form
     */
    private JPanel createPaymentForm() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Booking summary section
        JPanel summaryPanel = createBookingSummary();
        formPanel.add(summaryPanel);
        formPanel.add(Box.createVerticalStrut(30));

        // Payment details section
        JPanel paymentDetailsPanel = createPaymentDetails();
        formPanel.add(paymentDetailsPanel);

        return formPanel;
    }

    /**
     * Creates booking summary display
     * Shows what the customer is paying for - important for user confidence
     *
     * @return JPanel containing booking summary information
     */
    private JPanel createBookingSummary() {
        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
        summaryPanel.setBackground(new Color(248, 249, 250));
        summaryPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 221, 225), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Section title
        JLabel titleLabel = new JLabel("Booking Summary");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Main.TEXT_DARK);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Booking information display
        bookingInfoLabel = new JLabel("Please select an event to continue");
        bookingInfoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        bookingInfoLabel.setForeground(Main.TEXT_LIGHT);
        bookingInfoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Total amount display - prominent for payment confirmation
        totalAmountLabel = new JLabel("Total: $0.00");
        totalAmountLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        totalAmountLabel.setForeground(Main.SUCCESS_GREEN);
        totalAmountLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        summaryPanel.add(titleLabel);
        summaryPanel.add(Box.createVerticalStrut(10));
        summaryPanel.add(bookingInfoLabel);
        summaryPanel.add(Box.createVerticalStrut(15));
        summaryPanel.add(totalAmountLabel);

        return summaryPanel;
    }

    /**
     * Creates the payment details form
     * This demonstrates various input field types and validation setup
     *
     * @return JPanel containing payment input fields
     */
    private JPanel createPaymentDetails() {
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Section title
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        JLabel sectionTitle = new JLabel("Payment Information");
        sectionTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        sectionTitle.setForeground(Main.TEXT_DARK);
        detailsPanel.add(sectionTitle, gbc);

        // Card Number field - demonstrates input formatting
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        detailsPanel.add(new JLabel("Card Number:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        cardNumberField = new JTextField(20);
        cardNumberField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cardNumberField.setToolTipText("Enter 16-digit card number");
        detailsPanel.add(cardNumberField, gbc);

        // Cardholder Name field
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        detailsPanel.add(new JLabel("Cardholder Name:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        cardHolderField = new JTextField(20);
        cardHolderField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cardHolderField.setToolTipText("Name as it appears on card");
        detailsPanel.add(cardHolderField, gbc);

        // Expiry Date - demonstrates dropdown components
        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE;
        detailsPanel.add(new JLabel("Expiry Date:"), gbc);

        JPanel expiryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        expiryPanel.setBackground(Color.WHITE);

        // Month dropdown
        String[] months = {"01", "02", "03", "04", "05", "06",
                          "07", "08", "09", "10", "11", "12"};
        expiryMonthCombo = new JComboBox<>(months);
        expiryMonthCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Year dropdown - demonstrates dynamic data generation
        String[] years = new String[10];
        int currentYear = java.time.Year.now().getValue();
        for (int i = 0; i < 10; i++) {
            years[i] = String.valueOf(currentYear + i);
        }
        expiryYearCombo = new JComboBox<>(years);
        expiryYearCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        expiryPanel.add(expiryMonthCombo);
        expiryPanel.add(new JLabel("/"));
        expiryPanel.add(expiryYearCombo);

        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        detailsPanel.add(expiryPanel, gbc);

        // CVV field - demonstrates input length limitation
        gbc.gridx = 0; gbc.gridy = 4; gbc.fill = GridBagConstraints.NONE;
        detailsPanel.add(new JLabel("CVV:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        cvvField = new JTextField(4);
        cvvField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cvvField.setToolTipText("3-digit security code");
        detailsPanel.add(cvvField, gbc);

        return detailsPanel;
    }

    /**
     * Creates the button panel for user actions
     * This demonstrates proper button layout and event handling
     *
     * @return JPanel containing action buttons
     */
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Back button - allows user to return to booking form
        backButton = new JButton("← Back to Booking");
        backButton.setPreferredSize(new Dimension(160, 50));
        backButton.setBackground(Main.TEXT_LIGHT);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> mainFrame.showCard(Main.BOOKING_CARD));

        // Process Payment button - main action button
        processPaymentButton = new JButton("🔒 Process Payment");
        processPaymentButton.setPreferredSize(new Dimension(180, 50));
        processPaymentButton.setBackground(Main.SUCCESS_GREEN);
        processPaymentButton.setForeground(Color.WHITE);
        processPaymentButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        processPaymentButton.setOpaque(true);
        processPaymentButton.setBorderPainted(false);
        processPaymentButton.setFocusPainted(false);
        processPaymentButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        processPaymentButton.addActionListener(new PaymentActionListener());

        buttonPanel.add(backButton);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(processPaymentButton);

        return buttonPanel;
    }

    /**
     * Sets the booking information for payment processing
     * This method demonstrates data transfer between GUI components
     *
     * @param customerName Customer's full name
     * @param customerEmail Customer's email address
     * @param customerPhone Customer's phone number
     * @param event Selected event for booking
     * @param seatType Type of seat selected
     * @param quantity Number of tickets
     * @param totalAmount Total payment amount
     */
    public void setBookingInfo(String customerName, String customerEmail, String customerPhone,
                              Event event, String seatType, int quantity, BigDecimal totalAmount) {
        // Store booking information for payment processing
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.selectedEvent = event;
        this.seatType = seatType;
        this.quantity = quantity;
        this.totalAmount = totalAmount;

        // Update display labels with booking information
        updateBookingDisplay();
    }

    /**
     * Updates the booking summary display
     * This demonstrates dynamic UI updates based on data changes
     */
    private void updateBookingDisplay() {
        if (selectedEvent != null) {
            // Create formatted booking summary text
            String bookingInfo = String.format(
                "<html><div style='line-height: 1.4;'>" +
                "<b>Event:</b> %s<br>" +
                "<b>Date:</b> %s<br>" +
                "<b>Venue:</b> %s<br>" +
                "<b>Seat Type:</b> %s<br>" +
                "<b>Quantity:</b> %d ticket(s)<br>" +
                "<b>Customer:</b> %s" +
                "</div></html>",
                selectedEvent.getName(),
                selectedEvent.getDate(),
                selectedEvent.getVenue(),
                seatType,
                quantity,
                customerName
            );

            bookingInfoLabel.setText(bookingInfo);
            totalAmountLabel.setText(String.format("Total: $%.2f", totalAmount));
        }
    }

    /**
     * Inner class for handling payment button actions
     * This demonstrates event handling and form validation
     */
    private class PaymentActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Validate payment form before processing
            if (validatePaymentForm()) {
                processPayment();
            }
        }
    }

    /**
     * Validates all payment form fields
     * This method demonstrates comprehensive input validation techniques
     *
     * @return true if all fields are valid, false otherwise
     */
    private boolean validatePaymentForm() {
        StringBuilder errors = new StringBuilder();

        // Validate card number - demonstrates regex pattern matching
        String cardNumber = cardNumberField.getText().replaceAll("\\s+", "");
        if (!isValidCardNumber(cardNumber)) {
            errors.append("• Please enter a valid 16-digit card number\n");
        }

        // Validate cardholder name - demonstrates string validation
        String cardHolder = cardHolderField.getText().trim();
        if (cardHolder.isEmpty() || cardHolder.length() < 2) {
            errors.append("• Please enter the cardholder's name\n");
        }

        // Validate CVV - demonstrates numeric input validation
        String cvv = cvvField.getText().trim();
        if (!isValidCVV(cvv)) {
            errors.append("• Please enter a valid 3-digit CVV\n");
        }

        // Validate expiry date - demonstrates date validation logic
        if (!isValidExpiryDate()) {
            errors.append("• Please select a valid expiry date\n");
        }

        // Show validation errors if any exist
        if (errors.length() > 0) {
            JOptionPane.showMessageDialog(this,
                "Please correct the following errors:\n\n" + errors.toString(),
                "Payment Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    /**
     * Validates credit card number format
     * This demonstrates regex pattern matching for input validation
     *
     * @param cardNumber The card number to validate
     * @return true if valid format, false otherwise
     */
    private boolean isValidCardNumber(String cardNumber) {
        // Check if card number contains only digits and is 16 characters long
        // This is a simplified validation for academic purposes
        Pattern cardPattern = Pattern.compile("^\\d{16}$");
        return cardPattern.matcher(cardNumber).matches();
    }

    /**
     * Validates CVV security code
     * This demonstrates numeric input validation
     *
     * @param cvv The CVV to validate
     * @return true if valid format, false otherwise
     */
    private boolean isValidCVV(String cvv) {
        // Check if CVV is exactly 3 digits
        Pattern cvvPattern = Pattern.compile("^\\d{3}$");
        return cvvPattern.matcher(cvv).matches();
    }

    /**
     * Validates expiry date selection
     * This demonstrates date validation logic
     *
     * @return true if expiry date is in the future, false otherwise
     */
    private boolean isValidExpiryDate() {
        try {
            int selectedMonth = Integer.parseInt((String) expiryMonthCombo.getSelectedItem());
            int selectedYear = Integer.parseInt((String) expiryYearCombo.getSelectedItem());

            // Get current date for comparison
            java.time.LocalDate now = java.time.LocalDate.now();
            java.time.LocalDate expiryDate = java.time.LocalDate.of(selectedYear, selectedMonth, 1);

            // Check if expiry date is in the future
            return expiryDate.isAfter(now.withDayOfMonth(1));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Processes payment directly without popup dialog
     * This method demonstrates seamless user experience
     */
    private void processPayment() {
        // Disable the payment button to prevent double-clicking
        processPaymentButton.setEnabled(false);
        processPaymentButton.setText("Processing...");

        // Create a background payment processing simulation
        // This demonstrates threading concepts without interrupting user flow
        SwingWorker<Void, Void> paymentWorker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Simulate realistic payment processing in background
                // 4-second processing delay for realistic experience
                Thread.sleep(4000);
                return null;
            }

            @Override
            protected void done() {
                // Re-enable button
                processPaymentButton.setEnabled(true);
                processPaymentButton.setText("🔒 Process Payment");

                // Automatically complete booking and show receipt
                completeBooking();
            }
        };

        // Execute payment processing in background - no popup dialog
        paymentWorker.execute();
    }

    /**
     * Completes the booking after successful payment
     * This demonstrates the final step in the business process
     * Automatically navigates to receipt page for seamless user experience
     */
    private void completeBooking() {
        try {
            // Create the booking object with all collected information
            Booking booking = bookingController.createBooking(
                customerName, customerEmail, customerPhone,
                selectedEvent.getId(), seatType, quantity
            );

            if (booking != null) {
                // Automatically navigate to receipt page - no intermediate dialog needed
                // This creates a smooth, professional user experience
                mainFrame.showReceipt(booking);
            } else {
                JOptionPane.showMessageDialog(this,
                    "Failed to create booking. Please try again.",
                    "Booking Error",
                    JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            // Handle booking creation errors - only show dialog for actual errors
            JOptionPane.showMessageDialog(this,
                "Error creating booking: " + e.getMessage(),
                "Booking Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Clears all payment form fields
     * This method demonstrates form reset functionality
     */
    public void clearForm() {
        cardNumberField.setText("");
        cardHolderField.setText("");
        cvvField.setText("");
        expiryMonthCombo.setSelectedIndex(0);
        expiryYearCombo.setSelectedIndex(0);

        bookingInfoLabel.setText("Please select an event to continue");
        totalAmountLabel.setText("Total: $0.00");
    }
}
