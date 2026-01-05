FYP Management System
Project Overview

The FYP Management System (FYPMS) is a web-based application designed to manage the entire lifecycle of Final Year Projects (FYPs) at the Department of Computer Science, UET Lahore. It streamlines document submission, review, grading, and result release while ensuring security, version control, and transparency.

This system supports multiple user roles with distinct dashboards and permissions:

Students → Upload documents, submit projects, view feedback.

Supervisors → Review, approve, or request revisions.

Evaluation Committee → Grade using rubrics, request revisions.

FYP Committee → Set deadlines, monitor progress, release final results.

Features

Role-based Access Control

Document Workflow Management (Upload → Review → Approval → Grading → Final Release)

Version Tracking & Timestamp Logging

Deadline Monitoring & Notifications (Email/Dashboard Alerts)

Secure Submission & Grading

Action Logging for Transparency

System Requirements

Backend: Java Spring Boot / Node.js (or any preferred backend framework)

Frontend: React / Angular / Thymeleaf (or any preferred frontend framework)

Database: MySQL / PostgreSQL / MongoDB

Java Version: 17+ (if using Spring Boot)

Node Version: 18+ (if using Node.js)

Installation

Clone the repository

git clone https://github.com/yourusername/fyp-management-system.git
cd fyp-management-system


Backend Setup

Navigate to the backend folder and install dependencies

cd backend
# If Node.js
npm install
# If Spring Boot
./mvnw clean install


Database Setup

Create a database named fyp_management

Import the provided SQL schema (if using MySQL/PostgreSQL)

Configure Environment Variables

Set database credentials and mail server credentials in .env or application.properties

Run the Application

# Node.js
npm start
# Spring Boot
./mvnw spring-boot:run


Access the System

Open http://localhost:3000 (frontend) or http://localhost:8080 (backend) in your browser

Usage

Student: Register → Login → Upload document → Submit → View feedback

Supervisor: Login → View assigned students → Review document → Approve/Request Revision

Evaluation Committee: Login → Grade documents → Request revisions if needed

FYP Committee: Login → Set deadlines → Monitor submissions → Release final results

Project Structure
fyp-management-system/
│
├── backend/                 # Backend source code
│   ├── controllers/         # REST controllers
│   ├── services/            # Business logic
│   ├── repositories/        # Database access layer
│   ├── models/              # Entity classes
│   └── application.properties / .env
│
├── frontend/                # Frontend source code
│   ├── components/          # React/Vue/Angular components
│   ├── pages/               # Dashboard pages
│   └── services/            # API calls
│
├── documentation/           # UML diagrams, ERD, sequence, use case, architecture diagrams
├── README.md
└── LICENSE

Diagrams & Documentation

Use Case Diagram – Shows actors and interactions

Class Diagram – Shows entities, attributes, methods, and relationships

Sequence Diagram – Demonstrates document submission and grading workflow

ERD – Database structure and relationships

Architecture Diagram – Controller → Service → Repository flow

Contributing

Fork the repository

Create a new branch: git checkout -b feature/feature-name

Make changes and commit: git commit -m "Add new feature"

Push to your branch: git push origin feature/feature-name

Create a Pull Request

License

This project is licensed under the MIT License. See the LICENSE file for details.
