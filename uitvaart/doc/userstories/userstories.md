# User Stories Uitvaartondernemer

## Inleiding

Deze pagina geeft een overzicht van de user stories die voorzien moeten worden in de applicatie, samen met de endpoints die in het eLys-platform aangeboden worden. 

Dit zijn de **minimale** user stories die voorzien moeten worden voor de uitvaartondernemer om zijn werkproces te vervolledigen via het platform.


## Login

1. "Als uitvaartondernemer wil ik kunnen inloggen in de toepassing."

	We leggen de voorwaarden voor de integrator als ‘trusted party’ vast in een samenwerkingsovereenkomst. Dit bevat onder meer de verantwoordelijkheid om de eindgebruiker de juiste rol te geven en toegang te geven via een eigen bepaalde client access systeem.


## Dossierbeheer en gegevens ophalen

2. “Als uitvaartondernemer, kan ik een overlijdensdossier opzoeken dat nog niet toegekend is of wel aan mijn onderneming toegekend of door mijn onderneming behandeld is.”

    [GET/uitvaart/v1/overlijdens](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart%20sector#/overlijdens-controller/getDossiersByRijksregisternummer_1_1)

    **Opmerking:** Dit kan of door het rijksregisternummer, of door het dossiernummer of door de combinatie van achternaam, niscode en datum overlijden mee te geven.

    **Opmerking:** Deze request zou slechts 1 antwoord mogen terug geven, behalve in het uitzonderlijke geval dat er 2 overlijdens zijn met dezelfde achternaam, niscode en datum overlijden.

    **Opmerking:** Dossiers reeds toegekend aan een andere uitvaartonderneming zullen getoond worden met een aparte aanduiding.

    **Opmerking:** Behandelde dossiers door een andere uitvaartonderneming zullen niet opgehaald worden.

    **Opmerking:** Deze endpoint geeft een gelimiteerde set aan gegevens terug. Gebruik endpoint GET/uitvaart/v1/dossiers/{dossierNummer} voor het ophalen van meer details.


3. “Als uitvaartondernemer kan ik van een overlijden de verklaring transport opvragen, zodat ik aan de politie (of aan andere autoriteiten) kan aantonen dat ik het lichaam mag vervoeren bij een controle.”

    [GET/uitvaart/v1/dossiers/{dossierNummer}/transport-view](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart%20sector#/dossier-uitvaart-controller/getTransportView)

    **Opmerking:** Dit is zowel mogelijk voor toegekende als niet-toegekende dossiers.


4. “Als uitvaartondernemer moet ik na de opstart van een dossier een platformvergoeding betalen vooraleer ik verder kan gaan met mijn acties.”

    [POST/uitvaart/v1/dossiers/{dossierNummer}/start-met-betaling](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart%20sector#/Dossier%20Uitvaart/startMetBetaling)

    [GET/uitvaart/v1/betaling/{betalingId}/controleer-betaling](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart+sector#/Betaling/controleerBetaling)

    [POST/uitvaart/v1/betaling/wallet](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart%20sector#/Betaling/startWalletBetaling)

5. “Als uitvaartondernemer kan ik een betaling via wallet doen om het dossier op te starten.”

    **Via de Athumi Account web applicatie**

    [POST/uitvaart/v1/dossiers/{dossierNummer}/start-met-betaling](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart%20sector#/Dossier%20Uitvaart/startMetBetaling)

    [GET/uitvaart/v1/betaling/{betalingId}/controleer-betaling](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart+sector#/Betaling/controleerBetaling)

    **Via API**

    [POST/uitvaart/v1/betaling/wallet](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart%20sector#/Betaling/startWalletBetaling)

    **Opmerking:** Lees [deze pagina](https://athumi.atlassian.net/wiki/external/ODlkNWVjYzBmNzc2NDYwZmI2Y2M5ZDZmNDUzMDBhMzU) voor meer info.

6. "Als uitvaartondernemer kan ik een eenvoudig overzicht van 'Mijn dossiers' raadplegen zodat: ik niet steeds opnieuw moet zoeken naar een dossier; ik kan zoeken en sorteren in mijn dossiers op bepaalde parameters.”
    
    [GET/uitvaart/v2/dossiers](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart%20sector#/dossier-uitvaart-controller/getDossiersByRijksregisternummer_1_1_1)

    **Opmerking:** Dit zal enkel dossiers terug geven in de status ‘toegekend’ (reeds opgestart door de uitvaartonderneming en nog niet afgesloten)


7. “Als uitvaartondernemer kan ik aan de hand van het dossiernummer het detail van een overlijdensdossier raadplegen zodat ik zicht heb op de overlijdensdossiers en de status van het dossier (aanwezigheid verslag beëdigd arts, akten en toestemming).

    [GET/uitvaart/v1/dossiers/{dossierNummer}](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart%20sector#/dossier-uitvaart-controller/getDossierByDossierNummer)


8. “Als uitvaartondernemer zie ik een melding wanneer door de ambtenaar burgerlijke stand van de gemeente van overlijden een wijziging is doorgevoerd op het dossier.”

    **Melding in lijst**

    [GET/uitvaart/v2/dossiers](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart%20sector#/dossier-uitvaart-controller/getDossiersByRijksregisternummer_1_1_1)
    
      "heeftActies": true

    **Melding in detail dossier**

    [GET/uitvaart/v1/dossiers/{dossierNummer}](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart%20sector#/dossier-uitvaart-controller/getDossierByDossierNummer)

      "acties": ["string"]

    **Opmerking:** mogelijke acties: Een overlijdensakte werd opgeladen/gewijzigd; een toestemming werd opgeladen/gewijzigd; een rijksregisternummer van de overledene werd toegevoegd aan het dossier (indien niet ingevuld door de arts in de vaststelling); de bronbevraging is gebeurd voor socio-economische gegevens van de ouders (vader/meemoeder); het verslag beëdigd arts werd gekoppeld.

9. “Ik kan als uitvaartondernemer aangeven dat ik bovenstaande melding heb gezien, zodat deze verdwijnt voor alle gebruikers binnen de onderneming.”

    [POST/uitvaart/v1/dossiers/{dossierNummer}/verwijder-recente-acties](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart%20sector#/dossier-uitvaart-controller/verwijderRecenteActies)


10. “Als uitvaartondernemer wil ik een dossier afsluiten, zodat: aan het lokaal bestuur wordt doorgegeven dat ik klaar ben met de uitvaartafhandeling; dit dossier niet meer in mijn lijst met dossiers staat.”

    [POST/uitvaart/v1/dossiers/{dossierNummer}/behandeld](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart%20sector#/dossier-uitvaart-controller/dossierBehandeld)

    **Opmerking:** Voorwaarden voor afsluiten dossier: overlijdensakte(n) aanwezig, inlichtingenfiche uitvaart ingediend, toestemming aanwezig, socio-economische gegevens ingediend

11. “Als uitvaartondernemer, kan ik een dossier heropenen indien ik bijvoorbeeld alsnog een wijziging wil doorvoeren aan de ingediende inlichtingenfiche uitvaart.”

    [POST/uitvaart/v1/dossiers/{dossierNummer}/heropen](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart%20sector#/dossier-uitvaart-controller/heropen)

    **Opmerking:** Gebruik eerst endpoint GET/uitvaart/v1/overlijdens om het juiste dossier op te zoeken.

## Gegevens en documenten ophalen en ingeven

12. “Als uitvaartondernemer, wil ik de nationale en internationale overlijdensakte die door de ambtenaar burgerlijke stand van de gemeente van overlijden zijn opgeladen in het platform, downloaden.”

    [GET/uitvaart/v1/dossiers/{id}/documenten/{type}](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart%20sector#/dossier-uitvaart-controller/downloadDocument)

    **Types:** NATIONALE_AKTE, INTERNATIONALE_AKTE

    **Opmerking:** Deze documenten worden na 90 dagen gearchiveerd, dan geeft het endpoint een 410 terug.

13. "“Als uitvaartondernemer wil ik voor een overleden persoon de aanwezigheid en inhoud van de laatste wilsbeschikking bekijken zodat ik hiermee rekening kan houden voor de aanvraag tot begraven/crematie.”

    [GET/uitvaart/v1/laatste-wilsbeschikking/{id}](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart+sector#/Laatste%20wilsbeschikking%20Uitvaart/getLaatsteWilsbeschikking)

    **Opmerking:** De laatste wilsbeschikking wordt na 90 dagen gearchiveerd, dan geeft het endpoint een 410 terug.

14. “Als uitvaartondernemer, wil ik de aanvraag tot toestemming begraven / crematie inclusief bijhorende documenten (PDF, Word, JPEG, PNG) kunnen invullen via het platform, zodat de burgerlijke stand van de gemeente van overlijden deze toestemming kan opmaken aan de hand van de ingediende aanvraag.”

    [POST/uitvaart/v1/dossiers/{dossierNummer}/inlichtingenfiche](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart%20sector#/inlichtingenfiche-uitvaart-controller/createInlichtingenficheUitvaart)

    **Opmerking:** Bekijk het [schema](../diagrams/inlichtingenfiche.jpg) met de opbouw van de aanvraag toestemming/crematie of de Athumi GUI om te weten welke documenten wanneer verwacht worden: verzoek nabestaanden, toestemming eigenaar, verzoek nabestaanden as partner.

15. “In het geval van een doodgeboorte >180d zwangerschap, wil ik op vraag van de ouders het verzoek tot opname van de naam en/of voornaam en/of het verzoek tot opname van de vader/meemoeder (inclusief het ondertekend verzoek in PDF, Word, JPEG, PNG) op de akte levenloos kind via het platform doorgeven aan de ambtenaar van de burgerlijke stand via de inlichtingenfiche.”

    [POST/uitvaart/v1/dossiers/{dossierNummer}/inlichtingenfiche](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart%20sector#/inlichtingenfiche-uitvaart-controller/createInlichtingenficheUitvaart)

    **Opmerking:** Bij opname vader/meemoeder is een ondertekend document verzoek opname als vader of meemoeder noodzakelijk.

    **Opmerking:** Alle data (metadata + documenten) van de inlichtingenfiche uitvaart (bovenstaande 2 user stories) worden als 1 geheel doorgestuurd zodat alles in 1 transactie kan uitgevoerd worden.

16. “Als uitvaartondernemer kan ik de gegevens van een reeds ingediende inlichtingenfiche uitvaart bekijken en indien nodig wijzigen, zodat ik foutieve aanvragen tot begraven / crematie of foutieve gegevens voor opname akte levenloos kind kan corrigeren. Het lokaal bestuur ontvangt hiervan een melding zodat de burgerlijke stand de aangepaste documenten indien nodig opnieuw kan opladen.”

    [GET/uitvaart/v1/dossiers/{dossierNummer}/inlichtingenfiche](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart%20sector#/inlichtingenfiche-uitvaart-controller/getToestemming)

    [PUT/uitvaart/v1/dossiers/{dossierNummer}/inlichtingenfiche](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart%20sector#/inlichtingenfiche-uitvaart-controller/editInlichtingenficheUitvaart)


17. “Als uitvaartondernemer, wil ik het document toestemming tot begraven / crematie dat door de ambtenaar burgerlijke stand van de gemeente van overlijden is opgeladen in het platform, kunnen downloaden.”

    [GET/uitvaart/v1/dossiers/{id}/documenten/{type}](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart%20sector#/dossier-uitvaart-controller/downloadDocument)

    **Type:** TOESTEMMING_BEGRAFENIS_OF_CREMATIE


## Socio-economische gegevens

18. “Als uitvaartondernemer moet ik, in overleg met de nabestaanden, de socio-economische gegevens indienen, zodat deze via het lokaal bestuur aan het Departement Zorg kunnen bezorgd worden voor de opmaak van de overlijdensstatistieken. Indien ik geen zicht heb op de gegevens, vul ik de vragen in met ‘onbekend’.”

    [PUT/uitvaart/v1/dossiers/{dossierNummer}/socio-economische-gegevens](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart%20sector#/socio-economische-gegevens-controller/saveSocioEconomischeGegevens)

    “Ik kan de ingediende gegevens bekijken en indien nodig updaten.”

    [GET/uitvaart/v1/dossiers/{dossierNummer}/socio-economische-gegevens]( https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart%20sector#/socio-economische-gegevens-controller/getSocioEconomischeGegevens)

    [PUT/uitvaart/v1/dossiers/{dossierNummer}/socio-economische-gegevens](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart%20sector#/socio-economische-gegevens-controller/saveSocioEconomischeGegevens)

## Unhappy flow

19. “Als uitvaartondernemer, kan ik een dossier annuleren, in het geval de nabestaanden  alsnog een andere uitvaartondernemer hebben aangesteld.”

    [POST/uitvaart/v1/dossiers/{dossierNummer}/annuleer](https://elys.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Uitvaart%20sector#/dossier-uitvaart-controller/startDossierOp_1)

    **Opmerking:** Gevolgen van deze actie: Het dossier is niet langer zichtbaar voor mijn uitvaartonderneming; er is geen terugbetaling voorzien; het dossier is beschikbaar voor een andere uitvaartondernemer om op te starten (en opnieuw te betalen); de inlichtingenfiche en toestemming worden verwijderd van het dossier; het lokaal bestuur ziet dat het dossier werd geannuleerd.
