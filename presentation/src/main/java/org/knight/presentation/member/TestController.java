package org.knight.presentation.member;

import lombok.AllArgsConstructor;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;
import org.knight.infrastructure.fisco.service.biz.ChainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/12 11:32
 */
@RestController("/test/")
@AllArgsConstructor
public class TestController {

    @Autowired
    private final ChainService chainService;
    @GetMapping("quickTest")
    void quickTest() throws ContractException {
        chainService.quickTest();
    }
}
