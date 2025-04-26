package com.React.react.Service;

import com.React.react.Model.Product;
import com.React.react.Reposoitry.ProductRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo Repo;
    public List<Product> getallProducts() {
      return   Repo.findAll();
    }
    public Product getProductById(int id){
       return  Repo.findById(id).orElse(new Product());
    }
    public  void upDateProduct(Product id){
         Repo.save(id);
    }
    public void AddProduct(Product id){
         Repo.save(id);
    }
    public  void DeleteProduct(int id){
          Repo.deleteById(id);
    }

    public Product addProductwithImage(Product product, MultipartFile imageFile) throws IOException {
     product.setImageName(imageFile.getOriginalFilename());
     product.setImageType(imageFile.getContentType());
     product.setImageDate(imageFile.getBytes());
     return Repo.save(product);


    }
    public Product updateProduct(int id,Product product,MultipartFile imageFile) throws IOException{
        product.setImageDate(imageFile.getBytes());
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        return Repo.save(product);
    }
    public void deleteProduct(int id){
        if (Repo.existsById(id)) {
            Repo.deleteById(id);
        } else {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
    }

    public List<Product> searchProducts(String keyword) {
        return  Repo.searchProduct(keyword);
    }
}
