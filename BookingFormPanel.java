
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

/**
 * Booking Form Panel for Mini Ticket System
 * Academic project for BIT 4043 OOP course
 * Handles ticket booking form with price calculation
 */
public class BookingFormPanel extends JPanel {
    private Main mainFrame;
    private BookingController bookingController;
    private Event selectedEvent;

    // Form components
    private JLabel eventInfoLabel;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JComboBox<String> seatTypeCombo;
    private JSpinner quantitySpinner;
    private JLabel priceLabel;
    private JButton calculateButton;
    private JButton bookButton;
    private JButton backButton;

    public BookingFormPanel(Main mainFrame, BookingController bookingController) {
        this.mainFrame = mainFrame;
        this.bookingController = bookingController;
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Create header
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        // Create form panel
        JPanel formPanel = createFormPanel();
        add(formPanel, BorderLayout.CENTER);

        // Create button panel
        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createHeaderPanel() {
        // Use the reusable header method with background image
        JPanel headerPanel = Main.createHeaderWithBackground(
            "Book Your Tickets",
            null, // No subtitle, we'll add event info separately
            Main.PRIMARY_DARK
        );

        // Add event info label below the main header
        eventInfoLabel = new JLabel("Select an event to continue", JLabel.CENTER);
        eventInfoLabel.setFont(new Font("Segoe UI", Font.ITALIC, 17));
        eventInfoLabel.setForeground(new Color(135, 206, 250)); // Light sky blue
        eventInfoLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        // Create a wrapper to add the event info
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setOpaque(false);
        wrapperPanel.add(headerPanel, BorderLayout.CENTER);
        wrapperPanel.add(eventInfoLabel, BorderLayout.SOUTH);

        return wrapperPanel;
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Customer Name
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Customer Name:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        nameField = new JTextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 12));
        formPanel.add(nameField, gbc);

        // Customer Email
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        emailField = new JTextField(20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 12));
        formPanel.add(emailField, gbc);

        // Customer Phone
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        phoneField = new JTextField(20);
        phoneField.setFont(new Font("Arial", Font.PLAIN, 12));
        formPanel.add(phoneField, gbc);

        // Seat Type
        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Seat Type:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        seatTypeCombo = new JComboBox<>(bookingController.getSeatTypes());
        seatTypeCombo.setFont(new Font("Arial", Font.PLAIN, 12));
        seatTypeCombo.addActionListener(e -> updatePrice());
        formPanel.add(seatTypeCombo, gbc);

        // Quantity
        gbc.gridx = 0; gbc.gridy = 4; gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Quantity:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        quantitySpinner.setFont(new Font("Arial", Font.PLAIN, 12));
        quantitySpinner.addChangeListener(e -> updatePrice());
        formPanel.add(quantitySpinner, gbc);

        // Price calculation
        gbc.gridx = 0; gbc.gridy = 5; gbc.fill = GridBagConstraints.NONE;
        calculateButton = new JButton("Calculate Price");
        calculateButton.setBackground(new Color(100, 150, 200));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFont(new Font("Arial", Font.BOLD, 12));
        calculateButton.setOpaque(true);
        calculateButton.setBorderPainted(false);
        calculateButton.setFocusPainted(false);
        calculateButton.addActionListener(e -> updatePrice());
        formPanel.add(calculateButton, gbc);

        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        priceLabel = new JLabel("Total: $0.00");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        priceLabel.setForeground(new Color(0, 120, 0));
        formPanel.add(priceLabel, gbc);

        return formPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        backButton = new JButton("Back to Home");
        backButton.setPreferredSize(new Dimension(120, 35));
        backButton.setBackground(new Color(150, 150, 150));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 12));
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> mainFrame.showHome());

        bookButton = new JButton("Book Tickets");
        bookButton.setPreferredSize(new Dimension(120, 35));
        bookButton.setBackground(new Color(70, 130, 180));
        bookButton.setForeground(Color.WHITE);
        bookButton.setFont(new Font("Arial", Font.BOLD, 12));
        bookButton.setOpaque(true);
        bookButton.setBorderPainted(false);
        bookButton.setFocusPainted(false);
        bookButton.addActionListener(new BookingActionListener());

        buttonPanel.add(backButton);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(bookButton);

        return buttonPanel;
    }

    public void setSelectedEvent(int eventId) {
        selectedEvent = bookingController.getEventById(eventId);
        if (selectedEvent != null) {
            eventInfoLabel.setText(selectedEvent.getName() + " - " + selectedEvent.getDate() + " at " + selectedEvent.getVenue());
            updatePrice();
        }
    }

    private void updatePrice() {
        if (selectedEvent != null) {
            String seatType = (String) seatTypeCombo.getSelectedItem();
            int quantity = (Integer) quantitySpinner.getValue();
            BigDecimal totalPrice = bookingController.calculatePrice(selectedEvent.getId(), seatType, quantity);
            priceLabel.setText("Total: $" + totalPrice.toString());
        }
    }

    /**
     * Inner class for handling booking form submission
     * This demonstrates event handling and form processing
     * Now navigates to payment page instead of directly creating booking
     */
    private class BookingActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Validate form before proceeding to payment
            if (validateForm()) {
                // Collect customer information from form
                String customerName = nameField.getText().trim();
                String customerEmail = emailField.getText().trim();
                String customerPhone = phoneField.getText().trim();
                String seatType = (String) seatTypeCombo.getSelectedItem();
                int quantity = (Integer) quantitySpinner.getValue();

                // Calculate total amount for payment
                BigDecimal totalAmount = bookingController.calculatePrice(
                    selectedEvent.getId(), seatType, quantity);

                // Navigate to payment page with all booking information
                // This demonstrates the complete booking workflow
                mainFrame.showPayment(customerName, customerEmail, customerPhone,
                                    selectedEvent, seatType, quantity, totalAmount);
            }
        }
    }

    private boolean validateForm() {
        if (selectedEvent == null) {
            JOptionPane.showMessageDialog(this, "Please select an event.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (nameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter customer name.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            nameField.requestFocus();
            return false;
        }
        if (emailField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter email address.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            emailField.requestFocus();
            return false;
        }
        return true;
    }

    private void clearForm() {
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        seatTypeCombo.setSelectedIndex(0);
        quantitySpinner.setValue(1);
        priceLabel.setText("Total: $0.00");
    }
}
