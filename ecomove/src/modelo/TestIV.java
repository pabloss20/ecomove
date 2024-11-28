package modelo;

public class TestIV {
    public static void main(String[] args) {
        // Crear un Gestor de Premios
        GestorPremios gestor = new GestorPremios();
        
        // Cargar premios existentes desde el archivo
        gestor.cargarPremios();
        
        // Crear un nuevo premio
        Premio premio1 = new Premio(100, EstadoPremio.EN_TIENDA, "src/resources/images/premio1.jpg");
        Premio premio2 = new Premio(200, EstadoPremio.NO_DISPONIBLE, "src/resources/images/premio2.jpg");
        
        // Registrar premios
        gestor.registrarPremio(premio1);
        gestor.registrarPremio(premio2);
        
        // Mostrar todos los premios
        System.out.println("Todos los premios:");
        for (Premio p : gestor.getPremios()) {
            System.out.println(p);
        }

        // Mostrar solo los premios disponibles en tienda
        System.out.println("\nPremios en tienda:");
        for (Premio p : gestor.getPremiosEnTienda()) {
            System.out.println(p);
        }
    }
}