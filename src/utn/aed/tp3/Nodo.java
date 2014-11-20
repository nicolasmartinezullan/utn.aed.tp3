package utn.aed.tp3;

/**
 *
 * @author Nicolas Martinez Ullan - 66947 - 2014 - 1K9
 * @param <T> T de datos a contener
 */
public class Nodo<T> {
    private final T datos;
    private Nodo prox;

    public Nodo(T datos, Nodo prox) {
        this.datos = datos;
        this.prox = prox;
    }
    public Nodo(T datos) {
        this.datos = datos;
    }
    @Override
    public String toString() {
        return "Nodo{" + "datos=" + datos + ", prox=" + prox + '}';
    }
    public T getDatos() {
        return datos;
    }
    public Nodo getProx() {
        return prox;
    }
    public void setProx(Nodo prox) {
        this.prox = prox;
    }
}
