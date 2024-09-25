// Importación de bibliotecas necesarias para la interfaz de usuario y funcionalidad del compilador.
import com.formdev.flatlaf.FlatIntelliJLaf;
import compilerTools.CodeBlock;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import compilerTools.Directory;
import compilerTools.ErrorLSSL;
import compilerTools.Functions;
import compilerTools.Grammar;
import compilerTools.Production;
import compilerTools.TextColor;
import compilerTools.Token;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.Timer;

// Clase principal del compilador extendiendo JFrame para la interfaz gráfica.
public class Compilador extends javax.swing.JFrame {

    // Variables privadas de la clase
    private String title; // Título de la ventana
    private Directory directorio; // Manejo de directorios y archivos
    private ArrayList<Token> tokens; // Lista de tokens analizados
    private ArrayList<ErrorLSSL> errors; // Lista de errores detectados
    private ArrayList<TextColor> textsColor; // Colores para la sintaxis del texto
    private Timer timerKeyReleased; // Temporizador para eventos de liberación de teclas
    private ArrayList<Production> identProd; // Lista de producciones
    private HashMap<String, String> identificadores; // Mapa para manejar identificadores y sus valores
    private boolean codeHasBeenCompiled = false; // Indicador de si el código ha sido compilado

    /**
     * Constructor de la clase Compilador, para inicializar los componentes.
     */
    public Compilador() {
        initComponents();
        init();
    }
    
    // Método de inicialización de variables y configuraciones
    private void init() {
        title = "Compiler"; // Asigna un título a la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setTitle(title); // Establece el título de la ventana
        directorio = new Directory(this, jtpCode, title, ".comp"); // Inicializa el directorio de trabajo
        addWindowListener(new WindowAdapter() {// Cuando presiona la "X" de la esquina superior derecha
            @Override
            public void windowClosing(WindowEvent e) {
                directorio.Exit(); // Guarda cambios y cierra directorio
                System.exit(0);// Cierra la aplicación
            }
        });
        Functions.setLineNumberOnJTextComponent(jtpCode); // Muestra los números de línea en el editor de código
        // Configura el temporizador para detectar liberaciones de teclas
        timerKeyReleased = new Timer((int) (1000 * 0.3), (ActionEvent e) -> {
            timerKeyReleased.stop(); // Detiene el temporizador
            colorAnalysis();// Realiza un análisis de color para resaltar sintaxis
        });
        // Configura la inserción de asteriscos en nombres de archivos al modificar contenido
        Functions.insertAsteriskInName(this, jtpCode, () -> {
            timerKeyReleased.restart(); // Reinicia el temporizador
        });
        // Inicializa listas y mapas para tokens, errores, colores y producciones
        tokens = new ArrayList<>();
        errors = new ArrayList<>();
        textsColor = new ArrayList<>();
        identProd = new ArrayList<>();
        identificadores = new HashMap<>();
        // Configura el autocompletado en el editor de texto
        Functions.setAutocompleterJTextComponent(new String[]{}, jtpCode, () -> {
            timerKeyReleased.restart();
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootPanel = new javax.swing.JPanel();
        buttonsFilePanel = new javax.swing.JPanel();
        btnAbrir = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnGuardarC = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpCode = new javax.swing.JTextPane();
        panelButtonCompilerExecute = new javax.swing.JPanel();
        btnCompilar = new javax.swing.JButton();
        btnEjecutar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaOutputConsole = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTokens = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        btnAbrir.setText("Abrir");
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnGuardarC.setText("Guardar como");
        btnGuardarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonsFilePanelLayout = new javax.swing.GroupLayout(buttonsFilePanel);
        buttonsFilePanel.setLayout(buttonsFilePanelLayout);
        buttonsFilePanelLayout.setHorizontalGroup(
            buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAbrir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarC)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buttonsFilePanelLayout.setVerticalGroup(
            buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAbrir)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnGuardarC))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jtpCode);

