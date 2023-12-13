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
        if (modo.equals("Normal") || modo.equals("Quicktime")) {
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

    public Fichas getFicha(String tipo) {
        System.out.println(fichas.size());
        if (!fichas.isEmpty()) {
            int i;
            Fichas ficha;
            for (i = 0; i < fichas.size(); i++) {
                ficha = fichas.get(i);
                if (ficha instanceof fichaNormal) {
                    if (((fichaNormal) ficha).getTipo().equals(tipo)) {
                        fichas.remove(i);
                        return ficha;
                    }
                } else if (ficha instanceof fichaPesada) {
                    if (((fichaPesada) ficha).getTipo().equals(tipo)) {
                        fichas.remove(i);
                        return ficha;
                    }
                } else if (ficha instanceof fichaTemporal){
                    if (((fichaTemporal) ficha).getTipo().equals(tipo)) {
                        fichas.remove(i);
                        return ficha;
                    }
                }
            }
        } else {
            // Manejar el caso cuando el tamaño llega a cero
            throw new IllegalStateException("Tamaño de fichas agotado.");
        }
        return null;
    }

    public Fichas elegirTipoFicha(String tipo) {
        return getFicha(tipo);
    }

    public void addTiempo(int tiempo) {
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

    public int fichasNormales() {
        int cont = 0;
        for (Fichas ficha : fichas) {
            if (ficha instanceof fichaNormal) {
                cont += 1;
            }
        }
        return cont;
    }

    public int fichasPesadas() {
        int cont = 0;
        for (Fichas ficha : fichas) {
            if (ficha instanceof fichaPesada) {
                cont += 1;
            }
        }
        return cont;
    }

    public int fichasTemporales() {
        int cont = 0;
        for (Fichas ficha : fichas) {
            if (ficha instanceof fichaTemporal) {
                cont += 1;
            }
        }
        return cont;
    }

    public boolean siHayFichas(String tipoFicha) {
        if (!fichas.isEmpty()) {
            Fichas ficha;
            for (int i = 0; i < fichas.size(); i++) {
                ficha = fichas.get(i);
                if (ficha.getTipo().equals(tipoFicha)) {
                    return true;
                }
            }
        }
        return false;
    }
}
