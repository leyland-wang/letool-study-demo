package com.github.leyland.letool.demo.spring.listener;

/**
 * @ClassName MyApplicationEvent
 * @Description TODO
 * @Author Rungo
 * @Date 2023/4/16
 * @Version 1.0
 **/

import org.springframework.context.ApplicationEvent;

/**
 * �Զ����¼�
 */
public class MyApplicationEvent extends ApplicationEvent {
    private String name;


    /**
     * @param source �¼���������֮�����Ķ��󣨴Ӳ�Ϊnull��
     */
    public MyApplicationEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
