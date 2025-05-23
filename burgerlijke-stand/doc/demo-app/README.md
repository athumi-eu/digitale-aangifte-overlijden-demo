# Demo applicatie
Doordat er geen UI voorzien werd voor de burgerlijke stand is er gekozen om een demo applicatie te voorzien die een indicatie kan geven over de 
nodige schermen binnen de toepassing burgerlijke stand. 

Op beta staat deze app gedeployed onder: [https://elys.beta-athumi.eu/demo/dossiers?kbonummer=0207521503](https://elys.beta-athumi.eu/demo/dossiers?kbonummer=0207521503)

Het kboNummer is een vast kboNummer dat mee geconfigureerd is op de testclient van deze toepassing. In bovenstaande URL is het kboNummer van Leuven gebruikt, dit kan aangepast worden naar het juiste kboNummer van de gemeente.


## Gebruik
Uitleg over het gebruik van de api en de security kunnen gevonden worden in de doc/ folder.

Om de applicatie lokaal op te starten is volgend script voorzien:
- **beta**: `start-beta.sh`

Voordat u dit script uitvoerd moet u onder `src/main/resources` volgende file toevoegen:
- **beta**: `application-secret-beta.yml`

met de volgende structuur:
```yaml
dao:
  gemeentes:
    security-method: private_key_jwt
    config:
      <gemeentenaam>:
        naam: <Gemeentenaam>
        kbonummer: <kbonummer gemeente>
        clientid: <clientid gemeente>
        clientsecret: <clientsecret gemeente>
```
Het is mogelijk om meerdere gemeentes toe te voegen aan deze config.

Voor de beta omgeving kan u hier de client ingeven die u [zelf kan aanvragen](../../../algemeen/security/client-aanvragen.md).

Deze applicatie dient enkel als voorbeeld en is niet productie waardig.

## Overzicht van de applicatie
Deze applicatie is geschreven in spring-boot met thymeleaf.
Hier volgt een korte lijst me de voornaamste kenmerken:
* De code die de api van de backend called kan gevonden worden in `DossierDao`, `FileDao` en `VerslagDao`
* De `json` package bevat de data objecten die gebruikt worden in de communicatie met de backend
* De `parsing` package bevat de klassen die de data objecten converteert naar human readable format.
* De `configuration` package bevat de klassen die instaan voor de security configuratie
* In de resource/ folder kunnen zowel de thymeleaf templates als de application properties gevonden worden.

### Reference Documentation

Voor meer informatie rond de gebruikte technologie:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.3/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.3/gradle-plugin/packaging-oci-image.html)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.3.3/reference/htmlsingle/index.html#using.devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.3/reference/htmlsingle/index.html#web)
* [htmx](https://github.com/wimdeblauwe/htmx-spring-boot)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/3.3.3/reference/htmlsingle/index.html#web.servlet.spring-mvc.template-engines)

### Guides

De volgende handleidingen illustreren hoe u bepaalde functies concreet kunt gebruiken:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [htmx](https://www.youtube.com/watch?v=j-rfPoXe5aE)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)
