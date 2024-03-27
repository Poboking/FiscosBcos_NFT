package org.knight.presentation.admin;

import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.knight.app.biz.artwork.collection.CollectionService;
import org.knight.app.biz.artwork.collection.IssuedCollectionService;
import org.knight.app.biz.artwork.dto.collection.CollectionAddReqDTO;
import org.knight.app.biz.artwork.dto.collection.CollectionRespDTO;
import org.knight.app.biz.artwork.dto.collection.CollectionStatisticDataRespDTO;
import org.knight.app.biz.artwork.dto.collection.CollectionUpdateStoryReqDTO;
import org.knight.app.biz.artwork.dto.issuedcollection.IssuedCollectionRespDTO;
import org.knight.infrastructure.common.PageResult;

import java.util.List;
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

    private final IssuedCollectionService issuedCollectionService;

    @Autowired
    public BackCollectionController(CollectionService collectionService, IssuedCollectionService issuedCollectionService) {
        this.collectionService = collectionService;
        this.issuedCollectionService = issuedCollectionService;
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


    @GetMapping("findIssuedCollection")
    @ValidationStatusCode(code = "400")
    public List<IssuedCollectionRespDTO> findIssuedCollection(
            @RequestParam(name = "collectionId")String collectionId
    ){
        return issuedCollectionService.findIssuedCollection(collectionId);
    }

    @GetMapping("delCollection")
    @ValidationStatusCode(code = "400")
    public Map<String , Boolean> delCollection(
            @RequestParam(name = "id")String id
    ){
        return Map.of("result", collectionService.delCollection(id));
    }

    @PostMapping("addCollection")
    @ValidationStatusCode(code = "400")
    public Map<String, Boolean> addCollection(
            @Valid @RequestBody CollectionAddReqDTO reqDTO
    ){
        if (Boolean.FALSE.equals(collectionService.addCollection(reqDTO))){
            return Map.of("result", false);
        }
        String collectionId = collectionService.findCollectionId(reqDTO.getName(), reqDTO.getCreatorId());
        if (Boolean.FALSE.equals(issuedCollectionService.addIssuedCollection(reqDTO, collectionId))){
            return Map.of("result", false);
        }
        return Map.of("result", true);
    }

}
