# Ohjelmistotekniikka, harjoitustyö
-----------------------------------
# Caventure luolastoseikkailu

Sovelluksella käyttäjä voi pelata yksinkertaista luolastoseikkailua.

### Ohjelman toimivuudesta

Ohjelma on testattu laitoksen cubbli linuxilla javan versiolla 11, jolla se toimii. Muilla javan versioilla ohjelmaa
ei ole testattu.

Omalla windows koneella en ole saanut suoritettua mvn package komennolla generoitua jar tiedostoa. Graafisessa
käyttöliittymässä kaksoisklikkaus ei tee mitään näkyvää. Tämä saattaa johtua joidenkin windows asetusten tai tiedoston
oletus avaamis sovelluksen asetuksista. Windows subsystem for Linuxin puolella komentoriviltä ajamisen yritys kaatuu
virheviestiin jonka mukaan näyttölaitteelle ei ole oikeuksia. Kurssilaisten mukaan tämä viesti tulee yleensä
tilanteessa, jossa ssh yhteyden yli yritetään ajaa graafista sovellusta.

Olen kuitenkin saanut ajettua ohjelman windowsin puolella suoraan netbeansistä, jolloin se toimii moitteetta.

### Dokumentaatio

[Vaatimusmäärittely](/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](/dokumentaatio/tyoaikakirjanpito.md)

[Käyttöohje](/dokumentaatio/kayttoohje.md)

[Testausdokumentti](/dokumentaatio/testaus.md)

### Releaset

[Viikko 6](https://github.com/KA0Sgames/ot-harjoitustyo/releases/tag/viikko6)

[Loppupalautus](https://github.com/KA0Sgames/ot-harjoitustyo/releases/tag/Loppupalautus)

### Komentorivitoiminnot

#### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html

#### Suoritettavan jarin generointi

Komento

```
 mvn package
```

generoi hakemistoon target suoritettavan jar tiedoston Luolastoseikkailu-1.0-SNAPSHOT.jar

#### Checkstyle

Tiedoston checkstyle.xml määrittelemät tarkastukset suoritetaan komennolla:

```
mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset näkee avaamalla selaimella tiedoston target/site/checkstyle.html

#### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia generoituu kansioon target/site/apidocs/ ja juurikansion tarkasteluun pääsee avaamalla tiedoston index.html
