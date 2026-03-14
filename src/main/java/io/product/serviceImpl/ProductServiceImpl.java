package io.product.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.product.model.Product;
import io.product.repository.ProductRepository;
import io.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> findAllProduct() {

		return productRepository.findAll();
	}

	@Override
	public Optional<Product> findById(long id) {
		return productRepository.findById(id);

	}

	@Override
	public Product saveProduct(Product product) {

		productRepository.save(product);
		return product;
	}

	@Override
	public Product updateProduct(Long id, Product product) {
		product.setId(id);
		return productRepository.save(product);

	}

	@Override
	public void deleteProduct(long id) {
		productRepository.deleteById(id);

	}

}
