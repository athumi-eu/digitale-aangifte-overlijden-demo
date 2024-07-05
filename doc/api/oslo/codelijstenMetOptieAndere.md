# Codelijsten met optie "Andere" en een vrij tekstveld

## Context

Op de formulieren Model III C en D zijn er verschillende velden die zowel een code als een vrij tekstveld accepteren in 
geval van "Andere". Een voorbeeld hiervan is het veld "Plaats van overlijden" op Model III D strook B, dat ofwel een 
code (`ziekenhuis` of `thuis`) of vrije tekst accepteert (onder `andere, preciseer`). Andere voorbeelden zijn de plaats 
van het ongeval, medische risicofactoren tijdens de zwangerschap, ligging van het kind, aard van de assistentie bij de 
bevalling, indicaties voor een assistentiebevalling, afwijkingen van het kind, enzovoort.

OSLO maakt het mogelijk om een codelijst te vervangen door een vrij tekstveld. 
Voor een implementatiemodel is dit echter niet wenselijk, omdat de aanwezigheid van een code uit een lijst 
dan niet goed kan worden gevalideerd door de backend.

## Beslissing

Het implementatiemodel staat niet toe om vrije tekst in te vullen waar een codelijst verwacht wordt.

## Impact

Telkens wanneer een vrij tekstveld wordt toegevoegd aan de optie `Andere` in een codelijst, voegen we een apart veld toe. 
Het originele veld kan dan gevalideerd worden ten opzichte van de codelijst.

Voorbeeld:
```json
{
  "ligging": [
    "Stuit",
    "Andere"
  ],
  "andereLigging": "andere ligging"
}
```

Voor andere voorbeelden, zie `src/examples`.
