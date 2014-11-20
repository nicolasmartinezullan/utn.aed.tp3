package utn.aed.tp3;

public class In {

    public static String readLine() {
        int ch;
        String r = "";
        boolean done = false;
        while (!done) {
            try {
                ch = System.in.read();
                if (ch < 0 || (char) ch == '\n')
                    done = true;
                else if ((char) ch != '\r')
                    r += (char) ch;
            }
            catch (java.io.IOException e) {
                done = true;
            }
        }
        return r;
    }

    public static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(readLine().trim());
            }
            catch (NumberFormatException e) {
                System.out.print("Por favor, ingrese un numero entero: ");
            }
        }
    }

    public static double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(readLine().trim());
            }
            catch (NumberFormatException e) {
                System.out.print("Por favor, ingrese un numero decimal: ");
            }
        }
    }

    public static float readFloat() {
        while (true) {
            try {
                return Float.parseFloat(readLine().trim());
            }
            catch (NumberFormatException e) {
                System.out.print("Por favor, ingrese un numero decimal: ");
            }
        }
    }

    public static char readChar() {
        while (true) {
            try {
                String s = readLine().trim();
                if (s.length() != 1)
                    System.out.print("Por favor, ingrese un solo caracter: ");
                else
                    return (char) s.charAt(0);
            }
            catch (Exception e) {
                System.out.print("Por favor, ingrese un solo caracter: ");
            }
        }
    }

    public static int readNat() {
        while (true) {
            try {
                int a = Integer.parseInt(readLine().trim());
                if (a <= 0)
                    System.out.print("Por favor, ingrese un numero positivo: ");
                else
                    return a;
            }
            catch (NumberFormatException e) {
                System.out.print("Por favor, ingrese un numero positivo: ");
            }
        }
    }
}
