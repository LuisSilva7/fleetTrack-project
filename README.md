# FleetTrack - Vehicle Route, Tracking, and Maintenance Management

FleetTrack is designed to streamline the management of vehicle routes, tracking, maintenance, and driver assignments. By providing real-time insights, efficient management tools, and data-driven features, FleetTrack empowers fleet managers to optimize their operations and reduce costs.

# Why? (Motivation / Problem to Solve)

FleetTrack addresses the complexity of fleet management by offering an integrated solution for route optimization, real-time tracking, and proactive maintenance scheduling. This ensures reduced downtime, improved resource allocation, and better overall fleet performance.

## Features

- **Real-Time Vehicle Tracking**: Provides live location updates for all vehicles, ensuring managers have complete visibility of their fleet.
- **Route Optimization**: Facilitates the creation and assignment of efficient routes, saving time and reducing fuel costs.
- **Driver Management**: Tracks driver assignments, performance, and availability, helping ensure compliance with regulations and schedules.
- **Maintenance Scheduling**: Automatically tracks vehicle maintenance needs, sending alerts for upcoming or overdue services.

## Technologies Used

- **Backend**:
  - **Docker**: A containerization platform used to package and deploy the application in lightweight, isolated environments, ensuring consistency across different environments and simplifying the deployment process.
  - **Java** - Programming language used for backend development.
  - **Quarkus**: A Kubernetes-native Java framework optimized for containerized environments, ensuring high performance and low memory footprint.
  - **RESTEasy**: A JAX-RS implementation for building RESTful APIs with Quarkus.
  - **Hibernate ORM with Panache**: Simplifies data access and management in Quarkus-based applications.
  - **MySQL** - Relational database for storing parking, payment, and user data.

## How to Run the Project Locally

### Installation Steps

1. **Clone the repository:**

   ```bash
   git clone https://github.com/LuisSilva7/fleetTrack-project.git
   ```

2. **Navigate to the project backend directory:**

   ```bash
   cd fleetTrack
   ```

3. **Run all containers:**

   ```bash
   docker compose up -d
   ```

4. **Run backend server:**

   ```bash
   mvn quarkus:dev
   ```

The application will be available at http://localhost:8888.

### Maintainer

- **Luis Silva** (Owner/Developer)
