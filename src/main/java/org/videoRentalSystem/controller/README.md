## What is ResponseEntity?

ResponseEntity<T> is a Spring class that represents the entire HTTP response, including:

Status code (like 200 OK, 404 Not Found, 201 Created, etc.)

Headers (like Content-Type, Location, etc.)

Body (the actual data — like JSON or text)

In simple terms, it lets you fully control what goes back to the client.

## 📘 Common Spring Annotations

### 🏷️ `@PathVariable`

Used when data already **exists** and is part of the **URL**.
Think of it like accessing a specific record (e.g., `/videos/5`).

```java
@GetMapping("/videos/{id}")
public VideoDTO getVideoById(@PathVariable Long id) {
    return videoService.getVideoByID(id);
}
```

🧩 **Example Request:**

```
GET /videos/5
```

---

### 📦 `@RequestBody`

Used when you are **sending new data** in the **request body**, usually in JSON form.
Think of it like submitting a form.

```java
@PostMapping("/videos")
public VideoDTO addVideo(@RequestBody VideoDTO videoDTO) {
    return videoService.addVideo(videoDTO);
}
```

🧩 **Example Request:**

```http
POST /videos
Content-Type: application/json

{
  "title": "Inception",
  "rating": 4.9
}
```

---

### 🔍 `@RequestParam`

Used to extract **query parameters** from the URL — usually for searching, filtering, or pagination.
Think of it like `/videos?title=Inception`.

```java
@GetMapping("/videos/search")
public VideoDTO getVideoByTitle(@RequestParam String title) {
    return videoService.getVideoByTitle(title);
}
```

🧩 **Example Request:**

```
GET /videos/search?title=Inception
```

