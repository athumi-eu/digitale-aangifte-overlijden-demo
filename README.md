# Digitale-aangifte-overlijden-demo
**Let op:** 'DAO' is de tijdelijke projectcodenaam die tot nu toe is gebruikt. In de toekomst zal worden overgeschakeld naar 'eLys', de officiële naam van het platform. Zorg er daarom voor dat er geen hardcoded configuratie op deze projectcodenaam wordt toegepast.

Met deze demoapp zouden wij graag demonstreren hoe het mogelijk is om met ons platform te integreren.
Deze demo app kan gebruikt worden voor de verschillende integratoren die het systeem kent. 

Voor meer informatie rond de integratie als **Lokaal bestuur** kan je navigeren naar: [Startpunt burgerlijke stand](burgerlijke-stand/doc/README.md)

Voor meer informatie rond de integratie als **Arts** kan je navigeren naar: [Startpunt arts](vaststelling/doc/README.md)

Voor meer informatie rond de integratie als **Departement zorg** kan je navigeren naar: [Startpunt departement zorg](departementzorg/doc/README.md)

Voor meer informatie rond de integratie als **Uitvaartondernemer** kan je navigeren naar: [Startpunt uitvaartondernemer](uitvaart/doc/README.md)

Voor meer informatie rond de integratie als **Begraafplaats** kan je navigeren naar: [Startpunt begraafplaats](begraafplaats/doc/README.md)

Voor meer informatie rond de integratie als **Crematorium** kan je navigeren naar: [Startpunt crematorium](crematorium/doc/README.md)

## Omgevingen

Het dao platform bestaat uit 2 toepassingen, namelijk een frontend voor de Artsen en uitvaart ondernemers, en een API waar de verschillende actoren mee kunnen integreren.

### Frontend:
| Omgeving | Link                                                           | Omschrijving                                                                                                                                                                                                                               |
|----------|----------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| test     | https://dao.test-athumi.eu/ en https://dao.api.test-athumi.eu/ | Deze omgeving is geconnecteerd met een eigen oauth implementatie, en bevat nooit persoonsgegevens. Deze is bedoeld voor het develop team om tijdens het ontwikkel process te testen.                                                       |
| beta | https://dao.beta-athumi.eu/ en https://dao.api.beta-athumi.eu/ | Deze omgeving is geconnecteerd met acm-idm maar bevat nooit persoonsgegevens. Deze is bedoeld voor acceptatie testen, en connectie met integraten.                                                                                         |
| Acceptatie | https://dao.beta-athumi.eu/ en https://dao.api.beta-athumi.eu/ | Deze omgeving is geconnecteerd met acm-idm en gaat naar de magda test omgeving. Wegens limitaties aan deze omgeving kan hier echte gegevens van het rijksregister beschikbaar zijn. Deze is vooral bedoeld voor end to end flows te testen |
| Productie | https://dao.athumi.eu/ en https://dao.api.athumi.eu/ | De productie omgeving.                                                                                                                                                                                                                     |

![screenshot](static/athumi.jpg)