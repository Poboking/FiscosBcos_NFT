package org.knight.app.biz.exception.collection;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/14 22:57
 */
@ExceptionMapper(code = "500", msg = "ServerError: Collection Story Delete Exception")
public class CollectionStoryDeleteException extends RuntimeException{
        public CollectionStoryDeleteException(String message) {
            super(message);
        }
}
