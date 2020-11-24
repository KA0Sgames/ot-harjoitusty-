# Ohjelmistotekniikka, harjoitustyö
-----------------------------------
# Caventure luolastoseikkailu

Sovelluksella käyttäjä voi pelata yksinkertaista luolastoseikkailua.


### Dokumentaatio

[Vaatimusmäärittely](/dokumentaatio/vaatimusmaarittely.md)

[työaikakirjanpito](/dokumentaatio/tyoaikakirjanpito.md)

### Komentorivitoiminnot

#### Testaus

Testit suoritetaan komennolla: mvn test

Testikattavuusraportti luodaan komennolla: mvn jacoco:report

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html

#### Suoritettavan jarin generointi

Komento: mvn package

#### Checkstyle

Tiedoston checkstyle.xml määrittelemät tarkastukset suoritetaan komennolla:

mvn jxr:jxr checkstyle:checkstyle

Mahdolliset virheilmoitukset näkee avaamalla selaimella tiedoston target/site/checkstyle.html
