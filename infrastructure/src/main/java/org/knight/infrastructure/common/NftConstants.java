package org.knight.infrastructure.common;

import java.time.format.DateTimeFormatter;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/8 11:31
 */
@SuppressWarnings("all")
public class NftConstants {

    public static final String 优先购业务类型_手动发放 = "1";
    public static final String 优先购业务类型_优先购任务 = "2";
    public static final String 执行优先购任务 = "executePreSaleTask";
    public static final String 优先购任务状态_未执行 = "1";
    public static final String 优先购任务状态_已执行 = "2";
    public static final String 优先购任务状态_已取消 = "3";
    public static final String 优先购资格状态_未使用 = "1";
    public static final String 优先购资格状态_已使用 = "2";
    public static final String 优先购资格状态_已作废 = "3";
    public static final String 区块链类型_不上链 = "noneChain";
    public static final String 上链_销毁艺术品 = "destroyArtwork";
    public static final String 上链_转让艺术品 = "transferArtwork";
    public static final String 上链_二级市场购买艺术品 = "marketBuyArtwork";
    public static final String 上链_同步唯一标识 = "syncUniqueId";
    public static final String 上链_同步交易HASH = "syncTransactionHash";
    public static final String 上链_铸造艺术品 = "mintArtwork";
    public static final String 上链_同步艺术品HASH = "syncArtworkHash";
    public static final String 上链_创建艺术品 = "chainAddArtwork";
    public static final String 创建区块链地址 = "createBlockChainAddr";
    public static final String 提现记录状态_审核中 = "1";
    public static final String 提现记录状态_已提现 = "2";
    public static final String 提现记录状态_已驳回 = "3";
    public static final String 结算账户_银行卡 = "bankCard";
    public static final String 结算账户_支付宝 = "alipay";
    public static final String 结算账户_微信 = "wechat";
    public static final String 兑换码状态_未兑换 = "1";
    public static final String 兑换码状态_已兑换 = "2";
    public static final String 兑换码状态_已作废 = "3";
    public static final String 商品类型_藏品 = "1";
    public static final String 商品类型_盲盒 = "2";
    public static final String 支付订单超时取消 = "payOrderTimeoutCancel";
    public static final String 支付订单业务模式_平台自营 = "1";
    public static final String 支付订单业务类型_二级市场 = "2";
    public static final String 支付订单状态_待付款 = "1";
    public static final String 支付订单状态_已付款 = "2";
    public static final String 支付订单状态_已取消 = "3";
    public static final String 转售的藏品状态_已发布 = "1";
    public static final String 转售的藏品状态_已卖出 = "2";
    public static final String 转售的藏品状态_已取消 = "3";
    public static final String 持有藏品状态_持有中 = "1";
    public static final String 持有藏品状态_已转赠 = "2";
    public static final String 持有藏品状态_转售中 = "3";
    public static final String 持有藏品状态_已卖出 = "4";
    public static final String 持有藏品状态_开盲盒销毁 = "5";
    public static final String 持有藏品状态_合成销毁 = "6";
    public static final String 藏品获取方式_购买 = "1";
    public static final String 藏品获取方式_赠送 = "2";
    public static final String 藏品获取方式_二级市场 = "3";
    public static final String 藏品获取方式_盲盒 = "4";
    public static final String 藏品获取方式_合成 = "5";
    public static final String 藏品获取方式_空投 = "6";
    public static final String 藏品获取方式_兑换码 = "7";
    public static final String 发送短信 = "sendSms";
    public static final String 短信发送状态_未发送 = "1";
    public static final String 短信发送状态_发送成功 = "2";
    public static final String 短信发送状态_发送失败 = "3";
    public static final String 限制 = "limit";
    public static final String 短信类型_验证码_修改支付密码 = "modifyPayPwd";
    public static final String 短信类型_验证码_登录 = "login";
    public static final String 会员余额变动日志类型_系统 = "1";
    public static final String 会员余额变动日志类型_购买藏品 = "2";
    public static final String 会员余额变动日志类型_购买转售的藏品 = "3";
    public static final String 会员余额变动日志类型_出售藏品 = "4";
    public static final String 会员余额变动日志类型_提现 = "5";
    public static final String 会员余额变动日志类型_提现驳回 = "6";
    public static final String 菜单类型_一级菜单 = "menu_1";
    public static final String 菜单类型_二级菜单 = "menu_2";
    public static final String 子系统_会员端 = "member";
    public static final String 子系统_后台管理 = "admin";
    public static final String 登录提示_登录成功 = "登录成功";
    public static final String 登录状态_成功 = "1";
    public static final String 登录状态_失败 = "0";
    public static final String 功能状态_启用 = "1";
    public static final String 功能状态_禁用 = "0";
    public static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDvsJrdNacJvZRzHauwPIJBIoJNFyjH47/uCIwBYVgkok3j+3bZZ3HhSW5Fsb23O0KN3wjqcJn3LJMFHN1UoMoiZDSQ7PYrz275bfCFNm2fjwQ8WMUgiRa4KLlTqQK/Gq/2+oDbmE4tb7dxZBrUowAhW9NNv+vESlE8nvP7XXFYpQIDAQAB";
    public static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAO+wmt01pwm9lHMdq7A8gkEigk0XKMfjv+4IjAFhWCSiTeP7dtlnceFJbkWxvbc7Qo3fCOpwmfcskwUc3VSgyiJkNJDs9ivPbvlt8IU2bZ+PBDxYxSCJFrgouVOpAr8ar/b6gNuYTi1vt3FkGtSjACFb002/68RKUTye8/tdcVilAgMBAAECgYA1COmrSqTUJeuD8Su9ChZ0HROhxR8T45PjMmbwIz7ilDsR1+E7R4VOKPZKW4Kz2VvnklMhtJqMs4MwXWunvxAaUFzQTTg2Fu/WU8Y9ha14OaWZABfChMZlpkmpJW9arKmI22ZuxCEsFGxghTiJQ3tK8npj5IZq5vk+6mFHQ6aJAQJBAPghz91Dpuj+0bOUfOUmzi22obWCBncAD/0CqCLnJlpfOoa9bOcXSusGuSPuKy5KiGyblHMgKI6bq7gcM2DWrGUCQQD3SkOcmia2s/6i7DUEzMKaB0bkkX4Ela/xrfV+A3GzTPv9bIBamu0VIHznuiZbeNeyw7sVo4/GTItq/zn2QJdBAkEA8xHsVoyXTVeShaDIWJKTFyT5dJ1TR++/udKIcuiNIap34tZdgGPI+EM1yoTduBM7YWlnGwA9urW0mj7F9e9WIQJAFjxqSfmeg40512KP/ed/lCQVXtYqU7U2BfBTg8pBfhLtEcOg4wTNTroGITwe2NjL5HovJ2n2sqkNXEio6Ji0QQJAFLW1Kt80qypMqot+mHhS+0KfdOpaKeMWMSR4Ij5VfE63WzETEeWAMQESxzhavN1WOTb3/p6icgcVbgPQBaWhGg==";
    /**
     * 管理员权限
     */
    public static final String ADMIN = "1";
    /**
     * 普通用户权限
     */
    public static final String USER = "0";
    /**
     * 时间格式化
     */
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter SIMPLE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final String 默认头像 = "https://ydlunacommon-cdn.nosdn.127.net/e5d302ac10bb57dbb7bace5281d5eb6a.png";
    public static final Integer 支付订单有效期 = 30;//30分钟
    public static final Double 提现单笔最大金额 = 10000.0;
    public static final Double 提现单笔最小金额 = 100.0;
    public static final String 默认时区 = "Asia/Shanghai";
    private static final String 通知公告类型_上新藏品 = "1";
    private static final String 通知公告类型_合成活动 = "2";
    private static final String 通知公告类型_空投活动 = "3";
    private static final String 通知公告类型_运营活动 = "4";
    /**
     * 使用旧密码修改
     */
    public static Integer USE_OLDPW = 0;
    /**
     * 使用邮箱验证码修改
     */
    public static Integer Get_Code_email = 2;
    /**
     * 使用手机验证码修改
     */
    public static Integer Get_Code_iphone = 3;

