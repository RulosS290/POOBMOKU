package domain;

public class player {
    private String colorCard;

    public player(String Color){
        colorCard = Color;
    }
    public String getColorCard(){
        return colorCard;
    }
    public void setColor(String Color){
        colorCard = Color;
    }
}
