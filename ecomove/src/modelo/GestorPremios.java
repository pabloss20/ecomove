package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorPremios 
{
    private static final String ARCHIVO_PREMIOS = "src/resources/files/premios.dat"; 
    private List<Premio> premios; 

    // Constructor
    public GestorPremios() 
    {
        this.premios = new ArrayList<>();
    }

    /***
     * Método que se encarga de guardar los premios en el archivo
     */
    public void guardarPremios() 
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_PREMIOS))) 
        {
            oos.writeObject(premios);
            System.out.println("Premios guardados correctamente.");
        } catch (IOException e) 
        {System.out.println("Error al guardar los premios: " + e.getMessage());}
    }

    /***
     * Método que se encarga de cargar los premios desde el archivo
     */
    @SuppressWarnings("unchecked")
    public void cargarPremios() 
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_PREMIOS))) 
        {
            premios = (List<Premio>) ois.readObject();
            System.out.println("Premios cargados correctamente.");
        } catch (IOException | ClassNotFoundException e) 
        { System.out.println("Error al cargar los premios: " + e.getMessage());}
    }

    /***
     * Método que permite registrar un nuevo premio
     * @param premio Objeto Premio que se desea registrar
     */
    public void registrarPremio(Premio premio) 
    {
        premios.add(premio);
        guardarPremios(); 
    }

    /***
     * Método que devuelve la lista completa de premios
     * @return Lista de premios
     */
    public List<Premio> getPremios() {return premios;}

    /***
     * Método que devuelve los premios disponibles en la tienda
     * @return Lista de premios con estado EN_TIENDA
     */
    public List<Premio> getPremiosEnTienda() 
    {
        List<Premio> enTienda = new ArrayList<>();
        for (Premio premio : premios) 
        {
            if (premio.getEstado() == EstadoPremio.EN_TIENDA) 
            {enTienda.add(premio);}
        }
        return enTienda;
    }
}