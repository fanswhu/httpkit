package com.fanswhu.httpkit.core;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Copyright (C), 2017-2021, 宝能有限公司
 * Author: jayce.feng
 * Date: 2021/3/6
 * Description:
 * version v1.0
 */
public class TypeUtils {

    /**
     * 泛型实例化
     * @param cl class
     * @param index  第几个参数
     * @param <T> 泛型
     * @return class
     */
    public static   <T> Class<T> getClazz(Class cl,int index) {
        //当前对象的直接超类的 Type
        Class clazz = null;
        Type genericSuperclass = cl.getGenericSuperclass();
        if(genericSuperclass instanceof ParameterizedType){
            //参数化类型
            ParameterizedType parameterizedType= (ParameterizedType) genericSuperclass;
            //返回表示此类型实际类型参数的 Type 对象的数组
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            clazz= (Class<T>)actualTypeArguments[index];
        }else{
            clazz= (Class<T>)genericSuperclass;
        }
        return  clazz;
    }
}
