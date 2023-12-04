package domain;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Jugador {
    private int puntuacion;
    private String color;
    private String nombre;
    private int Tamano;
    private ArrayList<Fichas> fichas = new ArrayList<>();

    public Jugador(String name, String color) {
        nombre = name;
        puntuacion = 0;
        this.color = color;
    }


    public String getColor() {
        return color;
    }

    private void addFichas(int totalFichas) {
        int minimo = 1;
        int maximo = 3;
        int numeroAleatorio;
        Random random = new Random();
        Fichas nuevaFicha = null;
        for (int i = 0; i < totalFichas; i++) {
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




        public void reset(){
            fichas.clear();
            //addFichas();
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
