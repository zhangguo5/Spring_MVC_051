package com.zhangguo.springmvc51.entities;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * 产品验证器
 *
 */
public class ProductValidator implements Validator {

	//当前验证器可以验证的类型
	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	//执行校验
	@Override
	public void validate(Object target, Errors errors) {
		//将要验证的对象转换成Product类型
		Product entity=(Product)target;
		//如果产品名称为空或为空格，使用工具类
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required", "产品名称必须填写");
		//价格，手动判断
		if(entity.getPrice()<0){
			errors.rejectValue("price", "product.price.gtZero", "产品价格必须大于等于0");
		}
		//产品类型必须选择
		if(entity.getProductType().getId()==0){
			errors.rejectValue("productType.id", "product.productType.id.required", "请选择产品类型");
		}
	}

}







