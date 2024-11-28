package modelo;

public class TestIII {
    public static void main(String[] args) {
        GestorResiduos gestorResiduos = new GestorResiduos();
        gestorResiduos.cargarResiduos();

        GestorSolicitudes gestorSolicitudes = new GestorSolicitudes();
        gestorSolicitudes.cargarSolicitudes();

        // Crear nuevas solicitudes
        Residuo plastico = new Plastico("Botellax", "Ruta12", "Botella de plástico reciclable", 500);
        Solicitud solicitud1 = new Solicitud("Juan Perez", "juan@example.com", plastico);

        gestorSolicitudes.registrarSolicitud(solicitud1);

        // Listar solicitudes pendientes
        System.out.println("Solicitudes pendientes:");
        for (Solicitud s : gestorSolicitudes.getSolicitudesPendientes()) {
            System.out.println(s);
        }

        // Procesar una solicitud
        String resultado = gestorSolicitudes.procesarSolicitud(solicitud1, true, gestorResiduos);
        System.out.println(resultado);

        // Mostrar residuos registrados
        System.out.println("Residuos registrados:");
        for (Residuo r : gestorResiduos.getResiduos()) {
            System.out.println(r);
        }

        // Cargar y mostrar solicitudes después de procesar
        gestorSolicitudes.cargarSolicitudes();
        System.out.println("Solicitudes después de procesar:");
        gestorSolicitudes.imprimirSolicitudes();
    }
}