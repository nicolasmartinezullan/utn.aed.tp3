package utn.aed.tp3;
import utn.aed.tp3.Enums.*;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - 2014 - 1K9
 */
public class Menu {
    private final Estadistica estadistica = new Estadistica();

    private final int MENU_PRINCIPAL_CARGAR_SESION = 1;
    private final int MENU_PRINCIPAL_TIEMPO_TOTAL = 2;
    private final int MENU_PRINCIPAL_PORCENTAJES_TIEMPO_TOTAL = 3;
    private final int MENU_PRINCIPAL_GASTO_CALORICO_TOTAL = 4;
    private final int MENU_PRINCIPAL_TIEMPO_POR_DIA_POR_GOLPE = 5;
    private final int MENU_PRINCIPAL_TIEMPO_POR_GOLPE = 6;
    private final int MENU_PRINCIPAL_PORCENTAJE_GANADOS_PERDIDOS = 7;
    private final int MENU_PRINCIPAL_PUNTO_BONUS = 8;
    private final int MENU_PRINCIPAL_SALIR = 9;
    private final int TABLA_COLUMNA_ANCHO = 11;
    private final String TABLA_ENCABEZADO_SEPARADOR = "--------------------------------------------------------------------";
    private final char TABLA_COLUMNA_SEPARADOR = '|';
    private final int CONTRINCANTE_NOMBRE_MAX = 30;

    private <E extends Enum<E>> String armarOpciones(Class<E> enumData) {
        StringBuilder st = new StringBuilder();
        for (Enum<E> e : enumData.getEnumConstants())
            st.append("(").append(e.ordinal() + 1).append(") ").append(e.name()).append("   ");
        st.delete(st.length() - 3, st.length());
        return st.toString();
    }

    private <E extends Enum<E>> E procesarSeleccion(Class<E> enumData) {
        E r = null;
        boolean validado = false;
        int seleccion;
        do {
            seleccion = In.readInt();
            for (E e : enumData.getEnumConstants())
                if (seleccion == e.ordinal() + 1) {
                    r = (E) e;
                    validado = true;
                    break;
                }
            if (!validado)
                System.out.print(String.format("Ingrese una opcion correcta [ %s ]: ", armarOpciones(enumData)));
        } while (!validado);
        return r;
    }
    
