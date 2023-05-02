package com.project.springbootwebstore.model.dto;

import java.util.List;

public class ProductCategoryDto {

    private final Long id;
    private final String categoryName;
    private final String categoryImage;
    private final List<String> subcategories;

    public ProductCategoryDto(Long id, String categoryName, String categoryImage, List<String> subcategories) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
        this.subcategories = subcategories;
    }

    private ProductCategoryDto(Builder builder) {

        this.id = builder.id;
        this.categoryName =  builder.categoryName;
        this.categoryImage =  builder.categoryImage;
        this.subcategories = builder.subcategories;
    }

    public Long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public List<String> getSubcategories() {
        return subcategories;
    }

    public static class Builder implements Buildable<ProductCategoryDto>{
        private  Long id;
        private  String categoryName;
        private  String categoryImage;
        private  List<String> subcategories;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setCategoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }

        public Builder setCategoryImage(String categoryImage) {
            this.categoryImage = categoryImage;
            return this;
        }

        public Builder setSubcategories(List<String> subcategories) {
            this.subcategories = subcategories;
            return this;
        }

        @Override
        public ProductCategoryDto build() {
            return new ProductCategoryDto(id,categoryName,categoryImage,subcategories);
        }
    }
}
