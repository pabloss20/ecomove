package modelo;

public class TestII {
    public static void main(String[] args) {
        GestorResiduos gestor = new GestorResiduos();

        // Cargar residuos existentes
        gestor.cargarResiduos();

        // Registrar nuevos residuos
        gestor.registrarResiduo(new Plastico("Botella", "Ruta1", "Botella de plástico reciclable", 500));
        gestor.registrarResiduo(new Plastico("Frasco", "Ruta2", "Frasco de vidrio reutilizable", 300));

        // Mostrar residuos registrados
        System.out.println("Residuos registrados:");
        for (Residuo r : gestor.getResiduos()) {
            System.out.println("- " + r.getNombre() + ": " + r.getInfo());
        }

        gestor.cargarResiduos();
        System.out.println("Residuos registrados:");
        for (Residuo r : gestor.getResiduos()) {
            System.out.println("- " + r.getNombre() + ": " + r.getInfo());
        }
    }
}