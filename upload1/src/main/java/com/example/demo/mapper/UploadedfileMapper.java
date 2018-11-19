package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.Uploadedfile;

@Mapper
public interface UploadedfileMapper {
	Uploadedfile findOne(int id);
	List<Uploadedfile> findAll();
	void insert(Uploadedfile uploadedFile);
	void delete(int id);
}
