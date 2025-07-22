# Gebruik van de API van het eLys platform voor beheer van overlijdensdossiers voor lokale besturen

## Inleiding

Dit document legt uit hoe je de API van het eLys platform kunt gebruiken om een overlijden te beheren.

## Gebruik van de api

Een voorbeeld van hoe de api kan gebruikt worden kan je terugvinden in de src/ folder. Deze code dient louter als
voorbeeld en **MAG NIET** gebruikt worden voor een productie waardige applicatie.

### Verwachte flow

Een verwachte flow van de applicatie is:

1. Inloggen van de gebruiker
2. Ophalen van de dossiers
3. Bekijken van het detail van een dossier
4. Acties uitvoeren op dit dossier
5. (Stap 3 en 4 herhalen voor een ander dossier)
6. Uitloggen van de gebruiker

### Inloggen

Om in te loggen moet een OAuth token meegegeven worden. Meer informatie hierover vind je in
het [security document](../../../algemeen/security/README.md).

## Endpoints

### Swagger

De volledige documentatie van de API is beschikbaar via Swagger-UI. Deze is te vinden op:

- **Locatie**: `/swagger-ui/index.html`
- [**Beta**](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Dienst%20burgerlijke%20stand)

### Json-ld context

Alle requests moeten voldoen aan het JSON-ld formaat met behulp van de volgende contextbestanden:

- **Contextbestand**: `/context/AangifteOverlijden.jsonld`
- **Contextbestand**: `/context/VerslagBeedigdArts.jsonld`

### Ophalen van dossiers met filter

- **Endpoint**: `/burgerlijke-stand/v1/dossiers`
- **Beschrijving**: Gebruik dit endpoint om een lijst van dossiers op te halen. Voor dit endpoint kunnen er een reeks
  query parameters meegegeven worden. Bij het niet meegeven van een status, of bij het opvragen van dossiers in status BEHANDELD, VERWIJDERD of ZWANGERSCHAP_MINDER_DAN_180D
  moet bijkomend minstens 1 van de volgende parameters worden meegegeven :
  Status dossier, rijksregisternummer, datum overlijden of achternaam overledene.
  Er kan optioneel nog verder gefilterd worden op de exacte postcode/district van het overlijden.

### Ophalen van het detail van een dossier

- **Endpoint**: `/burgerlijke-stand/v1/dossiers/{dossiernummer}`
- **Beschrijving**: Gebruik dit endpoint om het detail van een dossier op te vragen.

### Downloaden van een toestemming/(inter)nationale akte

- **Endpoint**: `/burgerlijke-stand/v1/dossiers/{dossiernummer}/aktes/{type}`
- **Endpoint**: `/burgerlijke-stand/v1/dossiers/{dossiernummer}/documenten/{type}`
- **Endpoint**: `/burgerlijke-stand/v1/dossiers/{dossiernummer}/toestemming`
- **Beschrijving**: Gebruik dit endpoint om aktes, toestemmingen of andere documenten van een dossier te downloaden.

### Uploaden van een toestemming/(inter)nationale akte

- **Endpoint**: `/burgerlijke-stand/v1/dossiers/{dossiernummer}/aktes/{type}`
- **Endpoint**: `/burgerlijke-stand/v1/dossiers/{dossiernummer}/toestemming`
- **Beschrijving**: Gebruik dit endpoint om aktes of een toestemming van een dossier te uploaden.
- **Opmerking:** De inhoud van het toestemmingsdocument ligt vast en hiervan is een [sjabloon](../../../static/Sjabloon_toestemmingbegravencrematie.docx) beschikbaar. De layout van
  dit sjabloon mag per gemeente gewijzigd worden, maar de inhoud mag niet wijzigen.

### Aanvullen van een dossier

- **Endpoint**: `/burgerlijke-stand/v1/dossiers/{dossiernummer}/aanvullen`
- **Beschrijving**: Gebruik dit endpoint om een dossier aan te vullen. Een aanvulling is niet altijd toegestaan. Bekijk
  de lijst met error codes voor meer uitleg.

