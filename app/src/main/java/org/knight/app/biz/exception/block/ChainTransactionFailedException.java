package org.knight.app.biz.exception.block;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/10 14:44
 */
@ExceptionMapper(code = "500", msg = "BlockError: Transaction Failed on FISCO BCOS Blockchain Network")
public class ChainTransactionFailedException extends BlockChainTransactionException{
    public ChainTransactionFailedException(String message) {
        super(message);
    }
}
