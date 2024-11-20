package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorResiduos 
{
    private static final String ARCHIVO_RESIDUOS = "src/resources/files/residuos.dat";
    private List<Residuo> residuos = new ArrayList<>();

    /***
     * Método que se encargar de agregar los residuos en su respectivo archivo
     */
    public void guardarResiduos() 
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_RESIDUOS))) 
        {oos.writeObject(residuos);} 
        catch (IOException e) {System.out.println("Error al guardar residuos: " + e.getMessage());}
    }


    /***
     * Método que se encarga de cargar los residuos registrados
     */
    @SuppressWarnings("unchecked")
    public void cargarResiduos() 
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_RESIDUOS))) 
        {residuos = (List<Residuo>) ois.readObject();} 
        catch (IOException | ClassNotFoundException e) 
        {System.out.println("Error al cargar residuos: " + e.getMessage());}
    }

    /***
     * Método que se encarga de registrar los residuos validando que no se encuentre registrado
     * @param residuo nombre del objeto, material y categoría
     */
    public void registrarResiduo(Residuo residuo) 
    {
        if (residuos.stream().anyMatch(r -> r.getNombre().equalsIgnoreCase(residuo.getNombre()))) 
        {
            System.out.println("Error: El residuo '" + residuo.getNombre() + "' ya está registrado.");
            return;
        }
        residuos.add(residuo);
        guardarResiduos();
    }

    public List<Residuo> getResiduos() {
        return residuos;
    }
}