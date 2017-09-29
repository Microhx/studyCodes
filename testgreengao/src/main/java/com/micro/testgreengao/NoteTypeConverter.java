package com.micro.testgreengao;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/9/25 - 14:46 <p>
 * interface :
 */

public class NoteTypeConverter implements PropertyConverter<NoteType,String> {

    @Override
    public NoteType convertToEntityProperty(String databaseValue) {
        return NoteType.valueOf(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(NoteType entityProperty) {
        return entityProperty.name();
    }
}
