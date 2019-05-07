package com.jt.pojo;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@TableName("tb_item_cat")
@Data
@Accessors(chain=true)
public class ItemCat extends BasePojo{
	@TableId(type=IdType.AUTO)
	private Long id;
	private Long parentId;
	private String name;
	private Integer status;
	private Integer sortOrder;
	private Boolean isParent;
	private Date created;
	private Date updated;
	
	
	public ItemCat() {
		super();
	}


	public ItemCat(Long id, Long parentId, String name, Integer status, Integer sortOrder, Boolean isParent,
			Date created, Date updated) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.status = status;
		this.sortOrder = sortOrder;
		this.isParent = isParent;
		this.created = created;
		this.updated = updated;
	}
	
	
	
	
	
	
}
