package com.hase.huatuo.healthcheck.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hase.huatuo.healthcheck.model.request.SMSPostBody;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class SMSUtils {

    @Value("${spring.sms.host}")
    private String smsServerHost;
    @Value("${spring.sms.path}")
    private String smsServerpath;
    @Value("${spring.sms.key}")
    private String smsServerkey;
    @Value("${spring.sms.template.register-verify}")
    private String registerVerifyTemplate;
    @Value("${spring.sms.template.alert-health-report}")
    private String alertHealthReportTemplate;

    private static SMSUtils smsutils;

    @PostConstruct
    public void init() {
        smsutils = this;
        smsutils.smsServerHost = this.smsServerHost;
        smsutils.smsServerpath = this.smsServerpath;
        smsutils.smsServerkey = this.smsServerkey;
        smsutils.registerVerifyTemplate = this.registerVerifyTemplate;
    }

    public static boolean sendSMSVerifyCode(String mobileNumber, String verifyCode) {
        SMSPostBody smsPostBody = new SMSPostBody();
        smsPostBody.setKey(smsutils.smsServerkey);
        smsPostBody.setTemplateCode(smsutils.registerVerifyTemplate);
        smsPostBody.setMobiles(mobileNumber);
        Map<String,Object> templateParam = new HashMap<>();
        templateParam.put("code",verifyCode);
        smsPostBody.setTemplateParam(templateParam);
        return sendSMS(smsPostBody);
    }

    public static boolean sendSMS(SMSPostBody smsPostBody) {
        boolean sendSuccess = false;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = "http://"+smsutils.smsServerHost + smsutils.smsServerpath;
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        httpPost.setHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        ObjectMapper mapper = new ObjectMapper();
        String entity = "";
        try {
            entity = mapper.writeValueAsString(smsPostBody);
            httpPost.setEntity(new StringEntity(entity));
            System.out.println("request parameters" + EntityUtils.toString(httpPost.getEntity()));
            response = httpClient.execute(httpPost);
            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "utf-8");
                Map<String, Object> result = mapper.readValue(content, Map.class);
                System.out.println("send SMS result " + result.get("code"));
                System.out.println("send SMS result " + content);
                if ("200".equals(result.get("code") + "")) {
                    sendSuccess = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();//关闭连接
                httpClient.close();//关闭浏览器
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
        return sendSuccess;
    }

    public static boolean sendSMSHuaTuoReport(String mobileNumbers, String status, String city, String workplace) {
        SMSPostBody smsPostBody = new SMSPostBody();
        smsPostBody.setKey(smsutils.smsServerkey);
        smsPostBody.setTemplateCode(smsutils.alertHealthReportTemplate);
        smsPostBody.setMobiles(mobileNumbers);
        Map<String,Object> templateParam = new HashMap<>();
        templateParam.put("status", status);
        String  position= city+"-"+workplace;
        if(position.length()>20){
            position = position.substring(0,20);
        }
        templateParam.put("city_workplace", position);
        smsPostBody.setTemplateParam(templateParam);
        return sendSMS(smsPostBody);
    }
}