### Afsluiten/heropen van een dossier

- **Endpoint**: `/burgerlijke-stand/v1/dossiers/{dossiernummer}/afsluiten`
- **Endpoint**: `/burgerlijke-stand/v1/dossiers/{dossiernummer}/heropen`
- **Beschrijving**: Gebruik dit endpoint om een dossier af te sluiten of te heropenen. Afsluiten of heropenen is niet altijd toegestaan. Bekijk
  de lijst met error codes voor meer uitleg.

### Wijzig plaats van overlijden

- **Endpoint**: `/burgerlijke-stand/v1/dossiers/{dossiernummer}/wijzig-plaats-overlijden`
- **Beschrijving**: Gebruik dit endpoint om het dossier door te geven aan een andere gemeente indien de plaats van overlijden niet correct bleek.

### Verwijder een dossier

- **Endpoint**: `/burgerlijke-stand/v1/dossiers/{dossiernummer}/verwijderen`
- **Beschrijving**: Gebruik dit endpoint om een foutief ingediende vaststelling te verwijderen. Dit is enkel nodig bij grote fouten, zoals een vaststelling voor een verkeerd persoon.

### Ontkoppel een uitvaartondernemer van een dossier

- **Endpoint**: `/burgerlijke-stand/v1/dossiers/{dossiernummer}/ontkoppel`
- **Beschrijving**: Gebruik dit endpoint om het dossier weg te halen bij de huidige uitvaartondernemer. Indien dit al voorkomt, is het normaalgezien de uitvaartondernemer zelf die dit doet. Dit endpoint is voorzien om het in uitzonderlijke gevallen door de ABS te laten doen.

### Ophalen van (een) verslag(en)

- **Endpoint**: `/burgerlijke-stand/v1/verslagen-beedigd-arts`
- **Endpoint**: `/burgerlijke-stand/v1/verslagen-beedigd-arts/{dossiernummer}`
- **Beschrijving**: Gebruik dit endpoint om een lijst van verslagen of het detail van een individueel verslag te
  bekijken.

### (ont)koppelen van een verslag

- **Endpoint**: `/burgerlijke-stand/v1/verslagen-beedigd-arts/{id}/ontkoppel`
- **Endpoint**: `/burgerlijke-stand/v1/verslagen-beedigd-arts/{id}/koppel`
- **Beschrijving**: Gebruik dit endpoint om een verslag te (ont)koppelen van een dossier.

### Verwijderen van een verslag

- **Endpoint**: `/burgerlijke-stand/v1/verslagen-beedigd-arts/{id}`
- **Beschrijving**: Gebruik dit endpoint om een verslag te verwijderen.

### Statistische gegevens opvragen

- **Endpoint**: `/burgerlijke-stand/v1/dossiers/{id}/statistische-gegevens`
- **Beschrijving**: Gebruik dit endpoint om statistische gegevens op te vragen.

### Statistische gegevens updaten

- **Endpoint**: `/burgerlijke-stand/v1/dossiers/{id}/statistische-gegevens/socio-economische-gegevens`
- **Beschrijving**: Gebruik dit endpoint om statistische gegevens te updaten.

### Refresh gegevens rijkregister

- **Endpoint**: `/burgerlijke-stand/v1/dossiers/{id}/statistische-gegevens/refresh`
- **Beschrijving**: Gebruik dit endpoint om de verrijking vanuit het rijksregister opnieuw te starten.


### Laatste wilsbeschikking
- **Endpoint**: `/burgerlijke-stand/v1/laatste-wilsbeschikking/{id}`
- **Beschrijving**: Gebruik dit endpoint om de laatste wilsbeschikking op te halen voor een dossier.

## Validatiefouten

Als er validatiefouten optreden bij het versturen van een request, zal de API een HTTP 400-fout terugsturen.

## Error codes

Een lijst met error codes kan hier worden teruggevonden.

[Error codes lokaal bestuur](https://athumi.atlassian.net/wiki/external/MWJiOTdjM2RlMDlhNDMwMDk5YTJlZjhkZjg4YTk2YWQ)
