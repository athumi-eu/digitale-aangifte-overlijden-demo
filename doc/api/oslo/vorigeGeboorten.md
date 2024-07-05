# Vorige geboorten

Pad: `moeder.vorigeGeboorten`

## Context

Strook C van Model III D heeft een sectie over de vorige geboorten van de moeder. Het bevat de volgende informatie:
- aantal geboorten
- aantal doodgeboorten
- aantal kinderen in leven
- datum van de vorige geboorte

Het verstrekken van deze informatie volgens het [Applicatieprofiel Overlijdensaangifte](https://data.vlaanderen.be/doc/applicatieprofiel/overlijdensaangifte)
zal complex zijn, omdat de klant een `OSLO-Persoon::Persoon` moet definiëren voor elk kind en het aan de moeder moet koppelen via een `OSLO-Persoon::Persoonsrelatie`.

> moeder<OSLO-Persoon::Persoon>   
> → gezinsrelatie[]<OSLO-Persoon::Persoonsrelatie>    
> → kind<OSLO-Persoon::Persoon>   
> → Overlijden<Persoonsgebeurtenis>  
> → Overlijden/geboorte<Persoonsgebeurtenis>

Bovendien moet de backend alle kinderen verwerken om het juiste aantal (dood)geboorten te tellen en de datum van de laatste geboorte te verzamelen.
Vanwege deze complexiteit hebben we een alternatieve manier gedefinieerd om de informatie te verstrekken.

## Beslissing

Het contract van het vereenvoudigde `vorigeGeboorten` object is als volgt gedefinieerd:

```js
{
    vorigeGeboorten: {
        aantalLevendGeboren: { waarde: number };
        aantalDoodGeboren: { waarde: number };
        aantalKinderenNogInLeven: { waarde: number };
        datumVorigeGeboorte: Date;
    }
}
```

Zie voorbeeld in `src/examples/modeld`.
