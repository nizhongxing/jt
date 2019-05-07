package com.it;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestHttpClient {
	/**
	 * 实现步骤：
	 * 1.创建httpClient对象
	 * 2.指定url请求地址
	 * 3.指定请求的方式 get/post
	 * 	规则：
	 *		一般查询操作使用get操作
	 *		如果涉及数据入库/更新并且数据很大时采用post提交	
	 *		涉密操作采用post提交	
	 * 4.发起请求获取response对象
	 * 5.判断请求是否正确 检查状态码200
	 * 6.从返回值对象中获取数据（json）之后进行转化对象
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	
	@Test
	public void testGet() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String url = "https://item.jd.com/100001550349.html";
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
		if(httpResponse.getStatusLine().getStatusCode() == 200) {
			System.out.println("请求正确返回！！！");
			String result = EntityUtils.toString(httpResponse.getEntity());
			System.out.println(result);
		}
	}
	
	
	
	
}
