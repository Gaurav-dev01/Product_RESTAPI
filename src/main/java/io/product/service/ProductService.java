package io.product.service;

import java.util.List;
import java.util.Optional;

import io.product.model.Product;

public interface ProductService {
	public List<Product> findAllProduct();

	public Optional<Product> findById(long id);

	public Product saveProduct(Product product);

	public Product updateProduct(Long id, Product product);

	public void deleteProduct(long id);

}
