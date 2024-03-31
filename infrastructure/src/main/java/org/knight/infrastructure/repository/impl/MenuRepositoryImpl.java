package org.knight.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.knight.infrastructure.dao.domain.MenuEntity;
import org.knight.infrastructure.dao.mapper.MenuMapper;
import org.knight.infrastructure.repository.MenuRepository;
import org.springframework.stereotype.Service;

/**
 * @author poboking
 * @description 针对表【menu】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class MenuRepositoryImpl extends ServiceImpl<MenuMapper, MenuEntity>
        implements MenuRepository {

}




