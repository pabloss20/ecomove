package modelo;

import java.util.ArrayList;

public class Contenedor 
{

    private int capacidadMaxima;
    private int capacidadActual;
    private final ArrayList<Residuo> residuos = new ArrayList<>();

    public Contenedor()
    {
        this.capacidadMaxima = 50;
        this.capacidadActual = 0;
    }

    public int getCapacidadMaxima(){return capacidadMaxima;}
    public int getCapacidadActual(){return capacidadActual;}
    public ArrayList<Residuo> getResiduos(){return residuos;}

    public void setCapacidadMaxima(int cupo){this.capacidadMaxima = cupo;}
    public void setCapacidadActual(int incremento){this.capacidadActual += incremento;}

    /***
     * Método que se encarga de agregar un residuo al arraylist de residuos
     * @param residuo objeto de tipo Residuo
     * @return resultado de la operación
     */
    public boolean insertarResiduo(Residuo residuo)
    {
        if (capacidadActual + 5 <= capacidadMaxima)
        {
            residuos.add(residuo);
            setCapacidadActual(5);
            return true;
        }
        else {return false;}
    }

    /***
     * Método que se encarga de eliminar un residuo del arraylist
     * @param residuo objeto de tipo Residuo
     * @return
     */
    public boolean eliminarResiduo(Residuo residuo)
    {
        if(residuos.remove(residuo))
        {
            setCapacidadActual(-5);
            return true;
        }
        else {return false;}
    }

    /*** 
     * Método que se encarga de limpiar el contenedor (arraylist) 
     */
    public void limpiarContenedor()
    {
        residuos.clear();
        capacidadActual = 0;
    }

    // Puede que sea necesario un método para mostrar lo que hay en el contenedor
}
