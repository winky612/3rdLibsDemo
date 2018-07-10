package com.zkhz.a3rdlibsdemo.room;


import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Room为原始类型和可选的装箱类型提供嵌入支持。然而，有时你可能使用一个单独存入数据库的自定义数据类型。
 * 为了添加这种类型的支持，你可以提供一个把自定义类转化为一个Room能够持久化的已知类型的TypeConverter。
 * 例如：如果我们想持久化日期的实例，我们可以写如下TypeConverter去存储相等的Unix时间戳在数据库中：一个把Date对象转换为Long对象。另一个逆向转换，从Long到Date。
 * 因为Room已经知道了如何持久化Long对象，它能使用转换器持久化Date类型。
 * <p>
 * <p>
 * 接着，你增加@TypeConverters注解到AppDatabase类为了Room能够使用你已经为每个entity定义的转换器和DAO
 */

public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
