# User Stories Burgerlijke Stand

## Inleiding

Deze pagina geeft een overzicht van de user stories die voorzien moeten worden in de applicatie, samen met de endpoints die in het DAO platform aangeboden worden. 

## Dossierbeheer en gegevens ophalen

1. "Ik wil als ambtenaar burgerlijke stand (ABS) snel op de hoogte zijn van nieuwe dossiers zodat ik hiervoor de overlijdensakten kan aanmaken en de data kan controleren."

    [GET /burgerlijke-stand/v1/dossiers](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Dienst%20burgerlijke%20stand#/dossier-burgerlijke-stand-controller/getDossiers)

    **Status:** NIEUW

2. "Als ABS wil ik dat de overlijdensgegevens nodig voor de opmaak van de overlijdensakte vanuit het platform doorstromen naar mijn toepassing zodat ik efficiënt de schrapping uit het rijksregister/opmaak overlijdensakte kan doen."

   "Als ABS wil ik alle persoons- en overlijdensgegevens van de overledene kunnen bekijken, inclusief het verslag van de beëdigd arts."

    [GET /burgerlijke-stand/v1/dossiers/{id}](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Dienst%20burgerlijke%20stand#/dossier-burgerlijke-stand-controller/getDossier)

    **Id**: (bv. 20250131-002-HXDJ)

3. "Ik wil als ABS snel op de hoogte zijn van nieuwe en gewijzigde inlichtingenfiches uitvaart."

   "Als ABS wil ik dat de gegevens nodig voor de opmaak van de toestemming vanuit het platform doorstromen naar mijn toepassing (sjabloon document) zodat ik efficiënt het toestemmingsdocument kan valideren en ondertekenen."

      [GET /burgerlijke-stand/v1/dossiers](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Dienst%20burgerlijke%20stand#/dossier-burgerlijke-stand-controller/getDossiers)

    **Status:** IN_BEHANDELING

    [GET /burgerlijke-stand/v1/dossiers/{id}/toestemming](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Dienst%20burgerlijke%20stand#/dossier-burgerlijke-stand-controller/downloadDocument)

   !! Gemeentelijke software voorziet diff om in data-object aanvraagTotToestemming: datumVanAanvraag > eigen datumVanAanvraag in storage

4. "Als ABS wil ik de bijlagen bij de inlichtingenfiche downloaden als onderdeel van de voorbereiding van de af te leveren documenten."

    [GET /burgerlijke-stand/v1/dossiers/{id}/documenten/{type}](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Dienst%20burgerlijke%20stand#/dossier-burgerlijke-stand-controller/downloadDocument_1)

    **Type:** VERZOEK_NABESTAANDE, TOESTEMMING_EIGENAAR, VERZOEK_NABESTAANDE_AS_PARTNER, VERZOEK_OPNAME_ALS_VADER_OF_MEEMOEDER

5. "Als ABS wil ik weten of er een laatste wilsbeschikking aanwezig is voor de overleden persoon en indien wel, de inhoud ervan bekijken zodat de afgeleverde toestemming hiermee in overeenstemming is."

    **API endpoint nog niet ontwikkeld**

6. "Wanneer ik alles heb afgewerkt voor een dossier (overlijdensakten, toestemming, aanvulling gegevens) wil ik als ABS een dossier afsluiten, zodat ik een duidelijk overzicht behoud en achterliggend de socio-economische gegevens aan het Departement Zorg bezorgd worden."

    [POST /burgerlijke-stand/v1/dossiers/{id}/afsluiten](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Dienst%20burgerlijke%20stand#/dossier-burgerlijke-stand-controller/afsluiten)

7. "Wanneer ik een behandeld dossier heb afgesloten, maar ik alsnog een fout heb opgemerkt (bv. in overlijdensakte) wil ik als ABS het dossier heropenen."

    [POST /burgerlijke-stand/v1/dossiers/{id}/heropen](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Dienst%20burgerlijke%20stand#/dossier-burgerlijke-stand-controller/heropen)

8. “Wanneer een dossier werd heropend, wil ik zien waarom dit het geval is: manuele actie, socio-economische gegevens werden ingevuld/gewijzigd, inlichtingenfiche uitvaart (aanvraag toestemming of verzoek opname akte levenloos kind) werd geüpdatet zodat ik kan evalueren welke actie van mij vereist wordt.”

   !! **API endpoint nog niet ontwikkeld**

## Gegevens en documenten opladen

9. "Als ABS wens ik dat na opmaak in DABS de overlijdensakten (afschrift overlijdensakte en uittreksel meertalige internationale akte) worden doorgestuurd naar het platform zodat de uitvaartondernemer deze zo snel mogelijk kan consulteren."

    [POST /burgerlijke-stand/v1/dossiers/{id}/aktes/nationaal](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Dienst%20burgerlijke%20stand#/dossier-burgerlijke-stand-controller/uploadAkteNationaal)
   
    [POST /burgerlijke-stand/v1/dossiers/{id}/aktes/internationaal](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Dienst%20burgerlijke%20stand#/dossier-burgerlijke-stand-controller/uploadAkteInternationaal)

10. "Als ABS kan ik de akten bekijken die in het platform zijn opgeladen."

    [GET /burgerlijke-stand/v1/dossiers/{id}/aktes/{type}](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Dienst%20burgerlijke%20stand#/dossier-burgerlijke-stand-controller/downloadDocument_2)
    
    **Types:** nationaal, internationaal

11. “Als ABS wil ik, na het consulteren van de laatste wilsbeschikking en de aanvraag tot crematie/begraven, dat het toestemmingsdocument dat ik heb opgemaakt in mijn toepassing, samen met het creatietijdstip, wordt doorgestuurd naar het platform zodat de uitvaartondernemer deze zo snel mogelijk de kan consulteren.”

    “Als ABS wil ik een nieuwe toestemming kunnen opladen als er een fout zou instaan.

       [POST /burgerlijke-stand/v1/dossiers/{id}/toestemming](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Dienst%20burgerlijke%20stand#/dossier-burgerlijke-stand-controller/uploadToestemming)

12. “Als ABS kan ik de toestemming bekijken die in het platform is opgeladen.”

    [GET /burgerlijke-stand/v1/dossiers/{id}/documenten/{type}](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Dienst%20burgerlijke%20stand#/dossier-burgerlijke-stand-controller/downloadDocument_1)

    **Type:** TOESTEMMING_BEGRAFENIS_OF_CREMATIE

13. “Als ABS wil ik het PV-nr, het rijksregisternummer van de overledene (enkel indien leeg) en rijksregisternummers ouders kunnen aanvullen zodat deze informatie zo snel mogelijk kan gedeeld worden met Departement zorg en/of kan gebruikt worden voor bronverrijking.”

    [POST /burgerlijke-stand/v1/dossiers/{id}/aanvullen](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Dienst%20burgerlijke%20stand#/dossier-burgerlijke-stand-controller/verrijken)

## Verslagen beëdigd arts

14. "Als ABS wil ik alle niet-gekoppelde verslagen voor overlijdens in mijn gemeenten kunnen consulteren en hier vlot in kunnen zoeken."

    [GET /burgerlijke-stand/v1/verslagen-beedigd-arts](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Dienst%20burgerlijke%20stand#/verslag-controller/getVerslagen)

15. "Als ABS wil ik de details bekijken van een niet-gekoppeld verslag."

    [GET /burgerlijke-stand/v1/verslagen-beedigd-arts/{id}](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Dienst%20burgerlijke%20stand#/verslag-controller/getVerslag)

16. "Als ABS wil ik een gevonden verslag koppelen aan een specifiek dossier."

    [POST /burgerlijke-stand/v1/verslagen-beedigd-arts/{id}/koppel](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Dienst%20burgerlijke%20stand#/verslag-controller/koppelVerslag)

17. "Als ABS wil ik een foutief gekoppeld verslag ontkoppelen zodat ik het verslag opnieuw kan zien in de niet-gekoppelde verslagen."

    [POST /burgerlijke-stand/v1/verslagen-beedigd-arts/{id}/ontkoppel](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Dienst%20burgerlijke%20stand#/verslag-controller/ontkoppelVerslag)

18. "Als ABS wil ik een niet-gekoppeld verslag verwijderen (indien foutief ingevuld)."

    [DELETE /burgerlijke-stand/v1/verslagen-beedigd-arts/{id}](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Dienst%20burgerlijke%20stand#/verslag-controller/deleteVerslag)

## Socio-economische gegevens

19. "Als ABS wil ik zicht hebben op de status en waarden van de socio-economische gegevens verrijkt door de authentieke bron, deze aangevuld door de uitvaartondernemer en deze bezorgd aan het Departement Zorg zodat ik aan mijn wettelijke taak kan voldoen."

    [GET /burgerlijke-stand/v1/dossiers/{id}/statistische-gegevens](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Dienst%20burgerlijke%20stand#/statistische-gegevens-controller/getStatistischeGegevens)

20. "Als ABS wil ik facultatief aanvullingen kunnen aanbrengen aan socio-economische gegevens waarvoor geen authentieke bron beschikbaar is."

    [PUT /burgerlijke-stand/v1/dossiers/{id}/statistische-gegevens/socio-economische-gegevens](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Dienst%20burgerlijke%20stand#/statistische-gegevens-controller/saveSocioEconomischeGegevens)

21. “Als ABS wil ik een manuele herbevraging van het Rijksregister kunnen doen in het platform, wanneer ik een wijziging in de bron Rijksregister heb doorgevoerd en deze update moet doorgaan naar het Departement Zorg.”

    [POST /burgerlijke-stand/v1/dossiers/{id}/statistische-gegevens/refresh](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Dienst%20burgerlijke%20stand#/statistische-gegevens-controller/refreshRijksregisterVerrijking)

## Unhappy flows

22. "Als ABS wil ik een foutief ingediende vaststelling door de arts kunnen verwijderen, waardoor deze niet langer zichtbaar is voor de uitvaartondernemer en een nieuwe vaststelling voor hetzelfde rijksregisternummer kan ingediend worden."

    **API endpoint nog niet ontwikkeld**

23. "Als ABS wil ik een foutief verwijderde vaststelling heropenen."

    [POST /burgerlijke-stand/v1/dossiers/{id}/heropen](https://dao.api.beta-athumi.eu/swagger-ui/index.html?urls.primaryName=Dienst%20burgerlijke%20stand#/dossier-burgerlijke-stand-controller/heropen)

24. "Als ABS wil ik, in het geval de nabestaanden alsnog kiezen voor een andere uitvaartondernemer, het dossier bij de uitvaartondernemer annuleren, zodat het kan opgestart worden door de nieuwe partij."

    **API endpoint nog niet ontwikkeld**

25. "Als ABS wil ik een door de arts foutief ingegeven gemeente van overlijden wijzigen van mijn gemeente naar de juiste gemeente, zodanig dat het dossier uit mijn dossiers verdwijnt en bij de juiste gemeente verschijnt."

    **API endpoint nog niet ontwikkeld**
