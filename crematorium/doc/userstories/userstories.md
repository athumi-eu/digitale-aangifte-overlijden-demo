# User Stories Crematoria

## Inleiding

Deze pagina geeft een overzicht van de user stories die voorzien moeten worden in de applicatie, samen met de endpoints die in het eLys-platform aangeboden worden. 

Dit zijn de **minimale** user stories die voorzien moeten worden.

## User stories

1. "Vooraleer een overleden persoon te mogen cremeren, wil ik als crematorium de toestemming tot crematie en de bijhorende gestructureerde data nodig voor het crematieregister kunnen ophalen bij het eLys-platform. Dit doe ik op basis van het dossiernummer dat ik doorkrijg (buiten het platform) van de uitvaartondernemer. Op basis van de uit het platform opgehaalde informatie, kan ik valideren of de data van de overleden persoon, de uitvaartonderneming en de gekozen asbestemming overeenkomt met de informatie uit mijn systeem."

    [GET /crematorium/v1/toestemmingen](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Crematorium#/crematorium-toestemming-controller/byPeriode_1)

    Met gebruik van de dossiernummer parameter.

    Indien enkel het document nodig is, kan volgend endpoint gebruikt worden:

    [GET /crematorium/v1/toestemmingen/{dossiernummer}/toestemming](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Crematorium#/crematorium-toestemming-controller/downloadDocument)


2. "Om te weten welke crematies er voor mij aankomen, en die eventueel nog niet gereserveerd zijn, wil ik als crematorium een lijst kunnen ophalen in het eLys-platform van alle crematies waarvoor er toestemming is en die voor mijn crematoriumgroep bestemd zijn."

    [GET /crematorium/v1/toestemmingen](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Crematorium#/crematorium-toestemming-controller/byPeriode_1)

    Met gebruik van de van-tot parameters.
