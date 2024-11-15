package vista;

import java.io.DataInputStream;
import java.io.IOException;
import javax.swing.*;
import modelo.Administrador;
import modelo.Registro;

public class AdministradorGUI extends JFrame {

    private Administrador administrador;

    private final JTextField txtNombre;
    private final JTextField txtMensaje;
    private final JTextArea txtConversacion;
    private final JButton btnEmpezar, btnEnviar, btnSalir;
    private String nombre;
    
    @SuppressWarnings("unused")
    public AdministradorGUI() {
        // Configuración de la ventana
        setTitle("Administrador");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        txtNombre = new JTextField();
        txtMensaje = new JTextField();
        txtConversacion = new JTextArea();
        btnEmpezar = new JButton("Empezar");
        btnEnviar = new JButton("Enviar");
        btnSalir = new JButton("Salir");
        
        txtConversacion.setEditable(false);
        
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JLabel("Nombre de usuario"));
        add(txtNombre);
        add(btnEmpezar);
        add(new JScrollPane(txtConversacion));
        add(txtMensaje);
        add(btnEnviar);
        add(btnSalir);

        btnEnviar.setEnabled(false);
        txtMensaje.setEnabled(false);
        btnSalir.setEnabled(false);
        
        btnEmpezar.addActionListener(e -> empezar());

        btnEnviar.addActionListener(e -> enviarMensaje());

        btnSalir.addActionListener(e -> salir());
    }
    
    private void empezar() {
        nombre = txtNombre.getText();
        if (!nombre.isEmpty()) {
            
            administrador = new Administrador("Juan", "Sanchez", "jpxd@gmail.com", "12345678");
            Registro.registrarUsuario("Juan", "Sanchez", "jpxd@gmail.com", "12345678", "administrador");

            txtMensaje.setEnabled(true);
            btnEnviar.setEnabled(true);
            btnSalir.setEnabled(true);
            txtNombre.setEnabled(false);
            btnEmpezar.setEnabled(false);

            recibirMensajes();
        }
    }

    private void enviarMensaje() {
        String mensaje = txtMensaje.getText();
        txtConversacion.append(nombre + ": " + mensaje + "\n");
        txtMensaje.setText("");
        administrador.enviarMensaje(mensaje);
    }

    private void salir() {
        System.exit(0);
    }   

    public void recibirMensajes() {
        new Thread(() -> {
            try {
                DataInputStream in = new DataInputStream(administrador.getSocket().getInputStream());
                while (true) {
                    String mensaje = in.readUTF();
                    txtConversacion.append("Otro: " + mensaje + "\n");
                }
            } catch (IOException ex) {
                
            }
        }).start();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ClienteGUI().setVisible(true);
        });
    }
}
