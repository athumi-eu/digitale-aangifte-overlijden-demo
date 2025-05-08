# eLys - Platform voor overlijdensadministratie - demo
Met deze demoapp zouden wij graag demonstreren hoe het mogelijk is om met ons platform te integreren.
Deze demo app kan gebruikt worden voor de verschillende integratoren die het systeem kent, maar richt zich qua functionaliteit wel op het lokaal bestuur van overiljden. 

Voor meer informatie rond de integratie als **Lokaal bestuur van overlijden** kan je navigeren naar: [Startpunt burgerlijke stand](burgerlijke-stand/doc/README.md)

Voor meer informatie rond de integratie als **Arts** kan je navigeren naar: [Startpunt arts](vaststelling/doc/README.md)

Voor meer informatie rond de integratie als **Departement zorg** kan je navigeren naar: [Startpunt departement zorg](departementzorg/doc/README.md)

Voor meer informatie rond de integratie als **Uitvaartondernemer** kan je navigeren naar: [Startpunt uitvaartondernemer](uitvaart/doc/README.md)

Voor meer informatie rond de integratie als **Begraafplaats** kan je navigeren naar: [Startpunt begraafplaats](begraafplaats/doc/README.md)

Voor meer informatie rond de integratie als **Crematorium** kan je navigeren naar: [Startpunt crematorium](crematorium/doc/README.md)

## Omgevingen

Het eLys platform bestaat uit 2 toepassingen, namelijk een frontend voor de artsen en uitvaartondernemers, en een API waar de verschillende actoren mee kunnen integreren.
Volgende omgevingen zijn beschikbaar:

| Omgeving | Link                                                           | Omschrijving                                                                                                                                                                                                                                                                                            |
|----------|----------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| test     | https://elys.test-athumi.eu/ en https://elys.api.test-athumi.eu/ | Deze omgeving is geconnecteerd met een eigen oauth implementatie, en bevat nooit persoonsgegevens. Deze is bedoeld voor het ontwikkel team om tijdens het ontwikkel process te testen. Op deze omgeving is het mogelijk om als arts aan te melden via het endpoint https://elys.test-athumi.eu/login-arts. |
| beta | https://elys.beta-athumi.eu/ en https://elys.api.beta-athumi.eu/ | Deze omgeving is geconnecteerd met acm-idm maar bevat nooit persoonsgegevens. Deze is bedoeld voor acceptatie testen en connectie met integraten. Op deze omgeving is het mogelijk om als arts aan te melden via het endpoint https://elys.beta-athumi.eu/login-arts.                                  |
| acceptatie | https://elys.acc-athumi.eu/ en https://elys.api.acc-athumi.eu/ | Deze omgeving is geconnecteerd met acm-idm en gaat naar de magda test omgeving. Wegens limitaties aan deze omgeving kunnen hier echte gegevens van het rijksregister beschikbaar zijn. Deze is vooral bedoeld om end to end flows te testen.                                                              |
| productie | https://elys.athumi.eu/ en https://elys.api.athumi.eu/ | De productie omgeving.                                                                                                                                                                                                                                                                                  |

![screenshot](static/athumi.jpg)