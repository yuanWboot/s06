package com.zy.spring.ioc.context;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassPathXmlApplicationContext implements ApplicationContext {
    //创建一个HashMap作为IoC容器
    private Map iocContainer = new HashMap();

    public ClassPathXmlApplicationContext() {
        try {
            //得到配置文件的物理地址
            String filePath = this.getClass().getResource("/applicationContext.xml").getPath();
            //如果包含中文，可能会出错，需要解码重新赋值
            filePath = new URLDecoder().decode(filePath, "UTF-8");
            //SAXReader对象加载解析XML，创建File对象，得到xml文档对象document
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File(filePath));
            //获取XML文件中的根节点beans的子节点bean
            List<Node> beans = document.getRootElement().selectNodes("bean");
            for (Node node : beans) {
                Element element = (Element) node;
                String id = element.attributeValue("id");
                String className = element.attributeValue("class");
                Class name = Class.forName(className);
                //无参构造方法创建实例类
                Object obj = name.newInstance();
                //将获取到的id和实例对象放入到IoC容器中
                iocContainer.put(id, obj);
                //获取到XML文件中的property这个节点的属性名name和值value
                List<Node> properties = element.selectNodes("property");
                for (Node p : properties) {
                    Element property = (Element) p;
                    String proName = property.attributeValue("name");
                    String proValue = property.attributeValue("value");

                    //截取字符，从索引为0开始，索引1结束，截取一个字符转换为大写；截取从索引为1开始到最后的字符

                    String setMethodName = "set" + proName.substring(0, 1).toUpperCase() + proName.substring(1);
                    System.out.println("准备执行" + setMethodName + "方法注入数据");
                    Method setMethod = name.getMethod(setMethodName, String.class);
                    setMethod.invoke(obj, proValue);//通过setter方法注入数据
                }
                iocContainer.put(id, obj);
            }
            System.out.println(iocContainer);
            System.out.println("IOC容器初始化完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getBean(String beanId) {
        return iocContainer.get(beanId);
    }
}