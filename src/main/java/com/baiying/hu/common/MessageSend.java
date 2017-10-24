package com.baiying.hu.common;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * Created by jmx
 * 2017/10/20.
 */
public class MessageSend {

    //初始化ascClient需要的几个参数
    private static final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
    private static final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
    private static final String accessKeyId = "LTAITGgWfahgK9pc";//你的accessKeyId,参考本文档步骤2
    private static final String accessKeySecret = "q3A0cgDn4uFf7sMJOUhvlZxUv1TPMV";

    public static  SendSmsResponse sendSms(String phone, String codeValue) throws ClientException {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);

        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("呼百应");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_103805019");

        request.setTemplateParam("{\"code\":" + codeValue + "}");

        return acsClient.getAcsResponse(request);
    }

    public static synchronized String createRandomVcode() {
        //验证码
        StringBuilder vcode = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            vcode.append((int) (Math.random() * 9));
        }
        return vcode.toString();
    }

//    public static void main(String[] args) throws ClientException {
////        String code = MessageSend.createRandomVcode();
////        SendSmsResponse sendSmsResponse = MessageSend.sendSms("18721956624", code);
////        System.out.println(sendSmsResponse);
//        LocalDateTime d7 = LocalDateTime.now().plusMinutes(5);
////        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
//        Date out = Date.from(d7.atZone(ZoneId.systemDefault()).toInstant());
//        System.out.println(out);
//
//        Date in = new Date();
//        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
//        System.out.println(ldt);
//    }
}
