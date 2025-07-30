# Release notes

## Versions

### 0.25.0


### 0.24.0

#### Arts
##### Bevestiging
Na het indienen van een vaststelling wordt er een bevestiging getoond met Dossiernummer

##### Bezwaren en Risico's
Bezwaren en risico's zijn nu een verplicht veld met een ja of nee radio button in de plaats van de impliciete checkmarks hiervoor

##### Persoon gegevens
De geboorte plek van de overledene en/of moeder kan onbepaald zijn, zowel uit het rijksregister als via de api/frontend. 
We gebruiken hiervoor de land niscode `999` conform met het rijksregister.

#### Burgerlijke stand lokaal bestuur
##### Laatste wilsbeschikking
In deze versie is er een nieuw endpoint voorzien voor de laatste wilsbeschikking van een dossier op te halen.
Dit endpoint is beschikbaar op `/burgerlijke-stand/v1/laatste-wilsbeschikking/${dossiernummer}`. 
Op het dossier zijn er ook 2 velden toegevoegd: 
- `laatsteWilsbeschikkingURI` de uri voor de laatste wilsbeschikking op te halen. Indien er geen aanwezig is, is deze URI null.
- `laatsteBevragingRijksregister` de laatste keer dat het rijksregister is bevraagd, hiermee halen we ook de laatste wilsbeschikking op.

#### Uitvaart
##### Laatste wilsbeschikking
In deze versie is er een nieuw endpoint voorzien voor de laatste wilsbeschikking van een dossier op te halen.
Dit endpoint is beschikbaar op `/uitvaart/v1/laatste-wilsbeschikking/${dossiernummer}`.
Op het dossier zijn er ook 2 velden toegevoegd:
- `laatsteWilsbeschikkingUri` de uri voor de laatste wilsbeschikking op te halen. Indien er geen aanwezig is, is deze URI null.
- `laatsteBevragingRijksregister` de laatste keer dat het rijksregister is bevraagd, hiermee halen we ook de laatste wilsbeschikking op.

##### Mijn dossiers
Vanaf nu is de volgorde binnen mijn dossiers Naam - Voornaam in de plaats van Voornaam - Naam

##### Crematoria en gemeentes van begraven
Vanaf nu wordt er een melding gegeven wanneer een crematorium of de gemeentes van begraven al dan niet is aangesloten op het platform.
Integratoren kunnen dit ook opvragen via de endpoints:
- `/metadata/v1/gemeenten/hoofdgemeenten`
- `/metadata/v1/crematorium`

##### Inlichtingenfiche
Het is vanaf nu mogelijk om op de inlichtingenfiche de naam van het kind toe te voegen. 
Wanneer dit toegevoegd wordt zal het dossier ook de naam van het kind bevatten.

#### Begraafplaatsen en crematoria
##### Ophalen Toestemming
Vanaf deze versie is het mogelijk om de informatie van een dossier al op te vragen wanneer er een inlichtingenfiche werd opgesteld.
Dit is enkel beschikbaar in de endpoints bij het opvragen doormiddel van het rijksregister nummer of het dossiernummer.

