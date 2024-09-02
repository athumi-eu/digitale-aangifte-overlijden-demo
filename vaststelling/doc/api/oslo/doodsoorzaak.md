# Doodsoorzaak

Pad: `medischeToestand.doodsoorzaak.gerelateerdAan`

## Context

Op het formulier Model III D moet je kunnen aangeven of een doodsoorzaak en geassocieerde oorzaak gerelateerd is aan de moeder, het kind, of de foetus.
Het OSLO-model heeft deze gegevens tot nu toe niet opgenomen.

## Beslissing

Aan de component "doodsoorzaak" wordt een veld `gerelateerdAan` toegevoegd dat een code accepteert uit een codelijst.

## Impact

Validatie van ingevoerde data kan gebeuren volgens de codelijst `https://data.vlaanderen.be/ns/overlijdensaangifte#Doodsoorzaak.gerelateerdAan`.
Een codelijst is voor integratoren handiger om te implementeren dan extra links via Persoon.
