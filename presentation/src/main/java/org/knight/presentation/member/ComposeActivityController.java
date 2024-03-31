package org.knight.presentation.member;

import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.knight.app.biz.notice.ComposeActivityService;
import org.knight.app.biz.notice.dto.activity.ComposeActivityDetailRespDTO;
import org.knight.app.biz.notice.dto.activity.ComposeActivityRespDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/17 19:51
 */
//@CheckUserLogin
@Log4j2
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/composeActivity/")
@Tag(name = "ComposeActivityController", description = "COMPOSE_ACTIVITY_CONTROLLER")
public class ComposeActivityController {
    @Autowired
    private ComposeActivityService composeActivityService;

    @GetMapping("findActiveComposeActivity")
    @ValidationStatusCode(code = "400")
    public List<ComposeActivityRespDTO> findActiveComposeActivity() {
        return composeActivityService.getComposeActivityList();
    }

    @GetMapping("findActiveComposeActivityDetail")
    @ValidationStatusCode(code = "400")
    public ComposeActivityDetailRespDTO findActiveComposeActivityDetail(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        return composeActivityService.getComposeActivityDetail(id);
    }
}
