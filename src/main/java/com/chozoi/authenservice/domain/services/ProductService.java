package com.chozoi.authenservice.domain.services;

import com.chozoi.authenservice.app.base.reponse.ProductResult;
import com.chozoi.authenservice.app.dto.ProductDTO;
import com.chozoi.authenservice.domain.ModelMapper;
import com.chozoi.authenservice.domain.entities.*;
import com.chozoi.authenservice.domain.entities.categories.Category;
import com.chozoi.authenservice.domain.entities.categories.CategoryAttributeValue;
import com.chozoi.authenservice.domain.entities.products.Product;
import com.chozoi.authenservice.domain.exceptions.ResourceNotFoundException;
import com.chozoi.authenservice.domain.exceptions.TokenNotFoundException;
import com.chozoi.authenservice.domain.exceptions.UserNotFoundException;
import com.chozoi.authenservice.domain.model.TokenInfo;
import com.chozoi.authenservice.domain.repositories.*;
import com.chozoi.authenservice.domain.services.category.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
public class ProductService extends BaseService {
  @Autowired private ProductRepository productRepository;

  @Autowired private CategoryService categoryService;
  @Autowired private CacheManagerService cacheManagerService;
  @Autowired private CategoryAttributeValueRepository categoryAttributeValueRepository;
  @Autowired private UserRepository userRepository;

  @Autowired private ModelMapper modelMapper;
  private String[] images = {
    "https://salt.tikicdn.com/cache/550x550/ts/product/0a/fb/75/740106b009f436911a8ea4efdf7edadf.jpg",
    "https://salt.tikicdn.com/cache/550x550/media/catalog/product/a/m/american-edition-5-student-book.jpg",
    "https://salt.tikicdn.com/cache/w1200/ts/product/cc/6f/1a/bddcfae10b1ae4877dee0d85d11a325e.jpg",
    "https://salt.tikicdn.com/cache/w1200/ts/product/00/47/df/b02b462394bc3c59e5876ec0d9cb6ae8.jpg",
    "https://salt.tikicdn.com/cache/550x550/ts/product/dd/28/91/4a7bb0e7be810aade0c4ab45427508a4.jpg"
  };

