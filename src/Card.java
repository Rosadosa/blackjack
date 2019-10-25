public class Card {
    private String color;
    private int number;
    String plaatje;
    public Card(int number, String color){
        this.number = number;
        this.color = color;
    }

    public Card(String color, String plaatje){
        this.plaatje = plaatje;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }

    public String getPlaatje() {
        return plaatje;
    }
}
