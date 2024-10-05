<%@ page import="dev.yassiraitelghari.domain.User" %>
<%@ page import="java.util.List" %>
<%@ page import="dev.yassiraitelghari.utils.TaskErrors" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Responsive Admin Dashboard | Korsat X Parmaga</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        tailwind.config = {
            theme: {
                extend: {
                    colors: {
                        clifford: '#da373d',
                    }
                }
            }
        }
    </script>
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



        .wrapper2 {
            width: inherit;
            background: #fff;
            border-radius: 10px;
            padding: 18px 25px 20px;
            box-shadow: 0 0 30px rgba(0, 0, 0, 0.06);
            margin-bottom: 30px;
        }
        .wrapper2 :where(.title, li, li i, .details) {
            display: flex;
            align-items: center;
        }
        .title2 img {
            max-width: 21px;
        }
        .title2 h2 {
            font-size: 21px;
            font-weight: 600;
            margin-left: 8px;
        }
        .wrapper2 .content {
            margin: 10px 0;
        }
        .content2 p {
            font-size: 15px;
        }
        .content2 ul {
            display: flex;
            flex-wrap: wrap;
            padding: 7px;
            margin: 12px 0;
            border-radius: 5px;
            border: 1px solid #a6a6a6;
        }
        .content2 ul li {
            color: #333;
            margin: 4px 3px;
            list-style: none;
            border-radius: 5px;
            background: #f2f2f2;
            padding: 5px 8px 5px 10px;
            border: 1px solid #e3e1e1;
        }
        .content2 ul li i {
            height: 20px;
            width: 20px;
            color: #808080;
            margin-left: 8px;
            font-size: 12px;
            cursor: pointer;
            border-radius: 50%;
            background: #dfdfdf;
            justify-content: center;
        }
        .content2 ul input {
            flex: 1;
            padding: 5px;
            border: none;
            outline: none;
            font-size: 16px;
        }
        .wrapper2 .details {
            justify-content: space-between;
        }
        .details2 button {
            border: none;
            outline: none;
            color: #fff;
            font-size: 14px;
            cursor: pointer;
            padding: 9px 15px;
            border-radius: 5px;
            background: #2a2185;
            transition: background 0.3s ease;
        }
        .details2 button:hover {
            background: #fecd03;
        }

    </style>
</head>

