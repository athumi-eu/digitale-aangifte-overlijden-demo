# Status zwangerschap en bevalling

## Context

Het model III C maakt het mogelijk om aan te geven of een persoon zwanger is geweest en of die persoon bevallen is in het jaar voor de dood.
De opties zijn hier "ja, nee, onbekend". Vooral die laatste kan niet in het OSLO-model opgenomen worden.

Er werd een toevoeging gedaan aan OSLO `ZwangerschapsToestand`, maar niet aan `BevallingsToestand`.

## Beslissing

We onderscheiden een onbekende Persoonsgebeurtenis `Zwangerschap` en `Bevalling` via het type op deze relatie. 

## Impact

Via het `type` van de zwangerschaps- en bevallingsrelatie onderscheiden we of die relatie aanwezig ("ja"), afwezig ("nee"), of onbekend ("onbekend") is.
Het `type` kan een van deze zijn: `Zwangerschap`, `Bevalling`, of `OnbekendePersoonsgebeurtenis`.

Voorbeeld:

```json
{
  "zwangerschap": {
    "@type": "Zwangerschap"
  },
  "bevalling": {
    "@type": "OnbekendePersoonsgebeurtenis"
  }
}
```

Voor meer voorbeelden zie `src/examples/modelc`.
