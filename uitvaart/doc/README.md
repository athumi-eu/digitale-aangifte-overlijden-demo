# Startpunt Uitvaart

## Context uitvaart
Achtergrondinformatie over het platform en de toepassing voor de uitvaartondernemers kan [hier](userstories/context.md) gevonden worden.

De [User stories](userstories/userstories.md) pagina beschrijft welke user stories in de toepassing voorzien moeten worden, en welke eLys API's hiervoor gebruikt kunnen worden. 

De [algemene voorwaarden](https://docs.athumi.eu/elys/help/gebruiksvoorwaarden-elys) van eLys dienen geïntegreerd te worden in de toepassing van de integrator.

## API documentatie
Uitleg over hoe de API's kunnen gebruikt kunnen worden, staat [hier](api/README.md).

Meer info over het opzetten van de betaling vindt u [hier](https://athumi.atlassian.net/wiki/external/ODlkNWVjYzBmNzc2NDYwZmI2Y2M5ZDZmNDUzMDBhMzU).

Als voorbeeld staat [hier](https://athumi.atlassian.net/wiki/external/YmI2NTQyMzNhMjM0NGQyZDhlZWIwNzdhY2FkMTU5OWI) beschreven hoe de verschillende enum velden in de eLys GUI gebruikt worden.

## Security
Info over authenticeren en vereisten naar logging toe staan [hier](../../algemeen/security/README.md).

Daarnaast zijn er ook vereisten naar [bewaartermijnen](../../algemeen/security/bewaartermijnen.md) toe.

## Voorbeelden
Om een indicatie te geven van de verschillende mogelijkheden als integrator voor de uitvaartondernemers  
zijn er onder **src/tests/x.spec.ts** calls geschreven met voorbeelden van de toepassing.

Deze calls zijn bedoeld voor de test omgeving, en gebruiken een .env file om de juist parameters binnen te halen.

```properties
TARGET=https://elys.api.test-athumi.eu/
TOKEN_URL=https://elys.api.test-athumi.eu/oauth2/token
BASIC_CLIENT=
```
