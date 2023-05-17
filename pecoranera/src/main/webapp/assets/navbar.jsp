<!DOCTYPE html>
<html>
    <head>
        <script src="https://kit.fontawesome.com/0447c444c7.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">
    </head>
    <body> 
        <nav class="${param.active_page == 'home'  ? 'navbar-home' : ''}"">
            <div class="logo">
                <img src="${pageContext.request.contextPath}/images/logo.png" alt="">
                <div>
                    <span id="logo-name">PECORANERA</span><br>
                    <span id="logo-text">Jazz Art Bistrot</span>
                </div>
            </div>

            <ul>
                <button class="nav-button"></button>
                <li><a href="index.jsp" class="nav-links ${param.active_page == 'home'  ? 'active_link' : ''}">HOME</a></li>
                <li><a href="eventi.jsp" class="nav-links ${param.active_page == 'eventi'  ? 'active_link' : ''}">EVENTI</a></li>
                <li><a href="menu.jsp" class="nav-links ${param.active_page == 'menu'  ? 'active_link' : ''}">MENU</a></li>
                <li><a href="info.jsp" class="nav-links ${param.active_page == 'info'  ? 'active_link' : ''}"><span>INFO</span></a></li>
                <li id="nav-icons">
                    <a href="account.jsp" class="nav-links ${param.active_page == 'account'  ? 'active_link' : ''}"><i class="fa-regular fa-circle-user"></i></a>
                    <a href="carrello.jsp" class="nav-links ${param.active_page == 'carrello'  ? 'active_link' : ''}"><i class="fa-solid fa-cart-shopping"></i></a>
                </li>   
            </ul>
        </nav>
    </body>
</html>