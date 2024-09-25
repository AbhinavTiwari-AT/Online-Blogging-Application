Online Blogging Application

Online Blogging Application is a Spring Boot project designed for managing blog posts with various features like user authentication, post creation, categorization, commenting, and role management.

Entities Overview:
1. User:

Represents users of the application.
Fields: id, name, email, password, roles, etc.
Relationships: One-to-many relationship with Post and Comment.
2. Post:

Represents individual blog posts.
Fields: id, title, content, category, author, etc.
Relationships: Many-to-one relationship with User (author) and Category. One-to-many with Comment.
3. Comment:

Represents user comments on blog posts.
Fields: id, content, user, post, etc.
Relationships: Many-to-one relationship with both User and Post.
Categories:

Represents the different categories under which blog posts are classified.
Fields: id, name, etc.
Relationships: One-to-many relationship with Post.
4. Role:

Represents roles assigned to users, like ADMIN, USER.
Fields: id, roleName, etc.
Relationships: Many-to-many relationship with User.
Key Features:
JWT Authentication:

User login and access is protected using JWT tokens for secure authentication and authorization.
Roles and permissions control access to various features.
User Management:

User registration and login.
Role-based access control, allowing admins to manage posts, comments, and categories.
Post Management:

Users (with permission) can create, edit, delete, and view blog posts.
Posts can be categorized and displayed according to different categories.
Commenting System:

Registered users can comment on posts.
Admins or post authors can manage (delete/edit) comments.
Category Management:

Admins can create, update, and delete categories for organizing blog posts.
Technology Stack:
Backend: Spring Boot
Security: Spring Security with JWT for authentication.
ORM: Hibernate (JPA) for entity management.
Database: MySQL/PostgreSQL for storing user, post, comment, and category data.
Frontend (if applicable): May be built with frameworks like React.js or Angular for displaying blog content.
Typical API Endpoints (RESTful):
User Controller:

/api/users/ – Register new users, manage users (admin only).
/api/login/ – User login and JWT token generation.
Post Controller:

/api/posts/ – Create, update, delete, and view blog posts.
/api/posts/{postId} – Get details of a specific post.
Comment Controller:

/api/posts/{postId}/comments – Add, update, or delete comments on a post.
Category Controller:

/api/categories/ – Manage categories (admin only).
