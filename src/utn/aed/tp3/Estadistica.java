package utn.aed.tp3;
import utn.aed.tp3.Enums.*;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - 2014 - 1K9
 */
public class Estadistica {
    private final Lista<Sesion> sesiones = new Lista<>(Sesion.class);

    public Lista<Sesion> getSesiones() {
        return sesiones;
    }

    public void agregarSesion(Sesion s) {
        sesiones.agregar(s);
    }

    @Override
    public String toString() {
        return "Estadistica{" + "sesiones=" + sesiones + '}';
    }

    /**
     * Calcula el tiempo total en minutos empleado en todas las sesiones de
     * tenis.
     *
     * @return el tiempo total en minutos
     */
    public int tiempoTotalDeSesiones() {
        int acum = 0;
        Sesion[] a = sesiones.toArray();
        if (a != null)
            for (Sesion s : a)
                acum += s.getDuracion();
        return acum;
    }

    /**
     * Calcula los porcentajes de tiempos totales empleados por cada tipo de
     * sesion. Para leer los datos del arreglo hay que usar el metodo ordinal()
     * de cada TipoSesion para saber a que tipo de sesion se esta haciendo
     * referencia.
     *
     * Ejemplo: porcentajes[tp3.Enums.TipoSesion.PARTIDO.ordinal()], si el
     * orginal() es 1, entonces su porcentaje de tiempo estara en el indice 1
     * del arreglo
     *
     * @return un arreglo con los porcentajes de tiempo total para cada tipo de
     * sesion
     */
    public float[] porcentajesTiempoTotalPorTipoSesion() {
        float[] r = new float[TipoSesion.values().length];
        int tiempoTotal = tiempoTotalDeSesiones();
        Sesion[] array = sesiones.toArray();
        if (array != null) {
            for (Sesion s : array)
                if (s instanceof Clase)
                    r[TipoSesion.CLASE.ordinal()] += s.getDuracion();
                else if (s instanceof Partido)
                    r[TipoSesion.PARTIDO.ordinal()] += s.getDuracion();
            for (int i = 0; i < TipoSesion.values().length; i++)
                r[i] = (r[i] * (float) 100 / tiempoTotal);
        }
        return r;
    }

    /**
     * Calcula el gasto calorico total en todas las sesiones de tenis teniendo
     * en cuenta que: cada 10 minutos de partido se consumen 55 KCal; cada 10
     * minutos de clase con golpe drive o reves se consumen 45 KCal; cada 10
     * minutos de clase con golpe de saque o volea se consumen 40 KCal.
     *
     * @return gasto calorico total en sesiones de tenis
     */
    public float gastoCaloricoTotal() {
        float partido = 0, claseDriveReves = 0, claseSaqueVolea = 0;
        Sesion[] array = sesiones.toArray();
        if (array != null)
            for (Sesion s : array)
                if (s instanceof Partido)
                    partido += (55.f * (float) s.getDuracion() / 10.f);
                else if (s instanceof Clase)
                    if (((Clase) s).getGolpeTrabajado() == Golpe.DRIVE || ((Clase) s).getGolpeTrabajado() == Golpe.REVES)
                        claseDriveReves += (45.f * s.getDuracion() / 10.f);
                    else if (((Clase) s).getGolpeTrabajado() == Golpe.SAQUE || ((Clase) s).getGolpeTrabajado() == Golpe.VOLEA)
                        claseSaqueVolea += (40.f * s.getDuracion() / 10.f);
        return partido + claseDriveReves + claseSaqueVolea;
    }

    /**
     * Calcula la cantidad de minutos trabajados por dia y por golpe para las
     * clases de tenis.
     *
     * Nota: el primer indice hace referencia a los dias se semana y cada dia
     * corresponde a su correspondiente en el enum Dia, y se vincula el indice
     * con el ordinal() del dia. Lo mismo para los golpes (segundo indice).
     *
     * Ejemplo:
     * matriz[tp3.Enums.Dia.SABADO.orginal()][tp3.Enums.Golpe.DRIVE.orginal()]
     *
     * @return matriz con cantidad de minutos acumulados por dia (filas) y por
     * golpe (columnas)
     */
    public int[][] tiempoTotalPorDiaPorGolpe() {
        int[][] m = new int[Dia.values().length][Golpe.values().length];
        Clase[] array = sesiones.traerPorTipo(Clase.class).toArray();
        if (array != null)
            for (Clase c : array)
                m[c.getDiaSemana().ordinal()][c.getGolpeTrabajado().ordinal()] = c.getDuracion();
        return m;
    }

