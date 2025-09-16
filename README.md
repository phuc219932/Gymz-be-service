# Gymz-backend-service
Gymz backend-service with SpringBoot

## Công nghệ sử dụng

- **Java 17**
- **Spring Boot 3.5.4**
- **Spring Security**
- **Spring Data JPA**
- **MySQL**
- **Maven**
- **BCrypt** (mã hóa mật khẩu)

## Cấu trúc dự án

```
src/main/java/com/vnair/usermanagement/
├── controller/          # REST Controllers
├── service/            # Business Logic Layer
├── repository/         # Data Access Layer
├── model/            # JPA Entities
├── dto/               # Data Transfer Objects
├── exception/         # Exception Handling
└── UserManagementApiApplication.java
```

## Cài đặt và chạy ứng dụng

### Yêu cầu hệ thống

- **Java 17** hoặc cao hơn
- **Maven 3.6+**
- **Docker** và **Docker Compose**

# Hướng dẫn sử dụng project

## Bước 1: Khởi động ELK bằng Docker Compose

Chạy lệnh sau trong thư mục gốc của project để dựng ELK stack:

```powershell
docker-compose up -d
```

## Bước 2: Chạy project Spring Boot

Chạy lệnh sau để khởi động ứng dụng:

```powershell
./mvnw spring-boot:run
```

Hoặc nếu đã cài đặt Maven trên máy:

```powershell
mvn spring-boot:run
```

