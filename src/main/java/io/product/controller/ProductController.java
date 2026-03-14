package io.product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.product.model.Product;
import io.product.service.ProductService;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/hello/{name}")
	public String display(@PathVariable String name) {

		return "Hello " + name;
	}

//	Get all products
	@GetMapping("/products")
	public List<Product> findAllProduct() {

		return productService.findAllProduct();

	}

//	Get product by ID

//	Using java 8
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> findById(@PathVariable long id) {
		try {
			Optional<Product> obj = productService.findById(id);
			return obj.map(product -> new ResponseEntity<>(product, HttpStatus.OK))
					.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
		} catch (Exception e) {

			return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * @GetMapping("/product/{id}") public ResponseEntity<Product>
	 * findById(@PathVariable long id) { try { Optional<Product> obj =
	 * productService.findById(id); if (obj.isPresent()) { return new
	 * ResponseEntity<Product>(obj.get(), HttpStatus.OK); } else { return new
	 * ResponseEntity<Product>(HttpStatus.NOT_FOUND); } } catch (Exception e) {
	 * 
	 * return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR); } }
	 */

//	Save product

	@PostMapping("/product")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		try {
			System.out.println("\n Product created and save successfully.!");
			return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	update product

//	using java 8
	@PutMapping("/product/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
		try {
			Optional<Product> obj = productService.findById(id);
			return obj.map(existingProduct -> {
				productService.updateProduct(id, product);
				System.out.println("Product id: " + id + " updated successfully!");
				return new ResponseEntity<Product>(HttpStatus.OK);
			}).orElseGet(() -> new ResponseEntity<Product>(HttpStatus.NOT_FOUND));


		} catch (Exception e) {
			return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	/*
	 * @PutMapping("/product/{id}") public ResponseEntity<Product>
	 * updateProduct(@PathVariable Long id, @RequestBody Product product) { try {
	 * Optional<Product> obj = productService.findById(id);
	 * 
	 * if (obj.isPresent()) { productService.updateProduct(id, product);
	 * System.out.println("Product id: " + id + " updated successfully.! "); return
	 * new ResponseEntity<Product>(HttpStatus.OK);
	 * 
	 * } else { return new ResponseEntity<Product>(HttpStatus.NOT_FOUND); } } catch
	 * (Exception e) { return new
	 * ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR); } }
	 */

//	DELETE product
//	Using java 8
	@DeleteMapping("/product/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
		try {
			Optional<Product> obj = productService.findById(id);
			return obj.map(product -> {
				productService.deleteProduct(id);
				System.out.println("\nProduct deleted successfully.!");
				return new ResponseEntity<Product>(product, HttpStatus.NO_CONTENT);
			}).orElseGet(() -> new ResponseEntity<Product>(HttpStatus.NOT_FOUND));
		} catch (Exception e) {
			return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/*
	 * @DeleteMapping("/product/{id}") public ResponseEntity<Product>
	 * deleteProduct(@PathVariable Long id) { try { Optional<Product> obj =
	 * productService.findById(id); if (obj.isPresent()) {
	 * productService.deleteProduct(id);
	 * System.out.println("\nProduct deleted successfully.!"); return new
	 * ResponseEntity<Product>(obj.get(), HttpStatus.NO_CONTENT); } else { return
	 * new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	 * 
	 * } } catch (Exception e) { return new
	 * ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * }
	 */

}
