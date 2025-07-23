🛍️ E-Commerce Backend – Spring Boot

A secure and scalable RESTful backend for a modern e-commerce platform. Built with **Spring Boot**, **JWT authentication**, **MySQL**, and deployed on **Render**. 
It supports user and admin roles, product management, cart handling, and order placement.

---

🚀 Features

- 🔐 JWT-based User Authentication
- 🛡️ Role-based Authorization (USER / ADMIN)
- 🛍️ Product CRUD (Admin only)
- 🛒 Cart & Order Management
- 📬 Email Notifications (Gmail SMTP)
- ☁️ Deployed on Render | MySQL hosted on Railway

---
**BACKED SPRINGBOOT workflow**:

<img width="850" height="489" alt="image" src="https://github.com/user-attachments/assets/9bef33b4-df19-4c30-8518-3724b99dfae1" />

---

🧰 Tech Stack

| Layer        | Technology             |
|--------------|------------------------|
| Backend      | Spring Boot (REST API) |
| Security     | Spring Security + JWT  |
| Database     | MySQL (via Railway)    |
| Deployment   | Render                 |
| Build Tool   | Maven                  |
| Java Version | Java 17+               |

---

<details>
<summary>🔐 <strong>Authentication API</strong></summary>

- `POST /api/auth/register?role=CUSTOMER` – Register as Customer  
- `POST /api/auth/register?role=ADMIN` – Register as Admin  
- `POST /api/auth/login` – Login & receive JWT  

</details>

<details>
<summary>🧑‍💼 <strong>Admin APIs (JWT Required)</strong></summary>

- `POST /api/products` – Add Product  
- `PUT /api/products/{id}` – Update Product  
- `DELETE /api/products/{id}` – Delete Product  

</details>

<details>
<summary>🛒 <strong>User Cart & Order APIs</strong></summary>

- `POST /api/cart/add/{productId}` – Add to Cart  
- `GET /api/cart` – View Cart  
- `POST /api/cart/place-order` – Place Order  
- `GET /api/orders` – View User Orders  

</details>

---

🗄️ Database Schema (Auto-generated via JPA)

- `users`  
- `product`  
- `cart_items`  
- `orders`
- `order_items`

✨ Tables are managed automatically by Spring Data JPA.

---

▶️ How to Run Locally

```bash
git clone https://github.com/Bhanu-prakashcse/ecommerce-backend.git
cd ecommerce-backend
# Add MySQL config in application.properties
mvn spring-boot:run