<body>
<!-- =============== Navigation ================ -->
<div class="container">
    <jsp:include page="../components/aside.jsp" />
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
                <img src="assets/imgs/customer01.jpg" alt="">
            </div>
        </div>


        <div class="flex items-center justify-center p-12 mt-12 ">
            <!-- Author: FormBold Team -->
            <div class="mx-auto w-full max-w-[550px] bg-white">
                <form action="addTask" method="post">
                    <div class="mb-5">
                        <label for="name" class="mb-3 block text-base font-medium text-[#07074D]">
                            Task Title
                        </label>
                        <% TaskErrors taskErrors = (TaskErrors) request.getAttribute("error");
                            if(taskErrors!=null && taskErrors.getTitleError()!=null){
                                out.println("<p style='color:red;margin-bottom:5px;'>"+taskErrors.getTitleError()+"</p>");
                            }
                        %>
                        <input type="text" name="name" id="name" placeholder="Task Name"
                               class="w-full rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#6B7280] outline-none focus:border-[#6A64F1] focus:shadow-md" />


                    </div>
                    <div class="mb-5">
                        <label  class="mb-3 block text-base font-medium text-[#07074D]">
                            Task Description
                        </label>
                        <%if(taskErrors!=null && taskErrors.getDescriptionError()!=null){
                                out.println("<p style='color:red;margin-bottom:5px;'>"+taskErrors.getDescriptionError()+"</p>");
                            }
                        %>
                        <textarea rows="10" id="description" name="description" placeholder="Description..."
                                  class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"></textarea>
                    </div>
                    <div class="-mx-3 flex flex-wrap">
                        <div class="w-full px-3 sm:w-1/2">
                            <div class="mb-5">
                                <label for="startDate" class="mb-3 block text-base font-medium text-[#07074D]">
                                    Start Date
                                </label>
                                <input type="date" name="startDate" id="startDate"
                                       class="w-full rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#6B7280] outline-none focus:border-[#6A64F1] focus:shadow-md" />
                            </div>
                        </div>
                        <div class="w-full px-3 sm:w-1/2">
                            <div class="mb-5">
                                <label for="startTime" class="mb-3 block text-base font-medium text-[#07074D]">
                                    Start Time
                                </label>
                                <input type="time" name="startTime" id="startTime"
                                       class="w-full rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#6B7280] outline-none focus:border-[#6A64F1] focus:shadow-md" />
                            </div>
                        </div>
                        <%if(taskErrors!=null && taskErrors.getStartDateError()!=null){
                            out.println("<p style='color:red; padding-right: 0.75rem ; padding-left:0.75rem;margin-bottom:5px;'>"+taskErrors.getStartDateError()+"</p>");
                        }
                        %>
                    </div>

                    <div class="-mx-3 flex flex-wrap">
                        <div class="w-full px-3 sm:w-1/2">
                            <div class="mb-5">
                                <label for="dateLimit" class="mb-3 block text-base font-medium text-[#07074D]">
                                    End Date
                                </label>
                                <input type="date" name="dateLimit" id="dateLimit"
                                       class="w-full rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#6B7280] outline-none focus:border-[#6A64F1] focus:shadow-md" />
                            </div>
                        </div>
                        <div class="w-full px-3 sm:w-1/2">
                            <div class="mb-5">
                                <label for="timeLimit" class="mb-3 block text-base font-medium text-[#07074D]">
                                    End Time
                                </label>
                                <input type="time" name="timeLimit" id="timeLimit"
                                       class="w-full rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#6B7280] outline-none focus:border-[#6A64F1] focus:shadow-md" />
                            </div>
                        </div>
                        <%if(taskErrors!=null && taskErrors.getEndDateError()!=null){
                            out.println("<p style='color:red;padding-right: 0.75rem ; padding-left:0.75rem;margin-bottom:5px;'>"+taskErrors.getEndDateError()+"</p>");
                        }
                        %>
                    </div>
                    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/thinline.css">

                    <div class="wrapper2">
                        <div class="title2">
                            <h2>Tags</h2>
                        </div>
                        <div class="content2">
                            <p>Press enter or add a comma after each tag</p>
                            <ul><input type="text" spellcheck="false"></ul>
                        </div>
                        <div class="details2">
                            <p><span>10</span> tags are remaining</p>
                            <button>Remove All</button>
                        </div>
                    </div>
                    <div>
                        <button type="submit"
                                class="hover:shadow-form w-full rounded-md bg-[#6A64F1] py-3 px-8 text-center text-base font-semibold text-white outline-none">
                            Add Task
                        </button>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>

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
<script>
    const ul = document.querySelector("ul"),
        input = document.querySelector("input"),
        tagNumb = document.querySelector(".details2 span");

    let maxTags = 10,
        tags = [];

    countTags();
    createTag();

    function countTags() {
        input.focus();
        tagNumb.innerText = maxTags - tags.length;
    }

    function createTag() {
        ul.querySelectorAll("li").forEach((li) => li.remove());
        tags
            .slice()
            .reverse()
            .forEach((tag) => {
                let liTag = `<li>${tag} <i class="uit uit-multiply" onclick="remove(this, '${tag}')"></i></li>`;
                ul.insertAdjacentHTML("afterbegin", liTag);
            });
        countTags();
    }

    function remove(element, tag) {
        let index = tags.indexOf(tag);
        tags = [...tags.slice(0, index), ...tags.slice(index + 1)];
        element.parentElement.remove();
        countTags();
    }

    function addTag(e) {
        if (e.key == "Enter") {
            let tag = e.target.value.replace(/\s+/g, " ");
            if (tag.length > 1 && !tags.includes(tag)) {
                if (tags.length < 10) {
                    tag.split(",").forEach((tag) => {
                        tags.push(tag);
                        createTag();
                    });
                }
            }
            e.target.value = "";
        }
    }

    input.addEventListener("keyup", addTag);

    const removeBtn = document.querySelector(".details2 button");
    removeBtn.addEventListener("click", () => {
        tags.length = 0;
        ul.querySelectorAll("li").forEach((li) => li.remove());
        countTags();
    });

</script>
</body>

</html>