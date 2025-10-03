# Release notes

| Environment | Badge                                     |
|-------------|-------------------------------------------|
| Beta        | [![version][beta-version]][beta-actuator] |
| Acceptance  | [![version][acc-version]][acc-actuator]   |
| Production  | [![version][prd-version]][prd-actuator]   | 

[beta-version]: https://img.shields.io/badge/dynamic/json?label=version&query=build.version&url=https://elys.api.beta-athumi.eu/actuator/info&color=yellowgreen&style=for-the-badge&logo=amazonaws&logoColor=white

[beta-actuator]: https://elys.api.beta-athumi.eu/actuator/info

[acc-version]: https://img.shields.io/badge/dynamic/json?label=version&query=build.version&url=https://elys.api.acc-athumi.eu/actuator/info&color=yellowgreen&style=for-the-badge&logo=amazonaws&logoColor=white

[acc-actuator]: https://elys.api.acc-athumi.eu/actuator/info

[prd-version]: https://img.shields.io/badge/dynamic/json?label=version&query=build.version&url=https://elys.api.athumi.eu/actuator/info&color=yellowgreen&style=for-the-badge&logo=amazonaws&logoColor=white

[prd-actuator]: https://elys.api.athumi.eu/actuator/info

## 0.XX.0 - Planned TBC
### Algemeen API
> [!WARNING]
> #### Breaking change
> In de toekomst zal enkel TLS v1.3 ondersteund worden. 

---

## 0.30.0 - Planned 16/10/2025
### Arts:
#### Persoonsgegevens:
Geboortedatums kunnen onvolledig ingevuld worden (bv. enkel maand en/of jaar). Ook kan “00/00/0000” ingevuld worden voor een onbekende identiteit.
Onvolledige gegevens in het rijksregister worden ook in eLys onvolledig ingevuld en kunnen manueel gecorrigeerd worden.

#### Bevestiging na indienen vaststelling:
Het overzicht van de ingediende gegevens kan via een ‘afdrukken’ knop naar een (fysieke of PDF) printer verstuurd worden.

#### Openen vaststelling vanuit EPD:
Bij openen van de web-app vanuit een elektronisch patiëntendossier (EPD) is het mogelijk om een aantal gegevens (zoals het rijksregisternummer van de overledene) mee te sturen zodat die al vooringevuld kunnen worden in eLys.​

--- 

### Uitvaartondernemer:

> [!WARNING]
> #### Breaking change - Inlichtingen fiche
> Geboortedatums kunnen onvolledig ingevuld worden (bv. enkel maand en/of jaar).
> Onvolledige gegevens in het rijksregister worden ook in eLys onvolledig ingevuld en kunnen manueel gecorrigeerd worden.

---

### Burgerlijke stand lokaal bestuur:
Gewijzigde dossiers kunnen gepagineerd opgehaald worden via een nieuw endpoint /burgerlijke-stand/v1/dossiers/gewijzigd.

## 0.29.0 - Released 2/10/2025
### Arts:
#### Bezwaren en risico’s:
Waarschuwing bij niet-natuurlijk overlijden en niet aanduiden gerechtelijk-geneeskundig bezwaar (standaard zal dit aangeduid worden bij een niet-natuurlijk overlijden).
Waarschuwingen bij niet respecteren van een aantal logicaregels in het aanduiden van bezwaren (bijvoorbeeld risico op ioniserende stralen -> verwacht ook verplichte onmiddellijke kisting)

---

### Uitvaartondernemer:
#### Toelating transport:
Uitbreiding van de toelating transport met een aangepaste waarschuwing in functie van de aangeduide bezwaren en risico’s in de vaststelling.

#### Opstarten van een dossier:
Extra controle op status van betalingen zodat dubbele betalingen ook in onverwachte scenario’s (bv. sluiten van de browser tijdens betalingsproces) vermeden worden.

---

## 0.28.0 - Released 17/09/2025

### Arts:

#### Vaststelling labels:

Enkele labels zijn gewijzigd naar de correcte termen (woonzorgcentrum, zelfdoding).

#### Tijdstip vaststelling:

Datum van de medische vaststelling wordt default ingevuld met de huidige datum en er wordt een waarschuwing getoond bij
ingave van een datum meer dan 3 dagen in het verleden.

