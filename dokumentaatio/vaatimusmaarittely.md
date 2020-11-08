# Vaatimusmaarittely

### Sovelluksen tarkoitus

Sovelluksella käyttäjä voi pelata luolastohenkistä roolipeliä. Sovellusta voi käyttää useampi rekisteröitynyt
käyttäjä, joilla on omat yksilölliset hahmonsa.

### Käyttäjät

Sovelluksessa on ainoastaan peruskäyttäjä. Mahdollisesti jatkokehityksenä ohjelmistotekniikka kurssin ulkopuolella
lisätään pääkäyttäjä, joka voi hallinnoida kaikkia käyttäjiä.

### Käyttöliittymäluonnos

Sovellus koostuu neljästä näkymästä.

Sovellus aukeaa kirjautumisnäkymään, josta on mahdollisuus siirtyä uuden käyttäjän luomisnäkymään tai onnistuneen
kirjautumisen yhteydessä hahmon valinta/luonti näkymään, josta hahmon valitsemalla pääsee pelaamaan peliä.

### Perusversion tarjoama toiminnallisuus

###### Ennen kirjautumista

- Käyttäjä voi luoda käyttäjätunnuksen

  * Käyttäjätunnuksen täytyy olla uniikki ja pituudeltaan vähintään neljä merkkiä

- Käyttäjä voi kirjautua järjestelmään

  * Kirjautuminen onnistuu syötettäessä olemassa oleva käyttäjätunnus ja sitä vastaava salasana
  * Jos käyttäjätunnusta ei ole olemassa, ilmoittaa järjestelmä tästä
  * Jos salasana ei vastaa annettua käyttäjätunnusta, ilmoittaa järjestelmä tästä

##### Kirjautumisen jälkeen

- Käyttäjä näkee omat hahmonsa

- Käyttäjä voi luoda uuden hahmon

  * Jos käyttäjällä on jo kolme luotua hahmoa, niin järjestelmä ilmoittaa, ettei uutta voida luoda poistamatta jotain
aikaisemmista hahmoista
  * Käyttäjän hahmot näkyvät ainoastaan käyttäjälle itselleen
  * Käyttäjä voi valita hahmon ja siirtyä pelaamaan sillä

##### Hahmon valinnan jälkeen

- Käyttäjä voi pelata peliä
  * Käyttäjän hahmo voi liikkua luolastossa
  * Käyttäjän hahmo voi taistella hirviöitä vastaan
  * Mikäli käyttäjän hahmo liikkuu tietylle etäisyydelle hirviöstä, se ottaa hahmon kohteekseen ja liikkuu hahmoa
kohden hyökätäkseen
  * Hahmo ansaitsee kokemuspisteitä tehdessään vahinkoa hirviöihin
  * Kokemustasot vaikuttavat hahmon tekemään vahinkoon
  * Luolastossa on satunnainen määrä hirviöitä ja niitä ilmestyy lisää tiettyyn lukumäärään asti satunnaisin väliajoin
  * Hahmo voi tallentaa kehityksensä (kokemuspisteet), mikäli ei ole taistelussa, eli ei ole minkään hirviön kohteena
  * Hahmo voi kuolla, jolloin se menettää keräämänsä kokemuksen edellisen tallennuksen jälkeen
  * Hahmon kuollessa palataan hahmon valitsemisvalikkoon

### Jatkokehitysideoita

Perusversion jälkeen järjestelmää täydennetään ajan salliessa esim. seuraavilla ominaisuuksilla

- Hahmot ansaitsevat rahaa ja esineitä tappaessaan hirviöitä, myös nämä tallennetaan kun hahmo päättää tallentaa
tietonsa

- Luolasto luodaan satunnaisgeneraattorilla

- Hahmo voi valita mitä esineitä on päällä ja esineet vaikuttavat hahmon tekemään ja ottamaan vahinkoon
