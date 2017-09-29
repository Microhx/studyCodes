package com.micro.testgreengao.update;

import android.database.Cursor;
import android.text.TextUtils;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * greendao db upgrade helper class 
 */  
public class MigrationHelper {

    private static final String CONVERSION_CLASS_NOT_FOUND_EXCEPTION = "MIGRATION HELPER - CLASS DOESN'T MATCH WITH THE CURRENT PARAMETERS";  
    private static MigrationHelper instance;  
  
    public static MigrationHelper getInstance() {  
        if(instance == null) {  
            instance = new MigrationHelper();  
        }  
        return instance;  
    }  
  
    public void migrate(Database db, @SuppressWarnings("unchecked") Class<? extends AbstractDao<?, ?>>... daoClasses) {
        generateTempTables(db, daoClasses);  
        dropAllTables(db, true,daoClasses);  
        createAllTables(db, false,daoClasses);  
        restoreData(db, daoClasses);  
    }  
  
    @SuppressWarnings("unchecked")  
    private void generateTempTables(Database db, Class<? extends AbstractDao<?, ?>>... daoClasses) {
        for(int i = 0; i < daoClasses.length; i++) {  
            DaoConfig daoConfig = new DaoConfig(db, daoClasses[i]);
  
            String divider = "";  
            String tableName = daoConfig.tablename;  
            String tempTableName = daoConfig.tablename.concat("_TEMP");  
            ArrayList<String> properties = new ArrayList<String>();
  
            StringBuilder createTableStringBuilder = new StringBuilder();  
  
            createTableStringBuilder.append("CREATE TABLE ").append(tempTableName).append(" (");  
  
            for(int j = 0; j < daoConfig.properties.length; j++) {  
                String columnName = daoConfig.properties[j].columnName;  
  
                if(getColumns(db, tableName).contains(columnName)) {  
                    properties.add(columnName);  
  
                    String type = null;  
  
                    try {  
                        type = getTypeByClass(daoConfig.properties[j].type);  
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
                    createTableStringBuilder.append(divider).append(columnName).append(" ").append(type);  
  
                    if(daoConfig.properties[j].primaryKey) {  
                        createTableStringBuilder.append(" PRIMARY KEY");  
                    }  
  
                    divider = ",";  
                }  
            }  
            createTableStringBuilder.append(");");  
            if(createTableStringBuilder.toString().contains(" ();")){  
                continue;  
            }  
  
            db.execSQL(createTableStringBuilder.toString());  

            StringBuilder insertTableStringBuilder = new StringBuilder();
            insertTableStringBuilder.append("INSERT INTO ").append(tempTableName).append(" (");  
            insertTableStringBuilder.append(TextUtils.join(",", properties));
            insertTableStringBuilder.append(") SELECT ");  
            insertTableStringBuilder.append(TextUtils.join(",", properties));  
            insertTableStringBuilder.append(" FROM ").append(tableName).append(";");  
  
            db.execSQL(insertTableStringBuilder.toString());  
        }  
    }  
  
  
    private void dropAllTables(Database db, boolean ifExists,Class<? extends AbstractDao<?, ?>>... daoClasses){
        for(int i = 0; i < daoClasses.length; i++) {  
            DaoConfig daoConfig = new DaoConfig(db, daoClasses[i]);  
            String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\""+daoConfig.tablename+"\"";  
            db.execSQL(sql);  
        }  
    }  
    private void createAllTables(Database db, boolean ifNotExists,Class<? extends AbstractDao<?, ?>>... daoClasses){
        for(int i = 0; i < daoClasses.length; i++) {  
            DaoConfig daoConfig = new DaoConfig(db, daoClasses[i]);  
  
            String divider = "";  
            String tableName = daoConfig.tablename;  
            ArrayList<String> properties = new ArrayList<String>();  
  
            StringBuilder createTableStringBuilder = new StringBuilder();  
  
            String constraint = ifNotExists? "IF NOT EXISTS ": "";  
  
            createTableStringBuilder.append("CREATE TABLE ").append(constraint).append(tableName).append(" (");  
  
            for(int j = 0; j < daoConfig.properties.length; j++) {  
                String columnName = daoConfig.properties[j].columnName;  
                properties.add(columnName);  
                String type = null;  
  
                try {  
                    type = getTypeByClass(daoConfig.properties[j].type);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
                createTableStringBuilder.append(divider).append(columnName).append(" ").append(type);  
  
                if(daoConfig.properties[j].primaryKey) {  
                    createTableStringBuilder.append(" PRIMARY KEY");  
                }  
  
                divider = ",";  
            }  
            createTableStringBuilder.append(");");  
            db.execSQL(createTableStringBuilder.toString());  
        }  
    }  
  
    private void restoreData(Database db, Class<? extends AbstractDao<?, ?>>... daoClasses) {
        for(int i = 0; i < daoClasses.length; i++) {  
            DaoConfig daoConfig = new DaoConfig(db, daoClasses[i]);  
  
            String tableName = daoConfig.tablename;  
            String tempTableName = daoConfig.tablename.concat("_TEMP");  
            ArrayList<String> properties = new ArrayList<String>();  
  
            for (int j = 0; j < daoConfig.properties.length; j++) {  
                String columnName = daoConfig.properties[j].columnName;  
  
                if(getColumns(db, tempTableName).contains(columnName)) {  
                    properties.add(columnName);  
                }  
            }  
  
            StringBuilder insertTableStringBuilder = new StringBuilder();  
  
            insertTableStringBuilder.append("INSERT INTO ").append(tableName).append(" (");  
            insertTableStringBuilder.append(TextUtils.join(",", properties));  
            insertTableStringBuilder.append(") SELECT ");  
            insertTableStringBuilder.append(TextUtils.join(",", properties));  
            insertTableStringBuilder.append(" FROM ").append(tempTableName).append(";");  
  
            StringBuilder dropTableStringBuilder = new StringBuilder();  
  
            dropTableStringBuilder.append("DROP TABLE ").append(tempTableName);
  
            if(insertTableStringBuilder.toString().contains("()")){  
                continue;  
            }  
  
            db.execSQL(insertTableStringBuilder.toString());  
            db.execSQL(dropTableStringBuilder.toString());  
        }  
    }  
  
    private String getTypeByClass(Class<?> type) throws Exception {
        if(type.equals(String.class)) {
            return "TEXT";  
        }

        //for fix bugs
        if(type.equals(Long.class)    || type.equals(long.class) ||
           type.equals(Integer.class) || type.equals(int.class) ||
           type.equals(Character.class)    || type.equals(char.class) ||
           type.equals(Short.class)    || type.equals(short.class) ||
           type.equals(Byte.class)    || type.equals(byte.class) ||
           type.equals(Date.class)) {
            return "INTEGER";
        }

        if(type.equals(Float.class) || type.equals(float.class)) {
            return "FLOAT";
        }

        if(type.equals(Double.class) || type.equals(double.class)) {
            return "DOUBLE";
        }

        if(type.equals(Boolean.class)) {  
            return "BOOLEAN";  
        }  
  
        Exception exception = new Exception(CONVERSION_CLASS_NOT_FOUND_EXCEPTION.concat(" - Class: ").concat(type.toString()));  
        throw exception;  
    }  
  
    private static List<String> getColumns(Database db, String tableName) {
        List<String> columns = new ArrayList<String>();  
        Cursor cursor = null;
        try {  
            cursor = db.rawQuery("SELECT * FROM " + tableName + " limit 1", null);  
            if (cursor != null) {  
                columns = new ArrayList<>(Arrays.asList(cursor.getColumnNames()));
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (cursor != null)  
                cursor.close();  
        }  
        return columns;  
    }  
}  