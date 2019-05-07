package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jt.anno.CacheAnnotation;
import com.jt.anno.CacheAnnotation.CACHE_TYPE;
import com.jt.service.ItemCatService;
import com.jt.vo.EasyUITree;


@RestController
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
	/**
	 * 关于框架编码的说明
	 * 使用旧的SSM框架时3-4版本
	 * 1.使用旧的框架时，如果返回数据为String，则将数据通过@ResponseBody返回时，采用ISO-8859-1编码格式
	 * 返回数据必定乱码
	 * 2.如果返回数据为对象时（pojo/vo），采用utf-8格式
	 * 
	 * 当使用springboot时，返回的数据都是UTF-8
	 * 
	 * Post乱码问题response.setCon
	 * 
	 * @RequestMapping(value="/queryItemName",produces="text/html;chartset=utf-8")
	 * @param itemCatId
	 * @return
	 */
	
	
	
	@RequestMapping(value="/queryItemName")
	public String findItemCatNameById(Long itemCatId) {
		
		return itemCatService.findItemCatNameById(itemCatId);
		
	}
	
	//实现商品分类信息树形结构展现  ?id=350
	/**
	 * @RequestParam：将请求参数绑定到你控制器的方法参数上（是springmvc中接收普通参数的注解）
	 * defaultValue:如果没有传递参数,则设定默认值
	 * name:参数名称
	 * required:true/false 是否必须传递参数
	 * value:表示参数名称
	 * @param parentId
	 * @return
	*/
	
	@RequestMapping("/list")
	@CacheAnnotation(index=0,cacheType=CACHE_TYPE.FIND)
	public List<EasyUITree> findItemCatList
			(@RequestParam(name="id" ,defaultValue="0")Long parentId) {
		
		//获取一级商品分类信息
		return itemCatService.findItemCatList(parentId);
		
	}
	
	
	
	
}
