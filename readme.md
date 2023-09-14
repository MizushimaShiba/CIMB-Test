# Blog API

This is a simple RESTful API for managing blog posts. It allows you to perform basic CRUD (Create, Read, Update, Delete) operations on blog posts.

## Getting Started

These instructions will help you set up and run the API on your local machine.

### Prerequisites

To run this API, you need to have the following software installed:

- Java Development Kit (JDK)
- Apache Maven

### Installing

1. Clone this repository to your local machine:

```bash
git clone https://github.com/MizushimaShiba/CIMB-Test.git
```

2. Change to the project directory:

```bash
cd CIMB-Test
```

3. Build the project using Maven:

```bash
mvn clean install
```

4. Run the application:

Please run using intellij idea CE

The API should now be running on http://localhost:9090

## Endpoints

### Retrieve All Blog Posts
- Endpoint: `GET /api/getAllBlogs`
- Description: This endpoint allows you to retrieve a list of all existing blog posts.
- Response: The API will return a JSON array containing all the blog posts. Each blog post object within the array includes its `id`, `title`, `body`, and `author`.
- HTTP Status Codes:
- `200 OK`: Blog posts retrieved successfully.
- `500 Internal Server Error`: If an unexpected error occurs on the server.

### Create a Blog Post
- Endpoint: `POST /api/addBlog`
- Description: This endpoint allows you to create a new blog post. You need to provide a JSON payload in the request body with the following properties:
- `title` (string, required): The title of the blog post.
- `body` (string, required): The content of the blog post.
- `author` (string, required): The author of the blog post.
- Response: Upon successful creation, the API will return a JSON response with the newly created blog post, including its `id` (auto-generated), `title`, `body`, and `author`.
- HTTP Status Codes:
- `201 Created`: Blog post created successfully.
- `400 Bad Request`: If the request body is invalid or missing any of the required properties.
- `500 Internal Server Error`: If an unexpected error occurs on the server.

```json
{
  "author":"Test",
  "title":"Test",
  "body":"Test"
}
```

### Retrieve a Blog Post
- Endpoint: `GET /api/getBlogsById/{id}`
- Description: Use this endpoint to retrieve a single blog post by specifying its unique identifier `id` in the URL path.
- Response: The API will respond with a JSON object representing the blog post, including its `id`, `title`, `body`, and `author`.
- HTTP Status Codes:
- `200 OK`: Blog post retrieved successfully.
- `404 Not Found`: If no blog post with the specified `id` is found.
- `500 Internal Server Error`: If an unexpected error occurs on the server.

### Update a Blog Post
- Endpoint: `POST /api/editBlogs/{id}`
- Description: You can use this endpoint to update an existing blog post by specifying its `id` in the URL path. The request body should contain a JSON payload with the following properties:
- `title` (string, required): The updated title of the blog post.
- `body` (string, required): The updated content of the blog post.
- `author` (string, required): The updated author of the blog post.
- Response: The API will respond with a JSON object representing the updated blog post, including its `id`, `title`, `body`, and `author`.
- HTTP Status Codes:
- `200 OK`: Blog post updated successfully.
- `404 Not Found`: If no blog post with the specified `id` is found.
- `500 Internal Server Error`: If an unexpected error occurs on the server.

```json
{
  "author":"Test",
  "title":"Test",
  "body":"Test"
}
```

### Delete a Blog Post
- Endpoint: `DELETE /api/deleteBlogById/{id}`
- Description: Use this endpoint to delete a blog post by specifying its unique identifier `id` in the URL path.
- HTTP Status Codes:
- `204 No Content`: Blog post deleted successfully.
- `404 Not Found`: If no blog post with the specified `id` is found.
- `500 Internal Server Error`: If an unexpected error occurs on the server.

## Built with
- Spring Boot - The web framework used
- H2 Database - In-memory database for storing blog post data

## Author
- Mizushima Shiba 水島芝




