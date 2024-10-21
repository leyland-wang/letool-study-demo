package com.github.leyland.letool.demo.basic.core.anno;

import java.lang.annotation.*;


@Target({ ElementType.FIELD })
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeMapper {

    /**
     * ���ݿ��ֶΣ����Ϊ "" �򲻽��д��� -- һ��ֻ��Ҫ�����Լ���
     * @return
     */
    String value();

    /**
     * JavaBean��Ա��������   --  ����Map��ʽ������
     * @return
     */
    String properties() default "";


    /**
     * ��������     --  ����Map��ʽ������
     * @return
     */
    Class<?> type() default void.class;

    /**
     * �������͸�ʽ��
     * @return
     */
    String dateFormat() default "";


    /**
     * �ֶ�����
     * @return
     */
    String description() default "";



}
