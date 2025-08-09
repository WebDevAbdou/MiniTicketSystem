#!/bin/bash

# GitHub Setup Script for Event Ticket Booking System
# Run this script to automatically set up your GitHub repository

echo "🚀 Setting up GitHub repository for Event Ticket Booking System..."

# Check if git is installed
if ! command -v git &> /dev/null; then
    echo "❌ Git is not installed. Please install Git first:"
    echo "   Download from: https://git-scm.com/"
    exit 1
fi

# Get user input
echo ""
echo "📝 Please provide your GitHub details:"
read -p "GitHub Username: " github_username
read -p "Repository Name (default: event-ticket-booking-system): " repo_name

# Set default repository name if not provided
if [ -z "$repo_name" ]; then
    repo_name="event-ticket-booking-system"
fi

echo ""
echo "🔧 Initializing Git repository..."

# Initialize git repository
git init

# Add all files
git add .

# Make initial commit
git commit -m "Initial commit: Event Ticket Booking System

- Complete Java Swing GUI application
- 3 main modules: Home, Booking Form, Receipt
- Payment processing with validation
- PDF receipt generation
- Professional design with modern UI
- Academic project for BIT 4043 OOP course"

# Add remote origin
git remote add origin "https://github.com/$github_username/$repo_name.git"

# Set main branch
git branch -M main

echo ""
echo "✅ Git repository initialized successfully!"
echo ""
echo "📋 Next steps:"
echo "1. Go to https://github.com and create a new repository named: $repo_name"
echo "2. Make sure the repository is PUBLIC"
echo "3. DO NOT initialize with README (you already have one)"
echo "4. After creating the repository, run:"
echo "   git push -u origin main"
echo ""
echo "🔗 Your repository will be available at:"
echo "   https://github.com/$github_username/$repo_name"
echo ""
echo "🎉 Happy coding!"
