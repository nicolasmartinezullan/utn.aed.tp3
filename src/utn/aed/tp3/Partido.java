package utn.aed.tp3;
import utn.aed.tp3.Enums.Dia;

/**
 *
 * @author Nicolas Martinez Ullan - 66947 - 2014 - 1K9
 */
public class Partido extends Sesion {
    private final boolean ganado;
    private final String nombreContrincante;

    public Partido(Dia diaSemana, int duracion, String nombreContrincante, boolean ganado) {
        super(diaSemana, duracion);
        this.nombreContrincante = nombreContrincante;
        this.ganado = ganado;
    }
    public boolean isGanado() {
        return ganado;
    }
    public String getNombreContrincante() {
        return nombreContrincante;
    }
    @Override
    public String toString() {
        return "Partido{" + "ganado=" + ganado + ", nombreContrincante=" + nombreContrincante + ", dia=" + super.getDiaSemana() + ", duracion=" + super.getDuracion() + '}';
    }

}
