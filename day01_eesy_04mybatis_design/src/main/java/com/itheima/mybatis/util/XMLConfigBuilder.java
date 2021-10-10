package com.itheima.mybatis.util;

import com.itheima.mybatis.annotations.Select;
import com.itheima.mybatis.cfg.Configuration;
import com.itheima.mybatis.cfg.Mapper;
import com.itheima.mybatis.io.Resources;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**使用dom4j技术解析SqlMapConfig.xml文件,dom4j + xpath
 * Created by zhanglin on 2021/7/19.
 */
public class XMLConfigBuilder {


    /**
     *
     * @param config
     * @return
     * @throws IOException
     */
    public static Configuration loadConfiguration(InputStream config) {

        try {
            //定义封装连接信息的配置对象（mybatis的配置对象）
            Configuration cfg = new Configuration();
            // 1.获得解析器对象
             SAXReader reader = new SAXReader();
            // 2.获取document对象
            Document doc = reader.read(config);
            // 3.获取根元素
            Element root = doc.getRootElement();

            System.err.println("rootName: "+root.getName());
            //4、使用xpath中选择指定节点的方式，获取所有property节点
            List<Element> propertyElements = root.selectNodes("//property" );
            //5、遍历节点
            for (Element propertyElement : propertyElements) {
                //判断节点是链接数据库信息的那部分信息
                //取出name属性的值
                String name = propertyElement.attributeValue("name");

                if("driver".equals(name)){
                    //表示驱动
                    //获取property标签中的value属性的值
                    String driver = propertyElement.attributeValue("value");
                    cfg.setDriver(driver);
                }
                if("url".equals(name)){
                    String url = propertyElement.attributeValue("value");
                    cfg.setUrl(url);
                }
                if("username".equals(name)){
                    String username = propertyElement.attributeValue("value");
                    cfg.setUsername(username);
                }
                if("password".equals(name)){
                    String password = propertyElement.attributeValue("value");
                    cfg.setPassword(password);
                }
            }

            // 取出mappers中的所有mapper标签，判断他们使用的resource还是class属性
            List<Element> mapperElements =root.selectNodes("//mappers/mapper");

            for ( Element mapperElement:mapperElements){
                //判断mapperElement使用的是哪个属性
                Attribute attribute = mapperElement.attribute("resource");
                if (attribute !=null ){
                    System.err.println("使用的是XML");

                    //表示有resource属性，用的是xml
                    String mapperPath = attribute.getValue();//获取属性的值
                    //把映射配置文件的内容获取出来，封装在一个map里面
                    Map<String,Mapper> mappers = loadMapperConfiguration(mapperPath);

                    cfg.setMappers(mappers);

                }else{
                    System.err.println("使用的注解");
                    //表示没有resource属性，使用的是注解
                    //获取class属性的值
                    String daoClassPath = mapperElement.attributeValue("class");
                    //根据daoClassPath 获取封装的必要信息
                    Map<String ,Mapper> mappers =loadmapperAnnotation(daoClassPath);
                    //给configuration中的mapper赋值
                    cfg.setMappers(mappers);
                }
            }
            return  cfg;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {

                config.close();
            } catch (Exception e){
               throw new RuntimeException(e);
            }

        }
    }

    /**
     *
     * 根据传入的参数，解析xml，并且封装到map中
     * @param mapperPath 映射文件的位置
     * @return map中包含了获取的唯一标识(key是由dao的全限定类名和方法名组成)，以及
     * 执行所需要的必要信息（value 是一个Mapper对象，里面存放有执行的sql语句和要封装的全限定类名）
     */
    public static Map<String,Mapper> loadMapperConfiguration(String mapperPath) throws IOException {

        InputStream in =null;
        try {
            Map<String,Mapper> mappers = new HashMap<String, Mapper>();
            in= Resources.getResourceAsStream(mapperPath);
            SAXReader reader = new SAXReader();

            Document document = reader.read(in);
            Element root = document.getRootElement();
            String namesapce=root.attributeValue("namespace");
            //获取所有select节点
            List<Element> selectElements = root.selectNodes("//select");
            for (Element selectElement: selectElements){
                //取出id属性的值， 组成map中key的部分
                String id = selectElement.attributeValue("id");
                //取出resultType属性的值，组成map中value的值
                String resultType = selectElement.attributeValue("resultType");
                //取出文本内容 ，组成map中value的一部分
                String queryString= selectElement.getText();
                //创建key
                String key = namesapce+"."+id;
                //创建value
                Mapper mapper = new Mapper();
                mapper.setQueryString(queryString);
                mapper.setResultType(resultType);
                //把key和value存入mappers中
                mappers.put(key,mapper);
            }
            return mappers;

        } catch (Exception e) {
            throw new RuntimeException();
        }finally {
                in.close();
        }
    }


    /**
     *根据传入的参数，得到dao中所有被select注解标注的方法
     * @param daoClassPath
     * @return
     */
    public static Map<String,Mapper> loadmapperAnnotation(String daoClassPath) throws ClassNotFoundException {

        Map<String ,Mapper> mappers = new HashMap<String, Mapper>();
        //得到到接口的字节码对象
        Class daoClass = Class.forName(daoClassPath);
        //得到到接口中的方法数组
        Method[] methods = daoClass.getMethods();
        for (Method method:methods){
            //取出每个方法，判断是否有select注解
               boolean isAnnottated = method.isAnnotationPresent(Select.class);
                if(isAnnottated){
                    //创建Mapper对象
                    Mapper mapper = new Mapper();
                    //取出注解的value属性值
                    Select selectAnno = method.getAnnotation(Select.class);
                    String queryString = selectAnno.value();
                    mapper.setQueryString(queryString);
                    //获取当前方法的返回值，还要求必须带有泛型信息
                    Type type = method.getGenericReturnType();//List<User>
                    //判断type是不是参数化的类型
                    if (type instanceof ParameterizedType){
                        ParameterizedType ptype = (ParameterizedType)type;
                        //得到参数话类型中的实际类型参数
                        Type[] types = ptype.getActualTypeArguments();
                        Class domainClass = (Class)types[0];
                        //获取domainClass的类名
                        String resultType = domainClass.getName();
                        mapper.setResultType(resultType);

                    }
                //组装key的信息.获取方法的名称
                    String methodName = method.getName();
                    String className= method.getDeclaringClass().getName();
                    String key = className +"."+methodName;
                    mappers.put(key,mapper);
                }
        }

        return  mappers;
    }
























}
