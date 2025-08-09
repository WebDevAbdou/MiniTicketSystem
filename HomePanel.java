
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * Home Panel for Mini Ticket System
 * Academic project for BIT 4043 OOP course
 * Displays system information and available events
 */
public class HomePanel extends JPanel {
    private Main mainFrame;
    private BookingController bookingController;
    private JPanel eventsPanel;
    private JScrollPane scrollPane;

    public HomePanel(Main mainFrame, BookingController bookingController) {
        this.mainFrame = mainFrame;
        this.bookingController = bookingController;
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Create header panel
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        // Create events panel
        createEventsPanel();
        add(scrollPane, BorderLayout.CENTER);

        // Create footer panel
        JPanel footerPanel = createFooterPanel();
        add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel createHeaderPanel() {
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
                    g2d.setColor(new Color(Main.PRIMARY_DARK.getRed(), Main.PRIMARY_DARK.getGreen(),
                                         Main.PRIMARY_DARK.getBlue(), 180)); // 180/255 = ~70% opacity
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                    g2d.dispose();
                } else {
                    // Fallback to solid color background
                    setBackground(Main.PRIMARY_DARK);
                }
            }
        };

        // Set transparent background so custom painting shows
        // Check if background image exists
        boolean hasBackgroundImage = new File("images/header_background.jpg").exists();
        headerPanel.setOpaque(!hasBackgroundImage);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Create centered brand panel with logo and title
        JPanel brandPanel = createCenteredBrandPanel();
        headerPanel.add(brandPanel, BorderLayout.CENTER);
        return headerPanel;
    }

    /**
     * Create centered brand panel with logo and application name
     */
    private JPanel createCenteredBrandPanel() {
        JPanel brandPanel = new JPanel();
        brandPanel.setLayout(new BoxLayout(brandPanel, BoxLayout.Y_AXIS));
        brandPanel.setOpaque(false); // Transparent for background image

        // Main brand row with logo and title
        JPanel mainBrandRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        mainBrandRow.setOpaque(false);

        // Create logo component
        JLabel logoLabel = createScaledLogo();
        if (logoLabel != null) {
            mainBrandRow.add(logoLabel);
        }

        // Application name with elegant styling
        JLabel appNameLabel = new JLabel("Event Ticket Booking");
        appNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 42));
        appNameLabel.setForeground(Color.WHITE);
        // Add text shadow effect
        appNameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        mainBrandRow.add(appNameLabel);

        // Subtitle
        JLabel subtitleLabel = new JLabel("BIT 4043 Object-Oriented Programming Project");
        subtitleLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        subtitleLabel.setForeground(new Color(220, 220, 220));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Welcome message with elegant styling
        JLabel welcomeLabel = new JLabel("Welcome! Browse our exciting events and book your tickets.");
        welcomeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        welcomeLabel.setForeground(new Color(135, 206, 250)); // Light sky blue
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components with proper spacing
        brandPanel.add(Box.createVerticalStrut(10));
        brandPanel.add(mainBrandRow);
        brandPanel.add(Box.createVerticalStrut(8));
        brandPanel.add(subtitleLabel);
        brandPanel.add(Box.createVerticalStrut(5));
        brandPanel.add(welcomeLabel);
        brandPanel.add(Box.createVerticalStrut(10));

        return brandPanel;
    }

    /**
     * Create scaled logo for the brand panel
     */
    private JLabel createScaledLogo() {
        try {
            // Try to load the logo with proper scaling for header brand
            File logoFile = new File("images/logo.png");
            if (logoFile.exists()) {
                ImageIcon originalLogo = new ImageIcon("images/logo.png");

                // Calculate optimal size for brand header (professional size)
                int maxWidth = 80;   // Bigger but still professional
                int maxHeight = 70;  // Proportional height

                Image originalImage = originalLogo.getImage();
                int originalWidth = originalLogo.getIconWidth();
                int originalHeight = originalLogo.getIconHeight();

                // Calculate scaling factor to fit within bounds while maintaining aspect ratio
                double scaleX = (double) maxWidth / originalWidth;
                double scaleY = (double) maxHeight / originalHeight;
                double scale = Math.min(scaleX, scaleY); // Use smaller scale to fit both dimensions

                int scaledWidth = (int) (originalWidth * scale);
                int scaledHeight = (int) (originalHeight * scale);

                // Scale the image with high quality
                Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
                ImageIcon scaledLogo = new ImageIcon(scaledImage);

                JLabel logoLabel = new JLabel(scaledLogo);
                logoLabel.setHorizontalAlignment(JLabel.CENTER);
                logoLabel.setVerticalAlignment(JLabel.CENTER);

                return logoLabel;
            } else {
                // Return null if no logo file - will show text-only brand
                return null;
            }
        } catch (Exception e) {
            // Return null if error - will show text-only brand
            return null;
        }
    }

    /**
     * Create text-based logo fallback
     */
    private void createTextLogo(JLabel logoLabel) {
        logoLabel.setText("<html><div style='text-align: center;'>" +
                        "<div style='font-size: 24px; color: white;'>🎫</div>" +
                        "<div style='font-size: 14px; color: white; font-weight: bold;'>MINI TICKET</div>" +
                        "<div style='font-size: 12px; color: lightgray;'>SYSTEM</div>" +
                        "</html>");
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoLabel.setVerticalAlignment(JLabel.CENTER);
    }

    private void createEventsPanel() {
        eventsPanel = new JPanel();
        eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));
        eventsPanel.setBackground(Color.WHITE);
        eventsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add title for events section
        JLabel eventsTitle = new JLabel("Available Events");
        eventsTitle.setFont(new Font("Arial", Font.BOLD, 20));
        eventsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        eventsPanel.add(eventsTitle);
        eventsPanel.add(Box.createVerticalStrut(20));

        // Load events
        refreshEventList();

        scrollPane = new JScrollPane(eventsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    public void refreshEventList() {
        // Remove existing event panels (keep title)
        Component[] components = eventsPanel.getComponents();
        for (int i = 2; i < components.length; i++) {
            eventsPanel.remove(components[i]);
        }

        ArrayList<Event> events = bookingController.getAllEvents();

        for (Event event : events) {
            JPanel eventPanel = createEventPanel(event);
            eventsPanel.add(eventPanel);
            eventsPanel.add(Box.createVerticalStrut(15));
        }

        eventsPanel.revalidate();
        eventsPanel.repaint();
    }

    private JPanel createEventPanel(Event event) {
        JPanel eventPanel = new JPanel(new BorderLayout());
        // Beautiful card design with professional styling
        eventPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 221, 225), 1),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        eventPanel.setBackground(Main.CARD_WHITE);
        eventPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 280)); // Increased height

        // Create image panel
        JPanel imagePanel = createImagePanel(event);
        eventPanel.add(imagePanel, BorderLayout.WEST);

        // Create content panel (details + button)
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Main.CARD_WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Event details panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Main.CARD_WHITE);

        // Event name
        JLabel nameLabel = new JLabel(event.getName());
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        nameLabel.setForeground(Main.TEXT_DARK);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Event description
        JLabel descLabel = new JLabel("<html><div style='width: 400px; line-height: 1.5;'>" + event.getDescription() + "</div></html>");
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descLabel.setForeground(Main.TEXT_LIGHT);
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Date info - simple label approach
        JLabel dateLabel = new JLabel("📅 " + event.getDate());
        dateLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        dateLabel.setForeground(Main.PRIMARY_BLUE);
        dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Venue info - simple label approach
        JLabel venueLabel = new JLabel("📍 " + event.getVenue());
        venueLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        venueLabel.setForeground(Main.PRIMARY_BLUE);
        venueLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Add components to details panel in proper order: Title → Description → Date → Venue
        detailsPanel.add(nameLabel);
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(descLabel);
        detailsPanel.add(Box.createVerticalStrut(8));
        detailsPanel.add(dateLabel);
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(venueLabel);
        detailsPanel.add(Box.createVerticalGlue()); // Push content to top

        // Create compact price and button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Main.CARD_WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0)); // Reduced padding

        // Compact price panel - positioned above button
        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0)); // No padding
        pricePanel.setBackground(Main.CARD_WHITE);
        pricePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); // Limit height

        JPanel priceInfoPanel = new JPanel();
        priceInfoPanel.setLayout(new BoxLayout(priceInfoPanel, BoxLayout.Y_AXIS));
        priceInfoPanel.setBackground(Main.CARD_WHITE);

        JLabel priceLabel = new JLabel("Starting from: $" + event.getBasePrice());
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Smaller font
        priceLabel.setForeground(Main.SUCCESS_GREEN);
        priceLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JLabel seatsLabel = new JLabel(event.getAvailableSeats() + " seats available");
        seatsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12)); // Smaller font
        seatsLabel.setForeground(Main.TEXT_LIGHT);
        seatsLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        priceInfoPanel.add(priceLabel);
        priceInfoPanel.add(seatsLabel);
        pricePanel.add(priceInfoPanel);

        // Button panel
        JPanel bookButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bookButtonPanel.setBackground(Main.CARD_WHITE);

        JButton bookButton = new JButton("Book Tickets");
        bookButton.setPreferredSize(new Dimension(160, 50));
        bookButton.setBackground(Main.ACCENT_BLUE);
        bookButton.setForeground(Color.WHITE);
        bookButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        bookButton.setFocusPainted(false);
        bookButton.setOpaque(true);
        bookButton.setBorderPainted(false);
        bookButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Beautiful rounded button effect
        bookButton.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        // Add beautiful hover effect
        bookButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bookButton.setBackground(Main.PRIMARY_BLUE);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bookButton.setBackground(Main.ACCENT_BLUE);
            }
        });

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showBookingForm(event.getId());
            }
        });

        bookButtonPanel.add(bookButton);

        // Add price panel and button panel to main button panel with minimal spacing
        buttonPanel.add(pricePanel);
        buttonPanel.add(Box.createVerticalStrut(3)); // Reduced spacing
        buttonPanel.add(bookButtonPanel);

        // Add details and button to content panel
        contentPanel.add(detailsPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        eventPanel.add(contentPanel, BorderLayout.CENTER);

        return eventPanel;
    }

    /**
     * Create image panel for event with fallback to placeholder
     */
    private JPanel createImagePanel(Event event) {
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setPreferredSize(new Dimension(200, 220));
        imagePanel.setBackground(new Color(245, 245, 245));
        imagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);

        try {
            // Try to load the event image
            File imageFile = new File(event.getImagePath());
            if (imageFile.exists()) {
                ImageIcon originalIcon = new ImageIcon(event.getImagePath());
                Image scaledImage = originalIcon.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                imageLabel.setIcon(scaledIcon);
            } else {
                // Fallback to placeholder
                createPlaceholderImage(imageLabel, event.getName());
            }
        } catch (Exception e) {
            // If image loading fails, create placeholder
            createPlaceholderImage(imageLabel, event.getName());
        }

        imagePanel.add(imageLabel, BorderLayout.CENTER);
        return imagePanel;
    }

    /**
     * Create a placeholder image when actual image is not available
     */
    private void createPlaceholderImage(JLabel imageLabel, String eventName) {
        // Create a simple colored placeholder
        imageLabel.setText("<html><div style='text-align: center; width: 160px;'>" +
                          "<div style='font-size: 24px; margin-bottom: 10px;'>🎭</div>" +
                          "<div style='font-size: 12px; color: #666;'>" + eventName + "</div>" +
                          "</html>");
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        imageLabel.setOpaque(true);
        imageLabel.setBackground(new Color(230, 230, 230));
        imageLabel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout());
        footerPanel.setBackground(new Color(240, 240, 240));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel footerLabel = new JLabel("© 2025 Event Ticket Booking - Academic Project");
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 10));
        footerLabel.setForeground(Color.GRAY);

        footerPanel.add(footerLabel);
        return footerPanel;
    }
}
