# Gebruik van de API van het eLys platform voor de uitvaartondernemer

## Inleiding

Dit document legt uit hoe je de API van het eLys platform kunt gebruiken om een dossier af te handelen in de context van de uitvaartondernemer.

Voor meer info omtrent de security kan je [hier meer lezen](../../../algemeen/security/README.md)

## Endpoints
Alle endpoints starten altijd met: `/uitvaart/v1`

### Swagger
De volledige documentatie van de API is beschikbaar via Swagger-UI. Deze is te vinden op:
- **Locatie**: `/swagger-ui/index.html`
- [**Beta**](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart%20sector)

### Dossiers ophalen
- **Endpoint**: `/dossiers`
- **Beschrijving**: Gebruik dit endpoint om alle dossiers op te halen. Dit is altijd voor jouw organisatie door het Kbonummer mee te geven.

### Dossier ophalen
- **Endpoint**: `/dossiers/{dossierNummer}`
- **Beschrijving**: Gebruik dit endpoint om een specifiek dossier op te halen. Let op dit zijn enkel dossiers gekoppeld aan de client.

### Overlijdens ophalen
- **Endpoint**: `/overlijdens`
- **Beschrijving**: Gebruik dit endpoint om alle nog niet geclaimde dossiers op te halen. Dit kan door te zoeken op basis van:
  Rijksregisternummer, dossiernummer, of achternaam samen met niscode van gemeente van overlijden en datum van overlijden.

### Toelating transport Dossier
- **Endpoint**: `/dossiers/{dossierNummer}/transport-view`
- **Beschrijving**: Gebruik dit endpoint om voor een specifiek dossier de toelating transport op te halen. Wanneer het dossier nog niet opgestart is kan elke uitvaartclient hier aan. 
  Wanneer het wel al opgestart is, is enkel de client die het dossier heeft opgestart gemachtigd om het dossier op te halen.

### Documenten van een Dossier bekijken
- **Endpoint**: `/dossiers/{dossierNummer}/documenten/{type}`
- **Beschrijving**: Dit endpoint maakt het mogelijk de documenten van een dossier te bekijken, mogelijke types: VERZOEK_NABESTAANDE, 
  TOESTEMMING_EIGENAAR, VERZOEK_NABESTAANDE_AS_PARTNER, VERZOEK_OPNAME_ALS_VADER_OF_MEEMOEDER, NATIONALE_AKTE, INTERNATIONALE_AKTE, TOESTEMMING_BEGRAFENIS_OF_CREMATIE

### Dossier van status wisselen
- **Endpoint**: `/dossiers/{dossierNummer}/{status}`
- **Beschrijving**: Dit endpoint maakt het mogelijk het dossier in een andere status te krijgen, de statussen zijn:
  - start-op: opstarten van een dossier
  - behandeld: afsluiten van een dossier
  - heropen: een afgesloten dossier heropenen
  - annuleer: een opgestart dossier annuleren, hierbij wordt de uitvaart ondernemer van het dossier verwijderd alsook de inlichtingen fiche.
    Wanneer het dossier opnieuw wordt opgestart wordt er ook verwacht dat er opnieuw een betaling is.

### Recente acties verwijderen
- **Endpoint**: `/dossiers/{dossierNummer}/verwijder-recente-acties`
- **Beschrijving**: Op het dossier worden een aantal recente acties getoond zodat het voor de uitvaartondernemer eenvoudig is om dit dossier verder op te nemen.
 Deze acties kunnen, over de organisatie heen, verwijderd worden zodat het duidelijk is wanneer nieuwe gelijkaardige acties gebeurd zijn.

### Inlichtingen fiche opladen, updaten en verkrijgen
- **Endpoint**: `/dossiers/{dossierNummer}/inlichtingenfiche`
- **Beschrijving**: De inlichtingen fiche kan als een multipart opgestuurd worden naar dit endpoint. 
  Bij een Post wordt er een creatie gedaan van een nieuwe inlichtingenfiche, bij een Put een update en bij een Get wordt de inlichtingen fiche doorgestuurd.
  Bij het updaten van een inlichtingen fiche wordt er verwacht dat de documenten die verwijderd werden meegegeven worden in een documentRemoveList. Documenten die niet meer kunnen voorkomen worden automatisch verwijderd.


### Socio economische gegevens opladen en verkrijgen
- **Endpoint**: `/dossiers/{dossierNummer}/socio-economische-gegevens`
- **Beschrijving**: De socio economische gegevens kunnen opgehaald worden met de Get en aangepast worden met de Put.  
  De gegevens worden vooringevuld met de gegevens die we hebben vanuit de verschillende bronnen.


## Validatiefouten

Als er validatiefouten optreden bij het versturen van een request, zal de API een HTTP 400-fout terugsturen. 

## Error codes

Een lijst met error codes kan hier worden teruggevonden.

[Error codes uitvaartondernemer](https://athumi.atlassian.net/wiki/external/ZDA4OTU0YTE2NzI1NDExMGExZjlhMzRjNTJlZjcxNmM)