    private void procesarMenuPrincipal(int opcion) {
        limpiarPantalla();
        imprimirMarco();
        System.out.println();
        switch (opcion) {
            case MENU_PRINCIPAL_CARGAR_SESION: {
                System.out.println("Carga de sesion de tenis\n".toUpperCase());
                System.out.print(String.format("Tipo [ %s ]: ", armarOpciones(TipoSesion.class)));
                TipoSesion tipoSesion = procesarSeleccion(TipoSesion.class);
                System.out.print(String.format("Dia [ %s ]: ", armarOpciones(Dia.class)));
                Dia dia = procesarSeleccion(Dia.class);
                System.out.print("Duracion (minutos): ");
                int duracion = In.readNat();
                switch (tipoSesion) {
                    case CLASE: {
                        System.out.print(String.format("Golpe trabajado [ %s ]: ", armarOpciones(Golpe.class)));
                        Golpe golpe = procesarSeleccion(Golpe.class);
                        estadistica.agregarSesion(new Clase(dia, duracion, golpe));
                        System.out.println("\nSesion cargada exitosamente.");
                        break;
                    }
                    case PARTIDO: {
                        System.out.print(String.format("Nombre contrincante (maximo %s caracteres, letras A-Z y espacios): ", CONTRINCANTE_NOMBRE_MAX));
                        String nombreContrincante = In.readLine().trim();
                        // Verificar que haya ingresado algo y que sea con los caracteres permitidos
                        while (nombreContrincante.equals("") || !nombreContrincante.matches("^[a-zA-Z ]+$")) {
                            System.out.print(String.format("Ingrese un nombre correcto (maximo %s caracteres, letras A-Z y espacios): ", CONTRINCANTE_NOMBRE_MAX));
                            nombreContrincante = In.readLine().trim();
                        }
                        // Recortar al maximo permitido
                        if (nombreContrincante.length() > CONTRINCANTE_NOMBRE_MAX)
                            nombreContrincante = nombreContrincante.substring(0, CONTRINCANTE_NOMBRE_MAX);
                        // Dejar solo un espacio entre palabras y pasar todas los caracteres a minusculas
                        nombreContrincante = nombreContrincante.replaceAll("( )+", " ").toLowerCase();
                        // Al menos va a tener un caracter y va a ser una letra de la a-z
                        StringBuilder st = new StringBuilder();
                        // Capitalizar la primera letra de cada palabra
                        for (String s : nombreContrincante.split(" "))
                            st.append(s.substring(0, 1).toUpperCase()).append(s.substring(1)).append(" ");
                        nombreContrincante = st.toString().trim();
                        System.out.print(String.format("Ganado [ %s ]: ", armarOpciones(Ganado.class)));
                        Ganado ganado = procesarSeleccion(Ganado.class);
                        estadistica.agregarSesion(new Partido(dia, duracion, nombreContrincante, ganado.value()));
                        System.out.println("\nSesion cargada exitosamente.");
                        break;
                    }
                    default:
                        System.out.println("\nError al cargar sesion. Comuniquese con el servicio tecnico");
                }
                imprimirPresioneEnterParaContinuar();
                break;
            }
            case MENU_PRINCIPAL_TIEMPO_TOTAL: {
                System.out.println(("Tiempo total empleado en sesiones de tenis: " + estadistica.tiempoTotalDeSesiones() + " minuto(s)").toUpperCase());
                imprimirPresioneEnterParaContinuar();
                break;
            }
            case MENU_PRINCIPAL_PORCENTAJES_TIEMPO_TOTAL: {
                System.out.println("Porcentajes del tiempo total por tipo de sesion\n".toUpperCase());
                float[] porcentajes = estadistica.porcentajesTiempoTotalPorTipoSesion();
                for (TipoSesion t : TipoSesion.values())
                    System.out.println(String.format(rellenarDerecha(String.format("%s:", t), 9, ' ') + "%.2f %%", porcentajes[t.ordinal()]));
                imprimirPresioneEnterParaContinuar();
                break;
            }
            case MENU_PRINCIPAL_GASTO_CALORICO_TOTAL: {
                System.out.println(String.format("Gasto calorico total en sesiones de tenis: %.2f KCal", estadistica.gastoCaloricoTotal()).toUpperCase());
                imprimirPresioneEnterParaContinuar();
                break;
            }
            case MENU_PRINCIPAL_TIEMPO_POR_DIA_POR_GOLPE: {
                System.out.println("Tiempo total por dia y por golpe trabajado (en minutos)\n".toUpperCase());
                int[][] m = estadistica.tiempoTotalPorDiaPorGolpe();
                StringBuilder tabla = new StringBuilder();
                tabla.append(armarFilaTabla(new String[]{" ", "DRIVE", "REVES", "VOLEA", "SAQUE"}));
                tabla.append(armarFilaTabla(new String[]{TABLA_ENCABEZADO_SEPARADOR, TABLA_ENCABEZADO_SEPARADOR, TABLA_ENCABEZADO_SEPARADOR, TABLA_ENCABEZADO_SEPARADOR, TABLA_ENCABEZADO_SEPARADOR}));
                String[] columnas = new String[Golpe.values().length + 1];
                for (Dia d : Dia.values()) {
                    columnas[0] = d.name();
                    for (Golpe g : Golpe.values())
                        columnas[g.ordinal() + 1] = Integer.toString(m[d.ordinal()][g.ordinal()]);
                    tabla.append(armarFilaTabla(columnas));
                }
                tabla.delete(tabla.length() - 1, tabla.length());
                System.out.println(tabla);
                imprimirPresioneEnterParaContinuar();
                break;
            }
            case MENU_PRINCIPAL_TIEMPO_POR_GOLPE: {
                System.out.println("Tiempo total por golpe (en minutos)\n".toUpperCase());
                int[] v = estadistica.tiempoTotalPorGolpe();
                for (Golpe g : Golpe.values())
                    System.out.println(String.format("%s: %s", g, v[g.ordinal()]));
                imprimirPresioneEnterParaContinuar();
                break;
            }
            case MENU_PRINCIPAL_PORCENTAJE_GANADOS_PERDIDOS: {
                System.out.println("Porcentaje de partidos ganados-perdidos por dia\n".toUpperCase());
                float[][] m = estadistica.porcentajePartidosGanadosPerdidosPorDia();
                StringBuilder tabla = new StringBuilder();
                tabla.append(armarFilaTabla(new String[]{" ", "GANADO", "PERDIDO"}));
                tabla.append(armarFilaTabla(new String[]{TABLA_ENCABEZADO_SEPARADOR, TABLA_ENCABEZADO_SEPARADOR, TABLA_ENCABEZADO_SEPARADOR}));
                String[] columnas = new String[Ganado.values().length + 1];
                for (Dia d : Dia.values()) {
                    columnas[0] = d.name();
                    for (Ganado g : Ganado.values())
                        columnas[g.ordinal() + 1] = String.format("%.2f %%", m[d.ordinal()][g.ordinal()]);
                    tabla.append(armarFilaTabla(columnas));
                }
                tabla.delete(tabla.length() - 1, tabla.length());
                System.out.println(tabla);
                imprimirPresioneEnterParaContinuar();
                break;
            }
            case MENU_PRINCIPAL_PUNTO_BONUS: {
                System.out.println("Contrincante al que mas veces se le gano\n".toUpperCase());
                NameValue nv = estadistica.contrincanteAlQueMasPartidosSeLeGano();
                if (nv != null) {
                    System.out.println("Contrincantes que igualan el numero maximo de veces ganadas a un jugador:");
                    for (String s : nv.getName().split(";"))
                        System.out.println("   " + s);
                    System.out.println("Veces ganadas: " + nv.getValue());
                }
                else {
                    System.out.println("No se han ganado partidos aun.");
                }
                imprimirPresioneEnterParaContinuar();
                break;
            }
            case MENU_PRINCIPAL_SALIR: {
                // Do nothing...
                break;
            }
            default: {
                System.out.println("Ingrese una opcion correcta.");
                imprimirPresioneEnterParaContinuar();
            }
        }
    }

