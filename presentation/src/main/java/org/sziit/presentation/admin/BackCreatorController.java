package org.sziit.presentation.admin;

import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.sziit.app.biz.artwork.creator.CreatorService;
import org.sziit.app.biz.artwork.dto.creator.CreatorAddOrUpdateReqDTO;
import org.sziit.app.biz.artwork.dto.creator.CreatorRespDTO;
import org.sziit.infrastructure.common.PageResult;

import java.util.List;
import java.util.Map;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/24 19:54
 */
@RestController
@Validated
@RequestMapping("/back/creator/")
public class BackCreatorController {

    private final CreatorService creatorService;

    @Autowired
    public BackCreatorController(CreatorService creatorService) {
        this.creatorService = creatorService;
    }

    @GetMapping("findAllCreator")
    @ValidationStatusCode(code = "500")
    public List<CreatorRespDTO> findAllCreator(){
        return creatorService.findAllCreator();
    }

    @GetMapping("findCreatorByPage")
    @ValidationStatusCode(code = "500")
    public PageResult<CreatorRespDTO> findCreatorByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize,
            @RequestParam(name = "name", required = false) String name){
        return creatorService.findCreatorByPage(current, pageSize, name);
    }

    @PostMapping("addOrUpdateCreator")
    @ValidationStatusCode(code = "500")
    public Map<String, Boolean> addOrUpdateCreator(@Valid @RequestBody CreatorAddOrUpdateReqDTO reqDTo){
       return Map.of("result",  creatorService.addOrUpdateCreator(reqDTo));
    }

    @GetMapping("delCreator")
    @ValidationStatusCode(code = "400")
    public Map<String, Boolean> delCreator(@RequestParam(name = "id") String id){
        return Map.of("result", creatorService.delCreator(id));
    }
}
