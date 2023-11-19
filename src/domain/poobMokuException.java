package domain;

public class poobMokuException extends Exception{
    public static final String OPEN_ERROR = "Error al abrir";
    public static final String PRIMITIVE_DATA_ERROR = "Se encontraron datos primitivos en lugar de objetos.";
    public static final String INVALID_CLASS = "Clase invalida.";
    public static final String CORRUPT_FILE = "Archivo corrupto.";
    public static final String FILE_NOT_FOUND = "No se ha encontrado el archivo.";
    public static final String INPUT_OUTPUT_ERROR = "Ha ocurrido un error de entrada/salida.";
    public static final String SAVE_ERROR = "Error al guardar";
    public static final String NOT_SERIALIZABLE = "La clase a guardar o una clase referenciada no implementa serializable.";

    public static final String CREATE_ERROR = "Error al crear";
    public final static String CLASS_NOT_FOUND = "La clase que se desea construir no fué encontrada.";
    public final static String ILLEGAL_ACCESS = "No se pude crear la instancia, no tiene acceso al constructor";
    public final static String INSTANTIATION_ERROR = "No se pude crear la instancia";
    public final static String NOT_METHOD_FOUND = "No se encontro el constructor";
    public final static String INVOCATION_ERROR = "Error en la invocación de un metodo/constructor";

    public final static String PAWN_NOT_INGAME = "Error, la ficha no esta en juego";
    public final static String SQUARE_FILLED = "Error, maxima capacidad en la casilla";
    public final static String SQUARE_BLOCK= "Error, bloqueo de casilla adelante";
    public  poobMokuException(String message){
        super(message);
    }
}
