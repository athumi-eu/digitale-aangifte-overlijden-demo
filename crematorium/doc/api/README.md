# Gebruik van de API van het DAO-platform voor het krijgen van toestemmingen voor een crematorium

## Inleiding

Dit document legt uit hoe je de API van het DAO-platform kunt gebruiken om toestemmingen te verkrijgen.

Voor meer info omtrent de security kan je [hier meer lezen](../../../algemeen/security/README.md)

## Endpoints

Alle endpoints starten altijd met: `/crematorium/v1`

### Swagger
De volledige documentatie van de API is beschikbaar via Swagger-UI. Deze is te vinden op:
- **Locatie**: `/swagger-ui/index.html`
- [**Beta**](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Crematorium)

### Toestemmingen ophalen
- **Endpoint**: `/toestemingen`
- **Beschrijving**: Dit endpoint geeft de metadata en de link voor de documenten. Er zijn verschillende mogelijkheden om te zoeken op dit endpoint:
  - Op basis van een dossiernummer met de query parameter dossiernummer
  - Op basis van een periode met wijzigingen met de parameters van=dateTime en tot=dateTime 

### Document toestemming tot crematie ophalen
- **Endpoint**: `/toestemmingen/{dossiernummer}/toestemming`
- **Beschrijving**: Hiermee kan er een toestemming gedownload worden 


