package com.cma.services;

import com.cma.commands.ProductForm;
import com.cma.domain.Product;

import java.util.List;

/**
 * Created by jt on 1/10/17.
 */
public interface ProductService {

    List<Product> listAll();

//    Product getById(Long id);

    Product saveOrUpdate(Product product);

//    void delete(Long id);

    Product saveOrUpdateProductForm(ProductForm productForm);
}
