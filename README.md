# OpenSourceHustlers (Backend) 


## What is OpenSourceHustlers? 

OpenSourceHustlers is a web application designed to make collaboration easier among developers in the open source community. 

By allowing users to create posts about their open source projects, the platform connects individuals with similar interests and work experience, promoting confidence and experience in contributing to larger open source projects. 

Our goal is to promote best practices in open source contribution and provide a supportive environment for beginner developers to start their journey in open source projects. Additionally, the platform fetches and serves open source projects from GitHub with tags suitable for beginner developers or featuring good first issues, further enhancing opportunities for involvement and learning in software development.

## Features 

- **Post Creation**: Create posts about their open source projects, detailing project descriptions, goals, and desired collaborators to find other developers.
- **User Connectivity**: Encourage connections between users with shared interests and experience levels, encouraging collaboration and knowledge sharing.
- **Promotion of Best Practices**: Learn and share best practices in open source contribution, guiding users through the process of effective collaboration and project management.
- **Supportive Environment**: Provide a supportive environment for beginner developers to start their journey in open source projects, offering guidance, resources, and mentorship opportunities.
- **GitHub Integration**: Find open source projects from GitHub with tags suitable for beginner developers or featuring good first issues, enhancing opportunities for involvement and learning.

## Getting Started

1. Clone this repository.

   `git clone https://github.com/imko/open-source-hustlers-backend.git`

2. Navigate to the project directory.

   `cd open-source-hustlers-backend`

3. Build the project using Maven.

   `./mvn clean install`

4. Run the applcation.

   `./mvn spring-boot:run`

5. The application will start running on `http://localhost:8080`. You can access the endpoints from this URL.

## Design 

#### Database
![image](https://github.com/imko/open-source-hustlers-backend/assets/46854966/6a59a660-49c0-4f54-9a0c-3e412fae8a53)


### Authentication 

![image](https://github.com/imko/open-source-hustlers-backend/assets/46854966/7068dbf9-61f8-416d-aac5-4fb37f47548e)

For this project, I opted for OAuth 2.0 Resource Server to enable stateless authentication and authorization using JWT. OAuth 2.0 Resource Server offers a robust and standardized approach to handling JWT authentication and authorization, ensuring compliance with security standards.

Upon successful authentication, the server issues a short-lived access token (1 hour) along with a refresh token (7 days), enabling users to access REST APIs on the resource server by including the access token in the HTTP header. The resource server validates the access token for each request and grants access to resources if valid; otherwise, it returns an unauthorized access error.

To manage token expiration, users can utilize the refresh token to obtain a new access token. Typically, the client automatically fetches the refresh token when the current access token expires, reducing the need for frequent user logins.

![image](https://github.com/imko/open-source-hustlers-backend/assets/46854966/89bb5823-2f9b-4e37-b575-0e2bac772dc0)


## Tech Stacks 
- Java 17
- Spring Boot 3
- Spring Security 6 - OAuth 2.0 Resource Server 
- Postgres (WIP: Currently H2) 
- Hibernate 

If interested, also check out the frontend project built with Next JS: [OpenSourceHustlers (Frontend)](https://github.com/imko/open-source-hustlers-frontend)
