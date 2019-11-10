package iset.miniprj.vapeOrDie.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_category")
	private long idCategory;
	
	@NotBlank( message = "Description is required")
	private String description;


@OneToMany
@JoinColumn(name = "id_category")
@JsonIgnoreProperties({"category"})
private List<Product> products ;

	public Category() {
		
	}

	public Category(@NotBlank(message = "Description is required") String description, List<Product> products) {
		this.description = description;
		this.products = products;
	}

	public long getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(long idCategory) {
		this.idCategory = idCategory;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
