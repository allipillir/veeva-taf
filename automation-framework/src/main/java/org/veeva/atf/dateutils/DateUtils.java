package org.veeva.atf.dateutils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String generateFileNameTimeStamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return  dateFormat.format(new Date());
    }
}
