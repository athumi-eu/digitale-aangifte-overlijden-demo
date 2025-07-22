# Release notes

## Versions

### 0.24
#### Burgerlijke stand lokaal bestuur
##### Laatste wilsbeschikking
In deze versie is er een nieuw endpoint voorzien voor de laatste wilsbeschikking van een dossier op te halen.
Dit endpoint is beschikbaar op `/burgerlijke-stand/v1/laatste-wilsbeschikking/${dossiernummer}`. 
Op het dossier zijn er ook 2 velden toegevoegd: 
- `laatsteWilsbeschikkingURI` de uri voor de laatste wilsbeschikking op te halen. Indien er geen aanwezig is, is deze URI null.
- `laatsteBevragingRijksregister` de laatste keer dat het rijksregister is bevraagd, hiermee halen we ook de laatste wilsbeschikking op.