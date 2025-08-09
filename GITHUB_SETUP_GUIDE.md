# 🚀 GitHub Setup Guide - Event Ticket Booking System

## 📋 **Prerequisites**

Before starting, make sure you have:
- ✅ **GitHub Account** - Create one at [github.com](https://github.com) if you don't have one
- ✅ **Git Installed** - Download from [git-scm.com](https://git-scm.com/) if not already installed
- ✅ **Terminal/Command Prompt** access

## 🔧 **Method 1: Using GitHub Desktop (Easiest)**

### **Step 1: Download GitHub Desktop**
1. Go to [desktop.github.com](https://desktop.github.com/)
2. Download and install GitHub Desktop
3. Sign in with your GitHub account

### **Step 2: Create Repository**
1. Open GitHub Desktop
2. Click **"Create a New Repository on your hard drive"**
3. Fill in details:
   - **Name**: `event-ticket-booking-system`
   - **Description**: `Professional Java Swing ticket booking application for BIT 4043 OOP course`
   - **Local Path**: Choose where to create the repository
   - ✅ Check **"Initialize this repository with a README"**
   - ✅ Check **"Git ignore"** and select **"Java"**
   - **License**: Choose **"MIT License"** (optional)

### **Step 3: Copy Your Project Files**
1. Navigate to the newly created repository folder
2. Copy ALL your project files into this folder:
   ```
   ├── Main.java
   ├── HomePanel.java
   ├── BookingFormPanel.java
   ├── PaymentPanel.java
   ├── ReceiptPanel.java
   ├── Event.java
   ├── Booking.java
   ├── BookingController.java
   ├── images/ (entire folder)
   ├── README.md
   ├── PRESENTATION_GUIDE.md
   └── PROJECT_SUMMARY.md
   ```

### **Step 4: Commit and Push**
1. Open GitHub Desktop
2. You'll see all your files listed as changes
3. Add a commit message: `Initial commit: Event Ticket Booking System`
4. Click **"Commit to main"**
5. Click **"Publish repository"**
6. ✅ Keep **"Keep this code private"** unchecked (make it public)
7. Click **"Publish Repository"**

## 🔧 **Method 2: Using Command Line (Advanced)**

### **Step 1: Create GitHub Repository Online**
1. Go to [github.com](https://github.com)
2. Click **"New repository"** (green button)
3. Fill in details:
   - **Repository name**: `event-ticket-booking-system`
   - **Description**: `Professional Java Swing ticket booking application for BIT 4043 OOP course`
   - ✅ **Public** repository
   - ✅ **Add a README file**
   - ✅ **Add .gitignore** → Choose **Java**
   - **License**: MIT License (optional)
4. Click **"Create repository"**

### **Step 2: Clone Repository to Your Computer**
```bash
# Navigate to where you want the project
cd ~/Desktop

# Clone the repository
git clone https://github.com/YOUR_USERNAME/event-ticket-booking-system.git

# Navigate into the project folder
cd event-ticket-booking-system
```

### **Step 3: Copy Your Project Files**
Copy all your project files into the cloned repository folder.

### **Step 4: Add, Commit, and Push**
```bash
# Add all files to git
git add .

# Commit with a message
git commit -m "Initial commit: Event Ticket Booking System"

# Push to GitHub
git push origin main
```

## 🔧 **Method 3: Upload Existing Project (Simplest)**

### **Step 1: Create Repository on GitHub**
1. Go to [github.com](https://github.com)
2. Click **"New repository"**
3. Name: `event-ticket-booking-system`
4. Description: `Professional Java Swing ticket booking application for BIT 4043 OOP course`
5. ✅ **Public**
6. ❌ **Don't** initialize with README (since you have one)
7. Click **"Create repository"**

### **Step 2: Initialize Git in Your Project**
```bash
# Navigate to your project folder
cd /Users/mac/Desktop/MiniTicketSystem

# Initialize git repository
git init

# Add all files
git add .

# Make initial commit
git commit -m "Initial commit: Event Ticket Booking System"

# Add GitHub as remote origin
git remote add origin https://github.com/YOUR_USERNAME/event-ticket-booking-system.git

# Push to GitHub
git branch -M main
git push -u origin main
```

## 📝 **Recommended Repository Settings**

### **Repository Name**
`event-ticket-booking-system`

### **Description**
`Professional Java Swing ticket booking application for BIT 4043 Object-Oriented Programming course. Features 3 modules: Home, Booking Form, and Receipt with payment processing and PDF generation.`

### **Topics/Tags** (Add these in repository settings)
- `java`
- `swing`
- `gui`
- `oop`
- `academic-project`
- `ticket-booking`
- `university-project`

### **README Badges** (Optional - Add to top of README.md)
```markdown
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-GUI-blue?style=for-the-badge)
![Academic](https://img.shields.io/badge/Academic-Project-green?style=for-the-badge)
```

## 🎯 **After Upload - Repository Enhancement**

### **1. Create Releases**
1. Go to your repository on GitHub
2. Click **"Releases"** → **"Create a new release"**
3. Tag: `v1.0.0`
4. Title: `Event Ticket Booking System v1.0`
5. Description: Include features and screenshots

### **2. Add Screenshots**
1. Create a `screenshots/` folder
2. Take screenshots of your application
3. Add them to README.md

### **3. Enable GitHub Pages** (Optional)
1. Go to repository **Settings**
2. Scroll to **Pages**
3. Source: **Deploy from a branch**
4. Branch: **main**
5. Your documentation will be available at: `https://YOUR_USERNAME.github.io/event-ticket-booking-system`

## 🔗 **Sharing Your Repository**

### **For Academic Submission**
- **Repository URL**: `https://github.com/YOUR_USERNAME/event-ticket-booking-system`
- **Clone URL**: `https://github.com/YOUR_USERNAME/event-ticket-booking-system.git`

### **For Portfolio/Resume**
- Add to your GitHub profile
- Include in your resume as a project
- Share with potential employers

## 🎉 **Benefits of Having Your Project on GitHub**

1. **Professional Portfolio** - Shows your coding skills
2. **Version Control** - Track changes and improvements
3. **Backup** - Your code is safely stored in the cloud
4. **Collaboration** - Easy to share with classmates or instructors
5. **Academic Credit** - Demonstrates real-world development practices
6. **Future Reference** - Access your code from anywhere

## 🆘 **Troubleshooting**

### **Common Issues:**

**1. "Repository already exists"**
- Choose a different name or delete the existing repository

**2. "Permission denied"**
- Make sure you're logged into the correct GitHub account
- Check if you have write permissions

**3. "Git not found"**
- Install Git from [git-scm.com](https://git-scm.com/)

**4. "Large files rejected"**
- Make sure your images are under 100MB each
- Use `.gitignore` to exclude large files

## 📞 **Need Help?**

If you encounter any issues:
1. Check GitHub's [documentation](https://docs.github.com/)
2. Ask on [GitHub Community](https://github.community/)
3. Contact your instructor for academic guidance

---

**🎯 Choose the method that feels most comfortable for you. Method 1 (GitHub Desktop) is recommended for beginners!**
