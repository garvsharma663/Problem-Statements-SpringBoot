Objective

Design and implement a Spring Boot RESTful web application that manages a video rental system.
The system should allow users to add, view, update, check out/in, and delete videos through REST API endpoints.

Requirements
1. Entity: Video

Each video record should have:

id (auto-generated)

title (String, required, max length 100)

checked (boolean, indicates if a video is checked out or not)

rating (float, range 0.0 – 5.0)

2. Functional Requirements

The API should expose the following operations:

Operation	HTTP Method	Endpoint	Description
Retrieve all videos	GET	/api/videos	Returns a list of all videos
Retrieve a video by ID	GET	/api/videos/{id}	Returns details of a specific video
Add a new video	POST	/api/videos	Adds a new video to the system
Update a video’s rating	PUT	/api/videos/{id}/rating?rating={value}	Updates the rating of a specific video
Check out / Check in a video	PUT	/api/videos/{id}/checkout?checked={true/false}	Changes the checked-out status
Delete a video	DELETE	/api/videos/{id}	Removes a video from the system
3. Business Logic

When a video is first added:

It should be unchecked (checked = false) by default.

It should have a rating of 0.0 by default.

The system should not allow null titles.

Ratings should not exceed 5.0.

All updates should be done via REST endpoints — no console input/output.

4. Technical Requirements

Framework: Spring Boot (latest stable version)

Language: Java 17+

Build Tool: Maven

Validation: Jakarta Bean Validation (@NotNull, @Max, etc.)

Dependency Injection: Use @Service, @RestController, and @Autowired

Persistence: Initially in-memory list; can later be extended to use JPA with a database

Testing Tool: Postman or cURL

1. Expected JSON Format

Example Request:

POST /api/videos
{
"title": "Interstellar",
"checked": false,
"rating": 4.8
}


Example Response:

{
"id": 1,
"title": "Interstellar",
"checked": false,
"rating": 4.8
}