package com.zy.spring.ioc.context;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class ClassPathXmlApplication implements ApplicationContext{
     private Map iocContainer = new HashMap();
    public ClassPathXmlApplication(){
        try {
            //得到配置文件的物理地址
      String filePath = this.getClass().getResource("/applicationContext.xml").getPath();
      //如果包含中文，可能会出错，需要解码重新赋值
      filePath = new URLDecoder().decode(filePath,"UTF-8");
      //SAXReader对象加载解析XML，创建File对象，得到xml文档对象document
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File(filePath));
        }catch (Exception e){

        }
    }
    public Object getBean(String beanId) {
        return null;
    }
}
