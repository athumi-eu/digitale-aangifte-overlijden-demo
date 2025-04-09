# Functionaliteit API crematoria

## Opvragen gegevens

### Architectuur

- Scope: elys_crematorium (!) wordt nog gewijzigd naar eLys
- Crematoria kunnen voor een overlijden gegevens opvragen vanaf dat er een toestemming tot crematie is opgeladen
- De gegevens van een overlijden kunnen specifiek voor één overlijden worden opgevraagd
    - Op dossiernummer 
- Er kan ook een lijst met gegevens van verschillende overlijdens worden opgevraagd
    - ‘Van’ tijdstip + optioneel 'tot' tijdstip als parameters voorzien
    - Het tijdstip is het laatste wijzigingstijdstip van de toestemming
    - Max 100 elementen + aanduiding of er nog meer overlijdens zijn die voldoen aan de opvraging (dus aantal > 100)


![meerdereToestemmingen](../diagrams/meerdereToestemmingen.png)

- Crematoria kunnen enkel gegevens opvragen die voor hen bestemd zijn
    - Er wordt een controle gedaan op basis van KBO-nummer - volgens indiening door uitvaartondernemer in inlichtingenfiche:

![keuzeBegraafplaats](../diagrams/keuzeBegraafplaats.png)

![keuzeCrematorium](../diagrams/keuzeCrematorium.png)

**Gebruikte lijst crematoria**

![lijstCrematoria](../diagrams/lijstCrematoria.png)

**(!) Belangrijk**: Crematoria moeten bevraging blijven herhalen. Toestemmingen kunnen namelijk vervallen (indien nieuwe inlichtingenfiche leidt tot een nieuwe toestemming die moet opgeladen worden door het lokaal bestuur).

### Voorbeeld opvragen van één overlijden

![opvragen1Overlijden](../diagrams/opvragen1Overlijden.png)

### Voorbeeld opvragen lijst van overlijdens

**Gegeven** dat onderstaande overlijdens geregistreerd zijn in het eLys-platform voor crematorium Blauwe Toren:

![opvragenLijstOverlijdens](../diagrams/opvragenLijstOverlijdens.png)

**wanneer** crematorium Blauwe Toren de toestemmingen opvraagt tussen 10/01/2025 10u en 10/01/2025 13u

**dan** worden volgende overlijdens wel of niet terug gegeven

![opvragenLijstOverlijdens_resultaat](../diagrams/opvragenLijstOverlijdens_resultaat.png)

## Welke gegevens

- het toestemmingsdocument voor de begrafenis / crematie
- gestructureerde informatie beschikbaar in het platform

![gegevens](../diagrams/gegevens.png)
