<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pecoranera Jazz Art Bistrot</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/eventi.css">
    <link rel="icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
    <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js" integrity="sha256-xLD7nhI62fcsEZK2/v8LsBcb4lG7dgULkuXoXB/j91c=" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/scripts/eventi.js"></script>
</head>
<body>

    <jsp:include page="./assets/navbar.jsp">
        <jsp:param name="active_page" value="eventi" />
    </jsp:include>
    
    <section id="upcoming">
        <h1>Prossimi Eventi</h1>

        <div>
            <div class="upcoming-event">
                <img src="${pageContext.request.contextPath}/images/lellopetrarca.jpg" alt="">

               <div>
                    <h2>Lello Petrarca Trio</h2>
        
                    <div class="tags">
                        <a class="event-tag">Jazz Classico</a>
                        <a class="event-tag">Jazz Moderno</a>
                    </div>
            
                    <div class="event-date">Marted&igrave; 30 Maggio</div>
               </div>
            </div>
    
            <div class="upcoming-event">
                <img src="${pageContext.request.contextPath}/images/tizia123.jpg" alt="">

                <div>
                    <h2>Tizio Caio Quartet</h2>
    
                    <div class="tags">
                        <a class="event-tag">Blues</a>
                        <a class="event-tag">Gipsy Jazz</a>
                    </div>
            
                    <div class="event-date">Venerd&igrave; 2 Giugno</div>
                </div>
            </div>
        </div>    

    </section>

    <div id="searchbar">
        <form action="">
            <button id="filtri-btn">Filtri</button>
            <input type="text" name="name-search" id="name-search">
            <input type="button" value="Cerca">

            <fieldset id="date-fieldset">
                <legend>Intervallo date</legend>

                <label for="data-inizio">Inizio:</label>
                <input type="date" name="data-inizio" id="data-inizio">

                <label for="data-fine">Fine:</label>
                <input type="date" name="data-fine" id="data-fine">
            </fieldset>
            

            <fieldset id="filter-fieldset">
                <legend>Filtri</legend>

                <div id="selected-filter">
                    <span>Tag selezionati</span>
                
                </div>

                <div id="filter-choice">
                    <span>Seleziona tag</span>
                        
                </div>

                <input type="submit" value="submit">
                <input type="reset">
            </fieldset>
        </form>
    </div>

    <section id="all-events">

    </section>

    <jsp:include page="./assets/footer.jsp"></jsp:include>
    
</body>
</html>