# OAuth 2.0 Client Authenticatie
Het DAO platform gebruikt het OAuth 2.0 protocol voor authorisatie met het Vlaams Toegangsbeheer als IDP.
Indien het OAuth 2.0 protocol niet gekend is, geeft 
[Authenticatie vlaanderen](https://authenticatie.vlaanderen.be/docs/beveiligen-van-toepassingen/integratie-methoden/oauth/) 
een duidelijk overzicht van het gebruikte protocol.

Voor het aanvragen van een Client voor zowel de Beta, Acceptatie als Productie kan je [deze beschrijving volgen.](client-aanvragen.md)

## Gebruik van de api
Om onze rest api aan te spreken dient er een access token meegegeven te worden in de vorm van Bearer Authentication.
Voor deze token aan te vragen, die uiteraard aangeleverd vanuit het Vlaams Toegansbeheer, kan je de [documentatie van de Vlaamse Overheid](https://authenticatie.vlaanderen.be/docs/beveiligen-van-api/oauth-rest/) volgen.


### Scopes
Voor het correct gebruik van onze applicatie moet er tijdens het aanvragen van de access token ook de juiste scopes meegeleverd worden.
Doordat er maar een beperkte set van API endpoints beschikbaar is, en deze per doelgroep geklusterd zijn, hebben we besloten om de scopes role based te maken.
Dit heeft als voordeel dat het, zowel tijdens het aanvragen van de client of access token, duidelijk is welke scopes er voor de gevraagde doelgroep nodig zijn.  

| Scope                  | Beschrijving                                                                                                                                                                                                   |
|------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------| 
| vo_info                | Deze scope zorgt ervoor dat het DAO platform de vo_info claims ontvangen tijdens de introspectie van het acces token. Dankzij deze claims zijn wij in staat om te diferentiëren welke actor de actie uitvoert. |
| dao_uitvaartondernemer | Deze scope dient gebruikt te worden voor een actor binnen de Uitvaart sector.                                                                                                                                  |
| dao_crematorium        | Deze scope dient gebruikt te worden voor een crematorium.                                                                                                                                                      |
| dao_depzorg            | Deze scope dient gebruikt te worden voor het Departement zorg.                                                                                                                                                 |
| dao_lbbs               | Deze scope dient gebruikt te worden voor een lokaal bestuur wanneer zij het platform benaderen als dienst burgelijke stand.                                                                                    |
| dao_begraafplaats      | Deze scope dient gebruikt te worden voor een lokaal bestuur wanneer zij het platform benaderen als verantwoordelijke voor de begraafplaats.                                                                    |

Tijdens het aanvragen van de access token moet altijd zowel `vo_info` als een van de andere scopes worden meegegeven. 
Omdat we de vo_info gebruiken om op een veilige manier het onderscheid te maken tussen de verschillende actoren, is er de verwachting dat er per actor (bvb een Lokaal bestuur) een client wordt aangevraagd.

### Headers
Bij het sturen van een request wordt er verwacht dat er volgende headers aanwezig zijn:

| Header           | Beschrijving                                                                                                                                                   |
|------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Authorization    | De access token in de vorm `Bearer {{token}}`                                                                                                                  |
| x-correlation-id | Een uuid die wordt gebruikt om bij elkaar horende requesten te koppelen. Dit kunnen verschillende requesten zijn die gebruikt worden om 1 scherm op te vullen. |
| x-tracing-id     | Een uuid die wordt gebruikt om requesten die binnen dezelfde lokale transactie worden verstuurd te koppelen.                                                   |
| x-request-id     | Een uuid die per request uniek is.                                                                                                                             | 

Een voorbeeld vanuit MAGDA:
![Request ids](../diagrams/request-ids.svg)


### Logging
Doordat het DAO platform omgaat met gevoelige data, heerst er de verwachting dat de clients van deze toepassing een correcte audit logging voorzien.
Het platform zal altijd loggen welke acties de client hebben uitgevoerd en dit telkens gecorreleerd op een of meerdere aangiftes. 
Wij verwachten dat deze informatie ook beschikbaar is in de audit logging van de clients. 
Ook is er de verwachting dat wanneer er gebruikers informatie gebruiken die afkomstig is vanuit het platform, of acties ondernemen met het platform, het mogelijk moet zijn
om deze handelingen te koppelen met de gebruiker. 

Wanneer er een incident voorkomt kan het zijn dat er contact wordt opgenomen met beheerder van de client om deze informatie op te vragen.

## Testen van de api
Om snel te controleren of alle acties correct zijn uitgevoerd is er een endpoint voorzien voor de security te testen:
- https://dao.api.beta-athumi.eu/health/v1/ping
- https://dao.api.acc-athumi.eu/health/v1/ping
- https://dao.api.athumi.eu/health/v1/ping

Per omgeving moet er een unieke client aangevraagd worden. 
