package com.atliuwei.chatbot.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @author liuwei
 * @version 1.0
 * @description TODO
 * @date 2023/3/7 17:36
 */
public class ApiTest {

    @Test
    //查询未回答的问题
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("");
        get.addHeader("cookie","");
        get.addHeader("Content-Type","application/json;charset=utf-8");

        CloseableHttpResponse respone = httpClient.execute(get);
        if(respone.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(respone.getEntity());
            System.out.print(res);
        }else{
            System.out.println(respone.getStatusLine().getStatusCode());
        }
    }

    @Test
    //回答问题
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("");
        post.addHeader("cookie","");
        post.addHeader("Content-Type","application/json;charset=utf-8");

        CloseableHttpResponse respone = httpClient.execute(post);

        String paramJson = "";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);

        if(respone.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(respone.getEntity());
            System.out.print(res);
        }else{
            System.out.println(respone.getStatusLine().getStatusCode());
        }
    }

    @Test
    //给ChatGTP发送请求
    public void test_chatGPT() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.openai.com/v1/completions");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer 自行申请 https://beta.openai.com/overview");

        String paramJson = "{\"model\": \"text-davinci-003\", \"prompt\": \"帮我写一个java冒泡排序\", \"temperature\": 0, \"max_tokens\": 1024}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }


}