        btnCompilar.setText("Compilar");
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });

        btnEjecutar.setText("Ejecutar");
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelButtonCompilerExecuteLayout = new javax.swing.GroupLayout(panelButtonCompilerExecute);
        panelButtonCompilerExecute.setLayout(panelButtonCompilerExecuteLayout);
        panelButtonCompilerExecuteLayout.setHorizontalGroup(
            panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonCompilerExecuteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCompilar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEjecutar)
                .addContainerGap())
        );
        panelButtonCompilerExecuteLayout.setVerticalGroup(
            panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonCompilerExecuteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCompilar)
                    .addComponent(btnEjecutar))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jtaOutputConsole.setEditable(false);
        jtaOutputConsole.setBackground(new java.awt.Color(255, 255, 255));
        jtaOutputConsole.setColumns(20);
        jtaOutputConsole.setRows(5);
        jScrollPane2.setViewportView(jtaOutputConsole);

        tblTokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Componente léxico", "Lexema", "[Línea, Columna]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTokens.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblTokens);

        javax.swing.GroupLayout rootPanelLayout = new javax.swing.GroupLayout(rootPanel);
        rootPanel.setLayout(rootPanelLayout);
        rootPanelLayout.setHorizontalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rootPanelLayout.createSequentialGroup()
                        .addComponent(buttonsFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelButtonCompilerExecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );
        rootPanelLayout.setVerticalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonsFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelButtonCompilerExecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        getContentPane().add(rootPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Acción del botón "Nuevo": crea un nuevo archivo y limpia los campos.
    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        directorio.New();
        clearFields();
    }//GEN-LAST:event_btnNuevoActionPerformed
    // Acción del botón "Abrir"
    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        if (directorio.Open()) {// Llama al método para abrir un archivo y verifica si la operación fue exitosa.
            colorAnalysis(); // Realiza el análisis de color del código.
            clearFields(); // Limpia los campos de errores y tokens.
        }
    }//GEN-LAST:event_btnAbrirActionPerformed
    // Acción del botón "Guardar"
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (directorio.Save()) { // Llama al método para guardar el archivo y verifica si fue exitoso.
            clearFields(); // Limpia los campos de errores y tokens.
        }
    }//GEN-LAST:event_btnGuardarActionPerformed
    // Acción del botón "Guardar como"
    private void btnGuardarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCActionPerformed
        if (directorio.SaveAs()) {// Llama al método para guardar el archivo con un nuevo nombre.
            clearFields();// Limpia los campos de errores y tokens.
        }
    }//GEN-LAST:event_btnGuardarCActionPerformed
    // Acción del botón "Compilar"
    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        // Verifica si el título contiene un asterisco (indicando cambios no guardados) o es igual al título original.
        if (getTitle().contains("*") || getTitle().equals(title)) {
            // Guarda el archivo y compila si la operación es exitosa.
            if (directorio.Save()) {
                compile();
            }
        } else {
            compile();// Si no hay cambios no guardados, compila directamente.
        }
    }//GEN-LAST:event_btnCompilarActionPerformed
    
    // Acción del botón "Ejecutar"
    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        btnCompilar.doClick();// Ejecuta el botón de compilación.
        if (codeHasBeenCompiled) { // Verifica si el código ha sido compilado.
            // Verifica si hay errores; si los hay, muestra un mensaje de error.
            if (!errors.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se puede ejecutar el código ya que se encontró uno o más errores",
                        "Error en la compilación", JOptionPane.ERROR_MESSAGE);
            } else {
                // Divide el código en bloques utilizando los delimitadores especificados.
                CodeBlock codeBlock = Functions.splitCodeInCodeBlocks(tokens, "{", "}", ";");
                System.out.println(codeBlock);// Imprime el bloque de código dividido para depuración.
            // Obtiene los bloques de código en el orden de ejecución.
                ArrayList<String> blocksOfCode = codeBlock.getBlocksOfCodeInOrderOfExec();
                System.out.println(blocksOfCode);// Imprime los bloques de código para depuración.
                executeCode(blocksOfCode, 1); // Ejecuta los bloques de código.

            }
        }
    }//GEN-LAST:event_btnEjecutarActionPerformed
    // Método para ejecutar los bloques de código en un ciclo definido por "repeats".
    private void executeCode(ArrayList<String> blocksOfCode, int repeats){
        // Repite la ejecución según el número de veces especificado.
        for(int j=1; j<=repeats; j++){
            int repeatCode = -1;// Inicializa una variable para controlar la repetición de código.
        // Itera sobre cada bloque de código.
            for (int i = 0; i < blocksOfCode.size(); i++){
                String blockOfCode = blocksOfCode.get(i);// Obtiene el bloque de código actual.
            // Verifica si se debe repetir un bloque de código.
                if(repeatCode!= -1){
                    // Obtiene las posiciones de los marcadores de repetición dentro del bloque.
                    int[] posicionMarcador = CodeBlock.getPositionOfBothMarkers(blocksOfCode, blockOfCode);
                    // Llama recursivamente a executeCode con el sub-bloque y el número de repeticiones.
                    executeCode(new ArrayList<>(blocksOfCode.subList(posicionMarcador[0], posicionMarcador[1])), repeatCode);
                    repeatCode = -1;// Reinicia la variable de repetición.
                    i = posicionMarcador[1]; // Avanza el índice para evitar re-ejecución del bloque.
                }
                else{
                    // Divide el bloque de código en sentencias individuales.
                    String[] sentences = blockOfCode.split(";");
                    for(String sentence : sentences){
                        sentence=sentence.trim();// Elimina espacios innecesarios en la sentencia.
                    // Verifica el tipo de sentencia y realiza la acción correspondiente.
                        if(sentence.startsWith("pintar")){
                            String parametro;// Obtiene el parámetro de la sentencia, verificando si es un identificador.
                            if(sentence.contains("$")){
                                parametro=identificadores.get(sentence.substring(9,sentence.length()-2));
                            }
                            else{
                                parametro = sentence.substring(9,sentence.length()-2);
                            }
                            System.out.println("Pintando de color "+ parametro+"...");// Acción de pintar.
                        }
                        else if(sentence.startsWith("izquierda")){
                            System.out.println("Moviéndose a la izquierda...");// Acción de moverse a la izquierda.
                        }
                        else if(sentence.startsWith("derecha")){
                            System.out.println("Moviéndose a la derecha...");// Acción de moverse a la derecha.
                        }
                        else if(sentence.startsWith("adelante")){
                            System.out.println("Moviéndose hacia adelante...");// Acción de moverse hacia adelante.
                        }
                        else if(sentence.contains("-->")){
                            System.out.println("Declarando identificador...");// Declaración de un identificador.
                        }
                        else if(sentence.contains("atras")){
                            System.out.println("Moviéndose hacia atras...");// Acción de moverse hacia atrás.
                        }
                        else if(sentence.startsWith("repetir")){
                            String parametro;
                            // Obtiene el parámetro de repetición, verificando si es un identificador.
                            if(sentence.contains("$")){
                                parametro=identificadores.get(sentence.substring(10,sentence.length()-2));
                            }else{
                                parametro=sentence.substring(10,sentence.length()-2);
                            }
                            repeatCode=Integer.parseInt(parametro); // Define la cantidad de repeticiones.
                        }
                    }
                }
            }
        }
    }
    // Método que coordina la compilación completa del código.
    private void compile() {
        clearFields();// Limpia los campos de errores, tokens, y otros elementos previos.
        lexicalAnalysis(); // Realiza el análisis léxico
        fillTableTokens();// Llena una tabla con los tokens obtenidos.
        syntacticAnalysis();// Realiza el análisis sintáctico (estructura del código).
        semanticAnalysis();    // Realiza el análisis semántico (verifica significado y coherencia del código).
        printConsole();        // Muestra el resultado en la consola.
        codeHasBeenCompiled = true;// Marca que el código ha sido compilado exitosamente.
    }
