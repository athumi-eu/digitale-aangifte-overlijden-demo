# Gebruik van de API van het DAO-platform voor beheer van overlijdensdossiers voor lokale besturen

## Inleiding

Dit document legt uit hoe je de API van het DAO-platform kunt gebruiken om een overlijden te beheren.

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
het [security document](../security/README.md).

Het token dat aangemaakt wordt in de voorbeeldcode werkt enkel voor de dev/test omgeving.
Integratoren worden verwacht enkel met de beta omgeving en daarboven te integreren.

## Endpoints

### Json-ld context

Alle requests moeten voldoen aan het JSON-ld formaat met behulp van de volgende contextbestanden:

- **Contextbestand**: `/context/AangifteOverlijden.jsonld`
- **Contextbestand**: `/context/VerslagBeedigdArts.jsonld`

### Ophalen van dossiers met filter

- **Endpoint**: `/burgerlijke-stand/v1/dossiers`
- **Beschrijving**: Gebruik dit endpoint om een lijst van dossiers op te halen. Voor dit endpoint kunnen er een reeks
  query parameters meegegeven worden. Minsten 1 van de volgende parameters moet meegegeven worden: Status dossier,
  rijksregisternummer, datum overlijden of achternaam overledene. Als status dossier behandeld of niet behandeld is moet
  nog 1 extra parameter meegegeven worden.

### Ophalen van het detail van een dossier

- **Endpoint**: `/burgerlijke-stand/v1/dossiers/{id}`
- **Beschrijving**: Gebruik dit endpoint om het detail van een dossier op te vragen

### (up/down)loaden van een toestemming/(inter)nationale akte

- **Endpoint**: `/burgerlijke-stand/v1/dossiers/{id}/aktes/{type}`
- **Endpoint**: `/burgerlijke-stand/v1/dossiers/{id}/toestemming`
- **Beschrijving**: Gebruik dit endpoint om aktes of toestemming aan een dossier toe te voegen.

### Verrijken van een dossier

- **Endpoint**: `/burgerlijke-stand/v1/dossiers/{id}/verrijken`
- **Beschrijving**: Gebruik dit endpoint om een dossier te verrijken. Een verrijking is niet altijd toegestaan. Bekijk
  de lijst met error codes voor meer uitleg.

### Afsluiten/heropen van een dossier
- **Endpoint**: `/burgerlijke-stand/v1/dossiers/{id}/afsluiten`
- **Endpoint**: `/burgerlijke-stand/v1/dossiers/{id}/heropen`
- **Beschrijving**: Gebruik dit endpoint om een dossier af te sluiten of te heropenen. Afsluiten of heropenen is niet altijd toegestaan. Bekijk
  de lijst met error codes voor meer uitleg.

### Ophalen van (een) verslag(en)

- **Endpoint**: `/burgerlijke-stand/v1/verslagen-beedigd-arts`
- **Endpoint**: `/burgerlijke-stand/v1/verslagen-beedigd-arts/{id}`
- **Beschrijving**: Gebruik dit endpoint om een lijst van verslagen of het detail van een individueel verslag te
  bekijken
- 
### (ont)koppelen van een verslag

- **Endpoint**: `/burgerlijke-stand/v1/verslagen-beedigd-arts/{id}/ontkoppel`
- **Endpoint**: `/burgerlijke-stand/v1/verslagen-beedigd-arts/{id}/koppel`
- **Beschrijving**: Gebruik dit endpoint om een verslag te (ont)koppelen van een dossier

### Verwijderen van een verslag

- **Endpoint**: `/burgerlijke-stand/v1/verslagen-beedigd-arts/{id}`
- **Beschrijving**: Gebruik dit endpoint om een verslag te verwijderen

## Swagger

De volledige documentatie van de API is beschikbaar via Swagger-UI. Deze is te vinden op:

- **Locatie**: `/swagger-ui/index.html`
- [**Test**](https://dao.api.test-athumi.eu/swagger-ui/index.html?urls.primaryName=Dienst%20burgerlijke%20stand)
- [**Beta**](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Dienst%20burgerlijke%20stand)

## Validatiefouten

Als er validatiefouten optreden bij het versturen van een request, zal de API een HTTP 400-fout terugsturen. 