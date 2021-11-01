package com.shf.metaObjectHandler;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

//自定义公共字段填充处理器
public class MyMetaObjectHandler extends MetaObjectHandler {
    //    插入操作自动填充
    @Override
    public void insertFill(MetaObject metaObject) {
//        获取到需要被填充的字段的值
        Object fieldValByName = getFieldValByName("name", metaObject);
        if (fieldValByName==null){
//          插入操作 满足条件
            setFieldValByName("name","weiyunhuiu",metaObject);
        }
    }

//    修改操作自动填充
    @Override
    public void updateFill(MetaObject metaObject) {
//        获取到需要被填充的字段的值
        Object fieldValByName = getFieldValByName("name", metaObject);
        if (fieldValByName==null){
//          插入操作 满足条件
            setFieldValByName("name","hahahahaha",metaObject);
        }
    }
}
