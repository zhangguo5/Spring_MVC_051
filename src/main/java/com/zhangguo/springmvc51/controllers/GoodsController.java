package com.zhangguo.springmvc51.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zhangguo.springmvc51.entities.Product;
import com.zhangguo.springmvc51.services.ProductService;
import com.zhangguo.springmvc51.services.ProductTypeService;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	@Autowired
	ProductService productService;
	@Autowired
	ProductTypeService productTypeService;
	
	// 新增，渲染出新增界面
	@RequestMapping("/add")
	public String add(Model model) {
		
		// 与form绑定的模型
		model.addAttribute("product", new Product());
		// 用于生成下拉列表
		model.addAttribute("productTypes", productTypeService.getAllProductTypes());
		return "product/addGoods";
	}

	// 新增保存，如果新增成功转回列表页，如果失败回新增页，保持页面数据
	@RequestMapping("/addGoodsSave")
	public String addSave(Model model, @Valid Product product, BindingResult bindingResult) {

		// 是否存在错误，如果没有，执行添加
		if (!bindingResult.hasErrors()) {
			// 根据类型的编号获得类型对象
			product.setProductType(productTypeService.getProductTypeById(product.getProductType().getId()));
			productService.addProduct(product);
			return "redirect:/";
		} else {
			// 与form绑定的模型
			model.addAttribute("product", product);
			// 用于生成下拉列表
			model.addAttribute("productTypes", productTypeService.getAllProductTypes());
			return "product/addGoods";
		}
	}
	
	@RequestMapping("/products")
	@ResponseBody
	public List<Product> getProduct(){
		return productService.getAllProducts();
	}
}
