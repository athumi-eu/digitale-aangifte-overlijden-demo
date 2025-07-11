# Startpunt Begraafplaats

## Context begraafplaats
Achtergrondinformatie over het platform kan [hier](userstories/context.md) gevonden worden.

## API documentatie
Een beschrijving van de functionaliteit staat [hier](https://docs.athumi.eu/elys/help/functionaliteiten-voor-de-gemeente-van-begraven).

Uitleg over hoe de API's kunnen gebruikt kunnen worden, staat [hier](api/README.md).

## Security
Info over authenticeren en vereisten naar logging toe staan [hier](../../algemeen/security/README.md).

Daarnaast zijn er ook vereisten naar [bewaartermijnen](../../algemeen/security/bewaartermijnen.md) toe.

## Voorbeelden
Om een indicatie te geven van de verschillende mogelijkheden als integrator voor een gemeente van begraven 
zijn er onder **src/tests/x.spec.ts** calls geschreven met voorbeelden van de toepassing.

Deze calls zijn bedoeld voor de test omgeving, en gebruiken een .env file om de juist parameters binnen te halen.

```properties
TARGET=https://elys.api.test-athumi.eu/
TOKEN_URL=https://elys.api.test-athumi.eu/oauth2/token
BASIC_CLIENT=
KBONUMMER=
```