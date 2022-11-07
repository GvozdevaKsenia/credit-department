<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div class="slider__item-content">
    <form action="${pageContext.request.contextPath}/success" method="post">
        <h1>Заявка успешно отправлена</h1>
        <button type="submit" class="slider__btn default-btn">ОК</button>
    </form>
</div>
</body>
</html>
