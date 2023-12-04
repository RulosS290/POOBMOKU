package domain;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Jugador {
    private int puntuacion;
    private Color color;
    private String nombre;
    private int modoJuego;
    private int Tamano;
    private int size;
    private ArrayList<Fichas> fichas = new ArrayList<>();

    public Jugador(String name, String color, int mode, int tamano) {
        nombre = name;
        puntuacion = 0;
        modoJuego = mode;
        Tamano = (tamano * tamano) / 2;
        size = tamano;
        setColor(color);
        addFichas();
    }

    private void setColor(String color) {
        switch (color) {
            case "Rojo":
                this.color = Color.RED;
                break;
            case "Azul":
                this.color = Color.BLUE;
                break;
            case "Verde":
                this.color = Color.GREEN;
                break;
            case "Amarillo":
                this.color = Color.YELLOW;
                break;
            case "Naranja":
                this.color = Color.ORANGE;
                break;
            case "Rosado":
                this.color = Color.PINK;
                break;
            case "Magenta":
                this.color = Color.MAGENTA;
                break;
            default:
                this.color = Color.BLACK;
                break;
        }
    }



    public Color getColor() {
        return color;
    }

    private void addFichas() {
        int minimo = 1;
        int maximo = 3;
        int numeroAleatorio;
        Random random = new Random();
        Fichas nuevaFicha = null;
        if (modoJuego == 0) {
            if (size == 10) {
                for (int i = 0; i < 50; i++) {
                    numeroAleatorio = random.nextInt(maximo - minimo + 1) + minimo;
                    if (numeroAleatorio == 1) {
                        nuevaFicha = new fichaNormal(this, color);
                    } else if (numeroAleatorio == 2) {
                        nuevaFicha = new fichaPesada(this, color);
                    } else {
                        nuevaFicha = new fichaTemporal(this, color);
                    }
                    fichas.add(nuevaFicha);
                }
            } else if (size == 15) {
                for (int i = 0; i < 113; i++) {
                    numeroAleatorio = random.nextInt(maximo - minimo + 1) + minimo;
                    if (numeroAleatorio == 1) {
                        nuevaFicha = new fichaNormal(this, color);
                    } else if (numeroAleatorio == 2) {
                        nuevaFicha = new fichaPesada(this, color);
                    } else {
                        nuevaFicha = new fichaTemporal(this, color);
                    }
                    fichas.add(nuevaFicha);
                }
            } else {
                for (int i = 0; i < 200; i++) {
                    numeroAleatorio = random.nextInt(maximo - minimo + 1) + minimo;
                    if (numeroAleatorio == 1) {
                        nuevaFicha = new fichaNormal(this, color);
                    } else if (numeroAleatorio == 2) {
                        nuevaFicha = new fichaPesada(this, color);
                    } else {
                        nuevaFicha = new fichaTemporal(this, color);
                    }
                    fichas.add(nuevaFicha);
                }
            }

        }
    }

    public void reset(){
        fichas.clear();
        addFichas();
    }

    public int getTamano() {
        return Tamano;
    }


    public Fichas getFicha() {
        if (Tamano > 0) {
            Tamano--;
            return fichas.get(Tamano);
        } else {
            // Manejar el caso cuando el tamaño llega a cero
            throw new IllegalStateException("Tamaño de fichas agotado.");
        }
    }

    public String getName() {
        return nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}