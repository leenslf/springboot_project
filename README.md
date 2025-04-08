### Available features:

- **User Registration API**
- **User Login API**
- **View Account Details**
- **Update Account Details (e.g., reset password)**
- **Delete Account**

### Prerequisites

- **Java 17** (or later LTS version)
- **Maven** (if not using the Maven Wrapper)


### Running via IntelliJ IDEA

1. Open the project in IntelliJ IDEA.
2. Ensure the project SDK is set to Java 17.
3. Open the main class: `com.example.clickndine.ClickndineApplication.java`.
4. Right-click on the file and select **Run 'ClickndineApplication.main()'**.
5. The application starts on port `8080` by default.
6. Test run http://localhost:8080/hello on your browser.

#### Register Test

```bash
curl -X POST http://localhost:8080/api/users/register \
  -H "Content-Type: application/json" \
  -d '{
        "name": "Alice Smith",
        "email": "alice@example.com",
        "phone": "1234567890",
        "password": "password123",
        "userType": "customer"
      }'
```

#### Endpoints

- **User Registration API:**  
  Endpoint: `POST /api/users/register`  
  Registers a new user using a JSON payload containing:
  - `name`
  - `email`
  - `phone`
  - `password`
  - `userType` (e.g., customer, courier, admin)

- **User Login API:**  
  Endpoint: `POST /api/users/login`  
  Validates user credentials and returns a dummy session token upon success.  
  Handles invalid credentials with appropriate error responses.

- **View Account Details:**  
  Endpoint: `GET /api/users/{id}`  
  Retrieves user account details by ID.

- **Update Account Details:**  
  Endpoint: `PUT /api/users/{id}`  
  Updates a user's information (e.g., reset password, update name, or contact info).

- **Delete Account:**  
  Endpoint: `DELETE /api/users/{id}`  
  Deletes a user's account by ID.
