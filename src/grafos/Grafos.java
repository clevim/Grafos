/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 *
 * @author italo
 */
public class Grafos {

    public static void main(String[] args) {

        /* Nome do Arquivo Txt da Matrix */
        String txt = "grafo.txt";

        /* Instanciando a classe */
        Grafos grafos = new Grafos();

        /* Retorno Das Funções do Programa */
        int numeroVertices = grafos.numeroVertices(txt);
        int numeroArestas = grafos.numeroArestas(txt);
        int matrizAdjacencia[][] = grafos.matrizAdjacencia(txt, numeroVertices);
        String matrizAdjacenciaString = "";

        /* Exibindo Número de Vértices */
        System.out.println("Número de Vertices: " + numeroVertices);
        System.out.println("\n");

        /* Exibindo Número de Arestas */
        System.out.println("Número de Arestas: " + numeroArestas);
        System.out.println("\n");

        /* Exibindo MatrizAdjacencia */
        System.out.println("Matriz de Adjacência:");
        for (int i = 1; i <= numeroVertices; i++) {
            for (int j = 1; j <= numeroVertices; j++) {
                matrizAdjacenciaString += " " + matrizAdjacencia[i][j] + " ";
            }
            matrizAdjacenciaString += ("\n");
        }
        System.out.println(matrizAdjacenciaString);
        System.out.println("\n");

        /* Exibindo O Grau de Cada Vértice */
        grauDosVertices(numeroVertices, matrizAdjacencia);

        /* Exibindo A Lista de Adjacencia */
        grafos.listaAdjacencia(numeroVertices, matrizAdjacencia);

    }

    public int numeroVertices(String txt) {

        int numeroVertices = 0;
        String[] caracteres = null;
        String lerLinha;

        try {

            FileInputStream arquivo = new FileInputStream(txt);
            BufferedReader d = new BufferedReader(new InputStreamReader(arquivo));

            do {

                lerLinha = d.readLine();

                if (lerLinha != null) {
                    caracteres = lerLinha.split(">");
                    for (int i = 0; i < 2; i++) {
                        int verticeAtual = Integer.parseInt(caracteres[i]);
                        if (verticeAtual > numeroVertices) {
                            numeroVertices = verticeAtual;
                        }

                    }
                }

            } while (lerLinha != null);
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
        return numeroVertices;
    }

    public int numeroArestas(String txt) {

        int numeroArestas = 0;
        String[] caracteres = null;
        String lerLinha;

        try {

            FileInputStream arquivo = new FileInputStream(txt);
            BufferedReader d = new BufferedReader(new InputStreamReader(arquivo));

            do {

                lerLinha = d.readLine();

                if (lerLinha != null) {
                    numeroArestas++;
                }

            } while (lerLinha != null);
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
        return numeroArestas;
    }

    public int[][] matrizAdjacencia(String txt, int vertices) {

        String[] caracteres = null;
        int[][] m = new int[vertices + 1][vertices + 1];
        String Lerlinha;

        try {

            FileInputStream arquivo = new FileInputStream(txt);
            BufferedReader d = new BufferedReader(new InputStreamReader(arquivo));

            do {

                Lerlinha = d.readLine();

                if (Lerlinha != null) {

                    caracteres = Lerlinha.split(">");

                    int linha = Integer.parseInt(caracteres[0]);
                    int coluna = Integer.parseInt(caracteres[1]);
                    m[linha][coluna] = 1;
                    m[coluna][linha] = 1;

                }

            } while (Lerlinha != null);
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }

        return m;
    }

    public static void grauDosVertices(int numeroVertices, int[][] matrizAdjacencia) {

        String grauGeral = "";
        int grauIndividual = 0;

        for (int i = 1; i <= numeroVertices; i++) {
            for (int j = 1; j <= numeroVertices; j++) {
                grauIndividual = grauIndividual + matrizAdjacencia[i][j];
            }
            grauGeral += "O Grau do Vertice " + i + " é " + grauIndividual + ".\n";
            grauIndividual = 0;

        }

        System.out.println(grauGeral);

    }

    public static void listaAdjacencia(int numeroVertices, int[][] matrizAdjacencia) {

        String listaAdjacencia = "";
        String verticeAdjacente = "";

        for (int i = 1; i <= numeroVertices; i++) {
            for (int j = 1; j <= numeroVertices; j++) {
                if (matrizAdjacencia[i][j] != 0) {
                    verticeAdjacente = verticeAdjacente + j + " -> ";
                }
            }
            listaAdjacencia += ("Lista de Adjacencia do Vértice " + i + " = " + verticeAdjacente + "\r\n");
            verticeAdjacente = "";
        }

        System.out.println(listaAdjacencia);

    }

}
