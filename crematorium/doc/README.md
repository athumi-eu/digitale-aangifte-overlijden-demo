# Startpunt Crematorium

## Context crematoria
Achtergrondinformatie over het platform kan [hier](userstories/context.md) gevonden worden.

De [User stories](userstories/userstories.md) pagina beschrijft welke user stories in de toepassing voorzien moeten worden, en welke eLys API's hiervoor gebruikt kunnen worden. 

## API documentatie
Een beschrijving van de functionaliteit staat [hier](https://athumi.atlassian.net/wiki/external/YmIxMTFhNjY5NGFhNGQzOGIxNmZmMjgwN2U3YTJjZmU).

Uitleg over hoe de API's kunnen gebruikt kunnen worden, staat [hier](api/README.md).

## Security
Info over authenticeren en vereisten naar logging toe staan [hier](../../algemeen/security/README.md).

Daarnaast zijn er ook vereisten naar [bewaartermijnen](../../algemeen/security/bewaartermijnen.md) toe.

## Voorbeelden
Om een indicatie te geven van de verschillende mogelijkheden als integrator voor een crematorium 
zijn er onder **src/tests/x.spec.ts** calls geschreven met voorbeelden van de toepassing.

Deze calls zijn bedoeld voor de test omgeving, en gebruiken een .env file om de juist parameters binnen te halen.

```properties
TARGET=https://elys.api.test-athumi.eu/
TOKEN_URL=https://elys.api.test-athumi.eu/oauth2/token
BASIC_CLIENT=
KBONUMMER=
```