Tijdstip overlijden hoeft standaard niet ingevuld te worden, vermits dit meestal gelijk is aan het tijdstip van de
vaststelling - via een toggle kan een afwijkend tijdstip ingevuld worden of kan aangegeven worden dat het tijdstip niet
gekend is.

#### Adres overlijden:

Adres van overlijden moet altijd ingevuld worden bij optie ‘Thuis’ en wordt automatisch ingevuld met de plaats van
overlijden - bij deze optie wordt ook extra info (i) getoond. Bij opties ‘ziekenhuis’ en ‘woonzorgcentrum’ kan een adres
onthouden worden, zodat het later automatisch opnieuw ingevuld wordt.

#### Overzicht na indienen:

Gevoelige medische gegevens worden default verborgen maar kunnen via een toggle getoond worden.

--- 

### Uitvaartondernemer:

> [!WARNING]
> #### Breaking change:
> Het huidige endpoint voor het opstarten van een dossier wordt verwijderd, vanaf nu moet er ofwel gebruik worden
> gemaakt
> van het opstarten van een dossier met betaling via redirect flow of via wallet betaling.

#### Login:

Het inlogscherm is verbeterd en een link met registratie instructies is toegevoegd.

In het gebruikersmenu is een link toegevoegd naar de Athumi Account omgeving.

#### Opstarten van een dossier:

Voor betalingen wordt er connectie gemaakt met het externe systeem in de plaats van een zelfgemaakte mock interactie.

#### Annuleren van een dossier:

In het dialoogvenster is toegevoegd dat er geen terugbetaling mogelijk is.

---

## 0.27.0 - Released op 03/09/2025

### Uitvaartondernemer:

> [!WARNING]
> #### Breaking change:
> Het huidige endpoint voor het opstarten van een dossier wordt verwijderd in release 0.28.0, vanaf nu moet
> er ofwel gebruik worden gemaakt van het opstarten van een dossier met betaling via redirect flow,
> gereleased in 0.26.0 of via wallet betaling gereleased in de huidige release.

#### Opstarten van een dossier:

Om een betaling te kunnen doen zonder een redirect flow waarvoor een ACM/IDM login flow noodzakelijk
is, wordt een Wallet betaling mogelijk via een api call. Dit is een synchrone flow waarbij een al voorgeladen
bedrag kan gebruikt worden voor het dossier op te starten. Ook wordt er connectie gemaakt met het
externe systeem in de plaats van een zelfgemaakte mock interactie.

---

### Burgelijke stand lokaal bestuur:

#### Dossiers ophalen:

Er is een kleine verbetering naar het typeren van de adres informatie bij plaats van overlijden.---

## 0.26.0 - Released op 20/08/2025

### Uitvaartondernemer:

#### Opstarten van een dossier:

Bij het opstarten van een dossier wordt er een betaal flow gestart alvorens het dossier gekoppeld
wordt aan de uitvaartondernemer. Hierbij wordt er een redirect link terug gegeven waar men de
betaling kan uitvoeren. Dit is zowel wanneer het dossier gestart wordt via de GUI als via de API. Na het
uitvoeren van de betaling wordt er verwacht dat er via onze API een melding gebeurt om het dossier
verder te verwerken.

---

### Piloot fase:

#### Meldingen:

Er wordt een landingspagina voorzien die zowel kan dienen tijdens de initiële setup van de productie
omgeving als wanneer er iets mis is met de applicatie om door te navigeren naar een scherm met een
fout melding.

Er wordt in de GUI een banner voorzien die initieel gebruikt wordt om aan te geven dat enkel Leuven
en Lier aangesloten zijn. Maar naderhand kan gebruikt worden om communicatie uit te sturen naar de
gebruikers, zoals verwachte downtime.

Wanneer een vaststelling wordt aangemaakt voor een gemeente die niet in de piloot fase zit, wordt er
een foutmelding getoond.

---

## 0.25.0 - Released op 13/08/2025

### Audit log

Kleine verbeteringen omtrent het loggen van de acties van departement zorg.

---

### Crematoria & Begraafplaats

#### **Breaking change**

kboNummerUitvaartondernemer is nu een string in de plaats van een object dat een string bevat.

---

### Burgelijke stand lokaal bestuur

#### Swagger

