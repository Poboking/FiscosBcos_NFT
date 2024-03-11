package org.sziit.presentation.member;

import cn.hutool.core.text.CharSequenceUtil;
import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/10 19:58
 */
@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/transaction/")
@Tag(name = "TransactionController", description = "TRANSACTION_CONTROLLER")
public class TransactionController {


    @GetMapping("checkHasPreSale")
    @ValidationStatusCode(code = "400")
    public boolean checkHasPreSale(
            @RequestParam(name = "collectionId") String collectionId) {
        log.info(CharSequenceUtil.format("collectionId({}): checkHasPreSale", collectionId));
        return false;
    }


    @PostMapping("latestCollectionCreateOrder")
    @ValidationStatusCode(code = "400")
    public void latestCollectionCreateOrder(
            @RequestBody String memberId,
            @RequestParam(name = "collectionId") String collectionId) {
        log.info(CharSequenceUtil.format("collectionId({}), memberId({}): latestCollectionCreateOrder", collectionId, memberId));
    }
}
