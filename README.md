# Order Management System for E-commerce Businesses

## About This Project

The Order Management System is a backend web application designed to streamline the management of orders for e-commerce businesses. Whether handling a few orders or a large volume of transactions, this system provides a robust platform to efficiently manage and track orders from placement to delivery.

### Technologies Used

- **Spring Framework:** Leveraging the power of Spring Boot, Spring MVC, and Spring Data for robust and scalable backend development.
- **Hibernate:** Utilizing Hibernate ORM for seamless object-relational mapping and database interaction.
- **PostgreSQL:** Employing PostgreSQL as the relational database management system for storing order and customer data.
- **Java Development Kit (JDK) 17:** Utilizing the latest version of the JDK for Java development.
- **Lombok:** Enhancing productivity by reducing boilerplate code in Java classes.
- **Swagger:** Automatically generating interactive API documentation for better API understanding and testing.
- **RESTful API:** Building RESTful APIs to facilitate communication between frontend and backend components.
- **Spring Boot Starter Mail:** Enabling email notifications for user-related actions such as order confirmations.
- **Infobip:** Integrating Infobip API for sending SMS notifications.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17
- Apache Maven (if using for build management)
- PostgreSQL Server
- Infobip Account and API Key

### Installation

1. Clone the repository: `git clone https://github.com/khalilfarouqi/OrderWise.git`
2. Navigate to the project directory
3. Configure PostgreSQL database settings in `application.properties`
4. Configure email settings in `application.properties`
5. Configure Infobip settings in `application.properties`
6. Build and run the application

### Usage

1. Ensure the backend server is up and running.
2. Integrate the backend with your frontend or API client.
3. Start managing orders, tracking deliveries, and analyzing performance through API calls.

### Email and SMS Notifications

The system can send email notifications for various user-related actions such as order confirmations. Ensure you have configured the email settings in the `application.properties` file. Additionally, the system can send SMS notifications using the Infobip API. Ensure you have configured the Infobip settings in the `application.properties` file.

## License

This project is licensed under the [MIT License](link-to-license).

## Acknowledgements

Special thanks to the Spring Framework community for providing excellent documentation and resources for building enterprise-grade applications.
