package com.xielaoban.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Http 工具类
 * Created by SeanWu on 2017/3/15.
 */
public class XHttpUtils {

    private static final Logger log = LoggerFactory.getLogger(XHttpUtils.class);
    /**
     * 8 位 UCS 转换格式
     */
    private static final String UTF_8 = "UTF-8";

    public static String getResponseString(String url, String charSet) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {

            HttpGet httpget = new HttpGet(url);

            String responseBody = httpclient.execute(httpget, response -> {
                int HttpStatus;
                HttpStatus = response.getStatusLine().getStatusCode();
                if (HttpStatus >= 200 && HttpStatus < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity, charSet) : null;
                } else {
                    log.error("获取 " + url + "的内容失败，错误状态码：" + HttpStatus);
                    return "";
                }
            });
            //return new String(responseBody.getBytes(charSet), UTF_8);
            return responseBody;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException ignored) {
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
