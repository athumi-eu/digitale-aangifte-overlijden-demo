# Aanvragen Client

Voor meer informatie over het aanvragen van de client kan u gebruik maken van de documentatie van het [Beheerportaal Vlaanderen](https://vlaamseoverheid.atlassian.net/wiki/spaces/IKPubliek/pages/6282739963/Uw+OAuth-client+beheren+via+het+ACM-Beheerportaal).

Voor een snelle start kan u gebruik maken van volgende stappen:

## Stap 1: registratie eLys API Client

Een eerste stap is een client aanmaken in ACM/IDM en hiervoor toegang tot onze eLys API te vragen.

Ga naar het beheerportaal: 
* voor beta/acc: https://beheerportaal-ti.vlaanderen.be
* voor prod: https://beheerportaal.vlaanderen.be

De API waarvoor u toegang moet vragen heeft volgende kenmerken

| Omgeving   | Referentie                                 |
|------------|--------------------------------------------|
| beta       | eLys (Digitale Aangifte Overlijden) API beta|
| acceptatie | eLys (Digitale Aangifte Overlijden) API acc |
| productie  | eLys (Digitale Aangifte Overlijden) API     |

Er wordt verwacht dit te doen via
* het  OAuth2 Client Credentials Grant (CCG) protocol
* met authenticatie via een JWT
* conventie client naam: **eLys [omgeving] \<organisatie> client**
    * omgeving: De omgeving is een van de volgende waardes: beta/acc/prod
    * organisatie: De naam van de organisatie waarvoor de client wordt aangemaakt

Relevante ACM/IDM gebruikersinformatie vindt u op de website van het beheerportaal van de Vlaamse overheid:
* algemeen rond het beheer van API client met OAuth Client Credentials Grant </br> 
[Module OAuth Client Credentials Grant: API-Client beheren - Gebruikersomgeving Applicatie- en platformdiensten - Confluence (atlassian.net)](https://vlaamseoverheid.atlassian.net/wiki/spaces/GAEP/pages/6377410189/Module+OAuth+Client+Credentials+Grant+API-Client+beheren)

* specifiek over creatie van nieuwe API clients </br>
[Nieuwe OAuth client aanmaken](https://vlaamseoverheid.atlassian.net/wiki/x/RY4ffAE)

Voorgaande info zou u moeten toelaten om de client aan te maken en de aanvraag te sturen naar ons.


## Stap 2: probeer een eerste keer connectie te maken met onze API
Om onze rest api aan te spreken dient er een access token meegegeven te worden in de vorm van Bearer Authentication.
Om deze token aan te vragen, die uiteraard aangeleverd vanuit het Vlaams Toegansbeheer, kan je de [documentatie van de Vlaamse Overheid](https://authenticatie.vlaanderen.be/docs/beveiligen-van-api/oauth-rest/) volgen.

### Scopes
Voor het correct gebruik van onze applicatie moet er tijdens het aanvragen van de access token ook de juiste scopes meegeleverd worden.
Doordat er maar een beperkte set van API endpoints beschikbaar is, en deze per doelgroep geklusterd zijn, hebben we besloten om de scopes role based te maken.
Dit heeft als voordeel dat het, zowel tijdens het aanvragen van de client of access token, duidelijk is welke scopes er voor de gevraagde doelgroep nodig zijn.  

| Scope                  | Beschrijving                                                                                                                                                                                                   |
|------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------| 
| vo_info                | Deze scope zorgt ervoor dat het eLys platform de vo_info claims ontvangen tijdens de introspectie van het acces token. Dankzij deze claims zijn wij in staat om te differentiëren welke actor de actie uitvoert. |
| elys_uitvaartondernemer | Deze scope dient gebruikt te worden voor een actor binnen de Uitvaart sector.                                                                                                                                  |
| elys_crematorium        | Deze scope dient gebruikt te worden voor een crematorium.                                                                                                                                                      |
| elys_depzorg            | Deze scope dient gebruikt te worden voor het Departement zorg.                                                                                                                                                 |
| elys_lbbs               | Deze scope dient gebruikt te worden voor een lokaal bestuur wanneer zij het platform benaderen als dienst burgerlijke stand.                                                                                   |
| elys_begraafplaats      | Deze scope dient gebruikt te worden voor een lokaal bestuur wanneer zij het platform benaderen als verantwoordelijke voor de begraafplaats.                                                                    |
| elys_arts               | Deze scope dient gebruikt te worden voor een arts.                                                                                                                                                             |

Tijdens het aanvragen van de access token moet altijd zowel `vo_info` als een van de andere scopes worden meegegeven. 
Omdat we de vo_info gebruiken om op een veilige manier het onderscheid te maken tussen de verschillende actoren, is er de verwachting dat er per actor (bvb een Lokaal bestuur) een client wordt aangevraagd.

Het is belangrijk en uw verantwoordelijkheid om correct om te gaan met gebruikersrollen en applicatie scopes in deze client. Gebruikers mogen enkel toegang krijgen tot de toepasselijke functionaliteit van het eLys platform.

## Ping

We hebben volgende "ping" endpoints voorzien

| Omgeving   | Endpoint                                 |
|------------|--------------------------------------------|
| beta       | https://elys.api.beta-athumi.eu/health/v1/ping |
| acceptatie | https://elys.api.acc-athumi.eu/health/v1/ping |
| productie  | https://elys.api.athumi.eu/health/v1/ping     |

die u kan gebruiken om te testen of de registratie van uw client correct is verlopen.

Bij succesvolle aanmelding en ping call zal u als respons een JSON terug krijgen dat er ongeveer als volgt uitziet.

```json
{
  "VoOrgcode": <<Kbonummer vanuit ACMIDM>>,
  "scopes": <<Gevraagde scopes>>,
  "token": {
    "sub": <<Subject id>>,
    "vo_orgcode": <<Zelfde als VoOrgcode>>,
    "iss": "https://authenticatie-ti.vlaanderen.be/op",
    "active": true,
    "vo_applicatienaam": <<Aangemaakte client naam>>,
    "token_type": "bearer",
    "vo_orgnaam": <<Naam van de organisatie gekend bij ACMIDM>>,
    "client_id": <<Client id>>,
    "aud": [],
    "scope": [
      <<Gevraagde scopes>>
    ],
    "exp": "2024-12-19T09:55:56Z",
    "iat": "2024-12-19T08:55:56Z"
  },
  "clientId": <<Client id>>
}
```
