package com.jt.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class FileVo {
	//{"error":0,"url":"图片的保存路径","width":图片的宽度,"height":图片的高度}
	//文件上传后的文件的vo对象
	private Integer error=0;
	private String url;
	private Integer Width;
	private Integer height;
	
	
	
	public FileVo(Integer error, String ul, Integer width, Integer height) {
		super();
		this.error = error;
		this.url = url;
		Width = width;
		this.height = height;
	}



	public FileVo() {
		super();
	}
	
	
}
