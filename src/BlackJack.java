
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BlackJack {
    Deck deck = new Deck();
    List<Card> huidigdeck = deck.makeDeck();
    static List<Card> deelnemerKaarten = new ArrayList<>();
    static List<Card> dealerKaarten = new ArrayList<>();
    Random random = new Random();

    public static void main(String[] args) {
        BlackJack blackJack = new BlackJack();
        Scanner scanner = new Scanner(System.in);
        int deelnemerScore = blackJack.handScore(deelnemerKaarten);
        int dealerScore = blackJack.handScore(dealerKaarten);
        System.out.println("");
        System.out.println("De dealer heeft op het moment de volgende kaarten op tafel: " + blackJack.printKaarten(dealerKaarten));
        System.out.println("Met een totale waarde van: " + dealerScore);
        System.out.println("");
        System.out.println("Je hebt op het moment de volgende kaarten in je hand: " + blackJack.printKaarten(deelnemerKaarten));
        System.out.println("Met een totale waarde van: " + deelnemerScore);
        System.out.println("");
        System.out.printf("Wat wil je doen? Nog een kaart = k, dealer laten spelen = p, stoppen = q");

        while (deelnemerScore < 21 & dealerScore < 21) {
            String input = scanner.nextLine();
            if (input.equals("k")) {
                blackJack.kaartPakken(deelnemerKaarten);
                deelnemerScore = blackJack.handScore(deelnemerKaarten);
                System.out.println("Je hebt op het moment de volgende kaarten in je hand: " + blackJack.printKaarten(deelnemerKaarten));
                System.out.println("Met een totale waarde van: " + deelnemerScore);
            } else if (input.equals("q") | input.equals("quit")) {
                System.exit(0);
            } else if (input.equals("p")) {
                System.out.println("Je hebt gepast. De dealer gaat nu spelen.");
                while (dealerScore <= 17) {
                    blackJack.kaartPakken(dealerKaarten);
                    dealerScore = blackJack.handScore(dealerKaarten);
                    System.out.println("De dealer heeft nu de volgende kaarten op tafel: " + blackJack.printKaarten(dealerKaarten));
                    System.out.println("Met een totale waarde van: " + dealerScore);
                }
                if (dealerScore > 21) {
                    System.out.println("De bank heeft verloren met: " + dealerScore + " punten. Jij hebt gewonnen! :D");
                    System.exit(0);
                } else if (dealerScore == 21) {
                    System.out.println("De bank heeft gewonnen met precies: " + dealerScore + " punten. Helaas jij hebt verloren :(");
                    System.exit(0);
                } else if (deelnemerScore > dealerScore) {
                    System.out.println("Je hebt gewonnen met: " + deelnemerScore + " punten. Gefelicteerd!");
                    System.out.println("Ten opzichte van: " + dealerScore + " punten van de dealer.");
                }
            }
        }
        if (deelnemerScore > 21) {
            System.out.println("Helaas je hebt verloren :(. Je score was: " + deelnemerScore);
        } else if (deelnemerScore == 21) {
            System.out.println("Je hebt meteen gewonnen! Geluksvogel!");
        }
    }

    public void kaartPakken(List hand) {
        int geselecteerdeKaart = random.nextInt(huidigdeck.size());
        hand.add(huidigdeck.get(geselecteerdeKaart));
        System.out.print("Er is een " + huidigdeck.get(geselecteerdeKaart).getColor() + " ");
        if (huidigdeck.get(geselecteerdeKaart).getNumber() != 0) {
            System.out.print(huidigdeck.get(geselecteerdeKaart).getNumber());
        } else {
            System.out.print(huidigdeck.get(geselecteerdeKaart).getPlaatje());
        }
        System.out.println(" gepakt.");

        huidigdeck.remove(geselecteerdeKaart);
    }

    public int handScore(List<Card> hand) {
        int handscore = 0;
        for (Card kaart : hand) {
            if (kaart.getNumber() != 0) {
                handscore = handscore + kaart.getNumber();
            } else if (kaart.getPlaatje() == "B" | kaart.getPlaatje() == "V" | kaart.getPlaatje() == "H") {
                handscore = handscore + 10;
            } else if (kaart.getPlaatje() == "A") {
                if (handscore + 11 > 21) {
                    handscore = handscore + 1;
                } else if (handscore + 11 == 21) {
                    handscore = handscore + 11;
                } else {
                    handscore = handscore + 11;
                }
            }
        }
        return handscore;
    }

    public String printKaarten(List<Card> kaarten) {
        String kaartenOmTePrinten = "";
        for (Card kaart : kaarten) {
            kaartenOmTePrinten = kaartenOmTePrinten.concat((kaart.getColor()) + " ");
            if (kaart.getNumber() != 0) {
                kaartenOmTePrinten = kaartenOmTePrinten.concat((kaart.getNumber()) + ", ");
            } else {
                kaartenOmTePrinten = kaartenOmTePrinten.concat((kaart.getPlaatje()) + ", ");
            }
        }
        return kaartenOmTePrinten;
    }

    BlackJack() {
        System.out.println("Welkom bij blackjack! Er worden nu kaarten gedeeld. Voor jou: ");
        kaartPakken(deelnemerKaarten);
        kaartPakken(deelnemerKaarten);
        System.out.println("De dealer krijgt: ");
        kaartPakken(dealerKaarten);
    }
}
