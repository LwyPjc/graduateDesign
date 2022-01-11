package com.graduation.education.converter;

import cn.hutool.core.util.NumberUtil;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 字符串转Date
 */
public class String2Date implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        //如果是数字，就是时间戳转字符串
        if(NumberUtil.isNumber(s)){
            return new Date(Long.valueOf(s));
        }else {
            //默认转换流程
            SimpleDateFormat format = new SimpleDateFormat(s.contains("00:00:00")?"yyyy-MM-dd":"yyyy-MM-dd HH:mm:ss");
            try {
                return format.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
