package com.zhangguo.springmvc51.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.zhangguo.springmvc51.entities.Product;
import com.zhangguo.springmvc51.entities.ProductValidator;
import com.zhangguo.springmvc51.services.ProductService;
import com.zhangguo.springmvc51.services.ProductTypeService;

@Controller
@RequestMapping
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	ProductTypeService productTypeService;

	// 展示与搜索action
	@RequestMapping
	public String index(Model model, String searchKey) {
		model.addAttribute("products", productService.getProductsByName(searchKey));
		model.addAttribute("searchKey", searchKey);
		return "product/index";
	}

	// 删除，id为路径变量
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		productService.deleteProduct(id);
		return "redirect:/";
	}

	// 多删除，ids的值为多个id参数组成
	@RequestMapping("/deletes")
	public String deletes(@RequestParam("id") int[] ids) {
		productService.deletesProduct(ids);
		return "redirect:/";
	}

	// 新增，渲染出新增界面
	@RequestMapping("/add")
	public String add(Model model) {
		// 与form绑定的模型
		model.addAttribute("product", new Product());
		// 用于生成下拉列表
		model.addAttribute("productTypes", productTypeService.getAllProductTypes());
		return "product/add";
	}

	// 新增保存，如果新增成功转回列表页，如果失败回新增页，保持页面数据
	@RequestMapping("/addSave")
	public String addSave(Model model, Product product, BindingResult bindingResult) {

		// 创建一个产品验证器
		ProductValidator validator = new ProductValidator();
		// 执行验证，将验证的结果给bindingResult，该类型继承Errors
		validator.validate(product, bindingResult);

		// 获得所有的字段错误信息，非必要
		for (FieldError fielderror : bindingResult.getFieldErrors()) {
			System.out.println(fielderror.getField() + "，" + fielderror.getCode() + "，" + fielderror.getDefaultMessage());
		}

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
			return "product/add";
		}
	}

	// 编辑，渲染出编辑界面，路径变量id是用户要编辑的产品编号
	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable int id) {
		// 与form绑定的模型
		model.addAttribute("product", productService.getProductById(id));
		// 用于生成下拉列表
		model.addAttribute("productTypes", productTypeService.getAllProductTypes());
		return "product/edit";
	}

	// 编辑后保存，如果更新成功转回列表页，如果失败回编辑页，保持页面数据
	@RequestMapping("/editSave")
	public String editSave(Model model, Product product) {
		try {
			// 根据类型的编号获得类型对象
			product.setProductType(productTypeService.getProductTypeById(product.getProductType().getId()));
			productService.updateProduct(product);
			return "redirect:/";
		} catch (Exception exp) {
			// 与form绑定的模型
			model.addAttribute("product", product);
			// 用于生成下拉列表
			model.addAttribute("productTypes", productTypeService.getAllProductTypes());
			// 错误消息
			model.addAttribute("message", exp.getMessage());
			return "product/edit";
		}
	}

}
