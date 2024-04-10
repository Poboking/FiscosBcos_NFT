import cn.hutool.core.date.DateUtil;
import org.junit.Test;
import org.knight.infrastructure.common.NftConstants;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/8 17:10
 */

public class TimeTest {
    @Test
    public void TimeTest(){

        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
        Timestamp timestamp = Timestamp.from(zonedDateTime.toInstant());
        String strFormat = DateUtil.format(timestamp, NftConstants.DATE_FORMAT);
        Timestamp resultTimestamp = Timestamp.valueOf(strFormat);


        System.out.println(Timestamp.from(zonedDateTime.toInstant()));
        System.err.println(Timestamp.from(zonedDateTime.toInstant()));
        Timestamp from = Timestamp.from(zonedDateTime.toInstant());
        String format = DateUtil.format(from, NftConstants.DATE_FORMAT);
        System.err.println(Timestamp.valueOf(format));
        System.out.println(format);
    }
}
