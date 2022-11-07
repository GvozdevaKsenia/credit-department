<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">

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
    <link rel="stylesheet" href="<c:url value="/css/normalize.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/fonts.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/jquery.fancybox.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/slick.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/jquery.formstyler.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/media.css"/>">
</head>

<body>
<header class="header">
    <div class="header__top">
        <div class="container">
            <div class="header__contacts">
                <a class="header__phone" href="tel:+375292964431"><img src="${pageContext.request.contextPath}/img/phone.png" width=22 height="19"
                                                                       alt=""> +375(29)2964431</a> <a
                    class="header__email" href="#"> <img src="${pageContext.request.contextPath}/img/email.png"
                                                         width=20 height=17> gvozdeva.ksenia.m@gmail.com</a>
            </div>
            <%@ include file="header.jsp"%>
        </div>
    </div>

    <div class="header__content">
        <div class="container">
            <div class="header__content-inner">
                <div class="header__logo">
                    <a href="#">
                        <img src="${pageContext.request.contextPath}/img/logo.png.png" alt="" width="181" height="81">
                    </a>
                </div>
                <nav class="menu">
                    <ul>
                        <li><a href="#Главная">Главная</a></li>
                        <li><a href="#Uslugi">Услуги</a></li>
                        <li><a href="#credit">Кредиты</a></li>
                        <li><a href="#onas">О нас</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</header>


<section class="slider">
    <div class="container">
        <div class="slider__inner">
            <div class="slider__item">
                <div class="slider__item-content">
                    <div class="slider__title">
                        КОНСУЛЬТАЦИЯ ПО КРЕДИТОВАНИЮ
                    </div>
                    <div class="slider__text">
                        Сервис консультирования по выдаче кредита
                    </div>
                    <a href="#Consultation" class="slider__btn default-btn">
                        Оставить заявку
                    </a>
                </div>
            </div>
            <div class="slider__item">
                <div class="slider__item-content">
                    <div class="slider__title">
                        БАНКОВСКИЕ ВОЗМОЖНОСТИ
                    </div>
                    <div class="slider__text">
                        Связь с государственными и частными банками
                    </div>
                    <a href="${pageContext.request.contextPath}/banks">
                        <span class="slider__btn default-btn">Просмотреть список банков</span>
                    </a>
                </div>
            </div>
            <div class="slider__item">
                <div class="slider__item-content">
                    <div class="slider__title">
                        НАША СВЯЗЬ ДОСТУПНА 24/7
                    </div>
                    <div class="slider__text">
                        О вопросах можно связаться со службой поддержки
                    </div>
                    <a href="tel:+375292964431" class="slider__btn default-btn">
                        Позвонить
                    </a>
                </div>
            </div>

        </div>
    </div>
</section>

<section class="services">
    <div class="container">
        <div class="services__top">
            <div class="services__title-box">
                <div class="services-title">
                    <ul>
                        <li>
                            <a name="Uslugi" href #target=_self>
                                Наши услуги
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="services__text">
                    Комплексный подход к вашему вопросу, своевременная правовая помощь, представление интересов
                    во
                    всех банковский инстанциях.
                </div>
            </div>
            <a href="#" class="services__btn">
                Показать все услуги
            </a>
        </div>
        <a name="credit" href="#target=_self">
            <div class="services__items">
                <div class="services__item">
                    <img src="${pageContext.request.contextPath}/img/about-1.png" alt="">
                    <div class="services__item-title">
                        Целевой кредит
                    </div>
                    <div class="services__item-text">
                        Кредит, выдаваемый банком на оплату конкретного товара или услуги
                    </div>
                    <div class="services__item-btn">
                        <a class="services__item-link" href="#">Подробнее</a>
                        <a data-fancybox data-src="#modal" href="javascript:;" class="default-btn">
                            Консультировать
                        </a>
                    </div>
                </div>
                <div class="services__item">
                    <img src="${pageContext.request.contextPath}/img/about-2.png" alt="">
                    <div class="services__item-title">
                        Долгосрочный кредит
                    </div>
                    <div class="services__item-text">
                        Крупные займы, выдаваемые на срок более 5 лет
                    </div>
                    <div class="services__item-btn">
                        <a class="services__item-link" href="#">Подробнее</a>
                        <a data-fancybox data-src="#modal" href="javascript:;" class="default-btn">
                            Консультировать
                        </a>
                    </div>
                </div>
                <div class="services__item">
                    <img src="${pageContext.request.contextPath}/img/about-3.png" alt="">
                    <div class="services__item-title">
                        Обеспеченный кредит
                    </div>
                    <div class="services__item-text">
                        Кредит предусматривает выплату стоимости товаров и процентов по кредиту частями.
                    </div>
                    <div class="services__item-btn">
                        <a class="services__item-link" href="#">Подробнее</a>
                        <a data-fancybox data-src="#modal" href="javascript:;" class="default-btn">
                            Консультировать
                        </a>
                    </div>
                </div>
            </div>
        </a>
    </div>
