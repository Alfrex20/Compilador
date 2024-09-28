
import jflex.exceptions.SilentExit;

/**
 * Esta clase se encarga de ejecutar el generador de analizadores léxicos (lexer) utilizando JFlex.
 * JFlex es una herramienta que genera analizadores léxicos a partir de especificaciones .flex.
 * 
 * @author yisus
 */
public class ExecuteJFlex {
    
    //Método principal que ejecuta el generador de JFlex para crear los analizadores léxicos.
    //Se definen dos archivos .flex: uno para el lexer principal y otro para el lexer de colores.
    //Estos archivos son generados en el directorio actual del usuario.
    public static void main(String omega[]) {
        // Definición de las rutas de los archivos .flex a generar
        String lexerFile = System.getProperty("user.dir") + "/src/Lexer.flex",
                lexerFileColor = System.getProperty("user.dir") + "/src/LexerColor.flex";
        try {
            // Llamada al generador de JFlex para crear los analizadores léxicos
            jflex.Main.generate(new String[]{lexerFile, lexerFileColor});
        } catch (SilentExit ex) {
            // Captura y manejo de errores en caso de fallar la generación
            System.out.println("Error al compilar/generar el archivo flex: " + ex);
        }
    }
}
