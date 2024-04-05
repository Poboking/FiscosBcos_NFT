package org.knight.infrastructure.fisco.service;

import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unchecked")
public class Utils extends Contract {
    public static final String[] BINARY_ARRAY = {"60806040526000805534801561001457600080fd5b5061014a806100246000396000f300608060405260043610610041576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806394116d4614610046575b600080fd5b34801561005257600080fd5b5061005b610071565b6040518082815260200191505060405180910390f35b6000804260005460405160200180838152602001828152602001925050506040516020818303038152906040526040518082805190602001908083835b6020831015156100d357805182526020820191506020810190506020830392506100ae565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600190049050600080815480929190600101919050555080915050905600a165627a7a7230582099836151e17ac9665218dd699012a18b1244b978192ed598fd76f6f2793972d30029"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"60806040526000805534801561001457600080fd5b5061014a806100246000396000f300608060405260043610610041576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680634fcaf9ca14610046575b600080fd5b34801561005257600080fd5b5061005b610071565b6040518082815260200191505060405180910390f35b6000804260005460405160200180838152602001828152602001925050506040516020818303038152906040526040518082805190602001908083835b6020831015156100d357805182526020820191506020810190506020830392506100ae565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600190049050600080815480929190600101919050555080915050905600a165627a7a723058203dd3a4fa775d8fae0063077688327e46fc24e2d4dd596cb3d0bcd50f524c910a0029"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[],\"name\":\"generateID\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_GENERATEID = "generateID";

    protected Utils(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public TransactionReceipt generateID() {
        final Function function = new Function(
                FUNC_GENERATEID, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] generateID(TransactionCallback callback) {
        final Function function = new Function(
                FUNC_GENERATEID, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForGenerateID() {
        final Function function = new Function(
                FUNC_GENERATEID, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<BigInteger> getGenerateIDOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_GENERATEID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public static Utils load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new Utils(contractAddress, client, credential);
    }

    public static Utils deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(Utils.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }
}