    /**
     * 排序类
     */
    public static final class SortOrder {
        /**
         * 升序
         */
        public static final String SORT_ORDER_ASC = "ascend";

        /**
         * 降序
         */
        public static final String SORT_ORDER_DESC = " descend";
    }

    /**
     * 缓存 Key
     */
    public static final class RedisKey {


        public static final Integer MINUTE_5 = 60 * 5;
        // redis锁 Key
        private static final String LOCK_TOKEN = "lock_token_";
        private static final String REDIS_COLLECTION = "Conllection_cahe";
        private static final String LOCK_PRODUCT_HOT_CACHE_CREATE_PREFIX = "LOCK_PRODUCT_HOT_CACHE_CREATE_PREFIX_";
        private static final String REMAINING_STOCK = "REMAINING_STOCK_"; //剩余数量
        private static final String ADD_ORDER = "ADD_ORDER_";//抢购锁 - 添加到订单表中
        private static final String READ_WRITE_LOCK = "READ_WRITE_LOCK_"; //商品的读写锁
        private static final String ADD_ORDER_BYUSER = "ADD_ORDER_BYUSER_"; //用户锁防止重复提交
        private static final String PAY_LOCK = "PAY_LOCK_";//支付锁，方式多用户同时支付同一订单
        private static final String USER_INFO = "user_";//用户信息记录
        private static final String ADMIN_UPDATE_LOCK = "ADMIN_UPDATE_LOCK_";//用于管理员更新商品信息的锁，不管是审核还是修改信息都用这把锁前缀
        //空值，用于空缓存用
        private static final String REDIS_EMPTY_CACHE = "{}";
        public static String SortPage = "SortPage";

