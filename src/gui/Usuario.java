package gui;

import conexion.Conector;

public class Usuario {
    private String nombre;
    private String dni;
    private int cPost;
    private String fechaN;
    private String correo;
    private int tlf;

    public Usuario() {
    }
    public Usuario(String dni, String nombre, int cPost, String fechaN, String correo, int tlf) {
        this.nombre = nombre;
        this.dni = dni;
        this.cPost = cPost;
        this.fechaN = fechaN;
        this.correo = correo;
        this.tlf = tlf;
    }

    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getNombre() {return nombre;}

    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getDni() {
        return dni;
    }

    public int getcPost() {
        return cPost;
    }
    public void setcPost(int cPost) {
        this.cPost = cPost;
    }

    public String getFechaN() {
        return fechaN;
    }
    public void setFechaN(String fechaN) {
        this.fechaN = fechaN;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getTlf() {
        return tlf;
    }
    public void setTlf(int tlf) {
        this.tlf = tlf;
    }
    
    public void save() {
        Conector.connect();
        Conector.insertarUsuario(this);
        Conector.close();
    }
}
