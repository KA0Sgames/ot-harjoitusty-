# Vaatimusmäärittely

### Sovelluksen tarkoitus

Sovelluksella käyttäjä voi pelata luolastohenkistä roolipeliä. Sovellusta voi käyttää useampi rekisteröitynyt
käyttäjä, joilla on omat yksilölliset hahmonsa.

### Käyttäjät

Sovelluksessa on ainoastaan peruskäyttäjä. Mahdollisesti jatkokehityksenä ohjelmistotekniikka kurssin ulkopuolella
lisätään pääkäyttäjä, joka voi hallinnoida kaikkia käyttäjiä.

### Käyttöliittymäluonnos

Sovellus koostuu neljästä näkymästä.

![vaatimusmäärittely](/dokumentaatio/pictures/Vaatimusmäärittely.jpg)

Sovellus aukeaa kirjautumisnäkymään, josta on mahdollisuus siirtyä uuden käyttäjän luomisnäkymään tai onnistuneen
kirjautumisen yhteydessä hahmon valinta/luonti näkymään, josta hahmon valitsemalla pääsee pelaamaan peliä.

### Perusversion tarjoama toiminnallisuus

###### Ennen kirjautumista

- [x] Käyttäjä voi luoda käyttäjätunnuksen

  * [x] Käyttäjätunnuksen täytyy olla uniikki ja pituudeltaan vähintään neljä merkkiä

- [x] Käyttäjä voi kirjautua järjestelmään

  * [x] Kirjautuminen onnistuu syötettäessä olemassa oleva käyttäjätunnus ja sitä vastaava salasana
  * [x] Jos käyttäjätunnusta ei ole olemassa, ilmoittaa järjestelmä tästä
  * [x] Jos salasana ei vastaa annettua käyttäjätunnusta, ilmoittaa järjestelmä tästä

##### Kirjautumisen jälkeen

- [x] Käyttäjä näkee omat hahmonsa

- [x] Käyttäjä voi luoda uuden hahmon

  * [x] Jos käyttäjällä on jo kolme luotua hahmoa, niin järjestelmä ilmoittaa, ettei uutta voida luoda poistamatta jotain
aikaisemmista hahmoista
  * [x] Käyttäjä voi luoda vain hahmon jolla on uniikki nimi ja nimen pituus on vähintään neljä merkkiä. Käyttäjälle ilmoitetaan mikäli näin ei ole.
- [x] Käyttäjän hahmot näkyvät ainoastaan käyttäjälle itselleen
- [x] Käyttäjä voi valita hahmon ja siirtyä pelaamaan sillä
- [x] Käyttäjä voi poistaa oman olemassa olevan hahmonsa

##### Hahmon valinnan jälkeen

- Käyttäjä voi pelata peliä
  * [x] Käyttäjän hahmo voi liikkua luolastossa
  * [ ] Käyttäjän hahmo voi taistella hirviöitä vastaan
  * [x] Mikäli käyttäjän hahmo liikkuu tietylle etäisyydelle hirviöstä, se ottaa hahmon kohteekseen ja liikkuu hahmoa
kohden hyökätäkseen
  * [ ] Hahmo ansaitsee kokemuspisteitä tehdessään vahinkoa hirviöihin
  * [ ] Kokemustasot vaikuttavat hahmon tekemään vahinkoon
  * [x] Luolastossa on alussa satunnainen määrä hirviöitä
  * [ ] Hirviöitä ilmestyy lisää tiettyyn lukumäärään asti satunnaisin väliajoin
  * [ ] Hahmo voi tallentaa kehityksensä (kokemuspisteet), mikäli ei ole taistelussa, eli ei ole minkään hirviön kohteena
  * [ ] Hahmo voi kuolla, jolloin se menettää keräämänsä kokemuksen edellisen tallennuksen jälkeen
  * [ ] Hahmon kuollessa palataan hahmon valitsemisvalikkoon

### Jatkokehitysideoita

Perusversion jälkeen järjestelmää täydennetään ajan salliessa esim. seuraavilla ominaisuuksilla

- Hahmot ansaitsevat rahaa ja esineitä tappaessaan hirviöitä, myös nämä tallennetaan kun hahmo päättää tallentaa
tietonsa

- Luolasto luodaan satunnaisgeneraattorilla

- Hahmo voi valita mitä esineitä on päällä ja esineet vaikuttavat hahmon tekemään ja ottamaan vahinkoon
