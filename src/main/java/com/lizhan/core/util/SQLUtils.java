package com.lizhan.core.util;

import org.apache.ibatis.session.RowBounds;

public class SQLUtils {

    public static String getPageSQL(String SQL, String dialect, RowBounds rowBounds) {
        StringBuffer pageSQL = new StringBuffer();
        if ("mysql".equalsIgnoreCase(dialect)) {
            pageSQL.append(SQL);
            pageSQL.append(" limit ");
            pageSQL.append(rowBounds.getOffset());
            pageSQL.append(",");
            pageSQL.append(rowBounds.getLimit());
            return pageSQL.toString();
        }

        if ("oracle".equalsIgnoreCase(dialect)) {

        }

        return SQL;
    }
}
