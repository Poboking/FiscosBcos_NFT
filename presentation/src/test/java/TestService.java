import org.junit.jupiter.api.Test;
import org.knight.infrastructure.common.NftConstants;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/31 17:38
 */
public class TestService {
    @Test
    public void test() {
        String now = LocalDateTime.now().format(NftConstants.DATE_FORMAT);
        Timestamp timestamp = Timestamp.valueOf(now);
        System.err.println(now);
        System.err.println(timestamp);
        System.err.println(timestamp.toLocalDateTime().format(NftConstants.DATE_FORMAT));
    }
}
