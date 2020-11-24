package ui;

// import dao.CaventureDao;

public class Main {
    public static void main(String[] args) throws Exception {
        Ui.main(args);
        
        /*  Tässä käytetty Main luokan main metodia vain testaamaan tietokannan toimimista, ennen kuin sille on käyttöliittymä
        CaventureDao dao = new CaventureDao();
        
        dao.createDB();
        
        if (dao.containsUser("Jaska")) {
            System.out.println("Nyt tapahtuu kummia! Jaska oli tietokannassa.");
        } else {
            System.out.println("Jaskaa ei löydy tietokannasta niinkuin ei pidäkkään löytyä.");
        }
        
        boolean onko = dao.addUser("Jari", "Aarnio");

        if (dao.containsUser("Jari")) {
            System.out.println("Jari löytyi tietokannasta kuten pitikin!");
        } else {
            System.out.println("Jostain syystä Jari ei nyt ole tietokannassa.");
        }
        
        if (dao.passwordMatches("Jari", "Pekka")) {
            System.out.println("Taas tapahtuu kummia, Jarilla väärä salasana!");
        } else {
            System.out.println("Jarin väärä salasana huomattiin!");
        }
        
        if (dao.passwordMatches("Jari", "Aarnio")) {
            System.out.println("Hyvä, oikea salasana tunnistettiin!");
        } else {
            System.out.println("Aijai, passmatch antoi falsen virheellisesti oikealla salasanalla.");
        }
        */
    }
}
