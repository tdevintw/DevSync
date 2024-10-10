<%@ page import="dev.yassiraitelghari.domain.Task" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Responsive Admin Dashboard | Korsat X Parmaga</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

    <!-- ======= Styles ====== -->
    <style>


        /* =========== Google Fonts ============ */
        @import url("https://fonts.googleapis.com/css2?family=Ubuntu:wght@300;400;500;700&display=swap");

        /* =============== Globals ============== */
        * {
            font-family: "Ubuntu", sans-serif;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        :root {
            --blue: #2a2185;
            --white: #fff;
            --gray: #f5f5f5;
            --black1: #222;
            --black2: #999;
        }

        body {
            min-height: 100vh;
            overflow-x: hidden;
        }

        .container {
            position: relative;
            width: 100%;
        }

        /* =============== Navigation ================ */
        .navigation {
            position: fixed;
            width: 300px;
            height: 100%;
            background: var(--blue);
            border-left: 10px solid var(--blue);
            transition: 0.5s;
            overflow: hidden;
        }

        .navigation.active {
            width: 80px;
        }

        .navigation ul {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
        }

        .navigation ul li {
            position: relative;
            width: 100%;
            list-style: none;
            border-top-left-radius: 30px;
            border-bottom-left-radius: 30px;
        }

        .navigation ul li:hover,
        .navigation ul li.hovered {
            background-color: var(--white);
        }

        .navigation ul li:nth-child(1) {
            margin-bottom: 40px;
            pointer-events: none;
        }

        .navigation ul li a {
            position: relative;
            display: block;
            width: 100%;
            display: flex;
            text-decoration: none;
            color: var(--white);
        }

        .navigation ul li:hover a,
        .navigation ul li.hovered a {
            color: var(--blue);
        }

        .navigation ul li a .icon {
            position: relative;
            display: block;
            min-width: 60px;
            height: 60px;
            line-height: 75px;
            text-align: center;
        }

        .navigation ul li a .icon ion-icon {
            font-size: 1.75rem;
        }

        .navigation ul li a .title {
            position: relative;
            display: block;
            padding: 0 10px;
            height: 60px;
            line-height: 60px;
            text-align: start;
            white-space: nowrap;
        }

        /* --------- curve outside ---------- */
        .navigation ul li:hover a::before,
        .navigation ul li.hovered a::before {
            content: "";
            position: absolute;
            right: 0;
            top: -50px;
            width: 50px;
            height: 50px;
            background-color: transparent;
            border-radius: 50%;
            box-shadow: 35px 35px 0 10px var(--white);
            pointer-events: none;
        }

        .navigation ul li:hover a::after,
        .navigation ul li.hovered a::after {
            content: "";
            position: absolute;
            right: 0;
            bottom: -50px;
            width: 50px;
            height: 50px;
            background-color: transparent;
            border-radius: 50%;
            box-shadow: 35px -35px 0 10px var(--white);
            pointer-events: none;
        }

        /* ===================== Main ===================== */
        .main {
            position: absolute;
            width: calc(100% - 300px);
            left: 300px;
            min-height: 100vh;
            background: var(--white);
            transition: 0.5s;
        }

        .main.active {
            width: calc(100% - 80px);
            left: 80px;
        }

        .topbar {
            width: 100%;
            height: 60px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 0 10px;
        }

        .toggle {
            position: relative;
            width: 60px;
            height: 60px;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 2.5rem;
            cursor: pointer;
        }

        .search {
            position: relative;
            width: 400px;
            margin: 0 10px;
        }

        .search label {
            position: relative;
            width: 100%;
        }

        .search label input {
            width: 100%;
            height: 40px;
            border-radius: 40px;
            padding: 5px 20px;
            padding-left: 35px;
            font-size: 18px;
            outline: none;
            border: 1px solid var(--black2);
        }

        .search label ion-icon {
            position: absolute;
            top: 0;
            left: 10px;
            font-size: 1.2rem;
        }

        .user {
            position: relative;
            width: 40px;
            height: 40px;
            border-radius: 50%;
            overflow: hidden;
            cursor: pointer;
        }

        .user img {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        /* ======================= Cards ====================== */
        .cardBox {
            position: relative;
            width: 100%;
            padding: 20px;
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            grid-gap: 30px;
        }

        .cardBox .card {
            position: relative;
            background: var(--white);
            padding: 30px;
            border-radius: 20px;
            display: flex;
            justify-content: space-between;
            cursor: pointer;
            box-shadow: 0 7px 25px rgba(0, 0, 0, 0.08);
        }

        .cardBox .card .numbers {
            position: relative;
            font-weight: 500;
            font-size: 2.5rem;
            color: var(--blue);
        }

        .cardBox .card .cardName {
            color: var(--black2);
            font-size: 1.1rem;
            margin-top: 5px;
        }

        .cardBox .card .iconBx {
            font-size: 3.5rem;
            color: var(--black2);
        }

        .cardBox .card:hover {
            background: var(--blue);
        }

        .cardBox .card:hover .numbers,
        .cardBox .card:hover .cardName,
        .cardBox .card:hover .iconBx {
            color: var(--white);
        }

        /* ================== Order Details List ============== */
        .details {
            position: relative;
            width: 100%;
            padding: 20px;
        }

        .details .recentOrders {
            position: relative;
            display: grid;
            background: var(--white);
            padding: 20px;
            box-shadow: 0 7px 25px rgba(0, 0, 0, 0.08);
            border-radius: 20px;
        }

        .details .cardHeader {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
        }

        .cardHeader h2 {
            font-weight: 600;
            color: var(--blue);
        }

        .cardHeader .btn {
            position: relative;
            padding: 5px 10px;
            background: var(--blue);
            text-decoration: none;
            color: var(--white);
            border-radius: 6px;
        }

        .details table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        .details table thead td {
            font-weight: 600;
        }

        .details .recentOrders table tr {
            color: var(--black1);
            border-bottom: 1px solid rgba(0, 0, 0, 0.1);
        }

        .details .recentOrders table tr:last-child {
            border-bottom: none;
        }

        .details .recentOrders table tbody tr:hover {
            background: var(--blue);
            color: var(--white);
        }

        .details .recentOrders table tr td {
            padding: 10px;
        }

        .details .recentOrders table tr td:last-child {
            text-align: center;
        }

        .details .recentOrders table tr td:nth-child(2) {
            text-align: center;
        }

        .details .recentOrders table tr td:nth-child(3) {
            text-align: center;
        }

        .status.delivered {
            padding: 2px 4px;
            background: #8de02c;
            color: var(--white);
            border-radius: 4px;
            font-size: 14px;
            font-weight: 500;
        }

        .status.pending {
            padding: 2px 4px;
            background: #e9b10a;
            color: var(--white);
            border-radius: 4px;
            font-size: 14px;
            font-weight: 500;
        }

        .status.return {
            padding: 2px 4px;
            background: #f00;
            color: var(--white);
            border-radius: 4px;
            font-size: 14px;
            font-weight: 500;
        }

        .status.inProgress {
            padding: 2px 4px;
            background: #1795ce;
            color: var(--white);
            border-radius: 4px;
            font-size: 14px;
            font-weight: 500;
        }

        .recentCustomers {
            position: relative;
            display: grid;
            min-height: 500px;
            padding: 20px;
            background: var(--white);
            box-shadow: 0 7px 25px rgba(0, 0, 0, 0.08);
            border-radius: 20px;
        }

        .recentCustomers .imgBx {
            position: relative;
            width: 40px;
            height: 40px;
            border-radius: 50px;
            overflow: hidden;
        }

        .recentCustomers .imgBx img {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        .recentCustomers table tr td {
            padding: 12px 10px;
        }

        .recentCustomers table tr td h4 {
            font-size: 16px;
            font-weight: 500;
            line-height: 1.2rem;
        }

        .recentCustomers table tr td h4 span {
            font-size: 14px;
            color: var(--black2);
        }

        .recentCustomers table tr:hover {
            background: var(--blue);
            color: var(--white);
        }

        .recentCustomers table tr:hover td h4 span {
            color: var(--white);
        }

        /* ====================== Responsive Design ========================== */
        @media (max-width: 991px) {
            .navigation {
                left: -300px;
            }

            .navigation.active {
                width: 300px;
                left: 0;
            }

            .main {
                width: 100%;
                left: 0;
            }

            .main.active {
                left: 300px;
            }

            .cardBox {
                grid-template-columns: repeat(2, 1fr);
            }
        }

        @media (max-width: 768px) {
            .details {
                grid-template-columns: 1fr;
            }

            .recentOrders {
                overflow-x: auto;
            }

            .status.inProgress {
                white-space: nowrap;
            }
        }

        @media (max-width: 480px) {
            .cardBox {
                grid-template-columns: repeat(1, 1fr);
            }

            .cardHeader h2 {
                font-size: 20px;
            }

            .user {
                min-width: 40px;
            }

            .navigation {
                width: 100%;
                left: -100%;
                z-index: 1000;
            }

            .navigation.active {
                width: 100%;
                left: 0;
            }

            .toggle {
                z-index: 10001;
            }

            .main.active .toggle {
                color: #fff;
                position: fixed;
                right: 0;
                left: initial;
            }
        }

        #container #menu-wrap .dots > div {
            position: relative;
        }

        #container #menu-wrap .dots > div:after {
            content: "";
            position: absolute;
            bottom: calc((25px / 2) - (6px / 2));
            left: 0;
        }

        #container #menu-wrap .dots > div:before {
            content: "";
            position: absolute;
            top: calc((25px / 2) - (6px / 2));
            left: 0;
        }

        #container #menu-wrap .menu {
            position: absolute;
            right: -10px;
            top: calc(-12px + 50px);
            width: 0;
            height: 0;
            background-color: rgba(255, 255, 255, 0.8);
            padding: 20px 15px;
            -webkit-box-shadow: 2px 4px 6px rgba(49, 49, 49, 0.2);
            box-shadow: 2px 4px 6px rgba(49, 49, 49, 0.2);
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-orient: vertical;
            -webkit-box-direction: normal;
            -ms-flex-direction: column;
            flex-direction: column;
            -webkit-box-align: start;
            -ms-flex-align: start;
            align-items: flex-start;
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            justify-content: center;
            opacity: 0;
            visibility: hidden;
        }

        #container #menu-wrap .menu ul {
            list-style: none;
        }

        #container #menu-wrap .menu ul li {
            margin: 15px 0;
        }

        #container #menu-wrap .menu ul li .link {
            text-decoration: none;
            color: rgba(49, 49, 49, 0.85);
            opacity: 0;
            visibility: hidden;
        }

        #container #menu-wrap .toggler {
            position: absolute;
            height: 100%;
            width: 100%;
            top: 0;
            left: 0;
            -webkit-appearance: none;
            -moz-appearance: none;
            appearance: none;
            cursor: pointer;
            z-index: 2;
        }

        #container #menu-wrap .toggler:hover + .dots > div,
        #container #menu-wrap .toggler:hover + .dots > div:after,
        #container #menu-wrap .toggler:hover + .dots > div:before {
            background-color: rgba(49, 49, 49, 0.6);
        }

        #container #menu-wrap .toggler:checked + .dots > div {
            -webkit-transform: translateX(calc(((25px / 2) - (6px / 2)) * -0.7071067812)) translateY(calc(((25px / 2) - (6px / 2)) * -0.7071067812));
            -ms-transform: translateX(calc(((25px / 2) - (6px / 2)) * -0.7071067812)) translateY(calc(((25px / 2) - (6px / 2)) * -0.7071067812));
            transform: translateX(calc(((25px / 2) - (6px / 2)) * -0.7071067812)) translateY(calc(((25px / 2) - (6px / 2)) * -0.7071067812));
        }

        #container #menu-wrap .toggler:checked + .dots > div:after {
            -webkit-transform: translateX(calc(((25px / 2) - (6px / 2)) * 0.7071067812)) translateY(calc((2 * (25px / 2) - (6px / 2)) * 0.7071067812));
            -ms-transform: translateX(calc(((25px / 2) - (6px / 2)) * 0.7071067812)) translateY(calc((2 * (25px / 2) - (6px / 2)) * 0.7071067812));
            transform: translateX(calc(((25px / 2) - (6px / 2)) * 0.7071067812)) translateY(calc((2 * (25px / 2) - (6px / 2)) * 0.7071067812));
        }

        #container #menu-wrap .toggler:checked + .dots > div:before {
            -webkit-transform: translateX(
                    calc(2 * (((25px / 2) - (6px / 2)) * 0.7071067812))
            ) translateY(
                    calc(((25px / 2) - (6px / 2)) - (((25px / 2) - (6px / 2)) * 0.7071067812))
            );
            -ms-transform: translateX(calc(2 * (((25px / 2) - (6px / 2)) * 0.7071067812))) translateY(
                    calc(((25px / 2) - (6px / 2)) - (((25px / 2) - (6px / 2)) * 0.7071067812))
            );
            transform: translateX(calc(2 * (((25px / 2) - (6px / 2)) * 0.7071067812))) translateY(
                    calc(((25px / 2) - (6px / 2)) - (((25px / 2) - (6px / 2)) * 0.7071067812))
            );
        }

        #container #menu-wrap .toggler:checked:hover + .dots > div,
        #container #menu-wrap .toggler:checked:hover + .dots > div:after,
        #container #menu-wrap .toggler:checked:hover + .dots > div:before {
            background-color: rgba(49, 49, 49, 0.6);
            -webkit-transition: 0.5s;
            -o-transition: 0.5s;
            transition: 0.5s;
        }

        #container #menu-wrap .toggler:checked ~ .menu {
            opacity: 1;
            visibility: visible;
            width: 150px;
            height: 130px;
            -webkit-transition: 0.5s;
            -o-transition: 0.5s;
            transition: 0.5s;
        }

        #menu-wrap .toggler:checked ~ .menu ul .link {
            opacity: 1;
            visibility: visible;
            -webkit-transition: 0.5s ease 0.3s;
            -o-transition: 0.5s ease 0.3s;
            transition: 0.5s ease 0.3s;
        }

        #menu-wrap .toggler:checked ~ .menu ul .link:hover {
            color: #2980b9;
            -webkit-transition: 0.2s;
            -o-transition: 0.2s;
            transition: 0.2s;
        }

        #menu-wrap .toggler:not(:checked) ~ .menu {
            -webkit-transition: 0.5s;
            -o-transition: 0.5s;
            transition: 0.5s;
        }

        #menu-wrap .toggler:not(:checked) ~ .menu ul .link {
            opacity: 0;
            visibility: hidden;
            -webkit-transition: 0.1s;
            -o-transition: 0.1s;
            transition: 0.1s;
        }

        @media (max-width: 600px) {
            #container {
                position: absolute;
                top: 50px;
                width: calc(100% - 40px);
                margin: 0;
            }
        }

        .button-3 {
            appearance: none;
            background-color: #2ea44f;
            border: 1px solid rgba(27, 31, 35, .15);
            border-radius: 6px;
            box-shadow: rgba(27, 31, 35, .1) 0 1px 0;
            box-sizing: border-box;
            color: #fff;
            cursor: pointer;
            display: inline-block;
            font-family: -apple-system, system-ui, "Segoe UI", Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji";
            font-size: 14px;
            font-weight: 600;
            line-height: 20px;
            padding: 6px 16px;
            position: relative;
            text-align: center;
            text-decoration: none;
            user-select: none;
            -webkit-user-select: none;
            touch-action: manipulation;
            vertical-align: middle;
            white-space: nowrap;
        }

        .button-3:focus:not(:focus-visible):not(.focus-visible) {
            box-shadow: none;
            outline: none;
        }

        .button-3:hover {
            background-color: #2c974b;
        }

        .button-3:focus {
            box-shadow: rgba(46, 164, 79, .4) 0 0 0 3px;
            outline: none;
        }

        .button-3:disabled {
            background-color: #94d3a2;
            border-color: rgba(27, 31, 35, .1);
            color: rgba(255, 255, 255, .8);
            cursor: default;
        }

        .button-3:active {
            background-color: #298e46;
            box-shadow: rgba(20, 70, 32, .2) 0 1px 0 inset;
        }

        .button-4 {
            appearance: none;
            background-color: red;
            border: 1px solid rgba(27, 31, 35, .15);
            border-radius: 6px;
            box-shadow: rgba(27, 31, 35, .1) 0 1px 0;
            box-sizing: border-box;
            color: #fff;
            cursor: pointer;
            display: inline-block;
            font-family: -apple-system, system-ui, "Segoe UI", Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji";
            font-size: 14px;
            font-weight: 600;
            line-height: 20px;
            padding: 6px 16px;
            position: relative;
            text-align: center;
            text-decoration: none;
            user-select: none;
            -webkit-user-select: none;
            touch-action: manipulation;
            vertical-align: middle;
            white-space: nowrap;
        }

        .button-4:focus:not(:focus-visible):not(.focus-visible) {
            box-shadow: none;
            outline: none;
        }

        .button-4:hover {
            background-color: darkred;
        }

        .button-4:focus {
            box-shadow: rgba(46, 164, 79, .4) 0 0 0 3px;
            outline: none;
        }

        .button-4:disabled {
            background-color: #94d3a2;
            border-color: rgba(27, 31, 35, .1);
            color: rgba(255, 255, 255, .8);
            cursor: default;
        }

        .button-4:active {
            background-color: darkred;
            box-shadow: rgba(20, 70, 32, .2) 0 1px 0 inset;
        }
    </style>
