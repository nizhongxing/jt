package com.jt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.util.ObjectMapperUtil;
import com.jt.vo.EasyUITree;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private ItemCatMapper itemCatMapper;
	
	//@Autowired
	private Jedis jedis;
	
	@Override
	public String findItemCatNameById(Long itemCatId) {
		ItemCat itemCat = itemCatMapper.selectById(itemCatId);
		return itemCat.getName();
	}

	@Override
	public List<EasyUITree> findItemCatList(Long parentId) {
		QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("parent_id", parentId);
		List<ItemCat> ItemCatList = itemCatMapper.selectList(queryWrapper);
		
		List<EasyUITree> treeList = new ArrayList<>();
		for (ItemCat itemCat : ItemCatList) {
			EasyUITree uiTree = new EasyUITree();
			uiTree.setId(itemCat.getId())
					.setText(itemCat.getName());
			String state = itemCat.getIsParent()?"closed":"open";
			uiTree.setState(state);
			treeList.add(uiTree);
			
		}
		return treeList;
	}

	@Override
	public List<EasyUITree> findItemCatCacheList(Long parentId) {
		List<EasyUITree> treeList = new ArrayList<>();
		
		//key的定义 类名_变量
		String key = "ITEM_CAT_"+parentId;
		String result = jedis.get(key);
		
		if(StringUtils.isEmpty(result)) {
			//缓存中没有数据，查询真实数据库
			treeList = findItemCatList(parentId);
			//将数据保存到缓存中
			String json = ObjectMapperUtil.toJSON(treeList);
			result = jedis.set(key, json);
			System.out.println("数据库操作！！！");
		}else {
			treeList = ObjectMapperUtil.toObject(result, treeList.getClass());
			System.out.println("redis缓存操作");
		}
		
		return treeList;
	}

	

	
	
	

}
