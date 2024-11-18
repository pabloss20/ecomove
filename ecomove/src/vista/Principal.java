package vista;

import java.awt.event.ActionEvent;
import javax.swing.*;
import modelo.Servidor;

public class Principal extends JFrame {

    Servidor servidor;

    private final JButton btnServidor;
    private JButton btnCliente;
    private JButton btnAdministrador;

    @SuppressWarnings("unused")
    public Principal() {

        setTitle("Ventana Principal");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        btnServidor = new JButton("Servidor");
        btnCliente = new JButton("Cliente");
        btnAdministrador = new JButton("Administrador");

        btnServidor.addActionListener((ActionEvent e) -> {

            // Se instancia el servidor y se deshabilita el botón para iniciar el servidor
            servidor = new Servidor(6000);
            Thread thread = new Thread(servidor);
            thread.start();

            btnServidor.setEnabled(false);
        });

        btnCliente.addActionListener((ActionEvent e) -> {

            btnCliente.setEnabled(false);
            
            ClienteGUI clienteGUI = new ClienteGUI();
            clienteGUI.setVisible(true);
        });

        btnAdministrador.addActionListener((ActionEvent e) -> {

            btnAdministrador.setEnabled(false);

            AdministradorGUI administradorGUI = new AdministradorGUI();
            administradorGUI.setVisible(true);
        });

        setLayout(null);
        btnServidor.setBounds(50, 30, 200, 30);
        btnCliente.setBounds(50, 70, 200, 30);
        btnAdministrador.setBounds(50, 110, 200, 30);

        add(btnServidor);
        add(btnCliente);
        add(btnAdministrador);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Principal().setVisible(true);
        });
    }
}
