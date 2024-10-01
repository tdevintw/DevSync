<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 80%;
            margin: auto;
            overflow: hidden;
        }

        .profile-header {
            display: flex;
            align-items: center;
            padding: 20px;
            background: #fff;
            margin-top: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .profile-picture img {
            border-radius: 50%;
            width: 100px;
            height: 100px;
            object-fit: cover;
        }

        .profile-info {
            margin-left: 20px;
        }

        .profile-info h1 {
            margin: 0;
            color: #333;
        }

        .profile-info p {
            margin: 5px 0 0;
            color: #666;
        }

        .profile-details {
            background: #fff;
            margin-top: 20px;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .profile-details h2 {
            margin-top: 0;
            color: #333;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #666;
        }

        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

    </style>
</head>
<body>
<div class="container">
    <div class="profile-header">
        <div class="profile-info">
            <h1>${user.name}</h1>
            <p>${user.email}</p>
        </div>
    </div>
    <div class="profile-details">
        <h2>Profile Details</h2>
        <form>
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" id="name" name="name" value="${user.name}">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" value="${user.email}">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" value="${user.password}">
            </div>
            <div class="form-group">
                <label for="confirm-password">Confirm Password</label>
                <input type="password" id="confirm-password" name="confirm-password" value="${user.password}">
            </div>
            <button type="submit">Update Profile</button>
        </form>
    </div>
</div>
</body>
</html>
