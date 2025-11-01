# 🌐 CORS (Cross-Origin Resource Sharing) Setup — Spring Boot + React Integration

## 🧠 What is CORS?

**CORS** (Cross-Origin Resource Sharing) allows your frontend (React) and backend (Spring Boot) to communicate safely when they run on **different domains or ports**.

### 🔒 Why it exists

Browsers follow the **Same-Origin Policy**, which blocks requests between:

* **Backend:** `http://localhost:8080`
* **Frontend:** `http://localhost:3000`

This prevents unauthorized sites from accessing your backend API.

### ✅ What CORS does

CORS lets the **server** say:

> “Hey browser, I trust that frontend — let it talk to me!”

The backend does this by adding HTTP headers like:

```
Access-Control-Allow-Origin: http://localhost:3000
Access-Control-Allow-Methods: GET, POST, PUT, DELETE
Access-Control-Allow-Headers: Content-Type
```

These tell the browser who’s allowed, which HTTP methods are valid, and which headers are accepted.

---

## ⚙️ How to Configure CORS in Spring Boot

There are 3 ways to configure CORS in Spring Boot, depending on your needs.

---

### 🧬 1. Controller-Level CORS (Basic)

If you want to allow CORS **only for one controller**, use the `@CrossOrigin` annotation.

**Example:**

```java
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/videos")
public class VideoController {
    // your CRUD methods
}
```

**Explanation:**

* `@CrossOrigin` → tells Spring to allow this React origin only for this controller.
* Used when you have different rules for different controllers.

✅ **Use case:** Small projects or testing.

---

### ⚙️ 2. Global CORS Configuration (Recommended Industry Practice)

For real-world, scalable projects — create a config file that defines CORS rules for all controllers.

**📁 File path:**

```
src/main/java/app/videoRentalSystem/config/GlobalCorsConfig.java
```

**📜 Code:**

``` Java
package app.videoRentalSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalCorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // all routes under /api
                        .allowedOrigins("http://localhost:3000") // React dev server
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*") // allow all headers
                        .allowCredentials(true); // allow cookies/auth headers
            }
        };
    }
}
```

**Explanation:**

| Line                                      | Meaning                                                     |
| ----------------------------------------- | ----------------------------------------------------------- |
| `@Configuration`                          | Marks this class as a Spring configuration component        |
| `@Bean`                                   | Registers a custom configuration bean in the Spring context |
| `addMapping("/api/**")`                   | Applies rules to all endpoints starting with `/api`         |
| `allowedOrigins("http://localhost:3000")` | Allows React app requests                                   |
| `allowedMethods(...)`                     | Defines which HTTP methods React can use                    |
| `allowedHeaders("*")`                     | Allows all headers like `Content-Type`, `Authorization`     |
| `allowCredentials(true)`                  | Enables cookies/JWT sharing between frontend & backend      |

✅ **Use case:** Professional projects and production-level code.

---

### 🔐 3. When Using Spring Security

If your project adds authentication (JWT, sessions, etc.), CORS must also be allowed inside the security filter.

In your `SecurityConfig.java`:

``` Java
http.cors().and().csrf().disable();
```

And make sure the **same `GlobalCorsConfig` bean** exists.

✅ **Use case:** Secure applications with user login or JWT tokens.

---

## 🏁 TL;DR Summary

| Concept                | What It Does                        | Where to Use               |
| ---------------------- | ----------------------------------- | -------------------------- |
| **Same-Origin Policy** | Blocks unsafe cross-domain requests | Always active in browsers  |
| **CORS**               | Allows safe cross-domain API access | Configured on backend      |
| **`@CrossOrigin`**     | Enables CORS per controller         | Quick testing              |
| **Global Config**      | Centralized rule for all APIs       | ✅ Industry standard        |
| **CORS + Security**    | Needed for JWT/Auth systems         | When using Spring Security |

---

## 🚀 Quick Checklist (For Integration with React)

* [x] Add CORS config in backend (`GlobalCorsConfig.java`)
* [x] Run Spring Boot app → `http://localhost:8080`
* [x] Run React app → `http://localhost:3000`
* [x] From React, make fetch/axios calls to `/api/videos`
* [x] Confirm there are **no CORS errors** in the browser console

---

> 🧩 **Pro Tip:** Never use `"*"` in production for `allowedOrigins`.
> Always specify your real frontend domain (like `https://myapp.com`) for security reasons.
