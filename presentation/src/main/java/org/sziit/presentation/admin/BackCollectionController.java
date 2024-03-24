package org.sziit.presentation.admin;

import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.sziit.app.biz.artwork.collection.CollectionService;
import org.sziit.app.biz.artwork.dto.collection.CollectionRespDTO;
import org.sziit.app.biz.artwork.dto.collection.CollectionStatisticDataRespDTO;
import org.sziit.app.biz.artwork.dto.collection.CollectionUpdateStoryReqDTO;
import org.sziit.infrastructure.common.PageResult;

import java.util.Map;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/24 15:14
 */
@Log4j2
@RestController
@RequestMapping("/back/collection/")
public class BackCollectionController {

    private final CollectionService collectionService;

    @Autowired
    public BackCollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping("getCollectionStatisticData")
    @ValidationStatusCode(code = "500")
    public CollectionStatisticDataRespDTO getCollectionStatisticData() {
        return collectionService.getCollectionStatisticData();
    }

    @GetMapping("findCollectionByPage")
    @ValidationStatusCode(code = "400")
    public PageResult<CollectionRespDTO> findCollectionByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize,
            @RequestParam(name = "commodityType", required = false) String commodityType,
            @RequestParam(name = "name", required = false) String name) {
        return collectionService.findCollectionByPage(current, pageSize, commodityType, name);
    }

    @PostMapping("updateCollectionStory")
    @ValidationStatusCode(code = "500")
    public Map<String, Boolean> updateCollectionStory(@Valid @RequestBody CollectionUpdateStoryReqDTO reqDTO) {
        return Map.of("result", collectionService.updateCollectionStory(reqDTO));
    }

}
