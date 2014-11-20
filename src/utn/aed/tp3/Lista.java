package utn.aed.tp3;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - 2014 - 1K9
 * @param <T> tipo de datos a contener
 */
public class Lista<T> {
    private Nodo<T> raiz;
    private int tamano;
    private final Class<T> tipo;

    public Lista(Class<T> tipo) {
        this.tipo = tipo;
    }

    public Class<T> getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        for (String s : ("Lista{" + "tamano=" + tamano + ", tipo=" + tipo.getName() + ", raiz=" + raiz + '}').split("datos="))
            st.append(s).append('\n');
        return st.delete(st.length() - 1, st.length()).toString();
    }

    public int tamano() {
        return tamano;
    }

    public void agregar(T dato) {
        if (raiz == null) {
            raiz = new Nodo(dato);
        }
        else {
            Nodo aux = raiz;
            for (int i = 1; i < tamano; i++) {
                aux = aux.getProx();
            }
            aux.setProx(new Nodo(dato));
        }
        tamano++;
    }

    public boolean remover(T objeto) {
        boolean flag = false;
        if (raiz != null) {
            Nodo<T> anterior = null;
            Nodo<T> actual = raiz;
            Nodo<T> proximo = raiz.getProx();
            while (actual != null) {
                if (actual.getDatos() == objeto) {
                    // Si es el primero
                    if (anterior == null) {
                        if (proximo == null) {
                            raiz = null;
                            flag = true;
                            break;
                        }
                        else {
                            raiz = proximo;
                            actual = proximo;
                            proximo = proximo.getProx();
                            flag = true;
                        }
                    }
                    else {
                        anterior.setProx(proximo);
                        actual = proximo;
                        if (proximo != null) {
                            proximo = proximo.getProx();
                        }
                        flag = true;
                    }
                    tamano--;
                }
                else {
                    anterior = actual;
                    actual = proximo;
                    if (proximo != null) {
                        proximo = proximo.getProx();
                    }
                }
            }
        }
        return flag;
    }

    public void limpiar() {
        raiz = null;
        tamano = 0;
    }

    public boolean estaVacia() {
        return (tamano == 0);
    }

    public boolean contiene(T objeto) {
        boolean flag = false;
        if (raiz != null) {
            Nodo<T> aux = raiz;
            for (int i = 1; i <= tamano; i++) {
                if (aux.getDatos() == objeto) {
                    flag = true;
                    break;
                }
                aux = aux.getProx();
            }
        }
        return flag;
    }

    public T[] toArray() {
        T[] array = null;
        if (tamano > 0) {
            array = (T[]) new Object[tamano];
//            array = (T[]) new Object[tamano];
//            array = (T[]) java.lang.reflect.Array.newInstance(tipo, tamano);
            Nodo<T> aux = raiz;
            for (int i = 0; i < tamano; i++) {
                array[i] = aux.getDatos();
                aux = aux.getProx();
            }
        }
        return array;
    }

    public <C extends Object> Lista<C> traerPorTipo(Class<C> tipo) {
        Lista<C> l = new Lista<>(tipo);
        if (tamano > 0) {
            Nodo<T> aux = raiz;
            for (int i = 0; i < tamano; i++) {
                if (aux.getDatos().getClass().equals(tipo))
                    l.agregar((C) aux.getDatos());
                aux = aux.getProx();
            }
        }
        return l;
    }
}
