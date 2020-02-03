package com.hase.huatuo.healthcheck.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;

@Component
public class SMSUtils {

    @Value("${spring.sms.host}")
    private String smsServerHost;
    @Value("${spring.sms.path}")
    private String smsServerpath;
    @Value("${spring.sms.key}")
    private String smsServerkey;

    private static SMSUtils smsutils;

    @PostConstruct
    public void init(){
        smsutils=this;
        smsutils.smsServerHost=this.smsServerHost;
        smsutils.smsServerpath=this.smsServerpath;
        smsutils.smsServerkey=this.smsServerkey;
    }

    public static boolean sendSMSVerifyCode(String mobileNumber,String verifyCode) {
        boolean success = false;
        StringBuffer smsBuilder = new StringBuffer("http://").append(smsutils.smsServerHost).append(smsutils.smsServerpath)
                .append("?path=SMS").append("&content=verifyCode").append(verifyCode).append("&mobiles=").append(mobileNumber).append("&key=").append(smsutils.smsServerkey);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(smsBuilder.toString());
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(response.getEntity(), "utf-8");
                ObjectMapper mapper = new ObjectMapper();
                Map<String,Object> result = mapper.readValue(content,Map.class);
                System.out.println("send SMS result "+result.get("code"));
                System.out.println("send SMS result "+content);
                if("200".equals(result.get("code")+"")){
                    success = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                response.close();//关闭连接
                httpClient.close();//关闭浏览器
            }catch (IOException ioe){
                System.out.println(ioe.getMessage());
            }
        }
        return success;
    }
}
