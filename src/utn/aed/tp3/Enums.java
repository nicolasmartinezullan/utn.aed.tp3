package utn.aed.tp3;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - 2014 - 1K9
 */
public class Enums {
    public enum Dia {
        DOMINGO, LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO;
        public int value() {return ordinal()+1;}
        @Override
        public String toString() {return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();}
    }

    public enum Golpe {
        DRIVE, REVES, VOLEA, SAQUE;
        public int value() {return (ordinal()+1);}
        @Override
        public String toString() {return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();}
    }
    
    public enum TipoSesion{
        CLASE, PARTIDO;
        public int value(){return ordinal()+1;}
        @Override
        public String toString(){return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();}
    }
    
    public enum Ganado{
        SI, NO;
        public boolean value(){return ordinal() == 0;}
        @Override
        public String toString(){return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();}
    }
}
