package utn.aed.tp3;
import utn.aed.tp3.Enums.*;

/**
 *
 * @author Nicolas Martinez Ullan - 66947
 */
public abstract class Sesion {
    private final Dia diaSemana;
    private final int duracion;

    public Sesion(Dia diaSemana, int duracion) {
        this.diaSemana = diaSemana;
        this.duracion = duracion;
    }

    /**
     * Devuelve el dia de la semana de la sesion
     *
     * @return dia de la semana
     */
    public Dia getDiaSemana() {
        return diaSemana;
    }

    /**
     * Devuelve la duracion de la sesion en minutos
     *
     * @return duracion de la sesion en minutos
     */
    public int getDuracion() {
        return duracion;
    }
}
