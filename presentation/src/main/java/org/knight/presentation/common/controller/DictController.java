package org.knight.presentation.common.controller;

import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.knight.app.biz.dict.DictService;
import org.knight.app.biz.dict.dto.DictItemRespDTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/10 19:21
 */
//@CheckUserLogin
@Log4j2
@RestController
@RequestMapping("/dictconfig/")
@Tag(name = "DictController", description = "DICT_CONTROLLER")
public class DictController {
    private final DictService dictService;

    @Autowired
    public DictController(DictService dictService) {
        this.dictService = dictService;
    }

    @GetMapping("findDictItemByDictTypeCode")
    @ValidationStatusCode(code = "400")
    public List<DictItemRespDTo> findDictItemByDictTypeCode(@RequestParam(name = "dictTypeCode", required = true) String dictTypeCode) {
        return dictService.getDictItemListByDictTypeCode(dictTypeCode);
    }

}
