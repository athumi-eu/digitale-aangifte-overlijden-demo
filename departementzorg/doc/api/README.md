# Gebruik van de API van het eLys platform voor het krijgen van statische data

## Inleiding

Dit document legt uit hoe je de API van het eLys platform kunt gebruiken om statische data te verkrijgen.
Voor meer info omtrent de security kan je [hier meerlezen](../../../algemeen/security/README.md)

## Endpoints

Alle endpoints starten altijd met: `/datadeling/v1`

### Vaststellingen ophalen
- **Endpoint**: `/vaststellingen`
- **Beschrijving**: Gebruik dit endpoint om alle vaststelling op te halen binnen een periode. Het gebruik van de parameter 'van' is verplicht. Als er nog meer elementen beschikbaar zijn, wordt het veld nogElementen op true gezet.

### Vaststelling ophalen
- **Endpoint**: `/vaststellingen/{id}`
- **Beschrijving**: Gebruik dit endpoint om een specifieke vaststelling op te halen.

## Swagger

De volledige documentatie van de API is beschikbaar via Swagger-UI. Deze is te vinden op:
- **Locatie**: `/swagger-ui/index.html`
- [**Test**](https://elys.api.test-athumi.eu/swagger-ui/index.html?urls.primaryName=Datadeling)
- [**Beta**](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Datadeling)
