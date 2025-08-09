# 🎫 Event Ticket Booking System

A professional, modern event ticket booking application built with Java Swing for **BIT 4043 Object-Oriented Programming** course.

## ✨ Key Features

### 🏠 **Home Module**
- **Event Gallery**: Browse 12+ events with high-quality images
- **System Information**: Display computer specs and Java version
- **Professional Branding**: Logo and consistent header design
- **Event Categories**: Sports, Music, Entertainment, Cultural events

### 📝 **Booking Form Module** 
- **Customer Information**: Name, email, phone validation
- **Seat Selection**: Standard, VIP, Premium options with pricing
- **Dynamic Pricing**: Real-time price calculation
- **Form Validation**: Comprehensive input checking with error messages

### 💳 **Payment Processing Module**
- **Secure Payment Form**: Credit card details with validation
- **Real-time Validation**: Card number, CVV, expiry date checking
- **Background Processing**: 4-second realistic payment simulation
- **Seamless Flow**: Direct transition to receipt (no popups)

### 🧾 **Receipt Module**
- **Detailed Confirmation**: Complete booking summary
- **PDF Download**: Professional receipt generation
- **Booking Management**: Unique booking ID and timestamp
- **Navigation Options**: Return home or make new booking

## 🚀 Quick Start Guide

### Prerequisites
- **Java 8+** (JDK 8 or higher)
- **Java IDE** (IntelliJ IDEA recommended)

### Running the Application
```bash
# 1. Navigate to project directory
cd MiniTicketSystem

# 2. Compile all Java files
javac *.java

# 3. Run the application
java Main
```

### Alternative (IDE Method)
1. Open project in IntelliJ IDEA
2. Right-click `Main.java` → Run 'Main.main()'
3. Application starts automatically

## 📱 Application Workflow

```
🏠 Home Page
    ↓ (Click "Book Now")
📝 Booking Form
    ↓ (Fill details + "Proceed to Payment")
💳 Payment Page  
    ↓ (Enter payment + "Process Payment")
🧾 Receipt Page
    ↓ (Download PDF or return home)
```

## 🎯 Academic Requirements ✅

| Requirement | Implementation | Status |
|-------------|----------------|---------|
| **3 Modules** | Home, Form, Receipt | ✅ Complete |
| **JFrame** | Main application window | ✅ Complete |
| **JPanel** | All page layouts | ✅ Complete |
| **JLabel** | Text and image displays | ✅ Complete |
| **JButton** | Navigation and actions | ✅ Complete |
| **JTextField** | User input fields | ✅ Complete |
| **JComboBox** | Dropdown selections | ✅ Complete |
| **Event Handling** | ActionListeners throughout | ✅ Complete |
| **Professional Design** | Modern blue theme | ✅ Complete |

## 🛠️ Technical Architecture

### Core Classes Overview
```java
Main.java              // 🎯 Application controller & navigation
├── HomePanel.java     // 🏠 Event display & system info
├── BookingFormPanel.java // 📝 Customer form & validation  
├── PaymentPanel.java  // 💳 Payment processing & validation
└── ReceiptPanel.java  // 🧾 Receipt display & PDF generation

// Data Models
Event.java            // 📅 Event information structure
Booking.java          // 🎫 Booking details structure
BookingController.java // 🎮 Business logic & data management
```

### Key Programming Concepts Demonstrated

#### 1. **Object-Oriented Programming**
```java
// Encapsulation: Private fields with public methods
public class Event {
    private int id;           // Private data
    private String title;     // Encapsulated
    
    public String getTitle() { // Public accessor
        return title;
    }
}
```

#### 2. **Event-Driven Programming**
```java
// ActionListener implementation for button clicks
button.addActionListener(e -> {
    // Handle user interaction
    processPayment();
});
```

#### 3. **Form Validation**
```java
// Input validation with regex patterns
Pattern cardPattern = Pattern.compile("^\\d{16}$");
if (!cardPattern.matcher(cardNumber).matches()) {
    showError("Invalid card number");
}
```

#### 4. **Threading (SwingWorker)**
```java
// Background processing without freezing UI
SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
    protected Void doInBackground() {
        Thread.sleep(4000); // Simulate processing
        return null;
    }
};
```

## 📊 Project Structure

```
MiniTicketSystem/
├── 📁 Source Files
│   ├── Main.java              # Application entry point & navigation
│   ├── HomePanel.java         # Home page with events & system info
│   ├── BookingFormPanel.java  # Booking form with validation
│   ├── PaymentPanel.java      # Payment processing & validation
│   ├── ReceiptPanel.java      # Receipt display & PDF generation
│   ├── Event.java             # Event data model
│   ├── Booking.java           # Booking data model
│   └── BookingController.java # Business logic controller
├── 📁 images/                 # Event photos & application assets
│   ├── logo.png              # Application logo
│   ├── header_background.jpg # Header background image
│   └── [event_images].jpg    # Individual event photos
└── 📄 README.md              # Project documentation
```

## 🎨 Design Philosophy

### Color Scheme (Professional Blue Theme)
- **Primary Dark**: `#192A56` - Headers and important elements
- **Primary Blue**: `#34495E` - Main UI components  
- **Accent Blue**: `#4A90E2` - Interactive elements
- **Success Green**: `#2ECC71` - Confirmation actions
- **Background**: `#F8F9FA` - Clean, light background

### Typography
- **Headers**: Segoe UI Bold (24-32px)
- **Body Text**: Segoe UI Regular (12-16px)
- **Buttons**: Segoe UI Bold (14-16px)

## 📝 Presentation Guide

### Code Explanation Points

1. **Main.java**: 
   - Application structure and navigation
   - CardLayout for page switching
   - Color constants and design consistency

2. **HomePanel.java**:
   - Event display with images
   - System information gathering
   - Grid layout for event cards

3. **BookingFormPanel.java**:
   - Form validation techniques
   - Dynamic price calculation
   - User input handling

4. **PaymentPanel.java**:
   - Credit card validation with regex
   - Background processing with SwingWorker
   - Seamless user experience design

5. **ReceiptPanel.java**:
   - PDF generation and file I/O
   - Professional receipt formatting
   - User workflow completion

### Demo Flow for Presentation
1. **Start Application** → Show home page design
2. **Browse Events** → Explain event data structure
3. **Book Ticket** → Demonstrate form validation
4. **Process Payment** → Show background processing
5. **View Receipt** → Display PDF generation
6. **Code Walkthrough** → Explain key programming concepts

## 🏆 Learning Outcomes Achieved

- ✅ **GUI Development**: Professional Swing application
- ✅ **OOP Principles**: Classes, objects, encapsulation
- ✅ **Event Handling**: User interaction management
- ✅ **Data Validation**: Input checking and error handling
- ✅ **Threading**: Background processing concepts
- ✅ **File I/O**: PDF generation and image loading
- ✅ **Software Design**: Clean architecture and code organization

## 👨‍💻 Academic Information

**Course**: BIT 4043 Object-Oriented Programming  
**Level**: Semester 2 IT Student  
**Project Type**: GUI Application Development  
**Assessment Focus**: Requirements (5%), Teamwork (5%), Presentation (50%), Creativity (20%), Functionality (15%)

---

*This project demonstrates comprehensive understanding of Java Swing GUI development, object-oriented programming principles, and professional software design practices suitable for academic presentation and evaluation.*
