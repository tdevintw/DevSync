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
        .button-75 {
            align-items: center;
            background-image: linear-gradient(135deg, #f34079 40%, #fc894d);
            border: 0;
            border-radius: 10px;
            box-sizing: border-box;
            color: #fff;
            cursor: pointer;
            display: flex;
            flex-direction: column;
            font-family: "Codec cold",sans-serif;
            font-size: 16px;
            font-weight: 700;
            height: 50px;
            justify-content: center;
            letter-spacing: .4px;
            line-height: 1;
            max-width: 100%;
            padding-left: 20px;
            padding-right: 20px;
            padding-top: 6px;
            text-decoration: none;
            text-transform: uppercase;
            user-select: none;
            -webkit-user-select: none;
            touch-action: manipulation;
            margin-top: 20px;
        }

        .button-75:active {
            outline: 0;
        }

        .button-75:hover {
            outline: 0;
        }

        .button-75 span {
            transition: all 200ms;
        }

        .button-75:hover span {
            transform: scale(.9);
            opacity: .75;
        }

        @media screen and (max-width: 991px) {
            .button-75 {
                font-size: 7px;
                height: 35px;
            }

            .button-75 span {
                line-height: 25px;
            }
        }

    </style>
</head>
<body>
<div class="container">
    <div class="profile-header">
        <div class="profile-info">
            <h1>${user.username}</h1>
            <p>${user.email}</p>
        </div>
    </div>
    <div class="profile-details">
        <h2>Profile Details</h2>
        <form action="profile" method="post">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" id="name" name="name" value="${user.name}">
            </div>
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" id="last_name" name="last_name" value="${user.lastName}">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" value="${user.password}">
            </div>
            <div class="form-group">
                <label for="confirm-password">Confirm Password</label>
                <input type="password" id="confirm-password" name="confirm_password" value="${user.password}">
            </div>
            <button type="submit">Update Profile</button>
        </form>
        <form action="profile" method="post" onsubmit="return confirm('Are you sure you want to delete your profile?');">
            <input type="hidden" name="_method" value="DELETE">
            <button  type="submit" class="button-75" >Delete Profile</button>
        </form>
    </div>
</div>
</body>
</html>
