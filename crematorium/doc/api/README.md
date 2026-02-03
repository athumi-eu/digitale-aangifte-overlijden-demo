# Gebruik van de API van het eLys platform voor het krijgen van toestemmingen voor een crematorium

## Inleiding

Dit document legt uit hoe je de API van het eLys platform kunt gebruiken om toestemmingen te verkrijgen.

Voor meer info omtrent de security kan je [hier meer lezen](../../../algemeen/security/README.md)

## Endpoints

Alle endpoints starten altijd met: `/crematorium/`

### Swagger
De volledige documentatie van de API is beschikbaar via Swagger-UI. Deze is te vinden op:
- [**Beta**](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Crematorium)

### Overlijdensgegevens ophalen
- **Endpoint**: `/overlijden/{dossiernummer}`
- **Beschrijving**: Met dit endpoint kunnen de overlijdensgegevens van een dossier worden opgezocht. Dit bevat geen gegevens over de (aanvraag tot) toestemming tot crematie. 
Dit kan gebruikt worden om te controleren of de uitvaartondernemer een geldig en het correcte dossiernummer opgeeft bij het maken van een reservatie.

### Toestemming(en) ophalen
- **Endpoint**: `/toestemmingen`
- **Beschrijving**: Dit endpoint geeft de data van de (aanvraag tot) toestemming tot crematie en de link voor de documenten. Er zijn verschillende mogelijkheden om te zoeken op dit endpoint:
  - Op basis van een dossiernummer met de query parameter dossiernummer
  - Op basis van een periode met wijzigingen met de parameters van=dateTime en tot=dateTime 

### Document toestemming tot crematie ophalen
- **Endpoint**: `/toestemmingen/{dossiernummer}/toestemming`
- **Beschrijving**: Hiermee kan er een toestemming gedownload worden.


