package ActividadesSpringboot.tarea1.Actividad.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

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

    // campo fecha nuevo
    @Column(name = "fecha_compra")
    @Temporal(TemporalType.DATE) 
    private Date fechaCompra;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
        name = "cliente_producto", // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "cliente_id"), // FK de Cliente
        inverseJoinColumns = @JoinColumn(name = "producto_id") // FK de Producto
    )
    private List<Producto> productos;

    // Relación OneToOne con Proveedor
    @OneToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;

    // Constructor vacío
    public Cliente() {}

    // Constructor
    public Cliente(String dni, String nombre, String apellido, String direccion, String telefono, Date fechaCompra) {
        this.nombre = nombre;
        this.dni = dni;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaCompra = fechaCompra;
    }

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

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    // Getters y Setter
}
