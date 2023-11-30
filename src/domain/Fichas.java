package domain;


import java.awt.*;

public abstract class Fichas {
    protected String ColorFicha;
    protected Player Jugador;

    public Fichas(Player jugador, String color){
        Jugador = jugador;
        ColorFicha = color;
    }

    public Color getColor(){
        if(ColorFicha.equals("blanco")){
            return Color.WHITE;
        }else{
            return Color.BLACK;
        }
    }

    // Otros métodos y atributos de la clase Fichas
}

