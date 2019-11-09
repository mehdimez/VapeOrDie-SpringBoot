package iset.miniprj.vapeOrDie.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import iset.miniprj.vapeOrDie.entities.Category;
import iset.miniprj.vapeOrDie.entities.Product;
import iset.miniprj.vapeOrDie.exception.ResourceNotFoundException;
import iset.miniprj.vapeOrDie.repository.CategoryRepository;
import iset.miniprj.vapeOrDie.repository.ProductRepository;

@RestController
@CrossOrigin
@RequestMapping("/rest")
public class ProductRestController {
	public static String uploadDirectory = System.getProperty("product.dir") + "/src/main/resources/static/upload";
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return (List<Product>) productRepository.findAll();
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId)
			throws ResourceNotFoundException {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found for this id : " + productId));
		return ResponseEntity.ok().body(product);
	}

	@PostMapping("/products/{idCategory}")
	public Product createProduct(@PathVariable(value = "idCategory") Long idCategory,
			@Valid @RequestBody Product product)
			throws ResourceNotFoundException {
		/*, @RequestParam("files") MultipartFile[] files
		 * StringBuilder fileName = new StringBuilder();
		MultipartFile file = files[0];
		Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
		fileName.append(file.getOriginalFilename());
		  try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		  product.setPicture(fileName.toString());*/
		return categoryRepository.findById(idCategory).map(category -> {
			product.setCategory(category);
			return productRepository.save(product);
		}).orElseThrow(() -> new ResourceNotFoundException("category not found"));
	}

	@PutMapping("/products/{idCategory}/{idProduct}")
	public Product updateProduct(@PathVariable(value = "idProduct") Long idProduct,
			@PathVariable(value = "idCategory") Long idCategory, @Valid @RequestBody Product productRequest)
			throws ResourceNotFoundException {
		if (!categoryRepository.existsById(idCategory))
			throw new ResourceNotFoundException("Category Id" + idCategory + " not found");
		return productRepository.findById(idProduct).map(product -> {
			product.setDescription(productRequest.getDescription());
			product.setPrice(productRequest.getPrice());
			product.setQuantity(productRequest.getQuantity());
			product.setName(productRequest.getName());
			product.setCategory(categoryRepository.findById(idCategory).get());
			return productRepository.save(product);
		}).orElseThrow(() -> new ResourceNotFoundException());
	}

	@DeleteMapping("/products/{idProduct}")
	public ResponseEntity<?> deleteProduct(@PathVariable(value = "idProduct") Long idProduct)
			throws ResourceNotFoundException {
		return productRepository.findById(idProduct).map(product -> {
			productRepository.delete(product);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + idProduct));
	}

	@GetMapping("/products/categoriesList")
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
	@GetMapping("/productsByCategory/{idCategory}")
	public List<Product> getAllProduitParCategories(@PathVariable(value = "idCategory") Long categoryId)
	{
		return productRepository.findByEmailAddress(categoryId);
	}

}
