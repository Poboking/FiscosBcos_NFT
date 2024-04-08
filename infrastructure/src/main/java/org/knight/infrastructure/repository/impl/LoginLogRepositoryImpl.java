package org.knight.infrastructure.repository.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.knight.infrastructure.dao.domain.LoginLogEntity;
import org.knight.infrastructure.dao.mapper.LoginLogMapper;
import org.knight.infrastructure.repository.LoginLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author poboking
 * @description 针对表【login_log】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:42
 */
@Service
public class LoginLogRepositoryImpl extends ServiceImpl<LoginLogMapper, LoginLogEntity>
        implements LoginLogRepository {

    private final LoginLogMapper loginLogMapper;

    @Autowired
    public LoginLogRepositoryImpl(LoginLogMapper loginLogMapper) {
        this.loginLogMapper = loginLogMapper;
    }

    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return IPage<LoginLogEntity> 分页数据
     */
    @Override
    public IPage<LoginLogEntity> getPageList(long current, long pageSize) {
        return loginLogMapper.selectPage(new Page<>(current, pageSize), null);
    }

    /**
     * 分页查询 - 根据ID
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param id       Log日志ID
     * @return IPage<LoginLogEntity> 分页数据
     */
    @Override
    public IPage<LoginLogEntity> getPageListById(long current, long pageSize, String id) {
        return loginLogMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<LoginLogEntity>()
                        .eq(!CharSequenceUtil.isBlank(id), "id", id));
    }

    /**
     * 分页查询 - 根据用户名
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param userName 用户名
     * @return IPage<LoginLogEntity> 分页数据
     */
    @Override
    public IPage<LoginLogEntity> getLoginLogByMoblie(long current, long pageSize, String userName) {
        return loginLogMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<LoginLogEntity>()
                        .eq(!CharSequenceUtil.isBlank(userName), "user_name", userName)
                        .orderByDesc("login_time"));
    }

    /**
     * 保存登录日志
     *
     * @param loginLogEntity 登录日志实体
     * @return boolean 是否保存成功
     */
    @Override
    public boolean saveLoginLog(LoginLogEntity loginLogEntity) {
        return save(loginLogEntity);
    }

    /**
     * 删除登录日志
     *
     * @param userName 登录日志实体
     * @return boolean 是否删除成功
     */
    @Override
    public boolean deleteLoginLogByUserName(String userName) {
        return remove(new QueryWrapper<LoginLogEntity>()
                .eq(!CharSequenceUtil.isBlank(userName), "user_name", userName));
    }

    /**
     * 更新登录日志
     *
     * @param loginLogEntity 登录日志实体
     * @return boolean 是否更新成功
     */
    @Override
    public boolean updateLoginLogByUserName(LoginLogEntity loginLogEntity) {
        return update(loginLogEntity, new QueryWrapper<LoginLogEntity>()
                .eq(!CharSequenceUtil.isBlank(loginLogEntity.getUserName()),
                        "user_name", loginLogEntity.getUserName()));
    }

    /**
     * 更新登录日志
     *
     * @param loginLogEntity 登录日志实体
     * @return boolean 是否更新成功
     */
    @Override
    public boolean updateLoginLogById(LoginLogEntity loginLogEntity) {
        return update(loginLogEntity, new QueryWrapper<LoginLogEntity>()
                .eq(!CharSequenceUtil.isBlank(loginLogEntity.getUserName()),
                        "id", loginLogEntity.getId()));
    }
}




