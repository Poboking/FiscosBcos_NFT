package org.sziit.presentation.member.transaction;

import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sziit.infrastructure.common.ToBeRealizedVO;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/20 22:41
 */
@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/withdraw/")
public class WithdrawController {

    // TODO: 2024/3/20 22:41 以下方法待实现
    @GetMapping("findByPage")
    @ValidationStatusCode(code = "400")
    public ToBeRealizedVO findByPage() {
        return new ToBeRealizedVO();
    }
}
