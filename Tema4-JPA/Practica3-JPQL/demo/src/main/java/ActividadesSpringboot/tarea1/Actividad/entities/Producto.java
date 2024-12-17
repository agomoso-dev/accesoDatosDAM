package ActividadesSpringboot.tarea1.Actividad.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @Column(name = "codigo", unique = true, nullable = false, length = 5)  
    private String codigo;

    @Column(name = "nombre", length = 40, nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 100)
    private String descripcion;

    @Column(name = "precio")
    private double precio;
   @ManyToMany(mappedBy = "productos", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cliente> clientes;
    // Relación muchos a uno con Proveedor
    @ManyToOne
    @JoinColumn(name = "proveedor_id") // Nombre de la columna en la base de datos
    private Proveedor proveedor;

    // Constructors
    public Producto() {
    }

    public Producto(String codigo, String nombre, String descripcion, double precio, Proveedor proveedor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.proveedor = proveedor;  
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    // Getters and Setters
}
