# Gebruik van de API van het DAO-platform voor het vaststellen van een overlijden

## Inleiding

Dit document legt uit hoe je de API van het DAO-platform kunt gebruiken om een overlijden te registreren. 

## Endpoints

### Json-ld context

Alle requests moeten voldoen aan het JSON-ld formaat met behulp van het contextbestand:
- **Contextbestand**: `/context/AangifteOverlijden.jsonld`

### Registreren van een overlijden van een persoon ouder dan 1 jaar
- **Endpoint**: `/vaststelling/ouder-dan-1-jaar`
- **Beschrijving**: Gebruik dit endpoint om een overlijden te registreren van een persoon die ouder is dan 1 jaar.

### Registreren van een overlijden van een persoon tot 1 jaar
- **Endpoint**: `/vaststelling/jonger-dan-1-jaar`
- **Beschrijving**: Gebruik dit endpoint om een overlijden te registreren van een kind jonger dan 1 jaar, of voor een doodgeboorte.

## Swagger

De volledige documentatie van de API is beschikbaar via Swagger-UI. Deze is te vinden op:
- **Locatie**: `/swagger-ui/index.html`

## Validatiefouten

Als er validatiefouten optreden bij het versturen van een request, zal de API een HTTP 400-fout terugsturen. 