    private int imprimirMenuPrincipal() {
        limpiarPantalla();
        imprimirMarco();
        System.out.println();
        System.out.println(MENU_PRINCIPAL_CARGAR_SESION + ". Cargar sesion");
        System.out.println(MENU_PRINCIPAL_TIEMPO_TOTAL + ". Tiempo total empleado en sesiones de tenis");
        System.out.println(MENU_PRINCIPAL_PORCENTAJES_TIEMPO_TOTAL + ". Porcentajes del tiempo total por tipo de sesion");
        System.out.println(MENU_PRINCIPAL_GASTO_CALORICO_TOTAL + ". Gasto calorico total");
        System.out.println(MENU_PRINCIPAL_TIEMPO_POR_DIA_POR_GOLPE + ". Tiempo total por dia y por golpe");
        System.out.println(MENU_PRINCIPAL_TIEMPO_POR_GOLPE + ". Tiempo total por golpe");
        System.out.println(MENU_PRINCIPAL_PORCENTAJE_GANADOS_PERDIDOS + ". Porcentaje ganados-perdidos por dia");
        System.out.println(MENU_PRINCIPAL_PUNTO_BONUS + ". Contrincante(s) al que mas partidos se le(s) gano");
        System.out.println(MENU_PRINCIPAL_SALIR + ". Salir");
        System.out.print("Opcion: ");
        return In.readInt();
    }

    private void imprimirMarco() {
        System.out.println("=============================================================================");
        System.out.println("=                   ESTADISTICAS DE SESIONES DE TENNIS                      =");
        System.out.println("=                           Tenista Empedernido                             =");
        System.out.println("=============================================================================");
    }

    private void imprimirPresioneEnterParaContinuar() {
        System.out.println("\nPresione ENTER para continuar...");
        In.readLine();
    }

    public void ejecutar() {
        int opcion;
        do {
            opcion = imprimirMenuPrincipal();
            procesarMenuPrincipal(opcion);
        } while (opcion != MENU_PRINCIPAL_SALIR);
    }

    private String armarFilaTabla(Object[] columnas) {
        StringBuilder fila = new StringBuilder();
        fila.append(TABLA_COLUMNA_SEPARADOR);
        for (Object c : columnas) {
            fila.append(armarColumnaTabla(c));
            fila.append(TABLA_COLUMNA_SEPARADOR);
        }
        fila.append("\n");
        return fila.toString();
    }

    private String armarColumnaTabla(Object contenido) {
        StringBuilder st = new StringBuilder();
        st.append(' ');
        st.append(contenido.toString().trim());
        int caracteresLibres = TABLA_COLUMNA_ANCHO - st.length();
        if (caracteresLibres > 1)
            for (int i = 0; i < caracteresLibres - 1; i++)
                st.append(' ');
        else if (caracteresLibres != 1)
            st.delete(TABLA_COLUMNA_ANCHO - 1, st.length());
        st.append(' ');
        return st.toString();
    }

    private String rellenarDerecha(String texto, int longitud, char caracter) {
        StringBuilder st = new StringBuilder();
        st.append(texto);
        for (int i = 0; i < longitud; i++)
            st.append(caracter);
        return st.substring(0, longitud);
    }

    private void limpiarPantalla() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}
