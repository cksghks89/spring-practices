package com.poscodx.aoptest.service;

import org.springframework.stereotype.Service;

import com.poscodx.aoptest.vo.ProductVo;

@Service
public class ProductService {

	public ProductVo find(String name) {
		System.out.println("[ProductService] finding...");

//		if (true) {
//			throw new RuntimeException("ProductService.find() Exception");
//		}

		return new ProductVo(name);
	}
}
