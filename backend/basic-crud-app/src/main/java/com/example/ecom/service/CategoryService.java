package com.example.ecom.service;

import com.example.ecom.dto.CategoryDto;

public interface CategoryService {

    //create
    CategoryDto createCategory(CategoryDto categoryDto);

    //update
    CategoryDto updateCategory(String categoryId,CategoryDto categoryDto);

    //delete
    void  deleteCategory(String categoryId);


    //get all
    //PageableResponse<CategoryDto> getAll(int pageNumber,int pageSize ,String sortBy,String sortDir);

    //List<CategoryDto> getAllCategory();

    //get by id

    CategoryDto getbyId(String categoryId);

    //search
}
