# Aanvragen Client

Voor meer informatie over het aanvragen van de client kan u gebruik maken van de documentatie van het [Beheerportaal Vlaanderen](https://vlaamseoverheid.atlassian.net/wiki/spaces/IKPubliek/pages/6282739963/Uw+OAuth-client+beheren+via+het+ACM-Beheerportaal).

Met als referentie: `DAO (Digitale Aangifte Overlijden) API beta` of `DAO (Digitale Aangifte Overlijden) API`  

Met als client name: `DAO [omgeving] <organisatie> client`  </br>
hierbij is de omgeving een van de volgende waardes: beta/acc/PROD </br> 
en organisatie: De organisatie waarvoor de client wordt aangemaakt.

Voor een snelle start kan u gebruik maken van volgende stappen:

## Stap 1: registratie DAO API Client

Een eerste stap is een client aanmaken in ACM/IDM en hiervoor toegang tot onze DAO API te vragen.

Volgende scopes moeten aangevraagd worden:


| Integrator         | Scope                  |
|--------------------|------------------------|
| Lokaal bestuur     | dao_lbbs               |
| Begraafplaats      | dao_begraafplaats      |
| Uitvaartondernemer | dao_uitvaartondernemer |
| Crematorium        | dao_crematorium        |

Het is belangrijk en uw verantwoordelijkheid om correct om te gaan met gebruikersrollen en applicatie scopes in deze client. Gebruikers mogen enkel toegang krijgen tot de toepasselijke functionaliteit van het DAO platform.

Praktisch

Ga naar het beheerportaal: https://beheerportaal-ti.vlaanderen.be

De API waarvoor u toegang moet vragen heeft volgende kenmerken

| Omgeving   | Referentie                                 |
|------------|--------------------------------------------|
| Beta & Acc | DAO (Digitale Aangifte Overlijden) API beta|
| Productie  | DAO (Digitale Aangifte Overlijden) API     |

Er wordt verwacht dit te doen via
* het  OAuth2 Client Credentials Grant (CCG) protocol
* met authenticatie via een JWT
* conventie client naam: **DAO [omgeving] \<organisatie> client**

Relevante ACM/IDM gebruikersinformatie vindt u op de website van het beheerportaal van de Vlaamse overheid:
* algemeen rond het beheer van API client met OAuth Client Credentials Grant
  * [Module OAuth Client Credentials Grant: API-Client beheren - Gebruikersomgeving Applicatie- en platformdiensten - Confluence (atlassian.net)](https://vlaamseoverheid.atlassian.net/wiki/spaces/GAEP/pages/6377410189/Module+OAuth+Client+Credentials+Grant+API-Client+beheren)

* specifiek over creatie van nieuwe API clients
  * [Nieuwe OAuth client aanmaken](https://vlaamseoverheid.atlassian.net/wiki/x/RY4ffAE)

Voorgaande info zou u moeten toelaten om de client aan te maken en de aanvraag te sturen naar ons.


## Stap 2: probeer een eerste keer connectie te maken met onze API via ACM/IDM T&I

We hebben volgend "ping" endpoint voorzien

https://dao.api.beta-athumi.eu/health/v1/ping

dat u kan gebruiken om te testen of de registratie van uw client correct is verlopen.

Bij succesvolle aanmelding en ping call zal u als respons een JSON terug krijgen dat er ongeveer als volgt uitziet.

```json
{
  "VoOrgcode": <<Kbonummer vanuit ACMIDM>>,
  "scopes": "dao_lbbs",
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
