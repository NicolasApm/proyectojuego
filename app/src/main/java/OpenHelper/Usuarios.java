package OpenHelper;

public class Usuarios {
    private int idusuario;
    private String nombre,apellido,edad;

    public Usuarios(int idusuario, String nombre, String apellido, String edad){
        setIdusuario(idusuario);
        setNombre(nombre);
        setApellido(apellido);
        setEdad(edad);

    }

    public String getApellido() {
        return apellido;
    }

    private void setApellido(String apellido) {
        this.apellido=apellido;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

   public void setEdad(String edad) {
        this.edad = edad;
    }

}
