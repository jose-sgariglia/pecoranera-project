<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pecoranera Jazz Art Bistrot</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/account.css">
    <link rel="icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
    <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js" integrity="sha256-xLD7nhI62fcsEZK2/v8LsBcb4lG7dgULkuXoXB/j91c=" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/scripts/account.js"></script>
</head>
<body>
    <jsp:include page="./assets/navbar.jsp">
        <jsp:param name="active_page" value="account" />
    </jsp:include>

    <section id="profile-section">
        <div id="side-nav">
            <a href="#panoramica" class="account-link">Panoramica</a>
            <a href="#ordini" class="account-link">Ordini</a>
            <a href="#preferenze" class="account-link">Preferenze</a>
            <a href="#dati" class="account-link">Dati Personali</a>
            <a href="index.jsp" class="account-link">Esci</a>
        </div>

        <div id="display-data">
            <section id="panoramica">
                <h2 class="page-header">Edmundo Peli</h2>

                <div>
                    <h3></h3>
                </div>
            </section>

            <section id="ordini">
                <p>ORDINI</p>
            </section>

            <section id="preferenze">
                <p>PREFERENZE</p>
            </section>
        </div>
    </section>

    <jsp:include page="./assets/footer.jsp"></jsp:include>
</body>
</html>