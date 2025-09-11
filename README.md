# Chinese Dictation

A comprehensive Chinese language dictation application built with Spring Boot, designed to help users practice and improve their Chinese writing skills through interactive dictation exercises.

## 🚀 Features

- **User Authentication**: Secure JWT-based authentication system
- **Dictation Exercises**: Interactive Chinese character dictation practice
- **Progress Tracking**: Monitor learning progress and performance
- **Email Notifications**: Automated email services using Brevo
- **User Management**: Profile management and account settings
- **Responsive Design**: Works seamlessly across different devices

## 🛠 Tech Stack

- **Backend**: Java 23+ with Spring Boot
- **Database**: PostgreSQL
- **Authentication**: JWT (JSON Web Tokens)
- **Email Service**: Brevo (formerly Sendinblue)
- **Build Tool**: Maven/Gradle
- **API Documentation**: Swagger/OpenAPI

## 📋 Prerequisites

Before running this application, make sure you have the following installed:

- Java 17 or higher
- Maven 3.9+
- PostgreSQL 12+
- Git

## ⚙️ Installation & Setup

### 1. Clone the repository
```bash
git clone https://github.com/yourusername/Chinese Dictation.git
cd Chinese-Dictation
```

### 2. Database Setup
Create a PostgreSQL database:
```sql
CREATE DATABASE chinese_dictation;
CREATE USER dictation_user WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE chinese_dictation TO dictation_user;
```

### 3. Environment Configuration
Create an `application.properties` or `application.yml` file in `src/main/resources/`:

```properties
server:
  port: 8080
  servlet:
    context-path: /api/v1

spring:
  application:
    name: chinese-dictation
  datasource:
    url: jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}
    driver-class-name: org.postgresql.Driver
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
  jpa:
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: update
  cache:
    type: redis
  data:
    redis:
      host: localhost
      port: 6379
  servlet:
    multipart:
      max-request-size: 50MB
      max-file-size: 50MB


application:
  security:
    jwt:
      expiration: 86400000
      secret-key: ${SECRET_KEY}
  email:
    brevo:
      api-key: ${BREVO_API_KEY}
      sender-email: ${BREVO_SENDER_EMAIL}
      sender-name: ${BREVO_SENDER_NAME}
  cloudinary:
    cloud-name: ${CLD_NAME}
    api-key: ${CLD_API_KEY}
    api-secret: ${CLD_API_SECRET}

springdoc:
  swagger-ui:
    path: /swagger-ui.html
  api-docs:
    path: /api-docs
```

### 4. Install Dependencies
```bash
# For Maven
mvn clean install

# For Gradle
./gradlew build
```

### 5. Run the Application
```bash
# For Maven
mvn spring-boot:run

# For Gradle
./gradlew bootRun
```

The application will start on `http://localhost:8080`

## 🔧 Configuration

### JWT Settings
- Configure JWT secret key in application properties
- Set appropriate token expiration time
- Customize token refresh logic

### Email Settings (Brevo)
1. Sign up for a Brevo account
2. Get your API key from the dashboard
3. Configure sender email and name
4. Set up email templates as needed

### Cloudinary Settings
1. Sign up your Cloudinary account
2. Generate your api_key
3. Get your clound name, api_key and api_secret
4. Set up Cloudinary Config

### Database Migration
The application uses Hibernate DDL auto-update. For production, consider using Flyway or Liquibase for database migrations.

## 📚 API Documentation

Once the application is running, you can access the API documentation at:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- API Docs: `http://localhost:8080/v3/api-docs`

## 🧪 Testing

Run the test suite:
```bash
# For Maven
mvn test

# For Gradle
./gradlew test
```

## 🚀 Deployment

### Docker Deployment
```dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/chinese-dictation-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Environment Variables for Production
```bash
export DB_HOST=your_db_host
export DB_PORT=5432
export DB_NAME=chinese_dictation
export DB_USERNAME=dictation_user
export DB_PASSWORD=your_secure_password
export JWT_SECRET=your_very_secure_jwt_secret
export BREVO_API_KEY=your_brevo_api_key
export BREV0_SENDER_EMAIL=sender_email
export BREVO_SENDER_NAME=sender_name
export CLD_NAME=your_cloudinary_name
export CLD_API_KEY=your_cloudinary_service
export CLD_API_SECRET=your_cloudinary_secret
```

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/yourdomain/chinesedictation/
│   │       ├── ChineseDictationApplication.java
│   │       ├── configuration/   # Configuration classes
│   │       ├── controller/      # REST controllers
│   │       ├── service/         # Business logic
│   │       ├── repository/      # Data access layer
│   │       ├── mapper/          # Mapping data from entiry to dto
│   │       ├── model/           # Model layer
|   |           ├── entity       # Entity classes
|   |           ├── dto          # Data transfer object classes
|   |           ├── enums        # Enums 
│   │       ├── email/           # Email configuration
│   │       ├── security/        # Security configuration
│   │       └── util/            # Utility classes
│   └── resources/
│       ├── application.properties
│       ├── static/              # Static resources
│       └── templates/           # Email templates
└── test/                        # Test classes
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

Sung V1no - [your.email@example.com](mailto:vsung2608@gmail.com)

Project Link: [https://github.com/vsung2608/Chinese-Dictation](https://github.com/yourusername/Chinese-Dictation)

## 🙏 Acknowledgments

- Spring Boot community for the excellent framework
- PostgreSQL team for the robust database system
- Brevo for reliable email services
- All contributors who helped improve this project

## 🐛 Known Issues

- List any known issues or limitations
- Link to GitHub issues for tracking

## 🔮 Future Enhancements

- [ ] Mobile application support
- [ ] Audio pronunciation features
- [ ] Advanced analytics dashboard
- [ ] Multi-language support
- [ ] Social features (leaderboards, sharing)

---

For more information, please refer to the [documentation](docs/) or [contact us](mailto:vsung2608@gmail.com).