        public static String REDIS_COLLECTION(Integer id) {
            return REDIS_COLLECTION + id;
        }

        public static String LOCK_PRODUCT_HOT_CACHE_CREATE_PREFIX(Integer id) {
            return LOCK_PRODUCT_HOT_CACHE_CREATE_PREFIX + id;
        }

        public static String REDIS_EMPTY_CACHE() {
            return REDIS_EMPTY_CACHE;
        }

        public static String LOCK_TOKEN(Integer stockUsedCount) {
            return LOCK_TOKEN + stockUsedCount;
        }

        public static String REMAINING_STOCK(Integer stockId) {
            return REMAINING_STOCK + stockId;
        }

        public static String ADD_ORDER(Integer id) {
            return ADD_ORDER + id;
        }

        public static String READ_WRITE_LOCK(Integer id) {
            return READ_WRITE_LOCK + id;
        }

        public static String ADD_ORDER_BYUSER(Integer userid) {
            return ADD_ORDER_BYUSER + userid;
        }

        public static String ADMIN_UPDATE_LOCK(Integer id) {
            return ADMIN_UPDATE_LOCK + id;
        }

        public static String PAY_LOCK(String id) {
            return PAY_LOCK + id;
        }

        public static String USER_INFO(Integer id) {
            return USER_INFO + id;
        }


    }

    /**
     * 规则限定类型
     */
    public static final class RuleLimitType {
        /**
         * 等于
         */
        public static final int EQUAL = 1;
        /**
         * 大于
         */
        public static final int GT = 2;
        /**
         * 小于
         */
        public static final int LT = 3;
        /**
         * 大于&等于
         */
        public static final int GE = 4;
        /**
         * 小于&等于
         */
        public static final int LE = 5;
        /**
         * 枚举
         */
        public static final int ENUM = 6;
    }
}