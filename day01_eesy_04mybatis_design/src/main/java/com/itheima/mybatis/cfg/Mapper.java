package com.itheima.mybatis.cfg;

/**用于封装执行的SQL语句和结果类型的全限定类名
 * Created by zhanglin on 2021/7/19.
 */
public class Mapper {

    private  String queryString;//sql
    private String resultType;//实体类全限定类名

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
