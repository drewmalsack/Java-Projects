# Software Engineering Portfolio: The Development Lab

Welcome to my central project repository. This collection tracks my journey through full-stack development, focusing on enterprise-grade Java, Spring Boot, and reactive frontend architectures.

## 🚀 Featured Projects

| Project | Stack | Core Concepts |
| :--- | :--- | :--- |
| [Parking Lot Visualizer] | Java, Spring Boot, JPA, Thymeleaf | CRUD, Geometric Validation, Spring Security, Bi-directional Mapping |

---

## 🛰️ Project Spotlight: Parking Lot Visualizer

**The Problem:** Managing a massive parking facility requires more than a spreadsheet; it needs situational awareness and strict business logic to ensure vehicles fit their designated spaces.

**The Solution:** A full-stack visualizer that allows administrators to manage a grid-based lot in real-time.

### 🛠️ Technical Highlights
* **Geometric Validation Engine:** Custom logic to prevent "Incompatible Size" errors (e.g., parking a Truck in a Compact space).
* **Bi-directional Entity Mapping:** Managed complex `OneToOne` relationships between Spaces and Vehicles with automated cleanup via `orphanRemoval`.
* **Security Hangar:** Implemented **Spring Security** with Role-Based Access Control (RBAC) and CSRF protection for dynamic JavaScript interactions.
* **Radar Search:** A specialized search-to-highlight feature using CSS animations and DOM manipulation to locate specific plates on the grid.
* **Live Dashboard:** Real-time occupancy metrics and efficiency calculations integrated into the view controller.

### 📸 Visuals
<img width="729" height="993" alt="Screenshot_2026-03-13_05-22-28" src="https://github.com/user-attachments/assets/1e0d8c5e-72a6-4097-8e58-c41436b79c51" />


---

## 🛠️ Tech Stack & Skills
* **Backend:** Java 17+, Spring Boot, Spring Data JPA, Spring Security.
* **Database:** H2 (In-memory for portability), SQL.
* **Frontend:** HTML5, JavaScript (ES6+), Thymeleaf.
* **Tools:** Maven, Git, Visual Studio.
