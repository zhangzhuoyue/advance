package factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzy
 * @date 2020/7/4 11:28
 */
public class FactoryBean {
    /**
     * 工厂类的两个任务
     * 1. 加载xml ，解析xml中的bean信息，然后使用反射技术实例化bean对象，然后放入map
     * 2. 通过接口方法，根据id获取bean
     */

    private static Map<String ,Object> map = new HashMap<>();
    static {
        InputStream inputStream = FactoryBean.class.getResourceAsStream("beans.xml");
        SAXReader reader = new SAXReader();
        try {
            Document read = reader.read(inputStream);
            Element rootElement = read.getRootElement();
            List<Element> elementList = rootElement.selectNodes("//bean");

            //实例化bean对象
            for (Element element : elementList){
                String id = element.attributeValue("id");
                String clazz = element.attributeValue("class");

                Class<?> aClass = Class.forName(clazz);
                Object newInstance = aClass.newInstance();
                map.put(id,newInstance);
            }

            //处理bean的依赖关系
            List<Element> propertyNodes = rootElement.selectNodes("//property");
            for (Element element : propertyNodes){
                String name = element.attributeValue("name");
                String ref = element.attributeValue("ref");

                //父节点
                Element elementParent = element.getParent();
                String paraentId = elementParent.attributeValue("id");
                Object paraentObject = map.get(paraentId);
                Method[] methods = paraentId.getClass().getMethods();
                for (int i = 0 ;i < methods.length ; i++){
                    if (("set"+name).equalsIgnoreCase(methods[i].getName())){
                        methods[i].invoke(paraentObject,map.get(ref));
                    }
                }
                //维护依赖注入关系
                map.put(paraentId,paraentObject);


            }
        } catch (DocumentException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public  static  Object getBean(String ClassId){
        return map.get(ClassId);
    }


}