  @Transactional
  public ResponseEntity createListProduct(String token) {
    try {
      TokenInfo tokenInfo = new TokenInfo();
      long totalProduct = getTotalProduct();
      List<Category> categoryList = categoryService.getListAllCategories();
      List<Product> productList = new ArrayList<>();
      Random random = new Random();
      for (long i = totalProduct + 1; i <= totalProduct + 5; i++) {
        Product product = new Product();
        product.setName("Iphone" + i);
        product.setState("Còn hàng");
        product.setDescriptionShort("Chi tiết sản phẩm" + i);
        product.setDescriptionLong("Thông tin nổi bật" + i);

        /** random quantity */
        int quantityMin = 1;
        int quantityMax = 30;
        int randomquantity = quantityMin + (quantityMax - quantityMin) * random.nextInt(10);

        product.setQuantity(randomquantity);
        product.setMainImage(images[random.nextInt(images.length)]);

        /** random price */
        double rangeMin = 4;
        double rangeMax = 30;
        double randomPrice = rangeMin + (rangeMax - rangeMin) * random.nextDouble();
        product.setPrice(randomPrice);

        /** random category */
        product.setCategory(categoryList.get(random.nextInt(categoryList.size())));
        productList.add(product);
      }
      productRepository.saveAll(productList);
      return new ResponseEntity<>("Fake Data thành công", HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public ResponseEntity createProduct(String token, ProductDTO dto) {

    TokenInfo tokenInfo = cacheManagerService.getToken(token);
    User user = userRepository.getOne(tokenInfo.getUserId());

    if (Objects.isNull(tokenInfo)) {
      throw new TokenNotFoundException("Token bị sai rồi bạn");
    }
    List<CategoryAttributeValue> categoryAttributeValues = new ArrayList<>();
    for (Integer id : dto.getCategoryAttributeValueID()) {
      CategoryAttributeValue categoryAttributeValue = new CategoryAttributeValue();
      categoryAttributeValue.setId(id);
      categoryAttributeValues.add(categoryAttributeValue);
    }
    List<Category> categoryList = categoryService.getListAllCategories();
    Product product = new Product();
    product.setName(dto.getName());
    product.setQuantity(dto.getQuantity());
    product.setDescriptionShort(dto.getDescription_Short());
    product.setPrice(dto.getPrice());
    product.setRate(dto.getRate());
    product.setState(dto.getState());
    product.setDescriptionLong(dto.getDescription_Long());
    product.setPrice(dto.getPrice());
    product.setCategory(categoryService.findOne(dto.getCategoryID()));
    product.setCreateBy(tokenInfo.getName());
    product.setUser(user);
    product.setCategoryAttributeValues(categoryAttributeValues);
    productRepository.save(product);
    return new ResponseEntity<>("Create success product", HttpStatus.CREATED);
  }

  public ResponseEntity deleteProduct(String token, Integer productID) {

    TokenInfo tokenInfo = cacheManagerService.getToken(token);
    if (Objects.isNull(tokenInfo)) {
      throw new TokenNotFoundException("Token bị sai rồi bạn");
    }
    Product product = productRepository.findProductByIdAndUserId(productID, tokenInfo.getUserId());
    if (Objects.isNull(product)) {
      throw new ResourceNotFoundException("Product not found ID : " + productID);
    }
    productRepository.deleteById(productID);
    return ResponseEntity.ok(true);
  }

  public ResponseEntity update(String token, Integer productID, ProductDTO dto) {
    TokenInfo tokenInfo = cacheManagerService.getToken(token);
    if (Objects.isNull(tokenInfo)) {
      throw new TokenNotFoundException("Token bị sai rồi bạn");
    }
    Product product = productRepository.findProductByIdAndUserId(productID, tokenInfo.getUserId());
    if (Objects.isNull(product)) {
      throw new ResourceNotFoundException("Product not found ID : " + productID);
    }
    product.setPrice(dto.getPrice());
    product.setRate(dto.getRate());
    product.setName(dto.getName());
    product.setQuantity(dto.getQuantity());
    product.setDescriptionLong(dto.getDescription_Long());
    product.setDescriptionShort(dto.getDescription_Short());
    product.setState(dto.getState());
    productRepository.save(product);
    return ResponseEntity.ok(true);
  }

  public ResponseEntity infoProduct(String token, Integer productID) {
    TokenInfo tokenInfo = cacheManagerService.getToken(token);
    if (Objects.isNull(tokenInfo)) {
      throw new TokenNotFoundException("Token bị sai rồi bạn");
    }
    Product product = productRepository.findProductByIdAndUserId(productID, tokenInfo.getUserId());
    if (Objects.isNull(product)) {
      throw new ResourceNotFoundException("Product not found ID : " + productID);
    }
    ProductDTO dto = modelMapper.productToResponse(product);
    return ResponseEntity.ok(dto);
  }

  public ResponseEntity allProduct(String token) {
    List<Product> productList = getListAllProduct();
    List<ProductDTO> listDto = new ArrayList<>();
    for (Product product : productList) {
      ProductDTO dto = modelMapper.productToResponse(product);
      listDto.add(dto);
    }
    return ResponseEntity.ok(listDto);
  }

  public ResponseEntity pageProduct(Integer curunpage, Integer totalpage) {
    Page<Product> productList = getPage(curunpage, totalpage);
    List<ProductResult> resultList = new ArrayList<>();
    for (Product product : productList) {
      ProductResult result = modelMapper.productResultToResponse(product);
      resultList.add(result);
    }
    return ResponseEntity.ok(resultList);
  }

  public Page<Product> getPage(Integer curunPage, Integer totalPage) {
    try {
      Pageable pageable = PageRequest.of(curunPage, totalPage);
      Page<Product> productPage = productRepository.findAll(pageable);
      return productPage;
    } catch (Exception e) {
      return null;
    }
  }

  public ResponseEntity<?> findByCategory(String token, Integer categoryID) {
    TokenInfo tokenInfo = cacheManagerService.getToken(token);
    if (Objects.isNull(tokenInfo)) {
      throw new TokenNotFoundException("Token bị sai rồi bạn");
    }
    List<Product> list = productRepository.findAllByCategory_Id(categoryID);
    if (Objects.isNull(list)) {
      throw new UserNotFoundException("List trống");
    }
    List<ProductDTO> dtoList = new ArrayList<>();
    for (Product product : list) {
      ProductDTO dto = modelMapper.productToResponse(product);
      dtoList.add(dto);
    }
    return ResponseEntity.ok(dtoList);
  }

  public long getTotalProduct() {
    return productRepository.getTotalProduct();
  }

  public List<Product> getListAllProduct() {
    try {
      return productRepository.findAll();
    } catch (Exception e) {
      return new ArrayList<>();
    }
  }
}
