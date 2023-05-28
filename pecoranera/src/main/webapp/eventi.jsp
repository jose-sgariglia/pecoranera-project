<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pecoranera Jazz Art Bistrot</title>
</head>
<body>
    
    <section id="upcoming">

        <div class="upcoming-event">
            <h2></h2>

            <span class="event-tag"></span>
            <span class="event-tag"></span>

            <div class="event-date"></div>
        </div>

        <div class="upcoming-event">
            <h2></h2>

            <span class="event-tag"></span>
            <span class="event-tag"></span>

            <div class="event-date"></div>
        </div>

    </section>

    <div id="searchbar">
        <form action="">
            <button id="filtri-btn">Filtri</button>
            <input type="text" name="name-search" id="name-search">
            <input type="button" value="search">

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
    
</body>
</html>