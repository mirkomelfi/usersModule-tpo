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
	String username;
	@ManyToMany//(cascade = CascadeType.ALL)
	@JoinTable(
			name = "user_cart",
			joinColumns = @JoinColumn(name = "username"),
			inverseJoinColumns = @JoinColumn(name = "product_id")
	)
	private List<Producto> productos;


	public Carrito() {
	}

	public Carrito(String username, List<Producto> productos) {
		this.username = username;
		this.productos = productos;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
}