</section>

<section class="about">
    <div class="container">
        <div class="about__inner">
            <div class="about__title">
                <ul>
                    <li><a name="onas" href #target=_self>
                        О компании
                    </a></li>
                </ul>
            </div>
            <div class="about__text">
                Компания специализируется на оказании услуг в сфере банковского права, кредитного права,
                представительства в судах. На сегодняшний день, коллектив
                компании
                объединяет
                высокопрофессиональных экспертов имеющих
                специализации в отдельных областях банковского дела.
            </div>
            <a href="${pageContext.request.contextPath}/about" class="about__btn default-btn">
                Узнать больше
            </a>
        </div>

    </div>
</section>

<section class="form">
    <div class="container">
        <div class="form__inner">
            <div class="form__content">
                <div class="form__title-box">
                    <div class="form__title">
                        <a name="Consultation" href #target=_self>
                            Получить Консультацию
                        </a>
                    </div>
                    <div class="form__text">
                        Комплексный подход к вашему вопросу, своевременная правовую помощь, представление интересов
                        во всех
                        банковских инстанциях.
                    </div>
                </div>
                <form action="${pageContext.request.contextPath}/main" method="post">
                    <div class="form__box">
                        <form>
                            <div class="form__box-inner">
                                <div class="form__box-left">
                                    <label for="emailId">
                                        E-mail
                                        <input type="text" name="email" id="emailId" value="${sessionScope.user.email}">
                                    </label>
                                    <label for="telephoneId">
                                        Контактный телефон
                                        <input type="text" name="telephone" id="telephoneId" value="${sessionScope.user.telephone}">
                                    </label>
                                    <label for="fioId">
                                        ФИО
                                        <input type="text" name="fio" id="fioId" value="${sessionScope.user.firstName} ${sessionScope.user.surname}">
                                    </label>
                                    <label for="topicId">
                                        Тема вопроса
                                        <select name="topic" id="topicId">
                                            <option>кредит</option>
                                            <option>чепуха</option>
                                            <option>вопрос</option>
                                            <option>ответ</option>
                                        </select>
                                    </label>
                                </div>
                                <div class="form__box-right">
                                    <label for="messageId">
                                        Сообщение
                                        <textarea name="message" id="messageId"></textarea>
                                    </label>
                                    <button type="submit" class="about__btn default-btn">Получить консультацию</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<footer class="footer">
    <div class="footer__content">
        <div class="container">
            <div class="footer__inner">
                <div class="footer__info">
                    <div class="footer__title">
                        Консультация по кредитованию
                    </div>
                    <div class="footer__text">
                        Полное руководство на всем этапе кредитования с дальнейшими инструкциями.
                    </div>
                    <a data-fancybox data-src="#modal" href="javascript:;" class="header__btn" href="#">
                        Бесплатная консультация
                    </a>
                    <ul class="footer__list">
                        <li><a class="footer__phone" href="tel:380963092145">+375 (29) 296 44 31</a></li>
                        <li><a href="#">gvozdeva.ksenia.m@gmail.com</a></li>
                        <li><a class="footer__adress" href="#">Новополоцк, ул. Молодежная-1</a></li>
                    </ul>
                </div>
                <div class="footer__map">
                    <iframe height="250px"
                            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2540.124391996489!2d30.364922015731644!3d50.457408279476354!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40d4cc919501b4ef%3A0x71a476f68f4c8246!2z0YPQuy4g0KTQtdC-0LTQvtGA0Ysg0J_Rg9GI0LjQvdC-0LksIDEzLCDQmtC40LXQsiwgMDIwMDA!5e0!3m2!1sru!2sua!4v1555013165886!5m2!1sru!2sua"
                            frameborder="0" style="border:0" allowfullscreen></iframe>
                </div>
            </div>
        </div>
    </div>
    <div class="footer__copy">
        <div class="container">
            <div class="copy__text">
                © 2017 Template by Anastasia Shaposhnyk. Все права защищены.
            </div>
        </div>
    </div>
</footer>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.fancybox.min.js"></script>
<script src="${pageContext.request.contextPath}/js/slick.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script src="js/jquery.formstyler.min.js"></script>

</body>

</html>