<%@ page import="dev.yassiraitelghari.domain.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="dev.yassiraitelghari.utils.TaskErrors" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Responsive Admin Dashboard | Korsat X Parmaga</title>

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
            display: flex;
            flex-direction: column;
            align-items: center;
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

        /* ====================== Responsive Design ========================== */
        @media (min-width: 1980px) {
            .tag-container {
                width: 45%;
            }
        }

        @media (max-width: 1980px) {
            .tag-container {
                width: 50%;
            }
        }

        @media (max-width: 1600px) {
            .tag-container {
                width: 60%;
            }
        }

        @media (max-width: 1200px) {
            .tag-container {
                width: 70%;
            }
        }

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
            .tag-container {
                width: 80%;
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
            .tag-container {
                width: 90%;
            }
        }

        .tag-container {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            text-align: center;
            margin-top: 3rem;
        }

        .logo {
            width: 40px;
            height: 40px;
        }

        .input-container {
            display: flex;
            flex-wrap: wrap;
            gap: 5px;
            background-color: #ffffff;
            padding: 5px;
            border-radius: 4px;
            margin: 10px 0;
            border: 1px solid #e3e1e1;
        }

        .input-container input {
            flex: 1;
            border: none;
            outline: none;
            padding: 5px;
        }

        .input-container ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
            display: flex;
            flex-wrap: wrap;
            gap: 5px;
        }

        .input-container ul li {
            background-color: #e0e0e0;
            padding: 5px 10px;
            border-radius: 4px;
            display: flex;
            align-items: center;
        }

        .input-container ul li .remove-tag {
            background: none;
            border: none;
            margin-left: 5px;
            cursor: pointer;
        }

        .remaining-tags {
            margin: 10px 0;
        }

        #remove-all {
            background-color: #2a2185;
            border: none;
            padding: 10px;
            border-radius: 4px;
            cursor: pointer;
            color: white;
        }

        #remove-all:hover {
            background-color: rgb(0, 0, 123); /* Slightly darker blue for hover effect */
        }
        .confirm button{
            background-color: #231d63;
            border: none;
            padding: 10px;
            border-radius: 4px;
            cursor: pointer;
            color: white;
            width: 7rem;
        }

    </style>
</head>

<body>
<!-- =============== Navigation ================ -->
<jsp:include page="../components/aside.jsp" />

<div class="container">
    <!-- ========================= Main ==================== -->
    <div class="main">
        <div class="topbar">
            <div class="toggle">
                <ion-icon name="menu-outline"></ion-icon>
            </div>

            <div class="search">
                <label>
                    <input type="text" placeholder="Search here" />
                    <ion-icon name="search-outline"></ion-icon>
                </label>
            </div>

            <div class="user">
                <img src="assets/imgs/customer01.jpg" alt="" />
            </div>
        </div>
        <h1
                style="
            width: 100%;
            margin-top: 5rem;
            padding-left: 2rem;
            font-size: large;
          "
        >
            <% out.println("Add Tags for Task :"+((Task)request.getSession().getAttribute("task")).getName());%>

        </h1>
        <div class="tag-container">
            <h2 style="margin-bottom: 20px">Tags</h2>
            <p style="font-style: italic">
                Press enter or add a comma after each tag
            </p>
            <div class="input-container">
                <ul id="tags"></ul>
                <input type="text" id="tag-input" placeholder="Add a tag" />
            </div>
            <button id="remove-all">Remove All</button>
        </div>
        <div class="confirm" style="margin-top: 3.5rem;display: flex;gap: 1rem;">
            <form action="addTags" method="post">
                <input type="hidden" name="cancel" value="cancel">
            <button type="submit">Cancel Task</button>
            </form>
            <form action="addTags" method="post">
                <input type="hidden" name="tags" value="cancel">
                <button type="submit">Save</button>
            </form>
        </div>
    </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        const tagInput = document.getElementById("tag-input");
        const tagContainer = document.getElementById("tags");
        const removeAllButton = document.getElementById("remove-all");
        let tags = [];

        tagInput.addEventListener("keypress", function(event) {
            if (event.key === "Enter" || event.key === ",") {
                event.preventDefault();
                addTag(tagInput.value.trim());
                tagInput.value = "";
            }
        });

        removeAllButton.addEventListener("click", function() {
            tags = [];
            updateTags();
        });

        function addTag(tag) {
            if (tag !== "" && !tags.includes(tag)) {
                tags.push(tag);
                updateTags();
            }
        }

        function removeTag(index) {
            tags.splice(index, 1);
            updateTags();
        }

        function updateTags() {
            tagContainer.innerHTML = "";
            tags.forEach((tag, index) => {
                const li = document.createElement("li");
                li.textContent = tag;
                const removeBtn = document.createElement("button");
                removeBtn.textContent = "x";
                removeBtn.classList.add("remove-tag");
                removeBtn.addEventListener("click", function() {
                    removeTag(index);
                });
                li.appendChild(removeBtn);
                tagContainer.appendChild(li);
            });
            document.querySelector(".remaining-tags").textContent = `${8 - tags.length} tags are remaining`;
        }
    });

</script>
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

<!-- ====== ionicons ======= -->
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>

</html>