package ActividadesSpringboot.tarea1.Actividad.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", unique = true, length = 40, nullable = false)
    private String nombre;

    @Column(name = "apellido", length = 40)
    private String apellido;

    @Column(name = "direccion", length = 100)
    private String direccion;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "dni", length = 9, nullable = false, unique = true)
    private String dni;

    @ManyToMany
    @JoinTable(
        name = "cliente_producto", // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "cliente_dni"), // Clave foránea de Cliente
        inverseJoinColumns = @JoinColumn(name = "producto_id") // Clave foránea de Producto
    )
    private Set<Producto> productos = new HashSet<>();


    // Relación OneToOne con Proveedor
    @OneToOne
    @JoinColumn(name = "id_proveedor") 
    private Proveedor proveedor;

    // Constructor vacío
    public Cliente() {
    }

    // Otros constructores
    public Cliente(String dni, String nombre) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public Cliente(String dni, String nombre, String apellido) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Cliente(String dni, String nombre, String apellido, String direccion, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.dni = dni;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    public void agregarProducto(Producto producto) {
        this.productos.add(producto);
        producto.getClientes().add(this);
    }

    public void eliminarProducto(Producto producto) {
        this.productos.remove(producto);
        producto.getClientes().remove(this);
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }


}
