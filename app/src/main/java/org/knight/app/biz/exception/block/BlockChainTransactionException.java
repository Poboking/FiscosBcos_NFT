package org.knight.app.biz.exception.block;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/9 21:50
 */
@ExceptionMapper(code = "500", msg = "BlockError: Transaction Failed on FISCO BCOS Blockchain Network")
public class BlockChainTransactionException extends RuntimeException {
    public BlockChainTransactionException(String message) {
        super(message);
    }
}
