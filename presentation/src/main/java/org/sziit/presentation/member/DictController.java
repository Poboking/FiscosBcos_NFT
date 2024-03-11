package org.sziit.presentation.member;

import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.sziit.app.biz.dict.DictService;
import org.sziit.app.biz.dict.dto.DictItemRespDto;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/10 19:21
 */
@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/dictconfig/")
@Tag(name = "DictController", description = "DICT_CONTROLLER")
public class DictController {
    @Autowired
    private DictService dictService;

    @GetMapping("findDictItemByPage")
    @ValidationStatusCode(code = "400")
    public List<DictItemRespDto> findDictItemByPage(
            @RequestParam(name = "dictTypeCode", required = true) String dictTypeCode
    ) {
        return dictService.getDictItemListByDictTypeCode(dictTypeCode);
    }
}