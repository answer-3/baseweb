package com.lizhan.core.base;

import com.lizhan.core.util.SQLUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class MyBatisPagePlugin implements Interceptor {

    private static String dialect;

    private static Map<Integer, RowBounds> rowBoundsMap = new HashMap<>();

    public MyBatisPagePlugin(String dialect) {
        this.dialect = dialect;
    }


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        Object paraObject = boundSql.getParameterObject();
        if (paraObject != null) {
            int key = paraObject.hashCode();
            if (rowBoundsMap.containsKey(key)) {
                String sql = boundSql.getSql();
                String pageSql = SQLUtils.getPageSQL(dialect, sql, rowBoundsMap.get(key));
                Field field = BoundSql.class.getDeclaredField("sql");
                if (field.isAccessible()) {
                    field.set(boundSql, pageSql);
                } else {
                    field.setAccessible(true);
                    field.set(boundSql, pageSql);
                    field.setAccessible(false);
                }
            }
            rowBoundsMap.remove(key);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        dialect = properties.getProperty("dialect");
    }

    public static void setRowBoundsMap(int key, int offset, int limit) {
        rowBoundsMap.put(key, new RowBounds(offset, limit));
    }
}