Bij het ophalen van een dossier stond dat VerslagBeeidigdArts als type kon meegestuurd worden als medisch verslag.  
Dit was echter niet correct, en is nu verwijderd uit de swagger documentatie.

---

### Uitvaart

#### Crematoria en gemeentes van begraven

De naamgeving en volgorde van de crematoria zijn aangepast. Zowel op het scherm als hoe de API deze doorgeven.

#### Kleine bugfixes

Wanneer een gemeente met postcode geselecteerd wordt kan de postcode niet meer gewijzigd worden.
De foutmelding bij geen connectie rijksregister is verduidelijkt, het niet vinden van een persoon geeft nu een andere
foutmelding dan connectie problemen.

---

### Arts

#### Kleine bugfixes vaststelling

Tijdstip van ongeval werd verplicht als er een datum is ingevuld.
Wanneer een gemeente met postcode geselecteerd wordt kan de postcode niet meer gewijzigd worden.
Naam en voornaam werden omgewisseld bij het doorsturen van een verslag beëdigd arts
De foutmelding bij geen connectie rijksregister is verduidelijkt, het niet vinden van een persoon geeft nu een andere
foutmelding dan connectie problemen.

---

## 0.24.0 - Released op 05/08/2025

### Arts

#### Bevestiging

Na het indienen van een vaststelling wordt er een bevestiging getoond met Dossiernummer

#### Bezwaren en Risico's

Bezwaren en risico's zijn nu een verplicht veld met een ja of nee radio button in de plaats van de impliciete checkmarks
hiervoor

#### Persoon gegevens

De geboorte plek van de overledene en/of moeder kan onbepaald zijn, zowel uit het rijksregister als via de api/frontend.
We gebruiken hiervoor de land niscode `999` conform met het rijksregister.

---

### Burgerlijke stand lokaal bestuur

#### Laatste wilsbeschikking

In deze versie is er een nieuw endpoint voorzien voor de laatste wilsbeschikking van een dossier op te halen.
Dit endpoint is beschikbaar op `/burgerlijke-stand/v1/laatste-wilsbeschikking/${dossiernummer}`.
Op het dossier zijn er ook 2 velden toegevoegd:

- `laatsteWilsbeschikkingURI` de uri voor de laatste wilsbeschikking op te halen. Indien er geen aanwezig is, is deze
  URI null.
- `laatsteBevragingRijksregister` de laatste keer dat het rijksregister is bevraagd, hiermee halen we ook de laatste
  wilsbeschikking op.

---

### Uitvaart

#### Laatste wilsbeschikking

In deze versie is er een nieuw endpoint voorzien voor de laatste wilsbeschikking van een dossier op te halen.
Dit endpoint is beschikbaar op `/uitvaart/v1/laatste-wilsbeschikking/${dossiernummer}`.
Op het dossier zijn er ook 2 velden toegevoegd:

- `laatsteWilsbeschikkingUri` de uri voor de laatste wilsbeschikking op te halen. Indien er geen aanwezig is, is deze
  URI null.
- `laatsteBevragingRijksregister` de laatste keer dat het rijksregister is bevraagd, hiermee halen we ook de laatste
  wilsbeschikking op.

#### Mijn dossiers

Vanaf nu is de volgorde binnen mijn dossiers Naam - Voornaam in de plaats van Voornaam - Naam

#### Crematoria en gemeentes van begraven

Vanaf nu wordt er een melding gegeven wanneer een crematorium of de gemeentes van begraven al dan niet is aangesloten op
het platform.
Integratoren kunnen dit ook opvragen via de endpoints:

- `/metadata/v1/gemeenten/hoofdgemeenten`
- `/metadata/v1/crematorium`

#### Inlichtingenfiche

Het is vanaf nu mogelijk om op de inlichtingenfiche de naam van het kind toe te voegen.
Wanneer dit toegevoegd wordt zal het dossier ook de naam van het kind bevatten.

---

### Begraafplaatsen en crematoria

#### Ophalen Toestemming

Vanaf deze versie is het mogelijk om de informatie van een dossier al op te vragen wanneer er een inlichtingenfiche werd
opgesteld.
Dit is enkel beschikbaar in de endpoints bij het opvragen doormiddel van het rijksregister nummer of het dossiernummer.

