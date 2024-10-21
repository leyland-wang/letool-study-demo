package com.github.leyland;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.leyland.letool.demo.spring.mvc.config.exception.IONoMoneyException;
import com.github.leyland.letool.demo.spring.mvc.convert.MyModel;
import com.github.leyland.letool.demo.spring.mvc.pojo.Child;
import com.github.leyland.letool.demo.spring.mvc.test.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import java.io.IOException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IONoMoneyException {
        System.out.println("Hello world!");


        System.out.println(Arrays.asList("NWXA,NWXS".split(",")).contains("NWXS"));

        System.out.println(System.currentTimeMillis());


        ObjectMapper mapper = new ObjectMapper();
        MyModel myModel = null;
        try {
            myModel = mapper.readValue("{\"modelId\":\"9999\",\"modelType\":\"999\",\"modelName\":\"666\"}", MyModel.class);
            System.out.println("-----------------");
            System.out.println(myModel);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("转换异常！");
        }


        ClassPathXmlApplicationContext application = new ClassPathXmlApplicationContext("spring-config.xml");

    }

    @Test
    public void test1() throws IONoMoneyException {


        throw new IONoMoneyException("KFC Crazy Thursday need 50RMB");
    }

    public class MyClassLoader extends ClassLoader {

        @Override
        public synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            // 判断需要特殊处理的类名
            if (name.startsWith("com.example.foo") || name.startsWith("com.example.bar")) {
                // 不加载指定类，直接返回空，即控制该类不被加载
                return null;
            }
            return super.loadClass(name, resolve);
        }
    }


    @Test
    public void firstSpringSource() {//初始化容器
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring-config-${dev}.xml");
    }


    @Test
    public void test2(){
        //系统环境属性
        Map<String, String> map = System.getenv();
        System.out.println(map);
        //JVM系统属性
        Properties properties = System.getProperties();
        System.out.println(properties);
    }


    @Test
    public void test3(){
        Child child = new Child();
    }


    @Test
    public void setProp() {
        System.setProperty("x","config");
        //初始化容器
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring-${x}.xml");
    }


    @Test
    public void circularPlaceholderReference() {
        //config key 对的value为${config}，在解析value时又会找到config key，造成循环变量引用…………
        System.setProperty("config", "${config}");
        //初始化容器
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring-${config}.xml");
    }


    @Test
    public void ExceTest(){

        extracted();
        System.out.println("上面异常了，不影响下面执行");
    }

    private static void extracted() {
        try {
            try {
                try {
                    System.out.println("这个产生的异常");
                    throw new RuntimeException("3异常");
                } catch (Exception e) {
                    //这里会输出《"3异常"》的方法调用栈
                    e.printStackTrace();
                    throw new RuntimeException("2异常");
                }
            } catch (Exception e) {
                //异常被重新定义了
                throw new RuntimeException("1异常");
            }
        } catch (Exception e) {
            //只会输出最后定义异常的方法调用栈信息，即定位到《"1异常"》这个代码处
            e.printStackTrace();
        } finally {

        }
    }


    @Test
    public void test4() {
        System.out.println(RuntimeException.class.getName());
        System.out.println(RuntimeException.class.getSimpleName());
        System.out.println(int.class.getName());
    }

    @Test
    public void test5() {
        StringBuffer jdbcSql = new StringBuffer();
        jdbcSql.append(" (SELECT ");
        jdbcSql.append("  distinct  " +
                " a.CHDRCOY  " +//公司号
                " ,a.CHDRNUM  " +//8位的保单号
                " from lcndta.p_chdrpf a  " +
                " where a.validflag = '1'   " +
                " and a.OCCDATE>'20180401' " +
                " and exists( select 1 from  lcndta.p_aglfpf b where a.agntcoy = b.agntcoy and a.agntnum = b.agntnum and b.tsalesunt = 'BK003') " +
                " and exists (  select 1  from  lcndta.p_ptrnpf p  where  p.CHDRCOY = a.CHDRCOY and p.CHDRNUM = a.CHDRNUM AND p.validflag <> '2'  " +
                " and p.transtime between ' startTime '  and 'endTime '" +
                //mantis:0154345  BA0077-爱守护尊享版上线建行银保通，获取保单详情取数交易失败  add by xiangquanlong 2019/1/29 start
                " and p.batctrcde in ('TC84','TC94','T669','BC43','BC68','BC46','B673','BC41','BD23','T643','T642','T607')   )" +
                //mantis:0154345  BA0077-爱守护尊享版上线建行银保通，获取保单详情取数交易失败  add by xiangquanlong 2019/1/29 end
                " WITH UR) "
        );
        jdbcSql.append(" union all ");
        jdbcSql.append(" (SELECT ");
        jdbcSql.append("  distinct  " +
                "   a.CHDRCOY  " +//公司号
                "  ,a.CHDRNUM  " +//8位的保单号
                "  from lcndta.p_chdrpf a  " +
                "  where a.validflag = '1'   " +
                "  and a.OCCDATE>'20180401' " +
                "  and exists(  select 1 from  lcndta.p_aglfpf b where  a.agntcoy = b.agntcoy  and a.agntnum = b.agntnum and b.tsalesunt = 'BK003'  ) " +
                //mantis:0154345  BA0077-爱守护尊享版上线建行银保通，获取保单详情取数交易失败  add by xiangquanlong 2019/1/29 start
                "  and exists( select 1 from  lcndta.p_pletpf p2  where  p2.effdate <> 99999999  and p2.CHDRCOY = a.CHDRCOY   and p2.CHDRNUM = a.CHDRNUM  and p2.batctrcde in ('TA83','TA85','TC38','TC60','T33A','T331','T510','T512','T514','T522','T555','T557','T575','T6A7','T6A8','T606','T622','T628','T632','T646', 'TA66','TA82','TCC7','TC61','T273','T676','T679','T680','T643','T642','T607')  " +
                //mantis:0154345  BA0077-爱守护尊享版上线建行银保通，获取保单详情取数交易失败  add by xiangquanlong 2019/1/29 end
                "  and  p2.transtime between 'startTime '  and 'endTime'  ) " + //--#结束时间#
                " WITH UR) "
        );
        System.out.println(jdbcSql.toString());
    }

    private int aaa;

    @Test
    public void test6() {
        for (Field declaredField : User.class.getDeclaredFields()) {
            System.out.println(declaredField);
        }
        int i = 0;
        System.out.println(i);
        System.out.println(aaa);
    }

}