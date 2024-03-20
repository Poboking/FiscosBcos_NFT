package org.sziit.presentation.member;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/19 20:37
 */
@Log4j2
@RestController
@RequestMapping("/carousel/")
@AllArgsConstructor
public class CarouselController {
    // TODO: 2024/3/19 20:37 待实现 接口findCarousel
    /** 响应数据
     * {
     *     "success": true,
     *     "msg": "success",
     *     "code": 200,
     *     "timestamp": 1710851728410,
     *     "data": [
     *         {
     *             "id": "1582049773127467008",
     *             "clickDealWay": "1",
     *             "clickDealWayName": "纯展示",
     *             "cover": "https://s1.ax1x.com/2022/11/26/zt6qOS.jpg",
     *             "link": "",
     *             "orderNo": 1.0,
     *             "createTime": "2022-10-18 00:43",
     *             "lastModifyTime": "2022-10-18 00:43"
     *         },
     *         {
     *             "id": "1582049735974322176",
     *             "clickDealWay": "1",
     *             "clickDealWayName": "纯展示",
     *             "cover": "https://s1.ax1x.com/2022/11/26/zt6HQf.jpg",
     *             "link": "",
     *             "orderNo": 2.0,
     *             "createTime": "2022-10-18 00:43",
     *             "lastModifyTime": "2022-10-18 00:43"
     *         },
     *         {
     *             "id": "1582049708983975936",
     *             "clickDealWay": "1",
     *             "clickDealWayName": "纯展示",
     *             "cover": "https://s1.ax1x.com/2022/11/26/zt65FA.jpg",
     *             "link": "",
     *             "orderNo": 3.0,
     *             "createTime": "2022-10-18 00:43",
     *             "lastModifyTime": "2022-10-18 00:43"
     *         }
     *     ]
     * }
     */
}
