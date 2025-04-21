package teatromorosemana5;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class TeatroMoroSemana5 {

    Timer Timer = new Timer();
    static Timer timerReserva = null;


    public static void mostrarPlanoAsientos(int filas, int asientosPorFila, boolean[]... sector) {
    for (int f = 0; f < filas; f++) {
        System.out.print("Fila " + (f + 1) + ": ");
        for (int a = 0; a < asientosPorFila; a++) {
            if (sector[f][a]) {
                System.out.print("[X]");
            } else {
                System.out.print("[O]");
            }
        }
        System.out.println();
    }
}
    
    public static boolean seleccionarAsiento(Scanner sc, int filas, int asientosPorFila, boolean[]... sector) {
    System.out.print("\nSeleccione una fila (1-" + filas + ") o 0 para salir: ");
    int filaSeleccionada = sc.nextInt();

    if (filaSeleccionada == 0) {
        return false;
    } else if (filaSeleccionada < 1 || filaSeleccionada > filas) {
        System.out.println("Fila inválida.");
        return false;
    }

    System.out.print("Seleccione un asiento (1-" + asientosPorFila + "): ");
    int asientoSeleccionado = sc.nextInt();

    if (asientoSeleccionado < 1 || asientoSeleccionado > asientosPorFila) {
        System.out.println("Asiento inválido.");
        return false;
    }

    boolean[] fila = sector[filaSeleccionada - 1];
    if (fila[asientoSeleccionado - 1]) {
        System.out.println("Ese asiento ya está ocupado.");
        return false;
    } else {
        fila[asientoSeleccionado - 1] = true;
        System.out.println("Reserva exitosa: Fila " + filaSeleccionada + " – Asiento " + asientoSeleccionado);
        return true;
    }
}

                
    public static boolean seleccionarReserva(Scanner sc, int filas, int asientosPorFila, boolean[]... sector) {
        System.out.print("\nSeleccione una fila (1-" + filas + ") o 0 para salir: ");
        int filaSeleccionada = sc.nextInt();

        if (filaSeleccionada == 0) {
            return false;
        } else if (filaSeleccionada < 1 || filaSeleccionada > filas) {
            System.out.println("Fila inválida.");
            return false;
        }

        System.out.print("Seleccione un asiento (1-" + asientosPorFila + "): ");
        int asientoSeleccionado = sc.nextInt();

        if (asientoSeleccionado < 1 || asientoSeleccionado > asientosPorFila) {
            System.out.println("Asiento inválido.");
            return false;
        }

        boolean[] fila = sector[filaSeleccionada - 1];

        if (fila[asientoSeleccionado - 1]) {
            System.out.println("Ese asiento ya está ocupado.");
            return false;
        } else {
            fila[asientoSeleccionado - 1] = true;
            System.out.println("Reserva exitosa: Fila " + filaSeleccionada + " – Asiento " + asientoSeleccionado);

            timerReserva = new Timer();

            final int filaTemporal = filaSeleccionada;
            final int asientoTemporal = asientoSeleccionado;

            TimerTask tareadeCancelacion = new TimerTask() {
                @Override

                public void run() {

                int reservaFila = filaSeleccionada;
                int reservaAsiento = asientoSeleccionado;
                boolean reservaPendiente = true;
                
                    fila[asientoTemporal - 1] = false;
                    System.out.println("!! La Reserva de Fila " + filaTemporal + " Asiento :" + asientoTemporal + " fue Cancelada por tiempo de espera");
                    reservaPendiente = false;
                    reservaFila = -1;
                    reservaAsiento = 1;
                    timerReserva.cancel();

                }
            };
            timerReserva.schedule(tareadeCancelacion, 600000);
        }
        return true;
    }
    
    public static double calcularDescuento(double precioBase, int promocion) {
        switch (promocion) {
            case 1 -> {
                System.out.println("Se ha escogido la tarifa dirigida a estudiantes. Se aplicará un descuento del 10%.");
                return precioBase * 0.9;
            }
            case 2 -> {
                System.out.println("Se ha escogido la tarifa dirigida a la tercera edad. Se aplicará un descuento del 15%.");
                return precioBase * 0.85;
            }
            case 0 -> {
                System.out.println("Sin descuento. Se aplicará la tarifa de Público General.");
                return precioBase;
            }
            default -> {
                System.out.println("Por favor, ingrese un número válido.");
                return precioBase;
            }
        }
    }    
    
    public static void main(String[] args) { 
        
        String saltoDeLinea = System.lineSeparator();
        Scanner scanner = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);

        int entradasSolicitadas;
        int entradasConfirmadas = 0;
        byte entradaElegida;
        String tipoEntradaElegida = "";
        String tarifaEntradaElegida = "";
        double valorEntradaElegida = 0;
        double totalValorEntradaElegida = 0;
        
        int seguirComprando = 1;
        int opcionMenuPrincipal;
        int opcionMenuReserva;
        int promocion = 0;
        String reduccionElegida = "";
        
        boolean eleccion1 = false;       
        
        final double precioVip = 30000;
        final double precioPlateaBaja = 15000;
        final double precioPlateaAlta = 18000;
        final double precioPalco = 13000;
        
        int filasVip = 3;
        int asientosVip = 4;
        int filasPlateaBaja = 3;
        int asientosPlateaBaja = 8;
        int filasPlateaAlta = 3;
        int asientosPlateaAlta = 12;
        int columnasPalco = 4;
        int asientosPalco = 8;
        
        
        boolean[] filaVip1 = new boolean [asientosVip];
        boolean[] filaVip2 = new boolean [asientosVip];    
        boolean[] filaVip3 = new boolean [asientosVip]; 
        boolean[] filaPlateaBaja1 = new boolean [asientosPlateaBaja];
        boolean[] filaPlateaBaja2 = new boolean [asientosPlateaBaja];
        boolean[] filaPlateaBaja3 = new boolean [asientosPlateaBaja];
        boolean[] filaPlateaAlta1 = new boolean [asientosPlateaAlta];
        boolean[] filaPlateaAlta2 = new boolean [asientosPlateaAlta];
        boolean[] filaPlateaAlta3 = new boolean [asientosPlateaAlta];
        boolean[] columnapalco1 = new boolean [asientosPalco];
        boolean[] columnapalco2 = new boolean [asientosPalco];        
        boolean[] columnapalco3 = new boolean [asientosPalco];
        boolean[] columnapalco4 = new boolean [asientosPalco];
        
        boolean[] filaActual = null; 
        int filaSeleccionada1 = -1;
        boolean[] filaSeleccionada2 = null;
        int asientoSel = 0;
        
        int j = 0;
        String[] carrito = new String[99];
        String[] historialDescuento = new String[99];
        int[] cantidadesPorEntrada = new int[99];    
        double[] historialPrecio = new double[99];
        double montoPago;
        double totalCompras = 0;
        double vuelto;
        int menuCarrito;
        
        
        for (int indice = 0; indice < 10; indice++) {
        }

        
        

        System.out.println("Hola. Bienvenido/a a la boleteria virtual del teatro Moro.");
        for (int i = 0; ; i++) {
            
            System.out.println(saltoDeLinea+"Menu Principal:"); 
            System.out.println("1. Comprar entrada");
            System.out.println("2. Reservar entrada");             
            System.out.println("3. Ver Promociones"); 
            System.out.println("4. Ver asientos disponibles");
            System.out.println("5. Ver carrito");   
            System.out.println("6. Salir");
            System.out.print(saltoDeLinea+ "Seleccione una opcion (Escriba 1, 2, 3, 4, 5, o 6): ");
            
            opcionMenuPrincipal = scanner.nextInt();

            switch (opcionMenuPrincipal) {
                case 1 -> {
                    seguirComprando = 1;
                    while (seguirComprando == 1) {
                    System.out.println("Has seleccionado 'Comprar entrada'.");
                    System.out.println("Por favor, escribe el numero indicado para escoger el tipo de entrada que deseas. (1, 2, 3 o 4.)" + saltoDeLinea + saltoDeLinea+ "=========TIPOS DE ENTRADAS========="  + saltoDeLinea + saltoDeLinea + "1- VIP =================== $" + precioVip + saltoDeLinea + "2- Platea baja =========== $" + precioPlateaBaja + saltoDeLinea + "3- Platea alta =========== $"+precioPlateaAlta + saltoDeLinea + "4- Palco ================= $" + precioPalco);
                    
                        do {
                            entradaElegida = scanner.nextByte();

                            switch (entradaElegida) {
                                case 1 -> {
                                    entradasConfirmadas=0;
                                    System.out.println("Has seleccionado una entrada para ingresar al sector VIP." + saltoDeLinea + "valor: $" + precioVip + saltoDeLinea + saltoDeLinea +  "Cuantas entradas de tipo VIP desea comprar?");                                
                                    entradasSolicitadas = scanner.nextInt();                                
                                    asientoSel = 0;
                                    
                                    System.out.println("(Plano de la sala (X = ocupado, O = libre)");
                                    mostrarPlanoAsientos(filasVip, asientosVip, filaVip1, filaVip2, filaVip3);

                                        while (entradasConfirmadas < entradasSolicitadas) {

                                            while (!seleccionarAsiento(scanner, filasVip, asientosVip, filaVip1, filaVip2, filaVip3)) {
                                            }

                                            mostrarPlanoAsientos(filasVip, asientosVip, filaVip1, filaVip2, filaVip3);
                                            valorEntradaElegida =  precioVip;
                                            tipoEntradaElegida = "Sector VIP";

                                            System.out.println("Desea comprar esta entrada con un descuento de promocion?" + saltoDeLinea+ "(Escriba 1 para Estudiantes, 2 para Tercera edad. 0 Para no aplicar promocion.)");
                                            promocion = scanner.nextInt();
                                            totalValorEntradaElegida = calcularDescuento(precioVip, promocion);

                                            carrito[j] = tipoEntradaElegida;
                                            cantidadesPorEntrada[j] = entradasSolicitadas;
                                            historialPrecio[j] = totalValorEntradaElegida;
                                            historialDescuento[j] = tarifaEntradaElegida;
                                            totalCompras += totalValorEntradaElegida;
                                            j= j+1;
                                            entradasConfirmadas++;

                                        }
                                            System.out.println("Desea seguir comprando entradas? (1 = Si, 2 = No, volver al menu principal)");
                                            seguirComprando = scanner.nextInt();
                                }

                                case 2 -> {
                                    entradasConfirmadas=0;
                                    System.out.println("Has seleccionado una entrada para ingresar al sector PlateaBaja." + saltoDeLinea + "valor: $" + precioPlateaBaja + saltoDeLinea + saltoDeLinea +  "Cuantas entradas de tipo PlateaBaja desea comprar?");                                
                                    entradasSolicitadas = scanner.nextInt();                                
                                    asientoSel = 0;
                                    
                                    System.out.println("(Plano de la sala (X = ocupado, O = libre)");
                                    mostrarPlanoAsientos(filasPlateaBaja, asientosPlateaBaja, filaPlateaBaja1, filaPlateaBaja2, filaPlateaBaja3);

                                        while (entradasConfirmadas < entradasSolicitadas) {

                                            while (!seleccionarAsiento(scanner, filasPlateaBaja, asientosPlateaBaja, filaPlateaBaja1, filaPlateaBaja2, filaPlateaBaja3)) {
                                            }

                                            mostrarPlanoAsientos(filasPlateaBaja, asientosPlateaBaja, filaPlateaBaja1, filaPlateaBaja2, filaPlateaBaja3);
                                            valorEntradaElegida =  precioPlateaBaja;
                                            tipoEntradaElegida = "Sector PlateaBaja";

                                            System.out.println("Desea comprar esta entrada con un descuento de promocion?" + saltoDeLinea+ "(Escriba 1 para Estudiantes, 2 para Tercera edad. 0 Para no aplicar promocion.)");
                                            promocion = scanner.nextInt();
                                            totalValorEntradaElegida = calcularDescuento(precioPlateaBaja, promocion);

                                            carrito[j] = tipoEntradaElegida;
                                            cantidadesPorEntrada[j] = entradasSolicitadas;
                                            historialPrecio[j] = totalValorEntradaElegida;
                                            historialDescuento[j] = tarifaEntradaElegida;
                                            totalCompras += totalValorEntradaElegida;
                                            j= j+1;
                                            entradasConfirmadas++;

                                        }
                                            System.out.println("Desea seguir comprando entradas? (1 = Si, 2 = No, volver al menu principal)");
                                            seguirComprando = scanner.nextInt();
                                }
                                case 3 -> {
                                    entradasConfirmadas=0;
                                    System.out.println("Has seleccionado una entrada para ingresar al sector PlateaAlta." + saltoDeLinea + "valor: $" + precioPlateaAlta + saltoDeLinea + saltoDeLinea +  "Cuantas entradas de tipo PlateaAlta desea comprar?");                                
                                    entradasSolicitadas = scanner.nextInt();                                
                                    asientoSel = 0;
                                    
                                    System.out.println("(Plano de la sala (X = ocupado, O = libre)");
                                    mostrarPlanoAsientos(filasPlateaAlta, asientosPlateaAlta, filaPlateaAlta1, filaPlateaAlta2, filaPlateaAlta3);

                                        while (entradasConfirmadas < entradasSolicitadas) {

                                            while (!seleccionarAsiento(scanner, filasPlateaAlta, asientosPlateaAlta, filaPlateaAlta1, filaPlateaAlta2, filaPlateaAlta3)) {
                                            }

                                            mostrarPlanoAsientos(filasPlateaAlta, asientosPlateaAlta, filaPlateaAlta1, filaPlateaAlta2, filaPlateaAlta3);
                                            valorEntradaElegida =  precioPlateaAlta;
                                            tipoEntradaElegida = "Sector PlateaBaja";

                                            System.out.println("Desea comprar esta entrada con un descuento de promocion?" + saltoDeLinea+ "(Escriba 1 para Estudiantes, 2 para Tercera edad. 0 Para no aplicar promocion.)");
                                            promocion = scanner.nextInt();
                                            totalValorEntradaElegida = calcularDescuento(precioPlateaAlta, promocion);

                                            carrito[j] = tipoEntradaElegida;
                                            cantidadesPorEntrada[j] = entradasSolicitadas;
                                            historialPrecio[j] = totalValorEntradaElegida;
                                            historialDescuento[j] = tarifaEntradaElegida;
                                            totalCompras += totalValorEntradaElegida;
                                            j= j+1;
                                            entradasConfirmadas++;

                                        }
                                            System.out.println("Desea seguir comprando entradas? (1 = Si, 2 = No, volver al menu principal)");
                                            seguirComprando = scanner.nextInt();
                                }

                                case 4 -> {
                                    entradasConfirmadas=0;                                
                                    System.out.println("Desea comprar esta entrada con un descuento de promocion?" + saltoDeLinea+ "(Escriba 1 para Estudiantes, 2 para Tercera edad. 0 Para no aplicar promocion.)");
                                    entradasSolicitadas = scanner.nextInt();                                
                                    asientoSel = 0;
                                    System.out.println("(Plano de la sala (X = ocupado, O = libre)");

                                    for (int f = 1; f <= columnasPalco; f++) {

                                        switch (f) {
                                            case 1 -> filaActual = columnapalco1;
                                            case 2 -> filaActual = columnapalco2;
                                            case 3 -> filaActual = columnapalco3;
                                            case 4 -> filaActual = columnapalco4;
                                            default -> {
                                            }
                                        }

                                    System.out.print("Fila " + f + ": ");    

                                        for (int a = 0; a < asientosPalco; a++) {
                                            if (filaActual[a]) System.out.print("[X]");
                                            else System.out.print("[O]");
                                        }                                
                                        System.out.println();
                                    }

                                    while (entradasConfirmadas < entradasSolicitadas) {


                                        System.out.print("\nSeleccione una fila (1-" + columnasPalco + ") o 0 para salir: ");


                                        if (sc.hasNextInt()) {
                                            filaSeleccionada1 = sc.nextInt(); 
                                        } else {
                                            sc.next();
                                            System.out.println("Por favor, ingrese un número válido.");
                                          continue;
                                        }

                                        if (filaSeleccionada1 == 0) {
                                          break;
                                        }   


                                        if (filaSeleccionada1 < 1 || filaSeleccionada1 > columnasPalco) {
                                            System.out.println("Fila inválida.");
                                          continue;
                                        }

                                        System.out.print("Seleccione un asiento (1-" + asientosPalco + "): ");

                                        asientoSel = sc.nextInt();

                                        if (asientoSel < 1 || asientoSel > asientosPalco) {
                                            System.out.println("Asiento inválido.");
                                          continue;
                                        }                                


                                        switch (filaSeleccionada1) {
                                            case 1 -> filaSeleccionada2 = columnapalco1;
                                            case 2 -> filaSeleccionada2 = columnapalco2;
                                            case 3 -> filaSeleccionada2 = columnapalco3;
                                            default -> {
                                            }
                                        }

                                        if (filaSeleccionada2[asientoSel - 1]) {
                                          System.out.println("Ese asiento ya está ocupado.");
                                        continue;
                                        } else {
                                          filaSeleccionada2[asientoSel - 1] = true;
                                          System.out.println("Reserva exitosa: Fila " + filaSeleccionada1 + " – Asiento " + asientoSel);
                                        }

                                        for (int f = 1; f <= columnasPalco; f++) {
                                            switch (f) {
                                                case 1 -> filaActual = columnapalco1;
                                                case 2 -> filaActual = columnapalco2;
                                                case 3 -> filaActual = columnapalco3;
                                                case 4 -> filaActual = columnapalco4;
                                                default -> {
                                                }
                                            }      

                                        System.out.print("Fila " + f + ": ");    

                                            for (int a = 0; a < asientosPalco; a++) {
                                                if (filaActual[a]) System.out.print("[X]");
                                                else System.out.print("[O]");
                                            }                                
                                            System.out.println();
                                        }                                


                                        valorEntradaElegida =  precioPalco;
                                        tipoEntradaElegida = "Palco.";

                                        System.out.println("Desea comprar esta entrada con un descuento de promocion? (1 para Estudiantes, 2 para Tercera edad. 0 Para no aplicar promocion.)");
                                        promocion = scanner.nextInt();

                                        switch (promocion) {
                                            case 1 -> {
                                                totalValorEntradaElegida = valorEntradaElegida * 0.9;
                                                tarifaEntradaElegida = "Estudiantes";
                                                reduccionElegida = "10%";
                                                System.out.println (" Se ha escogido la tarifa dirigida a estudiantes. Se aplicara un descuento del 10%." + saltoDeLinea);
                                                break;
                                            }
                                            case 2 -> {
                                                totalValorEntradaElegida = valorEntradaElegida * 0.85;
                                                tarifaEntradaElegida = "Tercera Edad";
                                                reduccionElegida = "15%";
                                                System.out.println ("Se ha escogido la tarifa dirigida a la tercera edad. Se aplicara un descuento del 15%." + saltoDeLinea);
                                            }

                                            case 0 -> {
                                                totalValorEntradaElegida = valorEntradaElegida;
                                                System.out.println ("Sin descuento. Se aplicara la tarifa de Publico General."+ saltoDeLinea);
                                                tarifaEntradaElegida = "Publico General";
                                                reduccionElegida = "0%";
                                            }

                                            default -> {
                                                System.out.println ("Por favor ingrese un numero valido");
                                            }
                                        }
                                            carrito[j] = tipoEntradaElegida;
                                            cantidadesPorEntrada[j] = entradasSolicitadas;
                                            historialPrecio[j] = totalValorEntradaElegida;
                                            historialDescuento[j] = tarifaEntradaElegida;
                                            totalCompras += totalValorEntradaElegida;
                                            j= j+1;
                                            entradasConfirmadas++;  
                                    }   
                                            System.out.println("Desea seguir comprando entradas? (1 = Si, 2 = No, volver al menu principal)");
                                            seguirComprando = scanner.nextInt();                                       
                                }

                                default -> System.out.println("Invalido. Ingrgese un numero del 1 al 4.");
                            }
                        } while (entradaElegida < 1 || entradaElegida > 4);                    
                    }
                }
                case 2 -> {

                        System.out.println(saltoDeLinea+"===========================" +saltoDeLinea+"Has seleccionado 'Reservar entradas:'" +saltoDeLinea+ "Por favor, escribe el numero indicado para escoger el tipo de entrada que deseas. (1, 2, 3 o 4.)" + saltoDeLinea + saltoDeLinea+ "=========TIPOS DE ENTRADAS========="  + saltoDeLinea + saltoDeLinea + "1- VIP =================== $" + precioVip + saltoDeLinea + "2- Platea baja =========== $" + precioPlateaBaja + saltoDeLinea + "3- Platea alta =========== $"+precioPlateaAlta + saltoDeLinea + "4- Palco ================= $" + precioPalco);                                           
                        opcionMenuReserva = scanner.nextInt();
                        
                        switch  (opcionMenuReserva) {
                            case 1 -> {
                                    entradasConfirmadas=0;
                                    System.out.println("Has seleccionado una entrada para ingresar al sector VIP." + saltoDeLinea + "valor: $" + precioVip + saltoDeLinea + saltoDeLinea +  "Cuantas entradas de tipo VIP desea comprar?");                                
                                    entradasSolicitadas = scanner.nextInt();                                
                                    asientoSel = 0;
                                    
                                    System.out.println("(Plano de la sala (X = ocupado, O = libre)");
                                    mostrarPlanoAsientos(filasVip, asientosVip, filaVip1, filaVip2, filaVip3);

                                        while (entradasConfirmadas < entradasSolicitadas) {

                                            while (!seleccionarReserva(scanner, filasVip, asientosVip, filaVip1, filaVip2, filaVip3)) {
                                            }

                                            mostrarPlanoAsientos(filasVip, asientosVip, filaVip1, filaVip2, filaVip3);
                                            valorEntradaElegida =  precioVip;
                                            tipoEntradaElegida = "Sector VIP";

                                            System.out.println("Desea reservar esta entrada con un descuento de promocion?" + saltoDeLinea+ "(Escriba 1 para Estudiantes, 2 para Tercera edad. 0 Para no aplicar promocion.)");
                                            promocion = scanner.nextInt();
                                            totalValorEntradaElegida = calcularDescuento(precioVip, promocion); 
                                    }
                                        break;
                                }
                            case 2 -> {
                                System.out.println("Reservando entradas de Platea Baja ");
                                    while (!seleccionarAsiento(scanner, filasPlateaBaja, asientosPlateaBaja, filaPlateaBaja1, filaPlateaBaja2, filaPlateaBaja3)) {
                                        // Intentar de nuevo o salir
                                    }                                
                            }
                            case 3 -> {                             
                                System.out.println("Reservando entradas de Playea Alta ");
                                    while (!seleccionarAsiento(scanner, filasPlateaAlta, asientosPlateaAlta, filaPlateaAlta1, filaPlateaAlta2, filaPlateaAlta3)) {
                                        // Intentar de nuevo o salir
                                    }                               
                            }
                            case 4 -> {
                                System.out.println("Reservando entradas de Palcos ");

                            }
                            case 5 -> {                             
                                System.out.println("Confirmando reservas de entradas... ");
                                if (reservaPendiente == true) {
                                    System.out.println("Reserva Confirmada" + reservaFila + "Asiento :" + reservaAsiento);
                                    timerReserva.cancel();
                                    reservaPendiente = false;
                                    reservaFila = -1;
                                    reservaAsiento = 1;
                                }
                            }    
                            case 0 -> {
                                return;
                                
                            }
                    }
                }
                case 3 -> {
                    seguirComprando = 1;
                    while (seguirComprando == 1) {
                        System.out.println(saltoDeLinea+"===========================" +saltoDeLinea+ "Has seleccionado 'Ver Promociones:'"+saltoDeLinea+ saltoDeLinea+ "- Descuento del 10% dirigido a estudiantes" + saltoDeLinea+ "- Descuento del 15% dirigido a adultos mayores" + saltoDeLinea);


                        System.out.println("Escriba 2 para volver al Menu Principal)");
                        seguirComprando = scanner.nextInt();                          

                        System.out.println("Volviendo..."+ saltoDeLinea+ "==========================="+ saltoDeLinea);
                    }
                }
                case 4 -> {
                    seguirComprando = 1;
                    while (seguirComprando == 1) {
                        System.out.println( saltoDeLinea+ "                    ========= Asientos disponibles ========="+ saltoDeLinea);

                        for (int f = 1; f <= filasVip; f++) {
                            switch (f) {
                                case 1 -> filaActual = filaVip1;
                                case 2 -> filaActual = filaVip2;
                                case 3 -> filaActual = filaVip3;
                                default -> {
                                }
                        }

                    System.out.print("Fila VIP" + f + ":                        ");    

                        for (int a = 0; a < asientosVip; a++) {
                            if (filaActual[a]) System.out.print("[X]");
                            else System.out.print("[O]");
                        }                                
                        System.out.println();
                    }

                    for (int f = 1; f <= filasPlateaBaja; f++) {
                        switch (f) {
                            case 1 -> filaActual = filaPlateaBaja1;
                            case 2 -> filaActual = filaPlateaBaja2;
                            case 3 -> filaActual = filaPlateaBaja3;
                            default -> {
                            }
                        }

                    System.out.print("Fila Platea Baja" + f + ":          ");    

                        for (int a = 0; a < asientosPlateaBaja; a++) {
                            if (filaActual[a]) System.out.print("[X]");
                            else System.out.print("[O]");
                        }                                
                        System.out.println();
                    }

                    for (int f = 1; f <= filasPlateaAlta; f++) {

                        switch (f) {
                            case 1 -> filaActual = filaPlateaAlta1;
                            case 2 -> filaActual = filaPlateaAlta2;
                            case 3 -> filaActual = filaPlateaAlta3;
                            default -> {
                            }
                        }

                    System.out.print("Fila Platea Alta" + f + ":    ");    

                        for (int a = 0; a < asientosPlateaAlta; a++) {
                            if (filaActual[a]) System.out.print("[X]");
                            else System.out.print("[O]");
                        }                                
                        System.out.println();
                    }   
                    
                    System.out.println(saltoDeLinea+ "Escriba 2 para volver al Menu Principal)");
                    seguirComprando = scanner.nextInt();                          

                    System.out.println("Volviendo..."+ saltoDeLinea+ "===========================");                    
                }
            }    
 
                case 5 -> {
                    seguirComprando = 1;
                    while (seguirComprando == 1) {                    
                    
                        System.out.println( saltoDeLinea + "======Carrito de Compras======" + saltoDeLinea );

                        System.out.println("Contenido del carrito:");
                        
                        for (int k = 0; k < carrito.length; k++) {
                            if (carrito[k] != null) {
                                System.out.println("[" + (k + 1) + "] " + carrito[k] +" "+ historialDescuento[k]+" $"+ historialPrecio[k]);
                            }
                        }
                        
                        System.out.println("Escriba 1 para Pagar, o 2 Salir al Menu Principal.");
                        menuCarrito = scanner.nextInt();  
                            
                        if (menuCarrito == 2) {  
                                       
                            System.out.println("Volviendo..."+ saltoDeLinea+ "===========================");  
                        break;    
         
                            
                        } else if (menuCarrito ==1) {
                            
                            System.out.println("Total de la compra = $" + totalCompras + saltoDeLinea + "Por favor. Ingrese el monto para realizar el pago.");
                            montoPago = scanner.nextDouble();

                            if (montoPago == totalCompras) {
                                System.out.println("Muchas gracias por tu compra en Teatro Moro. Desea seguir comprando?(ingresa True para volver al Menu Principal, o False para salir)");
                                eleccion1 = scanner.nextBoolean();    

                                if (eleccion1 == true){
                                    System.out.println("Volviendo al Menu Principal..." + saltoDeLinea);
                                }
                                else if (eleccion1 == false) {
                                    System.out.println("Muchas gracias por su compra. Vuelva pronto" + saltoDeLinea);
                                    System.exit(0);
                                }
                                break;
                            }
                            else if (montoPago > totalCompras) {
                                vuelto = montoPago - totalCompras;
                                System.out.println("Compra realizada con exito." + saltoDeLinea+ "Tu vuelto es: $" + vuelto + saltoDeLinea + saltoDeLinea + "Desea seguir comprando?(ingresa True para volver al Menu Principal, o False para salir)");
                                eleccion1 = scanner.nextBoolean();    

                                if (eleccion1 == true){
                                    System.out.println("Volviendo al Menu Principal..." + saltoDeLinea);
                                }
                                else if (eleccion1 == false) {
                                    System.out.println("Muchas gracias por su compra. Vuelva pronto" + saltoDeLinea);
                                    System.exit(0);
                                }
                                break;
                            }
                            else if (montoPago < totalCompras) {
                                System.out.println("El monto ingresado no es suficiente. El pago no se pudo realizar." + saltoDeLinea + "Desea reintentarlo? (ingresa True para volver al Menu Principal, o False para salir)");
                                eleccion1 = scanner.nextBoolean();

                                if (eleccion1 == true) {
                                    System.out.println("Por favor, ingrese el monto para realizar el pago. $" + totalCompras);
                                }
                                else {
                                    System.out.println("Muchas gracias. Vuelva pronto." + saltoDeLinea );
                                    break;
                                }
                            }
                            else {
                                System.out.println("Ingrese una opción válida. True para intentar de nuevo, False para volver al menú principal");
                            }                            
                            
                        }                                                                       
                    }                  
                }
                
                case 6 -> {
                    System.out.println("Muchas gracias por por utilizar el sistema vitual de Teatro Moro. Vuelva pronto" + saltoDeLinea);
                    return;
                    }
                
            default -> System.out.println("Opcion no valida. Intente nuevamente.");
            }
        }
    }
}