package utn.aed.tp3;
import utn.aed.tp3.Enums.Dia;
import utn.aed.tp3.Enums.Golpe;

/**
 *
 * @author Nicolas Martinez Ullan - 66947 - 2014 - 1K9
 */
public class Clase extends Sesion {
    private final Golpe golpeTrabajado;
    
    public Clase(Dia diaSemana, int duracion, Golpe golpeTrabajado) {
        super(diaSemana, duracion);
        this.golpeTrabajado = golpeTrabajado;
    }
    public Golpe getGolpeTrabajado() {
        return golpeTrabajado;
    }
    @Override
    public String toString() {
        return "Clase{" + "golpeTrabajado=" + golpeTrabajado + ", dia=" + super.getDiaSemana() + ", duracion=" + super.getDuracion() + '}';
    }
}