</head>

<body>
<!-- =============== Navigation ================ -->
<div class="container">
    <div class="navigation">
        <ul>
            <li>
                <a href="#">
                        <span class="icon">
                            <ion-icon name="logo-apple"></ion-icon>
                        </span>
                    <span class="title">Brand Name</span>
                </a>
            </li>

            <li>
                <a href="dashboard">
                        <span class="icon">
                            <ion-icon name="home-outline"></ion-icon>
                        </span>
                    <span class="title">Dashboard</span>
                </a>
            </li>

            <li>
                <a href="#">
                        <span class="icon">
                            <ion-icon name="people-outline"></ion-icon>
                        </span>
                    <span class="title">Customers</span>
                </a>
            </li>

            <li>
                <a href="#">
                        <span class="icon">
                            <ion-icon name="chatbubble-outline"></ion-icon>
                        </span>
                    <span class="title">Messages</span>
                </a>
            </li>

            <li>
                <a href="#">
                        <span class="icon">
                            <ion-icon name="help-outline"></ion-icon>
                        </span>
                    <span class="title">Help</span>
                </a>
            </li>

            <li>
                <a href="#">
                        <span class="icon">
                            <ion-icon name="settings-outline"></ion-icon>
                        </span>
                    <span class="title">Settings</span>
                </a>
            </li>

            <li>
                <a href="#">
                        <span class="icon">
                            <ion-icon name="lock-closed-outline"></ion-icon>
                        </span>
                    <span class="title">Password</span>
                </a>
            </li>

            <li>
                <a href="logout">
                        <span class="icon">
                            <ion-icon name="log-out-outline"></ion-icon>
                        </span>
                    <span class="title">Sign Out</span>
                </a>
            </li>
        </ul>
    </div>
    <!-- ========================= Main ==================== -->
    <div class="main">
        <div class="topbar">
            <div class="toggle">
                <ion-icon name="menu-outline"></ion-icon>
            </div>

            <div class="search">
                <label>
                    <input type="text" placeholder="Search here">
                    <ion-icon name="search-outline"></ion-icon>
                </label>
            </div>

            <div class="user">
                <img src="https://img.freepik.com/free-photo/portrait-man-laughing_23-2148859448.jpg?size=338&ext=jpg&ga=GA1.1.2008272138.1721865600&semt=ais_user"
                     alt="">
            </div>
        </div>

        <!-- ======================= Cards ================== -->
        <div class="cardBox">
            <div class="card">
                <div>
                    <div class="numbers">
                        <%
                            int size = (int)request.getAttribute("size");
                            out.println(size);%>
                    </div>
                    <div class="clipboard-outline">Tasks</div>
                </div>

                <div class="iconBx">
                    <ion-icon name="clipboard-outline"></ion-icon>
                </div>
            </div>
        </div>
        <div style="display: flex ; justify-content: flex-end;margin-right: 3rem">
            <form action="dashboard/addTask" method="get">
                <button type="submit" class="button-3">New Task</button>
            </form>
        </div>
        <div class="details">
            <div class="recentOrders">
                <div class="cardHeader">
                    <h2>My tasks</h2>
                    <a href="#" class="btn">View All</a>
                </div>
                <div style='overflow-x: auto; max-width: 100%;'>
                    <table>
                        <thead>
                        <tr>
                            <td style="text-align: center">Title</td>
                            <td style="text-align: center">Description</td>
                            <td style="text-align: center">Status</td>
                            <td style="text-align: center">Start Date</td>
                            <td style="text-align: center">End Date</td>
                            <td style="text-align: center">Edit Status</td>
                            <td style="text-align: center">Action</td>

                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<Task> tasks = (List<Task>) request.getAttribute("tasks");
                            if(tasks!=null && !tasks.isEmpty()) {

                                for (Task task : tasks) {
                                    out.println("<tr>");
                                    out.println("<td style='text-align:center'>" + task.getName() + "</td>");
                                    out.println("<td style='text-align:center'>" + task.getDescription() + "</td>");
                                    out.println("<td style='text-align:center'>" + task.getStatus() + "</td>");
                                    out.println("<td style='text-align:center'>" + task.getStartDate() + "</td>");
                                    out.println("<td style='text-align:center'>" + task.getDateLimit() + "</td>");
                                    if (task.getStatus().equals("In Progress")) {
                                        out.println("<td><dev style='display:flex;gap:10px;justify-content:center;'>");
                                        out.println("<form method='post' action='dashboard/update'><input type='hidden' name='task_id' value=" + task.getId() + "><input type='hidden' name='method' value='VALIDATE'><button class=\"button-3\" role=\"button\">Validate</button></form>\n");
                                        out.println("<form method='post' action='dashboard/update'><input type='hidden' name='task_id' value=" + task.getId() + "><input type='hidden' name='method' value='CANCELED'><button class=\"button-4\" role=\"button\">Cancel</button></form>\n");
                                        out.println("</dev></td>");
                                    } else {
                                        out.println("<td>Task " + task.getStatus() + "</td>\n");
                                    }
                                    out.println("<td><div style='display:flex;gap:5px;justify-content:center'>");
                                    if (task.isAddedByMe()) {
                                        out.println("<button style='background-color:green' class='button-3'>Edit</button>");
                                    } else if (!task.getIsReplaced() && task.getRequest() == null) {
                                        out.println("<form action='request' method='get'><input type='hidden' name='task_id' value=" + task.getId() + "><button  type='submit' style='background-color:gray' class='button-3'>Replace</button></form>");
                                    } else if (task.getRequest() != null) {
                                        out.println("<button  style='background-color:gray' class='button-3'>Request Sent</button>");
                                    }
                                    out.println("<form action='dashboard/delete' method='post'><input type='hidden' name='task_id' value=" + task.getId() + "><button type='submit' class='button-4'>Delete</button></form>");
                                    out.println("<div></td>");
                                    out.println("</tr>");
                                }
                            }
                        %>
                        </tbody>


                    </table>
                    <%if(size==0){%>
                    <h1 style="align-items: center">There is no Tasks </h1>
                    <%}%>
                </div>
            </div>
        </div>

    </div>
</div>

<!-- =========== Scripts =========  -->
<script>
    // add hovered class to selected list item
    let list = document.querySelectorAll(".navigation li");

    function activeLink() {
        list.forEach((item) => {
            item.classList.remove("hovered");
        });
        this.classList.add("hovered");
    }

    list.forEach((item) => item.addEventListener("mouseover", activeLink));

    // Menu Toggle
    let toggle = document.querySelector(".toggle");
    let navigation = document.querySelector(".navigation");
    let main = document.querySelector(".main");

    toggle.onclick = function () {
        navigation.classList.toggle("active");
        main.classList.toggle("active");
    };

</script>
<%
    if (request.getSession().getAttribute("insufficient_token") != null) {
        String alert = (String)request.getSession().getAttribute("insufficient_token");
        request.getSession().removeAttribute("insufficient_token");
        out.println("<script>alert('" +alert+ "');</script>");
    }
%>


<!-- ====== ionicons ======= -->
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>

</html>
