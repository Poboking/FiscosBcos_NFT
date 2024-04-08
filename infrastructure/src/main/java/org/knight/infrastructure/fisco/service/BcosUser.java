package org.knight.infrastructure.fisco.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Address;
import org.fisco.bcos.sdk.abi.datatypes.Event;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple3;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.eventsub.EventCallback;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class BcosUser extends Contract {
    public static final String[] BINARY_ARRAY = {};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"_userId\",\"type\":\"uint256\"},{\"name\":\"_tokenId\",\"type\":\"uint256\"}],\"name\":\"addToken\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"_contractOwner\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_userId\",\"type\":\"uint256\"},{\"name\":\"_index\",\"type\":\"uint256\"}],\"name\":\"getUserToken\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_userAddress\",\"type\":\"address\"}],\"name\":\"getUserByAddress\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[],\"name\":\"renounceOwnership\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_userId\",\"type\":\"uint256\"}],\"name\":\"getUserAddress\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[],\"name\":\"generateID\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_userId\",\"type\":\"uint256\"}],\"name\":\"getUserTokenCount\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_userId\",\"type\":\"uint256\"}],\"name\":\"getUser\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getUserCount\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_userAddress\",\"type\":\"address\"}],\"name\":\"createUser\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_to\",\"type\":\"address\"},{\"name\":\"_tokenId\",\"type\":\"uint256\"}],\"name\":\"initToken\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_userId\",\"type\":\"uint256\"},{\"name\":\"_tokenId\",\"type\":\"uint256\"}],\"name\":\"delToken\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"newOwner\",\"type\":\"address\"}],\"name\":\"transferOwnership\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_from\",\"type\":\"address\"},{\"name\":\"_to\",\"type\":\"address\"},{\"name\":\"_tokenId\",\"type\":\"uint256\"}],\"name\":\"transferToken\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"userAddress\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"userId\",\"type\":\"uint256\"}],\"name\":\"UserCreated\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"userId\",\"type\":\"uint256\"},{\"indexed\":true,\"name\":\"tokenId\",\"type\":\"uint256\"}],\"name\":\"TokenAdded\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"userId\",\"type\":\"uint256\"},{\"indexed\":true,\"name\":\"tokenId\",\"type\":\"uint256\"}],\"name\":\"TokenDeleted\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"from\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"to\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"tokenId\",\"type\":\"uint256\"}],\"name\":\"TokenTransferred\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"previousOwner\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"newOwner\",\"type\":\"address\"}],\"name\":\"OwnershipTransferred\",\"type\":\"event\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_ADDTOKEN = "addToken";

    public static final String FUNC__CONTRACTOWNER = "_contractOwner";

    public static final String FUNC_GETUSERTOKEN = "getUserToken";

    public static final String FUNC_GETUSERBYADDRESS = "getUserByAddress";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_GETUSERADDRESS = "getUserAddress";

    public static final String FUNC_GENERATEID = "generateID";

    public static final String FUNC_GETUSERTOKENCOUNT = "getUserTokenCount";

    public static final String FUNC_GETUSER = "getUser";

    public static final String FUNC_GETUSERCOUNT = "getUserCount";

    public static final String FUNC_CREATEUSER = "createUser";

    public static final String FUNC_INITTOKEN = "initToken";

    public static final String FUNC_DELTOKEN = "delToken";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_TRANSFERTOKEN = "transferToken";

    public static final Event USERCREATED_EVENT = new Event("UserCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event TOKENADDED_EVENT = new Event("TokenAdded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event TOKENDELETED_EVENT = new Event("TokenDeleted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event TOKENTRANSFERRED_EVENT = new Event("TokenTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    protected BcosUser(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public TransactionReceipt addToken(BigInteger _userId, BigInteger _tokenId) {
        final Function function = new Function(
                FUNC_ADDTOKEN, 
                Arrays.<Type>asList(new Uint256(_userId),
                new Uint256(_tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] addToken(BigInteger _userId, BigInteger _tokenId, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_ADDTOKEN, 
                Arrays.<Type>asList(new Uint256(_userId),
                new Uint256(_tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForAddToken(BigInteger _userId, BigInteger _tokenId) {
        final Function function = new Function(
                FUNC_ADDTOKEN, 
                Arrays.<Type>asList(new Uint256(_userId),
                new Uint256(_tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<BigInteger, BigInteger> getAddTokenInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_ADDTOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<BigInteger, BigInteger>(

                (BigInteger) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public String _contractOwner() throws ContractException {
        final Function function = new Function(FUNC__CONTRACTOWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public BigInteger getUserToken(BigInteger _userId, BigInteger _index) throws ContractException {
        final Function function = new Function(FUNC_GETUSERTOKEN, 
                Arrays.<Type>asList(new Uint256(_userId),
                new Uint256(_index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public BigInteger getUserByAddress(String _userAddress) throws ContractException {
        final Function function = new Function(FUNC_GETUSERBYADDRESS, 
                Arrays.<Type>asList(new Address(_userAddress)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public TransactionReceipt renounceOwnership() {
        final Function function = new Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] renounceOwnership(TransactionCallback callback) {
        final Function function = new Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForRenounceOwnership() {
        final Function function = new Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public String getUserAddress(BigInteger _userId) throws ContractException {
        final Function function = new Function(FUNC_GETUSERADDRESS, 
                Arrays.<Type>asList(new Uint256(_userId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
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

    public BigInteger getUserTokenCount(BigInteger _userId) throws ContractException {
        final Function function = new Function(FUNC_GETUSERTOKENCOUNT, 
                Arrays.<Type>asList(new Uint256(_userId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public Tuple3<String, BigInteger, BigInteger> getUser(BigInteger _userId) throws ContractException {
        final Function function = new Function(FUNC_GETUSER, 
                Arrays.<Type>asList(new Uint256(_userId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple3<String, BigInteger, BigInteger>(
                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue());
    }

    public BigInteger getUserCount() throws ContractException {
        final Function function = new Function(FUNC_GETUSERCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public TransactionReceipt createUser(String _userAddress) {
        final Function function = new Function(
                FUNC_CREATEUSER, 
                Arrays.<Type>asList(new Address(_userAddress)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] createUser(String _userAddress, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CREATEUSER, 
                Arrays.<Type>asList(new Address(_userAddress)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForCreateUser(String _userAddress) {
        final Function function = new Function(
                FUNC_CREATEUSER, 
                Arrays.<Type>asList(new Address(_userAddress)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<String> getCreateUserInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CREATEUSER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public TransactionReceipt initToken(String _to, BigInteger _tokenId) {
        final Function function = new Function(
                FUNC_INITTOKEN, 
                Arrays.<Type>asList(new Address(_to),
                new Uint256(_tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] initToken(String _to, BigInteger _tokenId, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_INITTOKEN, 
                Arrays.<Type>asList(new Address(_to),
                new Uint256(_tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForInitToken(String _to, BigInteger _tokenId) {
        final Function function = new Function(
                FUNC_INITTOKEN, 
                Arrays.<Type>asList(new Address(_to),
                new Uint256(_tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<String, BigInteger> getInitTokenInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_INITTOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public TransactionReceipt delToken(BigInteger _userId, BigInteger _tokenId) {
        final Function function = new Function(
                FUNC_DELTOKEN, 
                Arrays.<Type>asList(new Uint256(_userId),
                new Uint256(_tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] delToken(BigInteger _userId, BigInteger _tokenId, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_DELTOKEN, 
                Arrays.<Type>asList(new Uint256(_userId),
                new Uint256(_tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForDelToken(BigInteger _userId, BigInteger _tokenId) {
        final Function function = new Function(
                FUNC_DELTOKEN, 
                Arrays.<Type>asList(new Uint256(_userId),
                new Uint256(_tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<BigInteger, BigInteger> getDelTokenInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_DELTOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<BigInteger, BigInteger>(

                (BigInteger) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public TransactionReceipt transferOwnership(String newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new Address(newOwner)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] transferOwnership(String newOwner, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new Address(newOwner)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForTransferOwnership(String newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new Address(newOwner)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<String> getTransferOwnershipInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public TransactionReceipt transferToken(String _from, String _to, BigInteger _tokenId) {
        final Function function = new Function(
                FUNC_TRANSFERTOKEN, 
                Arrays.<Type>asList(new Address(_from),
                new Address(_to),
                new Uint256(_tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] transferToken(String _from, String _to, BigInteger _tokenId, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_TRANSFERTOKEN, 
                Arrays.<Type>asList(new Address(_from),
                new Address(_to),
                new Uint256(_tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForTransferToken(String _from, String _to, BigInteger _tokenId) {
        final Function function = new Function(
                FUNC_TRANSFERTOKEN, 
                Arrays.<Type>asList(new Address(_from),
                new Address(_to),
                new Uint256(_tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple3<String, String, BigInteger> getTransferTokenInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_TRANSFERTOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<String, String, BigInteger>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue()
                );
    }

    public List<UserCreatedEventResponse> getUserCreatedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(USERCREATED_EVENT, transactionReceipt);
        ArrayList<UserCreatedEventResponse> responses = new ArrayList<UserCreatedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            UserCreatedEventResponse typedResponse = new UserCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.userAddress = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.userId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeUserCreatedEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(USERCREATED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeUserCreatedEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(USERCREATED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<TokenAddedEventResponse> getTokenAddedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(TOKENADDED_EVENT, transactionReceipt);
        ArrayList<TokenAddedEventResponse> responses = new ArrayList<TokenAddedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            TokenAddedEventResponse typedResponse = new TokenAddedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.userId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeTokenAddedEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(TOKENADDED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeTokenAddedEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(TOKENADDED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<TokenDeletedEventResponse> getTokenDeletedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(TOKENDELETED_EVENT, transactionReceipt);
        ArrayList<TokenDeletedEventResponse> responses = new ArrayList<TokenDeletedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            TokenDeletedEventResponse typedResponse = new TokenDeletedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.userId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeTokenDeletedEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(TOKENDELETED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeTokenDeletedEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(TOKENDELETED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<TokenTransferredEventResponse> getTokenTransferredEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(TOKENTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<TokenTransferredEventResponse> responses = new ArrayList<TokenTransferredEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            TokenTransferredEventResponse typedResponse = new TokenTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeTokenTransferredEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(TOKENTRANSFERRED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeTokenTransferredEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(TOKENTRANSFERRED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeOwnershipTransferredEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeOwnershipTransferredEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public static BcosUser load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new BcosUser(contractAddress, client, credential);
    }

    public static BcosUser deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(BcosUser.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }

    public static class UserCreatedEventResponse {
        public TransactionReceipt.Logs log;

        public String userAddress;

        public BigInteger userId;
    }

    public static class TokenAddedEventResponse {
        public TransactionReceipt.Logs log;

        public BigInteger userId;

        public BigInteger tokenId;
    }

    public static class TokenDeletedEventResponse {
        public TransactionReceipt.Logs log;

        public BigInteger userId;

        public BigInteger tokenId;
    }

    public static class TokenTransferredEventResponse {
        public TransactionReceipt.Logs log;

        public String from;

        public String to;

        public BigInteger tokenId;
    }

    public static class OwnershipTransferredEventResponse {
        public TransactionReceipt.Logs log;

        public String previousOwner;

        public String newOwner;
    }
}
