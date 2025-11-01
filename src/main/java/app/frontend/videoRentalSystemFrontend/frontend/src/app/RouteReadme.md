# 📘 Angular Routing Module Cheatsheet (For Future Reference)

This document is your **quick reference guide** for understanding and writing Angular routing modules — especially useful when connecting frontend apps (like your Video Rental System) to backend routes.

---

## 📂 File: `app-routing.module.ts`

```typescript
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';

export const routes: Routes = [
  { path: '', component: HomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
```

---

## 🧠 Purpose

The routing module tells Angular **which component to show for a given URL path** — just like Spring Boot controllers map HTTP routes to services.

In simple terms:

> `RouterModule` in Angular = `@RestController` in Spring Boot.

---

## 🧩 Line-by-Line Breakdown

### 1. Import Required Modules

```typescript
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
```

* `NgModule`: Defines a module — a container for components, directives, pipes, and services.
* `RouterModule`: The core Angular library that enables routing.
* `Routes`: A TypeScript type that describes an array of route configurations.

---

### 2. Import Components for Routing

```typescript
import { HomeComponent } from './home/home.component';
```

* You must import the components that you’ll display when a route is accessed.
* Example: `path: 'videos'` → shows a `VideoListComponent`.

---

### 3. Define Route Paths

```typescript
export const routes: Routes = [
  { path: '', component: HomeComponent }
];
```

* `path` → URL segment.
* `component` → Which component to load when that path is visited.

| Path           | What it Does        | Example URL |
| -------------- | ------------------- | ----------- |
| `''`           | Default route       | `/`         |
| `'videos'`     | Show video list     | `/videos`   |
| `'videos/:id'` | Show specific video | `/videos/5` |

---

### 4. Register Routes in the App

```typescript
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
```

* `forRoot(routes)` tells Angular this is the main routing configuration.
* `exports` lets other modules (like `AppModule`) use the router features.

> 🧩 Think of `forRoot()` as registering all `@GetMapping` or `@PostMapping` endpoints globally.

---

### 5. Export the Routing Module

```typescript
export class AppRoutingModule { }
```

* Exports a class so it can be imported in your `app.module.ts`.
* It doesn’t contain logic — it’s just a configuration holder.

---

## 💡 Pro Tips (Industry Standard)

✅ Keep routes in their own module — don’t mix routing inside `app.module.ts`.

✅ For large apps, create **feature modules** (e.g., `VideoModule`, `UserModule`) and use `RouterModule.forChild()` for nested routing.

✅ Always have a **default route** (`path: ''`) and a **wildcard route** for 404s:

```typescript
{ path: '**', component: PageNotFoundComponent }
```

✅ Use `<router-outlet>` in `app.component.html` as the placeholder for routed views.

✅ Use `routerLink` directive for navigation:

```html
<a routerLink="/videos">All Videos</a>
```

✅ Always verify route changes using **Angular DevTools** or browser console logs.

---

## 🧾 Summary Table

| Keyword           | Purpose                                  |
| ----------------- | ---------------------------------------- |
| `Routes`          | Defines path → component mapping         |
| `RouterModule`    | Provides routing capabilities            |
| `forRoot()`       | Registers routes globally                |
| `forChild()`      | Registers routes inside feature modules  |
| `<router-outlet>` | Placeholder where routed component loads |
| `routerLink`      | Attribute for clickable navigation       |

---

## 🧠 Analogy with Spring Boot

| Spring Boot                   | Angular                                                    |
| ----------------------------- | ---------------------------------------------------------- |
| `@RestController`             | `@NgModule` with RouterModule                              |
| `@GetMapping("/videos")`      | `{ path: 'videos', component: VideoListComponent }`        |
| `@GetMapping("/videos/{id}")` | `{ path: 'videos/:id', component: VideoDetailsComponent }` |

> So when you visit `/videos` in your Angular app, it behaves like hitting `/api/videos` on your backend — except this is frontend routing handled by Angular’s router.

---

✅ **Always remember:** Routing in Angular is *frontend-only navigation*, not API calling — the browser’s URL changes, but it doesn’t refresh or contact the backend unless you explicitly make an HTTP call.

---

### 🏁 TL;DR Cheat Notes

* File name → `app-routing.module.ts`
* Register routes → `RouterModule.forRoot(routes)`
* Main placeholder → `<router-outlet>`
* Default route → `{ path: '', component: HomeComponent }`
* Wildcard route → `{ path: '**', component: PageNotFoundComponent }`

---


