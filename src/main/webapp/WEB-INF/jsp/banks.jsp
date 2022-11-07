<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Кредитный отдел</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
            href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400;1,500&family=Raleway:wght@300&display=swap"
            rel="stylesheet">
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/fonts.css">
    <link rel="stylesheet" href="css/jquery.fancybox.min.css">
    <link rel="stylesheet" href="css/slick.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/jquery.formstyler.css">
    <link rel="stylesheet" href="css/media.css">

    <style>
        #form-wrap {
            opacity: 0;
            transition: opacity .5s;
        }

        #form-wrap.open {
            transition: opacity .5s;
            opacity: 1;
        }

        #form-delete-wrap {
            opacity: 0;
            transition: opacity .5s;
        }

        #form-delete-wrap.open {
            transition: opacity .5s;
            opacity: 1;
        }

        .list2a {
            margin-bottom: 8px;
            padding: 0;
            list-style: none;
            counter-reset: li;
        }

        .list2a li {
            position: relative;
            border: 2px solid #1b1b1b;
            background:  #ffffff;
            padding: 16px 20px 16px 28px;
            margin: 12px 0 12px 40px;
            -webkit-transition-duration: 0.3s;
            transition-duration: 0.3s;
        }

        .list2a li:hover {
            background:#ffc222;
        }

        .list2a li:before {
            line-height: 32px;
            position: absolute;
            top: 4px;
            left: -40px;
            width: 40px;
            text-align: center;
            font-size: 16px;
            font-weight: bold;
            color: #ffc222;
            background: #1b1b1b;
            counter-increment: li;
            content: counter(li);
            -webkit-transition-duration: 0.2s;
            transition-duration: 0.2s;
        }

        .list2a li:hover:before {
            width: 46px;
        }

        .list2a li:after {
            position: absolute;
            left: 0;
            top: 4px;
            content: "";
            height: 0;
            width: 0;
            border: 16px solid transparent;
            border-left-color: #5e5e5e;
            -webkit-transition-duration: 0.2s;
            transition-duration: 0.2s
        }

        .list2a li:hover:after {
            margin-left: 6px;
        }
        .button{
            background:#ffc222;
            color:#1b1b1b;
            text-decoration:none;
            display:inline;
            width:120px;
            text-align:center;
            margin:0 auto;
            padding:5px 5px;
            transition:all 0.3s;
        }
        .button:hover{
            box-shadow:0px -5px 0 #1b1b1b inset;
        }
    </style>
</head>
<body>
<header class="header">
    <div class="header__top">
        <div class="container">
            <div class="header__contacts">
                <a class="header__phone" href="tel:+375292964431"><img src="img/phone.png" width=22 height="19"
                                                                       alt=""> +375(29)2964431</a> <a
                    class="header__email" href="#"> <img src="img/email.png"
                                                         width=20 height=17> gvozdeva.ksenia.m@gmail.com</a>
            </div>
        </div>
    </div>
</header>
<h1>Список банков:</h1>
<ul class="list2a">
    <c:forEach var="bank" items="${requestScope.banks}">
        <li>
            <h1 class="form__title">
                <a href=${pageContext.request.contextPath}/consultants?bankId=${bank.id}>${bank.name}</a>
            </h1>
            <a style="content: normal">Адрес: ${bank.address}</a><br>
            <a style="content: normal">Телефон: ${bank.telephone}</a><br>
            <a style="content: normal">Email: ${bank.email}</a><br>
        </li>
    </c:forEach>
</ul>
<br>
<button class="button" onclick='openForm()'>Добавить банк</button>
<div id='form-wrap'>
    <form role="form" action="${pageContext.request.contextPath}/banks" autocomplete="off" method="POST">
        <label for="bankName">
            <input type="text" name="bankName" id="bankName" placeholder="Название"><br>
        </label>
        <label for="address">
            <input type="text" name="address" id="address" placeholder="Адрес"><br>
        </label>
        <label for="telephone">
            <input type="text" name="telephone" id="telephone" placeholder="Телефон"><br>
        </label>
        <label for="email">
            <input type="text" name="email" id="email" placeholder="Email"><br>
        </label>
        <button type="submit" class="button">Сохранить</button>
    </form>
</div>

<button class="button" onclick='openDeleteForm()'>Удалить банк</button>
<div id='form-delete-wrap'>
    <form role="form" action="${pageContext.request.contextPath}/deleteBank" autocomplete="off" method="POST">
        <label for="bankDelName">
            <input type="text" name="bankDelName" id="bankDelName" placeholder="Название"><br>
        </label>
        <button type="submit" class="btn btn-success">Удалить</button>
    </form>
</div>

<script>
    const formWrap = document.getElementById('form-wrap');
    const formDeleteWrap = document.getElementById('form-delete-wrap');

    function openForm() {
        formWrap.classList.add('open');
    }

    function openDeleteForm() {
        formDeleteWrap.classList.add('open');
    }

</script>
</body>
</html>