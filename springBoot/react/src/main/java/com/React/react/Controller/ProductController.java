package com.React.react.Controller;

import com.React.react.Model.Product;
import com.React.react.Service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductService Service;

    @GetMapping("/")
    public  String greet(){
        return "Hello Product";
    }
    @GetMapping("/products")
    public List<Product> getallProducts(){
        return  Service.getallProducts();
    }
    @PostMapping("/products")
    public void AddProduct(@RequestBody Product id){
        Service.AddProduct(id);
    }
    @PutMapping("/products")
    public void UpdateProduct(@RequestBody Product id){
        Service.upDateProduct(id);
    }
    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable int id){
       return Service.getProductById(id);
    }
    @DeleteMapping("/products/{id}")
    public  void DeleteProduct(@PathVariable int id){
        Service.DeleteProduct(id);
    }
    @PostMapping("/product")
    public ResponseEntity<?>addProductwithImage(@RequestPart Product product, @RequestPart MultipartFile imageFile){
        try {
            Product product1 = Service.addProductwithImage(product, imageFile);
            return  new ResponseEntity<>(product1, HttpStatus.CREATED);
        }
    catch (Exception e){
return  new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    }
    @GetMapping("product/{id}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int id){
        Product product= Service.getProductById(id);
        byte[] imageFile=product.getImageDate();
        return  ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(imageFile);
    }
//    @DeleteMapping("/product/{id}")
//    public Product productDelete(int id){
//        Service.productDelete(id);
//    }
@PutMapping("/product/{id}")
    public  ResponseEntity<String> updateProduct(@PathVariable int id,@RequestPart Product product ,
                                                 @RequestPart MultipartFile imageFile){
Product product1=null;
      try  {
          product1=Service.updateProduct(id,product,imageFile);
      }
      catch (IOException e){

          return  new ResponseEntity<>("Failed to UPDATED",HttpStatus.BAD_REQUEST);

      }
      if(product1 !=null){
          return new ResponseEntity<>("Updated",HttpStatus.OK);
      }
      else{
          return  new ResponseEntity<>("Failed to UPDATED",HttpStatus.BAD_REQUEST);
      }
    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        try {
            Service.deleteProduct(id);
            return ResponseEntity.ok("Deleted Successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }
    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(String keyword){
List<Product>products=Service.searchProducts(keyword);
return new ResponseEntity<>(products,HttpStatus.OK);
    }

}
