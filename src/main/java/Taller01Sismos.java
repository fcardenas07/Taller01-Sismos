import java.util.Arrays;
import java.util.Scanner;

public class Taller01Sismos {
    public static void main(String[] args) {
        double[][] lecturasSismos = new double[7][10];
        mostrarMenu(lecturasSismos);
    }

    private static void mostrarMenu(double[][] lecturasSismos) {
        int opcion;
        do {
            System.out.print(menu());
            opcion = ingresarNumero();
            switch (opcion) {
                case 1 -> lecturasSismos = llenarArreglo();
                case 2 -> mostrarSismoMayorMagnitud(lecturasSismos);
                case 3 -> mostrarSismosMagnitudMayorA5(lecturasSismos);
                case 4 -> enviarSMS(lecturasSismos);
                case 5 -> mostrarLecturas(lecturasSismos);
            }

        } while (opcion != 6);
    }

    private static void enviarSMS(double[][] lecturasSismos) {
        int sismosMayoresA7 = contarSismosMayoresA(lecturasSismos, 7);
        for (int i = 0; i < sismosMayoresA7; i++) {
            System.out.println(mensajeSMS());
        }
    }

    private static void mostrarSismosMagnitudMayorA5(double[][] lecturasSismos) {
        if (noHayLecturas(lecturasSismos)) {
            System.out.println("No hay lecturas");
            return;
        }
        System.out.println("La cantidad de sismos con magnitud mayor a 5.0 fue de: " + contarSismosMayoresA(lecturasSismos, 5));

    }

    private static boolean noHayLecturas(double[][] lecturasSismos) {
        return Arrays.deepEquals(lecturasSismos, new double[7][10]);
    }

    private static int contarSismosMayoresA(double[][] lecturasSismos, int magnitud) {
        int contadorSismos = 0;
        for (double[] dia : lecturasSismos) {
            for (double lectura : dia) {
                if (lectura >= magnitud) {
                    contadorSismos++;
                }
            }
        }
        return contadorSismos;
    }

    private static void mostrarSismoMayorMagnitud(double[][] lecturasSismos) {
        if (noHayLecturas(lecturasSismos)) {
            System.out.println("No hay lecturas");
            return;
        }
        System.out.println("El sismo de mayor intensidad tuvo una lectura de: " + buscarSismoMayorMagnitud(lecturasSismos));
    }

    private static double buscarSismoMayorMagnitud(double[][] lecturasSismos) {
        double maximo = 0.0;
        for (double[] dia : lecturasSismos) {
            for (double lectura : dia) {
                if (lectura > maximo) {
                    maximo = lectura;
                }
            }
        }
        return maximo;
    }

    private static int ingresarNumero() {
        Scanner teclado = new Scanner(System.in);
        try {
            return validarOpcion(teclado.nextInt());
        } catch (Exception e) {
            System.out.println("Debe ingresar un numero");
            return ingresarNumero();
        }
    }

    private static int validarOpcion(int numero) {
        if (numero < 1 || numero > 6) {
            System.out.println("Ingrese una opcion valida");
            return ingresarNumero();
        }
        return numero;
    }

    private static void mostrarLecturas(double[][] lecturasSismos) {
        for (double[] dia : lecturasSismos) {
            System.out.println(Arrays.toString(dia));
        }
    }

    private static double[][] llenarArreglo() {
        double[][] lecturasSismos = new double[7][10];
        for (int i = 0; i < lecturasSismos.length; i++) {
            for (int j = 0; j < lecturasSismos[i].length; j++) {
                lecturasSismos[i][j] = generarNumeroAleatorio();
            }
        }
        return lecturasSismos;
    }

    private static double generarNumeroAleatorio() {
        double numeroAleatorio = Math.random() * 9.9;
        return Double.parseDouble(String.format("%.1f", numeroAleatorio).replace(",", "."));
    }


    private static String mensajeSMS() {
        return "Alerta!!! Se debe evacuar zona costera!";
    }
    private static String menu() {
        return """
                MENU SISMOS
                1. Ingresar datos
                2. Mostrar sismo de mayor magnitud
                3. Contar sismos mayores o iguales a 5.0
                4. Enviar SMS por cada sismo mayor o igual a 7.0
                5. Mostrar Lecturas
                6. Salir
                -> Ingrese una opcion:
                """;
    }
}
