package org.knight.infrastructure.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.knight.infrastructure.dao.domain.PayOrderEntity;

/**
 * @author poboking
 * @description 针对表【pay_order】的数据库操作Mapper
 * @createDate 2024-03-07 17:31:43
 * @Entity domain.PayOrderEntity
 */
@Mapper
public interface PayOrderMapper extends BaseMapper<PayOrderEntity> {

}




