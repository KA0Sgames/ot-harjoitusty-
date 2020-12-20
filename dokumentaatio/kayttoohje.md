# Käyttöohje

Lataa tiedosto [Luolastoseikkailu.jar](https://github.com/KA0Sgames/ot-harjoitustyo/releases/download/Loppupalautus/Luolastoseikkailu.jar)

### Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla

```
java -jar Luolastoseikkailu.jar
```

### Kirjautuminen

Sovellus käynnistyy kirjautimisnäkymään:

![login screen](/dokumentaatio/pictures/loginscreen.jpg)

Kirjautuminen onnistuu kirjoittamalla username kenttään olemassa oleva käyttäjätunnus, sekä Password kenttään sitä
vastaava salasana ja painamalla Login.

### Uuden käyttäjän luominen

Kirjautumisnäkymästä on mahdollista siirtyä uuden käyttäjän luontiin tarkoitettuun näkymään painamalla Create user.

Uusi käyttäjä luodaan syöttämällä Username kenttään haluttu käyttäjätunnus, sekä Password kenttään haluttu salasana,
ja tämän jälkeen painamalla Create.

![user creation screen](/dokumentaatio/pictures/usercreationscreen.jpg)

Onnistuneen käyttäjän luonnin yhteydessä palataan kirjautumisnäkymään.

### Hahmon valinta

Onnistuneen kirjautumisen myötä siirrytään käyttäjän hahmot listaavaan näkymään:

![character screen](/dokumentaatio/pictures/characterscreen.jpg)

Näkymässä voi luoda uuden hahmon kirjoittamalla halutun hahmon nimen tekstikenttään ja painamalla Create.

Näkymästä voi kirjautua ulos napilla Logout, jolloin palataan kirjautumisnäkymään.

Näkymässä listataan käyttäjän hahmot ja hahmolla voi siirtyä pelaamaan painamalla hahmon nimen näyttävää nappia.
Valitessa hahmo, siirrytään pelinäkymään.

Hahmon voi poistaa hahmon tietojen oikealla puolella olevalla Delete napilla.

### Pelaaminen

Hahmon valinnan jälkeen siirrytään pelinäkymään.

Pelissä voi liikkua nuolinäppäimillä. Muuta toiminnallisuutta pelissä ei vielä ole.

Pelaajan hahmo näkyy peliruudussa viisikulmiona ja peliin tulee myös satunnainen määrä hämähäkkejä, jotka liikkuvat
itsekseen. Mikäli pelaaja siirtyy tarpeeksi lähelle hämähäkkiä, lähtee se liikkumaan kohti pelaajaa.

![game screen](/dokumentaatio/pictures/gamescreen.jpg)
