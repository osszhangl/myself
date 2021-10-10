package com.itheima.mybatis.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**查询的注解
 * Created by zhanglin on 2021/7/21.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Select {

    /**
     * 配置SQL语句
     * @return
     */
    String value();


}
