package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.MenuEntity;
import org.sziit.infrastructure.dao.mapper.MenuMapper;
import org.sziit.infrastructure.repository.MenuService;

/**
 * @author poboking
 * @description 针对表【menu】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity>
        implements MenuService {

}




