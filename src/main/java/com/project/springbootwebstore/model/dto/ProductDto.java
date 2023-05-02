package com.project.springbootwebstore.model.dto;

import java.util.List;
import java.util.Map;

public class ProductDto {
    private final Long id;
    private final String name;
    private final String shortDescription;
    private final String fullDescription;
    private final String mainThumbnailPath;
    private final List<String> productImagesPaths;
    private final Double price;
    private final Long quantity;
    private final Long rating;
    private final String category;
    private final String subcategory;
    private final Long discount;
    private final Map<String, String> productAttributes;
    private final String creationDate;

    public ProductDto(Long id, String name, String shortDescription, String fullDescription, String mainThumbnailPath, List<String> productImagesPaths, Double price, Long quantity, Long rating, String category, String subcategory, Long discount, Map<String, String> productAttributes, String creationDate) {
        this.id = id;
        this.name = name;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.mainThumbnailPath = mainThumbnailPath;
        this.productImagesPaths = productImagesPaths;
        this.price = price;
        this.quantity = quantity;
        this.rating = rating;
        this.category = category;
        this.subcategory = subcategory;
        this.discount = discount;
        this.productAttributes = productAttributes;
        this.creationDate = creationDate;
    }


    private ProductDto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.shortDescription = builder.shortDescription;
        this.fullDescription = builder.fullDescription;
        this.mainThumbnailPath = builder.mainThumbnailPath;
        this.productImagesPaths = builder.productImagesPaths;
        this.price = builder.price;
        this.quantity = builder.quantity;
        this.rating = builder.rating;
        this.category = builder.category;
        this.subcategory = builder.subcategory;
        this.discount = builder.discount;
        this.productAttributes = builder.productAttributes;
        this.creationDate = builder.creationDate;
    }



   public static class Builder implements Buildable<ProductDto>{
       private  Long id;
       private  String name;
       private  String shortDescription;
       private  String fullDescription;
       private  String mainThumbnailPath;
       private  List<String> productImagesPaths;
       private  Double price;
       private  Long quantity;
       private  Long rating;
       private  String category;
       private  String subcategory;
       private  Long discount;
       private  Map<String, String> productAttributes;
       private  String creationDate;


       public Builder setId(Long id) {
           this.id = id;
           return this;
       }

       public Builder setName(String name) {
           this.name = name;
           return this;
       }

       public Builder setShortDescription(String shortDescription) {
           this.shortDescription = shortDescription;
           return this;
       }

       public Builder setFullDescription(String fullDescription) {
           this.fullDescription = fullDescription;
           return this;
       }

       public Builder setMainThumbnailPath(String mainThumbnailPath) {
           this.mainThumbnailPath = mainThumbnailPath;
           return this;
       }

       public Builder setProductImagesPaths(List<String> productImagesPaths) {
           this.productImagesPaths = productImagesPaths;
           return this;
       }

       public Builder setPrice(Double price) {
           this.price = price;
           return this;
       }

       public Builder setQuantity(Long quantity) {
           this.quantity = quantity;
           return this;
       }

       public Builder setRating(Long rating) {
           this.rating = rating;
          return this;
       }

       public Builder setCategory(String category) {
           this.category = category;
           return this;
       }

       public Builder setSubcategory(String subcategory) {
           this.subcategory = subcategory;
           return this;
       }

       public Builder setDiscount(Long discount) {
           this.discount = discount;
           return this;
       }

       public Builder setProductAttributes(Map<String, String> productAttributes) {
           this.productAttributes = productAttributes;
           return this;
       }

       public Builder setCreationDate(String creationDate) {
           this.creationDate = creationDate;
           return this;
       }

       @Override
        public ProductDto build() {

            return new ProductDto(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public String getMainThumbnailPath() {
        return mainThumbnailPath;
    }

    public List<String> getProductImagesPaths() {
        return productImagesPaths;
    }

    public Double getPrice() {
        return price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Long getRating() {
        return rating;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public Long getDiscount() {
        return discount;
    }

    public Map<String, String> getProductAttributes() {
        return productAttributes;
    }

    public String getCreationDate() {
        return creationDate;
    }
}
