package tpo.usersmodule.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carritos")
public class Carrito {
	@Column(nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int idCarrito;
	@ManyToMany//(cascade = CascadeType.ALL)
	@JoinTable(
			name = "user_cart",
			joinColumns = @JoinColumn(name = "idCarrito"),
			inverseJoinColumns = @JoinColumn(name = "product_id")
	)
	private List<Producto> productos;


	public Carrito() {
		this.productos = new ArrayList<>();
	}

	public Carrito(int idCarrito, List<Producto> productos) {
		this.idCarrito = idCarrito;
		this.productos = new ArrayList<>();
	}

	public int getIdCarrito() {
		return idCarrito;
	}

	public void setIdCarrito(int idCarrito) {
		this.idCarrito = idCarrito;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
}
