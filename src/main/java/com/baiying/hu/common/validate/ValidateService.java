package com.baiying.hu.common.validate;

/**
 * Created by jmx on 2017/7/10
 */
import com.baiying.hu.common.exception.DaVinceValidationException;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

/**
 * 注解解析
 * @author Goofy
 */
public class ValidateService {

    private static DV dv;

    public ValidateService() {
        super();
    }

    //解析的入口
    public static void valid(Object object) throws Exception{
        //获取object的类型
        Class<? extends Object> clazz=object.getClass();
        //获取该类型声明的成员
        Field[] fields=clazz.getDeclaredFields();
        //遍历属性
        for(Field field:fields){
            //对于private私有化的成员变量，通过setAccessible来修改器访问权限
            field.setAccessible(true);
            validate(field,object);
            //重新设置会私有权限
            field.setAccessible(false);
        }
    }


    public static void validate(Field field,Object object) throws Exception{

        String description;
        Object value;

        //获取对象的成员的注解信息
        dv=field.getAnnotation(DV.class);
        value=field.get(object);
        if(dv==null)return;

        description=dv.description().equals("")?field.getName():dv.description();

        /*************注解解析工作开始******************/
        if(!dv.nullable()){
            if(value==null|| StringUtils.isEmpty(value)){
                throw new DaVinceValidationException(description+"不能为空");
            }
        }

        if(value.toString().length()>dv.maxLength()&&dv.maxLength()!=0){
            throw new DaVinceValidationException(description+"长度不能超过"+dv.maxLength());
        }

        if(value.toString().length()<dv.minLength()&&dv.minLength()!=0){
            throw new DaVinceValidationException(description+"长度不能小于"+dv.minLength());
        }

        if(dv.regexType()!=RegexType.NONE){
            switch (dv.regexType()) {
                case NONE:
                    break;
                case SPECIALCHAR:
                    if(RegexUtils.hasSpecialChar(value.toString())){
                        throw new DaVinceValidationException(description+"不能含有特殊字符");
                    }
                    break;
                case CHINESE:
                    if(RegexUtils.isChinese2(value.toString())){
                        throw new DaVinceValidationException(description+"不能含有中文字符");
                    }
                    break;
                case EMAIL:
                    if(!RegexUtils.isEmail(value.toString())){
                        throw new DaVinceValidationException(description+"地址格式不正确");
                    }
                    break;
                case IP:
                    if(!RegexUtils.isIp(value.toString())){
                        throw new DaVinceValidationException(description+"地址格式不正确");
                    }
                    break;
                case NUMBER:
                    if(!RegexUtils.isNumber(value.toString())){
                        throw new DaVinceValidationException(description+"不是数字");
                    }
                    break;
                case PHONENUMBER:
                    if(!RegexUtils.isPhoneNumber(value.toString())){
                        throw new DaVinceValidationException(description+"手机号码有误");
                    }
                    break;
                default:
                    break;
            }
        }

        if(!dv.regexExpression().equals("")){
            if(value.toString().matches(dv.regexExpression())){
                throw new DaVinceValidationException(description+"格式不正确");
            }
        }
        /*************注解解析工作结束******************/
    }
}
