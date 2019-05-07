package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;
@TableName("tb_cart")
@Data
@Accessors(chain=true)
public class Cart extends BasePojo{
	@TableId(type=IdType.AUTO)
	private Long id;		//购物车主键
	private Long userId;	
	private Long itemId;
	private String itemTitle;	//商品的标题
	private String itemImage;	//商品图片
	private Long itemPrice;		//商品价格
	private Integer num;		//商品数量
	
}
