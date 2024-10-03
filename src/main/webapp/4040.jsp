<%--
  Created by IntelliJ IDEA.
  User: Yasser
  Date: 10/2/2024
  Time: 8:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        html {
            margin: 0;
            padding: 0;
            background-color: white;
        }

        body,
        html {
            width: 100%;
            height: 100%;
            overflow: hidden;
        }

        #svgContainer {
            width: 640px;
            height: 512px;
            background-color: white;
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            margin: auto;
        }

    </style>
    <script>
        var svgContainer = document.getElementById('svgContainer');
        var animItem = bodymovin.loadAnimation({
            wrapper: svgContainer,
            animType: 'svg',
            loop: true,
            animationData: JSON.parse(animationData)
        });

    </script>
</head>
<body>
<div id="svgContainer"></div>
</body>
</html>
