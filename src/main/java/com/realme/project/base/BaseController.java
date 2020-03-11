package com.realme.project.base;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.realme.common.result.ResultData;

import java.io.IOException;

/**
 * @program: springboot-zk-test
 * @description:
 * @author: realme
 * @create: 2020-03-11 15:11
 **/
public class BaseController {

    //发短信
    public static ResultData sendSMS(String phoneNumber , String code){
        ResultData resultData = new ResultData();
        int appId = 1400083996;
        String appKey = "a05387facaea7c3778959001161acd1b";
        int templateId = 109466;
        String smsSign = "为您发送验证码，3分钟有效";
        try {
            String[] params = {code , "3"};
            //String[] params = {code};
            SmsSingleSender smsSingleSender = new SmsSingleSender(appId , appKey);
            SmsSingleSenderResult result = smsSingleSender.sendWithParam("86" , phoneNumber , templateId , params , smsSign , "" ,"");
            if (result.result == 0){
                resultData.setPushCode(0);
                resultData.setPushMessage("发送成功");
            }else {
                resultData.setPushCode(result.result);
                resultData.setPushMessage("建议联系平台核查原因");
            }
        } catch (com.github.qcloudsms.httpclient.HTTPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (org.json.JSONException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultData;

    }
}
