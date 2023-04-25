## API Documentation: Create User

API endpoint to create a new user.

### Request

#### Endpoint

```http
POST /users
```

#### Headers

| Header        | Value              |
|---------------|--------------------|
| Content-Type  | application/json   |

#### Body

```json
{
    "first_name" : "Mohamed",
    "last_name" : "Ahmed",
    "username" : "Mohamed.Ahmed",
    "email" : "Mohamed.Ahmed@gmail.com",
    "mobile" : "01200848463"
}
```

### Response

#### Success Response

##### Status Code: 201 Created

```json
{
    "id" : "123",
    "first_name" : "Mohamed",
    "last_name" : "Ahmed",
    "username" : "Mohamed.Ahmed",
    "email" : "Mohamed.Ahmed@gmail.com",
    "mobile" : "01200848463",
    "created_at" : "2023-04-24T12:00:00Z",
    "updated_at" : "2023-04-24T12:00:00Z"
}
```

#### Error Response

##### Status Code: 400 Bad Request

If the request body is missing any of the required fields, or if any of the fields are not in the correct format, the API will return a 400 Bad Request response.

```json
{
    "error" : "Bad Request",
    "message" : "Invalid request body"
}
```

##### Status Code: 500 Internal Server Error

If the server encounters an error while processing the request, the API will return a 500 Internal Server Error response.

```json
{
    "error" : "Internal Server Error",
    "message" : "An error occurred while processing the request"
}
```
