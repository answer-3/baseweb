package com.lizhan.core.util;

import org.apache.ibatis.session.RowBounds;

public class SQLUtils {

    public static String getPageSQL(String dialect, String SQL, RowBounds rowBounds) {
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
            pageSQL.append("select * from (select tmp_tb.*,ROWNUM row_id from(");
            pageSQL.append(SQL);
            pageSQL.append(") tmp_tb where ROWNUM <=");
            pageSQL.append(rowBounds.getOffset() + rowBounds.getLimit());
            pageSQL.append(")where row_id > ");
            pageSQL.append(rowBounds.getOffset());
            return pageSQL.toString();
        }

        return SQL;
    }
}
