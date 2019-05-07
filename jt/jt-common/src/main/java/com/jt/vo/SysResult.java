package com.jt.vo;
/**
 * 该类表示页面统一返回方式
 * 1.状态码 status:200表示ok,201表示error
 * 2.状态信息 msg:状态码对应的具体消息
 * 3.data 表示服务端返回的数据
 */
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class SysResult {

	/**状态码:200表示ok,201表示error*/
	private Integer status;
	/**状态信息:状态码对应的具体消息*/
	private String msg;
	/**正确数据(一般用于存储业务层返回的数据)*/
	private Object data;
	
	
	public SysResult() {
		super();
	}


	public SysResult(String msg) {
		this.msg = msg;
	}


	public SysResult(Object data) {
		this.data = data;
	}


	public SysResult(int status, String msg, Object data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}


	//定义成的静态方法
	public static SysResult ok(String msg,Object data) {
		return new SysResult(200,msg,data);
	}
	
	public static SysResult ok() {
		return new SysResult(200,null,null);
	}
	
	
	
	public static SysResult ok(Object data) {
		return new SysResult(200,null,data );
	}
	
	
	
	//定义失败的静态方法
		public static SysResult fail(String msg,Object data) {
			return new SysResult(201,msg,data);
		}
		
		public static SysResult fail() {
			return new SysResult(201,null,null);
		}
		
		
		public static SysResult fail(String msg) {
			return new SysResult(201,msg,null);
		}
		
		public static SysResult fail(Object data) {
			return new SysResult(201,null,data );
		}
		
		
	
}
