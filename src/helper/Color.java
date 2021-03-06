//Mateo Sapiurka - Sebastian Bentancurt
package helper;

public class Color {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    /**
     * Retorna un String con un color ANSI
     * @param value
     * @param color
     * @return
     */
    public static String addColorToString(String value, String color) {
        String prefix = "";
        switch (color) {
            case "rojo":
            case "red":
                prefix = ANSI_RED;
                break;
            case "azul":
            case "blue":
                prefix = ANSI_BLUE;
                break;
            case "green":
                prefix = ANSI_GREEN;
                break;
            case "purple":
                prefix = ANSI_PURPLE;
                break;
            case "yellow":
                prefix = ANSI_YELLOW;
                break;
            default:
                break;
        }
        return prefix + value + ANSI_RESET;
    }
}