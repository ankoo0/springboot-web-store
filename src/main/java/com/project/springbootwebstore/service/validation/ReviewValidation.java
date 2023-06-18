package com.project.springbootwebstore.service.validation;

import com.project.springbootwebstore.dto.ReviewDto;

public class ReviewValidation implements Validator<ReviewDto> {

    @Override
    public boolean validate(ReviewDto reviewDto) {
        return false;
    }

    private boolean checkTitleLength(){

        throw new UnsupportedOperationException();
    }

    private boolean checkBodyLength(){

        throw new UnsupportedOperationException();
    }


    private boolean checkRatingValue(){

        throw new UnsupportedOperationException();
    }


    private boolean isNotRepeated(){

        throw new UnsupportedOperationException();
    }




}