// Método encargado del análisis léxico.
    private void lexicalAnalysis() {
        // El objeto Lexer se encargará de la tokenización del código.
        Lexer lexer;
        try {
            // Crea un archivo temporal "code.encrypter" para almacenar el código actual.
            File codigo = new File("code.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            // Convierte el texto del editor de código a bytes.
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText); // Escribe los bytes en el archivo.
        // Abre el archivo en modo lectura con codificación UTF-8.
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexer = new Lexer(entrada);// Inicializa el lexer con la entrada del archivo.
            // Mientras haya tokens por analizar, agrégalos a la lista.
            while (true) {
                Token token = lexer.yylex();// Lee el siguiente token.
                if (token == null) {// Si no hay más tokens, termina el bucle.
                    break;
                }
                tokens.add(token);// Agrega el token a la lista de tokens.
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
    }
    // Método encargado del análisis sintáctico (estructura del código).
    private void syntacticAnalysis() {
        // Crea una nueva gramática con los tokens y la lista de errores.
        Grammar gramatica = new Grammar(tokens, errors);
        
         /* Eliminación de errores léxicos */
        gramatica.delete(new String[]{"ERROR", "ERROR_1", "ERROR_2"},1);
        
        /* Agrupación de valores (números o colores) */
        gramatica.group("VALOR", "(NUMERO | COLOR)", true);
        
        /* Declaración de variables */
        gramatica.group("VARIABLE" , "TIPO_DATO IDENTIFICADOR OP_ASIG VALOR", true, identProd);
        gramatica.group("VARIABLE" , "TIPO_DATO OP_ASIG VALOR", true,
                2, "error sintactico: falta el identificador en la variable [#, %]");
        
        gramatica.finalLineColumn();
        
        gramatica.group("VARIABLE", "TIPO_DATO IDENTIFICADOR OP_ASIG",
                3,"error sintactico {}: falta el identificador en la variable [#, %]");
        
        gramatica.initialLineColumn();
        
        /* Eliminación de tipos de dato y operadores de asignación */
        gramatica.delete("TIPO_DATO",4,
                "error sintactico{}: falta el identificador en la variable [#, %]");
        gramatica.delete("OP_ASIG",4,
                "error sintactico{}: falta el identificador en la variable [#, %]");
        
         /* Agrupación de identificadores y definición de parámetros */
        gramatica.group("VALOR", "IDENTIFICADOR", true);
        gramatica.group("PARAMETROS", "VALOR(COMA VALOR)+", true);
        
       /* Agrupación de funciones */
        gramatica.group("FUNCION", "(MOVIMIENTO | PINTAR | DETENER_PINTAR | TOMAR |"
                + " LANZAR_MONEDA | VER | DETENER_REPETIR)", true);
        gramatica.group("FUNCION_COMP", "FUNCION PARENTESIS_A (VALOR | PARAMETROS)? PARENTESIS_C", true);
        gramatica.group("FUNCION_COMP", "FUNCION (VALOR | PARAMETROS)? PARENTESIS_C", true,
                6,"error_sintactico {}: falta el parentesis que abre la funcion[#,%]");
        gramatica.finalLineColumn();
        
        gramatica.group("FUNCION_COMP", "FUNCION PARENTESIS_A (VALOR | PARAMETROS)", true,
                7, "error_sintactico {}: falta el parentesis que abre la funcion[#,%]");
        
        gramatica.initialLineColumn();
        
        /* Eliminación de funciones incompletas */
        gramatica.delete("FUNCION",8,"error sintactico {}: la funcion no esta declarada correctamente");
        
        // Ejecuta un bucle para agrupar expresiones lógicas.
        gramatica.loopForFunExecUntilChangeNotDetected(()->{
        gramatica.group("EXP_LOGICA", "(FUNCION_COMP | EXP_LOGICA) (OP_LOGICO (FUNCION_COMP | EXP_LOGICA))+");
        gramatica.group("EXP_LOGICA", "PARENTESIS_A (EXP_LOGICA | FUNCION_COMP) PARENTESIS_C");
        });

       /* Eliminación de operadores lógicos fuera de expresiones */
        gramatica.delete("OP_LOGICO",10,
                "error sintactico{}: el operador logico no esta contenido en una expresion");
        
        /* Agrupación de expresiones lógicas como valores y parámetros */
        gramatica.group("VALOR", "EXP_LOGICA");// Agrupa expresiones lógicas como valores.
        gramatica.group("PARAMETROS", "VALOR (COMA VALOR)+");// Agrupa los parámetros.
        
        /* Agrupación de estructuras de control */
        gramatica.group("EST_CONTROL", "(REPETIR | ESTRUCTURA_SI)");// Agrupa estructuras de control.
        gramatica.group("EST_CONTROL_COMP", "EST_CONTROL PARENTESIS_A PARENTESIS_C");  // Agrupa estructuras de control con paréntesis.
        gramatica.group("EST_CONTROL_COMP", "EST_CONTROL (VALOR | PARAMETROS)");  // Agrupa estructuras de control con parámetros.
        gramatica.group("EST_CONTROL_COMP", "EST_CONTROL PARENTESIS_A (VALOR | PARAMETROS) PARENTESIS_C");  // Agrupa estructuras de control con parámetros y paréntesis.
        
       /* Eliminación de estructuras de control incompletas */
        gramatica.delete("EST_CONTROL",11,
                "error sintactico {}: La estructura de control no esta declarada correctamente [#,%]");
        
         /* Eliminación de paréntesis */
        gramatica.delete(new String []{"PARENTESIS_A","PARENTESIS_C"},12,
            "error sintactico {}: el parentesis [] no esta contenido en una agrupacion [#, %]");
        
        gramatica.finalLineColumn();
        
        /* Verificación de punto y coma al final de las sentencias */
        // Variables
        gramatica.group("VARIABLE_PC", "VARIABLE PUNTO_COMA",true);
        gramatica.group("VARIABLE_PC", "VARIABLE",true,
                13, "error sintactico {}: Falta el punto y coma final de la variable [#, %]");
        
        //Funciones
        gramatica.group("FUNCION_COMP_PC", "FUNCION_COMP PUNTO_COMA");
        gramatica.group("FUNCION_COMP_PC", "FUNCION_COMP", 14,
                "error sintactico {}: falta el punto y coma al final de la declaracion de la funcion");
        
        gramatica.initialLineColumn();
        
        /* Eliminacion del punto y coma */
        gramatica.delete("PUNTO_COMA",15,
                "error sintactico {}: el punto y coma no esta al final de una sentencia");
        
         /* Agrupación de sentencias completas */
        gramatica.group("SENTENCIAS", "(VARIABLE_PC | FUNCION_COMP_PC)+");
        
        /* Agrupación de estructuras de control completas con llaves */
        gramatica.loopForFunExecUntilChangeNotDetected(()->{
            gramatica.group("EST_CONTROL_COMP_LASLC","EST_CONTROL_COMP LLAVE_A (SENTENCIAS)? LLAVE_C",true);
            gramatica.group("SENTENCIAS","(SENTENCIAS | EST_CONTROL_COMP_LASLC)+");
        });
        
        /* Estructuras de funcion incompletas */
        gramatica.loopForFunExecUntilChangeNotDetected(()->{
            gramatica.initialLineColumn();
            
            gramatica.group("EST_CONTROL_COMP_LASLC", "EST_CONTROL_COMP (SENTENCIAS)? LLAVE_C",true,
                    15, "error sintactico {}: falta la llave que abre en la estructura de control");
            
            gramatica.finalLineColumn();
            
            gramatica.group("EST_CONTROL_COMP_LASLC", "EST_CONTROL_COMP LLAVE_A SENTENCIAS",true,
                    15, "error sintactico {}: falta la llave que cierra en la estructura de control");
            
            gramatica.group("SENTENCIAS","(SENTENCIAS | EST_CONTROL_COMP_LASLC)");
        });
        
        gramatica.delete(new String[]{"LLAVE_A","LLAVE_C"},16,
                "error sintactico {}: la llave {} no esta contenida en una agrupacion [#, %]");
        
        
        /* Mostrar gramáticas */
        gramatica.show();
    }

    private void semanticAnalysis() {
        // Crear un mapa para almacenar tipos de datos de identificadores
        HashMap<String, String> identDataType = new HashMap<>();
        identDataType.put("color", "COLOR");
        identDataType.put("número", "NUMERO");
        
        // Validar cada producción de identificadores
        for(Production id: identProd){
            // Comprobar si el tipo de dato del identificador no coincide con su valor
            if (!identDataType.get(id.lexemeRank(0)).equals(id.lexicalCompRank(-1))){
                errors.add(new ErrorLSSL(1 , "error semantico {}: valor no compatible con el tipo de dato", id, true));
            }
            // Validar si el color es un valor hexadecimal cuando el tipo de dato es COLOR
            else if (id.lexicalCompRank(-1).equals("COLOR") && !id.lexemeRank(-1).matches("#[0-9a-fA-F]+")){
                errors.add(new ErrorLSSL(2 , "error semantico {}: el color no es un numero hexadecimal", id, false));
            }
            // Si pasa todas las verificaciones, agregar el identificador a la tabla de símbolos
            else{
                identificadores.put(id.lexemeRank(1), id.lexemeRank(-1));
            }
        }
    }

    private void colorAnalysis() {
        // Limpiar la lista de rangos de colores
        textsColor.clear();
        // Crear un lexer para extraer colores
        LexerColor lexerColor;
        try {
            // Escribir el código fuente en un archivo temporal "color.encrypter"
            File codigo = new File("color.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            // Leer el archivo y pasar el contenido al lexer
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexerColor = new LexerColor(entrada);
            // Procesar los tokens de color y agregarlos a la lista
            while (true) {
                TextColor textColor = lexerColor.yylex();
                if (textColor == null) {
                    break;
                }
                textsColor.add(textColor);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
        // Aplicar el color al editor de texto
        Functions.colorTextPane(textsColor, jtpCode, new Color(40, 40, 40));
    }

    private void fillTableTokens() {
        // Iterar sobre todos los tokens y agregarlos a la tabla de tokens
        tokens.forEach(token -> {
            Object[] data = new Object[]{token.getLexicalComp(), token.getLexeme(), "[" + token.getLine() + ", " + token.getColumn() + "]"};
            Functions.addRowDataInTable(tblTokens, data);
        });
    }

    private void printConsole() {
        int sizeErrors = errors.size();
        // Si hay errores, ordenarlos por línea y columna y mostrarlos en la consola
        if (sizeErrors > 0) {
            Functions.sortErrorsByLineAndColumn(errors);
            String strErrors = "\n";
            for (ErrorLSSL error : errors) {
                String strError = String.valueOf(error);
                strErrors += strError + "\n";
            }
            jtaOutputConsole.setText("Compilación terminada...\n" + strErrors + "\nLa compilación terminó con errores...");
        } else {
            // Si no hay errores, mostrar un mensaje de compilación exitosa
            jtaOutputConsole.setText("Compilación terminada...");
        }
        // Posicionar el cursor al inicio de la consola
        jtaOutputConsole.setCaretPosition(0);
    }

    private void clearFields() {
        // Limpiar la tabla de tokens, la consola de salida y todas las estructuras de datos relacionadas con la compilación
        Functions.clearDataInTable(tblTokens);
        jtaOutputConsole.setText("");
        tokens.clear();
        errors.clear();
        identProd.clear();
        identificadores.clear();
        codeHasBeenCompiled = false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (UnsupportedLookAndFeelException ex) {
                System.out.println("LookAndFeel no soportado: " + ex);
            }
            new Compilador().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnCompilar;
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarC;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JPanel buttonsFilePanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jtaOutputConsole;
    private javax.swing.JTextPane jtpCode;
    private javax.swing.JPanel panelButtonCompilerExecute;
    private javax.swing.JPanel rootPanel;
    private javax.swing.JTable tblTokens;
    // End of variables declaration//GEN-END:variables
}
