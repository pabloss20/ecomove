package vista;

import javax.swing.*;

public class AdministradorGUI extends JFrame {

    private final JTextField txtNombre;
    private final JTextField txtMensaje;
    private final JTextArea txtConversacion;
    private final JButton btnEmpezar, btnEnviar, btnSalir;
    private String nombre;
    
    @SuppressWarnings("unused")
    public AdministradorGUI() {
        // Configuración de la ventana
        setTitle("Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Crear componentes
        txtNombre = new JTextField();
        txtMensaje = new JTextField();
        txtConversacion = new JTextArea();
        btnEmpezar = new JButton("Empezar");
        btnEnviar = new JButton("Enviar");
        btnSalir = new JButton("Salir");
        
        // Configurar área de texto de conversación
        txtConversacion.setEditable(false);
        
        // Configuración del layout
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        // Añadir los componentes a la ventana
        add(new JLabel("Nombre de usuario"));
        add(txtNombre);
        add(btnEmpezar);
        add(new JScrollPane(txtConversacion));
        add(txtMensaje);
        add(btnEnviar);
        add(btnSalir);
        
        // Configurar visibilidad de los botones y campos
        btnEnviar.setEnabled(false);
        txtMensaje.setEnabled(false);
        btnSalir.setEnabled(false);
        
        // Acción de "Empezar"
        btnEmpezar.addActionListener(e -> empezar());
        
        // Acción de "Enviar"
        btnEnviar.addActionListener(e -> enviarMensaje());
        
        // Acción de "Salir"
        btnSalir.addActionListener(e -> salir());
    }
    
    // Método para iniciar la interacción con el cliente
    private void empezar() {
        nombre = txtNombre.getText();
        if (!nombre.isEmpty()) {
            txtMensaje.setEnabled(true);
            btnEnviar.setEnabled(true);
            btnSalir.setEnabled(true);
            txtNombre.setEnabled(false);
            btnEmpezar.setEnabled(false);
        }
    }
    
    // Método para enviar un mensaje
    private void enviarMensaje() {
        String mensaje = txtMensaje.getText();
        txtConversacion.append(nombre + ": " + mensaje + "\n");
        txtMensaje.setText("");
    }
    
    // Método para cerrar la ventana
    private void salir() {
        System.exit(0);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdministradorGUI().setVisible(true);
        });
    }
}
