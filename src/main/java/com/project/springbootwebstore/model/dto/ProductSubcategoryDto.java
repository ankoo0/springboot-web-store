package com.project.springbootwebstore.model.dto;

public class ProductSubcategoryDto {
    private final Long id;
    private final String subcategoryName;
    private final String category;
    private final String subcategoryImage;

    public ProductSubcategoryDto(Long id, String name, String category, String subcategoryImage) {
        this.id = id;
        this.subcategoryName = name;
        this.category = category;
        this.subcategoryImage = subcategoryImage;
    }

    private ProductSubcategoryDto(Builder builder) {
        this.id = builder.id;
        this.subcategoryName = builder.name;
        this.category = builder.category;
        this.subcategoryImage = builder.subcategoryImage;
    }


    public Long getId() {
        return id;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public String getCategory() {
        return category;
    }

    public static class Builder implements Buildable<ProductSubcategoryDto>{

        private  Long id;
        private  String name;
        private  String category;
        private String subcategoryImage;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder setSubcategoryImage(String subcategoryImage) {
            this.subcategoryImage = subcategoryImage;
            return this;
        }

        @Override
        public ProductSubcategoryDto build() {
            return new ProductSubcategoryDto(id,name,category, subcategoryImage) ;
        }
    }
}
