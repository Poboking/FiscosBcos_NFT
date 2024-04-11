package org.knight.infrastructure.repository.conf;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/8 13:36
 */
@Configuration
@EnableTransactionManagement
//@ConditionalOnClass(value = {PaginationInnerInterceptor.class})
public class MybatisPlusPageConfig {
//    @Bean
//    public PaginationInnerInterceptor paginationInnerInterceptor() {
//        PaginationInnerInterceptor interceptor = new PaginationInnerInterceptor();
//        //物理分页
//        interceptor.setOverflow(true);
//        //设置数据库类型
//        interceptor.setDbType(DbType.MYSQL);
//        return interceptor;
//    }

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setDbType(DbType.MYSQL);
        paginationInnerInterceptor.setOverflow(true);
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return interceptor;
    }

//    @Bean
//    public ConfigurationCustomizer configurationCustomizer() {
//        return configuration -> configuration.setUseDeprecatedExecutor(false);
//    }

}