    /**
     * Calcula el tiempo total empleado en minutos para cada golpe trabajado en
     * las clases de tenis. Los tiempos se cargan en un vector que tiene tantos
     * indices como cantidad de valores del enumerador tp3.Enums.Golpe hay, y
     * cada indice hace referencia al golpe que tiene el mismo .ordinal().
     *
     * Ejemplo para leer el vector: vector[tp3.Enums.Golpe.DRIVE.ordinal()]
     *
     * @return vector con tiempo total en minutos empleado para cada golpe
     * trabajado
     */
    public int[] tiempoTotalPorGolpe() {
        int[] v = new int[Golpe.values().length];
        Clase[] array = sesiones.traerPorTipo(Clase.class).toArray();
        if (array != null)
            for (Clase c : array)
                v[c.getGolpeTrabajado().ordinal()] += c.getDuracion();
        return v;
    }

    /**
     * Calcula el porcentaje de partidos ganados frente a los partidos perdidos
     * por dia de la semana.
     *
     * Nota: la matriz de porcentajes devuelta contiene en el primer indice el
     * dia de la semana correspondiente al valor del enumerador tp3.Enums.Dia, y
     * en el segundo indice se marca si el porcenje se marca si el partido fue
     * ganado o perdido y se corresponde con los valores del enumerador
     * tp3.Enum.Ganado.
     *
     * Ejemplo:
     * matriz[tp3.Enum.Dia.LUNES.ordinal()][tp3.Enum.Ganado.SI.ordinal()]
     *
     * @return el porcentaje de partidos ganados frente a los perdidos por dia
     * de la semana
     */
    public float[][] porcentajePartidosGanadosPerdidosPorDia() {
        float[][] porcentajes = new float[Dia.values().length][Ganado.values().length];
        int[] totales = new int[Dia.values().length];
        Sesion[] array = sesiones.toArray();
        if (array != null)
            for (Sesion s : array)
                if (s instanceof Partido)
                    if (((Partido) s).isGanado()) {
                        porcentajes[s.getDiaSemana().ordinal()][Ganado.SI.ordinal()]++;
                        totales[s.getDiaSemana().ordinal()]++;
                    }
                    else {
                        porcentajes[s.getDiaSemana().ordinal()][Ganado.NO.ordinal()]++;
                        totales[s.getDiaSemana().ordinal()]++;
                    }
        for (Dia d : Dia.values())
            for (Ganado g : Ganado.values())
                if (totales[d.ordinal()] != 0)
                    porcentajes[d.ordinal()][g.ordinal()] = (porcentajes[d.ordinal()][g.ordinal()] * 100.f / (float) totales[d.ordinal()]);
        return porcentajes;
    }

    /**
     * Busca los nombres de los contrincantes a los que mas partidos se les
     * gano.
     *
     * @return los nombres de los contrincantes separados por punto y coma (;) y
     * la cantidad de partidos ganados. Si no se jugaron partidos devuelve null
     */
    public NameValue contrincanteAlQueMasPartidosSeLeGano() {
        NameValue salida = null;
        Partido[] partidos = sesiones.traerPorTipo(Partido.class).toArray();
        if (partidos != null) {
            String nombre;
            Lista<NameValue> lista = new Lista<>(NameValue.class);
            int contadorGanados;
            // Recorro cada uno de ellos
            for (int i = 0; i < partidos.length; i++)
                if (partidos[i] != null) {
                    // Si le gane, lo tomo como pivote, lo computo, y me fijo si hay mas ganados para este contrincante. Si no es ganado, lo borro porque no sirve
                    if (partidos[i].isGanado()) {
                        // Grabo nombre contrincante al que le gane
                        nombre = partidos[i].getNombreContrincante();
                        // Al menos tengo un partido ganado
                        contadorGanados = 1;
                        // Verifico si a este mismo contrincante le gane mas partidos
                        for (int j = i + 1; j < partidos.length; j++) {
                            /*Si el partido no fue borrado previamente por no servir 
                             o por ya ser computado para otro contrincante, y ademas es 
                             un partido que le corresponde al contrincante que estoy 
                             analizando, lo computo y lo borro para que no se vuelva a usar*/
                            if (partidos[j] != null && partidos[j].getNombreContrincante().equals(nombre)) {
                                contadorGanados++;
                                partidos[j] = null;
                            }
                        }
                        // Agrego los datos recabados a una lista auxiliar
                        lista.agregar(new NameValue(nombre, contadorGanados));
                    }
                    // Borro el registro analizado (lo haya computado o no) para que no se tenga en cuenta en futuros analisis para otroc contrincantes
                    partidos[i] = null;
                }
            NameValue mayor = new NameValue("", 0);
            // Busca cual fue el contrincante al que mas le gane
            if (!lista.estaVacia()) {
                for (NameValue par : lista.toArray())
                    if (par.getValue() > mayor.getValue())
                        mayor = par;
                StringBuilder nombres = new StringBuilder();
                nombres.append(mayor.getName());
                // Fijate si hay otros contrincantes a los que le gane la misma cantidad de veces
                for (NameValue par : lista.toArray())
                    if (!par.getName().equals(mayor.getName()) && par.getValue() == mayor.getValue())
                        nombres.append(";").append(par.getName());
                salida = new NameValue(nombres.toString(), mayor.getValue());
            }
        }
        return salida;
    }
}
