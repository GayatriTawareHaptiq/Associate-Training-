Secure Blog API
This project is a simple yet secure REST API for a blog application, built using Spring Boot. It demonstrates best practices for building secure, stateless APIs using JSON Web Tokens (JWT) for authentication and authorization. The application allows authenticated users to create blog posts, while all users can view them.

✨ Features
Secure REST endpoints for managing blog posts.

Stateless authentication using JSON Web Tokens (JWT).

Endpoint protection using Spring Security.

Clean, layered architecture (Controller, Service, Repository).

Comprehensive unit testing for the service and controller layers using JUnit 5 & Mockito.

In-memory H2 database for easy setup and testing.

🛠️ Technologies Used
Framework: Spring Boot

Security: Spring Security

Data: Spring Data JPA

Authentication: JSON Web Tokens (jjwt)

Database: H2 (In-memory)

Build Tool: Maven

Testing: JUnit 5, Mockito, AssertJ

📋 Prerequisites
Before you begin, ensure you have the following installed on your system:

Java Development Kit (JDK) 17 or later

Apache Maven

🚀 Getting Started
Follow these instructions to get a copy of the project up and running on your local machine for development and testing.

1. Clone the Repository
   First, clone the project repository to your local machine.

Bash

# Replace <your-repository-url> with the actual URL
git clone <your-repository-url>
cd secureblogWithTest
2. Build the Project
   Use Maven to compile the project and download all the necessary dependencies.

Bash

mvn clean install
3. Run the Application
   You can run the application using the Spring Boot Maven plugin.

Bash

mvn spring-boot:run
The application will start on http://localhost:8080.

🔌 API Endpoints
The API provides the following endpoints. Note that some endpoints require a valid JWT Bearer token in the Authorization header.

Authentication
(Note: These are example endpoints. You will need to implement the corresponding controller, e.g., AuthController.)

Method	Endpoint	Description
POST	/api/auth/register	Registers a new user.
POST	/api/auth/login	Authenticates a user and returns a JWT.

Export to Sheets
Blog Posts
Method	Endpoint	Description	Authentication Required
GET	/posts	Get a list of all posts.	No
POST	/posts	Create a new post.	Yes
GET	/posts/{id}	Get a single post by its ID.	No
DELETE	/posts/{id}	Delete a post by its ID.	Yes (Admin/Owner role)

Export to Sheets
Example POST /posts Request:

Header: Authorization: Bearer <your_jwt_token>

Body:

JSON

{
"title": "My First Secure Post",
"content": "This is the content of the post."
}
🧪 Testing
To run the complete suite of unit tests for the project, navigate to the root directory and run the following Maven command:

Bash

mvn test