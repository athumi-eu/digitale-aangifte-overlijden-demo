# Startpunt burgelijke stand
Doordat er geen UI voorzien werd voor de burgelijke stand is er gekozen om een Demo applicatie te voorzien die een indicatie kan geven over de 
nodige schermen binnen de toepassing burgelijke stand. 

Op test staat deze app gedeployed onder: [https://dao.test-athumi.eu/demo/dossiers?kbonummer=0207521503](https://dao.test-athumi.eu/demo/dossiers?kbonummer=0207521503)

Het kboNummer is een vast kboNummer. Dat mee geconfigureerd is op de testclient van deze toepassing. 


## Gebruik
Uitleg over het gebruik van de api en de security kunnen gevonden worden in de doc/ folder.

**Opgelet** Indien je deze applicatie wil opstarten dient de Oauth2 configuratie in de application.yml aangepast te worden zodat
deze een token ophaalt van ACM/IDM in plaats van de Spring Authorization server van onze dev/test omgeving.

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


