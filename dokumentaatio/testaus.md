# Testausdokumentti

Ohjelmaa on testattu automatisoiduin yksikkötestein JUnitilla.

### Yksikkötestaus

#### Sovelluslogiikka

Automatisoidut testit kohdistuvat pääosin sovelluslogiikkaan, eli pakkauksien domain ja domain.creatures luokkiin.

Jokaisella näistä luokista on oma testiluokkansa joka on nimetty lisäämällä luokan nimen perään 'Test'.

#### Dao-luokka

Dao luokkaa CaventureDao on testattu luomalla testejä varten erillinen testitietokanta testdatabase.db.

#### Testauskattavuus

Käyttöliittymää lukuunottamatta sovelluksen testauksen rivikattavuus on 94% ja haaraumakattavuus 84%.

![testikattavuus](/dokumentaatio/pictures/testikattavuus.jpg)

Testaamatta jäivät Dao-luokan poikkeustilanteet, Player luokan tilanteet, joissa ollaan lähellä seinää jota kohti
yritetään liikkua sekä Creature luokan changeDirection metodi osittain.

### Järjestelmätestaus

Sovelluksen järjestelmätestausta on tehty manuaalisesti.

#### Asennus

Sovelluksen repositorio on kloonattu yliopiston koneelle ja siitä on luotu mvn package komennolla jar, jonka
toimiminen on testattu sekä komentoriviltä, että graafisesta tiedostojenhallinnasta. Tällä tavalla testaamalla
sovellus on toiminut laitoksen cubbli linuxilla. Omalla windows koneella en ole saanut jar tiedostoa ajettua,
suoraan windowsin puolelta yritys käynnistää tiedosto graafisessa ympäristössä kaksoisklikkaamalla ei tehnyt mitään
näkyvää, Windows sybsystem for Linuxin puolella komentoriviltä ajaminen kaatuu jostain syystä näyttölaitteen
käyttöoikeuksiin, josta muut ovat huomauttaneet tämän olevan tavallista yritettäessä ajaa graafista ohjelmaa ssh
yhteyden kautta.

#### Toiminnallisuudet

Kaikki määrittelydokumentissa toteutetuksi merkatut ominaisuudet ja käyttöohjeen listaamat toiminnallisuudet on
käyty läpi. Kaikkiin syötekenttiin on myös testattu virheellisiä arvoja kuten liian lyhyitä käyttäjänimiä.

Pieni bugi ohjelman hahmonvalintaruutuun on jäänyt, joka näyttäytyy niin, että hahmon poiston yhteydessä edellinen
virheviesti jää näkyviin. Eli jos vaikka käyttäjällä on kolme hahmoa ja hän yrittää luoda uuden hahmon, tästä tulee
virheviesti, jos kuitenkin käyttäjä poistaa yhden hahmoista, niin näytöllä näkyy edelleen virheviesti, mutta tämä
ei estä uuden hahmon luomista.

### Sovellukseen jääneet laatuongelmat

Sovellus ei anna järkeviä virheilmoituksia tilanteissa jossa tietokannan käsittelyssä tapahtuu poikkeus.

