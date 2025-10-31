# 🧩 Understanding DTOs (Data Transfer Objects)

## 📘 What is a DTO?

A **Data Transfer Object (DTO)** is a simple Java object that carries data between layers
of an application (e.g., Controller ↔ Service ↔ Client).

In Spring Boot, DTOs are commonly used to:
- Hide internal entity details (like database IDs or sensitive data)
- Prevent direct exposure of JPA entities to the client
- Validate or transform incoming data before persistence
- Keep controllers lightweight and safe

---

## 🧠 Why Not Use Entities Directly?

Entities are **database-mapped** objects. They often contain:
- ORM annotations (`@Entity`, `@Table`, etc.)
- Relationships (`@OneToMany`, `@ManyToOne`)
- Lazy-loaded fields or internal IDs

Exposing them directly can cause:
- Security leaks (sensitive internal data)
- Infinite recursion in JSON (e.g., bidirectional relationships)
- Tight coupling between persistence and presentation layers

---

## 🧩 Example (Video Rental System)

**Entity (Database Layer)**:
```java
@Entity
@Table(name = "videos")
public class Video {
    private Long id;
    private String title;
    private boolean checked;
    private float rating;
}
