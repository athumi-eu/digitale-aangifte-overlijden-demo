# Indicatie van het type bevalling

Pad: `moeder.persoonsGegevens.bevalling.bevallingToestand.indicatiesBevallingType`

## Context

Formulier Model III D strook C bevat twee invulvelden gerelateerd aan de indicaties voor het type bevalling: maternale indicaties en indicaties bij het kind.
Het OSLO-model maakt geen onderscheid tussen deze twee, wat voor verwarring kan zorgen bij implementaties.

## Beslissing

De codelijst `IndicatieAssistentieType` wordt vervangen door twee andere codelijsten om beter aan te sluiten op formulier Model III D: `IndicatieBevallingTypeBijKind` en `IndicatieBevallingTypeBijMoeder`.

## Impact

De codes worden in twee velden verwacht via de `BevallingToestand` van `moeder`:
- `moeder.persoonsGegevens.bevalling.bevallingToestand.indicatiesBevallingTypeBijKind`
- `moeder.persoonsGegevens.bevalling.bevallingToestand.indicatiesBevallingTypeBijMoeder`

Telkens wanneer de code gelijk is aan `Andere` wordt een ingevulde beschrijving verwacht in de volgende velden:
- `moeder.persoonsGegevens.bevalling.bevallingToestand.andereIndicatiesBevallingTypeBijKind`
- `moeder.persoonsGegevens.bevalling.bevallingToestand.andereIndicatiesBevallingTypeBijMoeder`

Zie voorbeeld in `src/examples/modeld`.
