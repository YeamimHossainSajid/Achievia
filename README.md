# Achievia - Competitive Programming & Learning Platform 🏆

<div align="center">

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-6DB33F?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)  
[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk)](https://www.oracle.com/java/)  
[![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql)](https://www.mysql.com/)  
[![JWT Auth](https://img.shields.io/badge/JWT-Auth-000000?style=for-the-badge&logo=jsonwebtokens)](https://jwt.io/)  

🚀 Transform Your Coding Skills Through Competition and Community  

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)  
[![Issues](https://img.shields.io/github/issues/YeamimHossainSajid/Achievia)](https://github.com/YeamimHossainSajid/Achievia/issues)  
[![Forks](https://img.shields.io/github/forks/YeamimHossainSajid/Achievia)](https://github.com/YeamimHossainSajid/Achievia/network/members)  
[![Stars](https://img.shields.io/github/stars/YeamimHossainSajid/Achievia)](https://github.com/YeamimHossainSajid/Achievia/stargazers)  

</div>

---

## 📖 Table of Contents

- [Overview](#overview)  
- [Features](#features)  
- [System Architecture](#system-architecture)  
- [Tech Stack](#tech-stack)  
- [Installation](#installation)  
- [API Documentation](#api-documentation)  
- [Database Schema](#database-schema)  
- [Workflows](#workflows)  
- [Contributing](#contributing)  
- [License](#license)  
- [Support](#support)  
- [Acknowledgments](#acknowledgments)  

---

## 🎯 Overview

**Achievia** is a comprehensive competitive programming platform designed to help developers enhance their coding skills through structured competitions, real-time feedback, and community engagement.  

Built with **Spring Boot 3** and **Java 21**, Achievia provides a robust backend for coding challenges, user progression tracking, and competition management.  

> "Where Code Meets Growth" – Bridging the gap between learning and competitive programming excellence.

---

## ✨ Features

### 🏆 Core Features
- **Real-time Coding Competitions**: Time-bound challenges with live leaderboards  
- **Multi-language Support**: Java, Python, C++, JavaScript, and more  
- **Intelligent Problem Management**: Curated problem sets with progressive difficulty  
- **Performance Analytics**: Execution time, memory usage, and code quality metrics  
- **User Progression System**: XP, levels, and achievements tracking  

### 🎯 User Experience
- **Personalized Learning Paths**: Adaptive problem recommendations  
- **Community Engagement**: Code reviews, discussions, and mentorship programs  
- **Portfolio Building**: Showcase rankings and solved problems  
- **Mobile Responsive**: Works seamlessly across all devices  

### 🔧 Technical Features
- **RESTful API**: Clean, well-documented endpoints  
- **JWT Authentication**: Secure token-based authentication  
- **Code Execution Sandbox**: Secure code execution using Judge0 API  
- **Real-time Notifications**: Updates on competitions and results  

---

## 🏗️ System Architecture

### High-Level Architecture

```mermaid
flowchart TD
    A[User Registration/Login] --> B[Profile Created]
    B --> C[Join Competitions / Guilds / Chat Rooms]
    B --> D[Start Habits]
    C --> E[Submit Problems / Chat / Participate]
    D --> F[Log Habits / Gain XP]
    E --> G[Leaderboard / Achievements]
    F --> G
    G --> H[Wallet Transactions / Marketplace Purchases]
