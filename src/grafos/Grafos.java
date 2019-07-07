/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
        int matrizIncidencia[][] = grafos.matrizIncidencia(txt, numeroVertices, numeroArestas);
        int tipoGrafo = grafos.simplesPseudoMulti(numeroVertices, numeroArestas, matrizAdjacencia);
        int completoIncompleto = grafos.completoIncompleto(tipoGrafo, numeroVertices, matrizAdjacencia);
        int conexoDesconexo = grafos.conexoDesconeco(numeroVertices, matrizAdjacencia);

        String matrizAdjacenciaString = "";
        String matrizIncidenciaString = "";


        /* Exibindo Ordem e Tamanho */
        Grafos.ordemTamanhoGrafo(numeroVertices, numeroArestas);
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
        Grafos.listaAdjacencia(numeroVertices, matrizAdjacencia);

        /* Exibindo A Matriz de Indêcnia */
        System.out.println("Matriz de Incidência:");
        for (int i = 1; i <= numeroArestas; i++) {
            for (int j = 1; j <= numeroVertices; j++) {
                matrizIncidenciaString += " " + matrizIncidencia[i][j] + " ";
            }
            matrizIncidenciaString += ("\n");
        }
        System.out.println(matrizIncidenciaString);
        System.out.println("\n");

        /* Exibindo Se O Grafo é Simples, Pseudografo ou Multigrafo */
        grafos.simplesPseudoMulti(numeroVertices, numeroArestas, matrizAdjacencia);

        /* Exibindo O Tipo Do Grafo */
        switch (tipoGrafo) {
            case 0:
                System.out.println("O Grafo é Simples!");
                break;
            case 1:
                System.out.println("O Grafo é Pseudografo, possui laço!");
                break;
        }

        /* Exibindo Se O Grafo é Completo ou Incompleto */
        switch (completoIncompleto) {
            case 0:
                System.out.println("O Grafo é Incompleto!");
                break;
            case 1:
                System.out.println("O Grafo é Completo!");
                break;
        }

        /* Exibi Se O Grafo é Conexo ou Desconexo */
        switch (conexoDesconexo) {
            case 0:
                System.out.println("O Grafo é Desconexo!");
                break;
            case 1:
                System.out.println("O Grafo é Conexo!");
                break;
        }

        /* Exibi Se O Grafo Possui Ciclo ou Não */
        Grafos.ciclo(conexoDesconexo, tipoGrafo, numeroVertices, matrizAdjacencia);

    }

    public static void ordemTamanhoGrafo(int numeroVertices, int numeroArestas) {

        System.out.println("A Ordem do Grafo é: " + numeroVertices);
        System.out.println("O Tamanho do Grafo é: " + numeroArestas);

    }

    public int numeroVertices(String txt) {

        int numeroVertices = 0;
        String[] caracteres;
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
        } catch (IOException | NumberFormatException e) {
            System.out.println("ERROR: " + e);
        }
        return numeroVertices;
    }

    public int numeroArestas(String txt) {

        int numeroArestas = 0;
        String[] caracteres;
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
        } catch (IOException e) {
            System.out.println("ERROR: " + e);
        }
        return numeroArestas;
    }

    public int[][] matrizAdjacencia(String txt, int numeroVertices) {

        String[] caracteres;
        int[][] m = new int[numeroVertices + 1][numeroVertices + 1];
        String lerLinha;

        try {

            FileInputStream arquivo = new FileInputStream(txt);
            BufferedReader d = new BufferedReader(new InputStreamReader(arquivo));

            do {

                lerLinha = d.readLine();

                if (lerLinha != null) {

                    caracteres = lerLinha.split(">");

                    int linha = Integer.parseInt(caracteres[0]);
                    int coluna = Integer.parseInt(caracteres[1]);
                    m[linha][coluna] = 1;
                    m[coluna][linha] = 1;

                }

            } while (lerLinha != null);
        } catch (IOException | NumberFormatException e) {
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

    public int[][] matrizIncidencia(String txt, int vertices, int arestas) {

        int linhaMatriz = 1;
        int[][] matrizIncidencia = new int[arestas + 1][vertices + 1];
        String lerLinha;
        String[] caracteres;

        try {

            FileInputStream arquivo = new FileInputStream(txt);
            BufferedReader d = new BufferedReader(new InputStreamReader(arquivo));

            do {

                lerLinha = d.readLine();

                if (lerLinha != null) {

                    caracteres = lerLinha.split(">");

                    int tempUm = Integer.parseInt(caracteres[0]);
                    int tempDois = Integer.parseInt(caracteres[1]);

                    matrizIncidencia[linhaMatriz][tempUm] = 1;
                    matrizIncidencia[linhaMatriz][tempDois] = 1;

                }

                linhaMatriz++;

            } while (lerLinha != null);
        } catch (IOException | NumberFormatException e) {
            System.out.println("ERROR: " + e);
        }

        return matrizIncidencia;

    }

    //Faltando MultiGrafo
    public int simplesPseudoMulti(int numeroVertices, int numeroArestas, int[][] matrizAdjacencia) {

        int diagonalDaMatriz = 0;
        int tipoGrafo = 0;

        for (int i = 1; i <= numeroVertices; i++) {
            diagonalDaMatriz += matrizAdjacencia[i][i];
        }

        if (diagonalDaMatriz == 0) {
            return 0;
        }

        if (diagonalDaMatriz > 0) {
            return 1;
        }

        return tipoGrafo;
    }

    public int completoIncompleto(int tipoGrafo, int numeroVertices, int[][] matrizAdjacencia) {

        int completoIncompleto = 0;

        if (tipoGrafo != 0) {
            completoIncompleto = 0;
        } else {
            completoIncompleto = 1;

        }

        for (int i = 1; i <= numeroVertices; i++) {
            for (int j = 1; j <= numeroVertices; j++) {
                if (matrizAdjacencia[i][j] == 0 && i != j) {
                    completoIncompleto = 0;

                }
            }
        }

        return completoIncompleto;

    }

    public int conexoDesconeco(int numeroVertices, int[][] matrizAdjacencia) {

        int conexoDesconexo = 0;
        int cont = 0;

        conexoDesconexo = 1;

        for (int i = 1; i <= numeroVertices; i++) {
            matrizAdjacencia[i][i] = 0;
        }

        for (int i = 1; i <= numeroVertices; i++) {
            for (int j = 1; j <= numeroVertices; j++) {
                if (matrizAdjacencia[i][j] == 1) {
                    cont++;
                }
            }
        }

        if ((cont / 2) < (numeroVertices - 1)) {
            conexoDesconexo = 0;

        }

        return conexoDesconexo;

    }

    public static void ciclo(int conexoDesconexo, int tipoGrafo, int numeroVertices, int[][] matrizAdjacencia) {

        if (conexoDesconexo != 1 || tipoGrafo != 0) {
            System.out.println("O Grafo Não Possui Ciclo");
        } else {
            System.out.println("O Grafo Possui Ciclo");
        }

    }

}
