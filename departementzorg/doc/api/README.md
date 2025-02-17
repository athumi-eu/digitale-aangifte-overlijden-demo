# Gebruik van de API van het DAO-platform voor het krijgen van statische data

## Inleiding

Dit document legt uit hoe je de API van het DAO-platform kunt gebruiken om statische data te verkrijgen.
Voor meer info omtrent de security kan je [hier meerlezen](../../../burgerlijke-stand/doc/security/README.md)

## Endpoints

Alle endpoints starten altijd met: `/datadeling/v1`

### Vaststellingen ophalen
- **Endpoint**: `/vaststellingen`
- **Beschrijving**: Gebruik dit endpoint om alle vaststelling op te halen. Het gebruik van de parameter van is verplicht.

### Vaststelling ophalen
- **Endpoint**: `/vaststellingen/{id}`
- **Beschrijving**: Gebruik dit endpoint om een specifieke vaststelling op te halen.

## Swagger

De volledige documentatie van de API is beschikbaar via Swagger-UI. Deze is te vinden op:
- **Locatie**: `/swagger-ui/index.html`
- [**Test**](https://dao.api.test-athumi.eu/swagger-ui/index.html?urls.primaryName=Datadeling)
- [**Beta**](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Datadeling)
