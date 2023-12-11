package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;


public class Jugador implements Serializable {
    private int puntuacion;
    private String color;
    private String nombre;
    private ArrayList<Fichas> fichas = new ArrayList<>();
    private int tiempo;

    public Jugador(String name, String color) {
        nombre = name;
        puntuacion = 0;
        this.color = color;
    }
    public String getColor() {
        return color;
    }
    public void addFichas(int totalFichas, String modo) {
        if(modo.equals("Normal") || modo.equals("Quicktime")) {
            for (int i = 0; i < totalFichas; i++) {
                int numeroAleatorio = new Random().nextInt(3) + 1;
                if (numeroAleatorio == 1) {
                    fichaNormal nuevaFicha = new fichaNormal(this, color);
                    fichas.add(nuevaFicha);
                } else if (numeroAleatorio == 2) {
                    fichaPesada nuevaFicha = new fichaPesada(this, color);
                    fichas.add(nuevaFicha);
                } else {
                    fichaTemporal nuevaFicha = new fichaTemporal(this, color);
                    fichas.add(nuevaFicha);
                }
            }
        }
    }
    public Fichas getFicha() {
        System.out.println(fichas.size());
        if (!fichas.isEmpty()) {
            Fichas ultimaFicha = fichas.getLast();
            fichas.removeLast();
            return ultimaFicha;
        } else {
            // Manejar el caso cuando el tamaño llega a cero
            throw new IllegalStateException("Tamaño de fichas agotado.");
        }
    }
    public void addTiempo(int tiempo){
        this.tiempo = tiempo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    public int fichasNormales(){
        int cont = 0;
        for (Fichas ficha : fichas) {
            if (ficha instanceof fichaNormal) {
                cont += 1;
            }
        }
        return cont;
    }

    public int fichasPesadas(){
        int cont = 0;
        for (Fichas ficha : fichas) {
            if (ficha instanceof fichaPesada) {
                cont += 1;
            }
        }
        return cont;
    }
    public int fichasTemporales(){
        int cont = 0;
        for (Fichas ficha : fichas) {
            if (ficha instanceof fichaTemporal) {
                cont += 1;
            }
        }
        return cont;
    }

    public Fichas getFichaTablero() {
        if (!fichas.isEmpty()) {
            return fichas.getLast();
        } else {
            // Manejar el caso cuando el tamaño llega a cero
            throw new IllegalStateException("Tamaño de fichas agotado.");
        }
    }
}
