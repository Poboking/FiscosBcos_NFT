package org.knight.infrastructure.fisco.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Address;
import org.fisco.bcos.sdk.abi.datatypes.DynamicArray;
import org.fisco.bcos.sdk.abi.datatypes.Event;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.Utf8String;
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple3;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple4;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple5;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple7;
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
public class BcosNFT extends Contract {
    public static final String[] BINARY_ARRAY = {};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[{\"name\":\"_tokenURI\",\"type\":\"string\"}],\"name\":\"getTokenIdByTokenURI\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"creatorAddress\",\"type\":\"address\"},{\"name\":\"_name\",\"type\":\"string\"},{\"name\":\"baseURI\",\"type\":\"string\"},{\"name\":\"quantity\",\"type\":\"uint256\"}],\"name\":\"createNFT\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"creatorAddress\",\"type\":\"address\"},{\"name\":\"_name\",\"type\":\"string\"},{\"name\":\"baseURI\",\"type\":\"string\"}],\"name\":\"createNFT\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"_contractOwner\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_tokenId\",\"type\":\"uint256\"}],\"name\":\"getNFTCreator\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"string\"}],\"name\":\"tokenURIToTokenId\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_name\",\"type\":\"string\"},{\"name\":\"serialNumber\",\"type\":\"uint256\"}],\"name\":\"getTokenIdByNameAndSerialNumber\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"tokens\",\"outputs\":[{\"name\":\"creator\",\"type\":\"address\"},{\"name\":\"owner\",\"type\":\"address\"},{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"tokenURI\",\"type\":\"string\"},{\"name\":\"tokenId\",\"type\":\"uint256\"},{\"name\":\"serialNumber\",\"type\":\"uint256\"},{\"name\":\"quantity\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_tokenId\",\"type\":\"uint256\"}],\"name\":\"getNFT\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"tokenNameToIds\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[],\"name\":\"renounceOwnership\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_tokenId\",\"type\":\"uint256\"},{\"name\":\"_to\",\"type\":\"address\"}],\"name\":\"transferNFT\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[],\"name\":\"generateID\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_tokenId\",\"type\":\"uint256\"}],\"name\":\"getNFTSerialNumber\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_name\",\"type\":\"string\"}],\"name\":\"getTokenIdsByName\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_tokenId\",\"type\":\"uint256\"}],\"name\":\"getNFTQuantity\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"creatorAddress\",\"type\":\"address\"},{\"name\":\"_name\",\"type\":\"string\"},{\"name\":\"baseURI\",\"type\":\"string\"},{\"name\":\"serialNumber\",\"type\":\"uint256\"},{\"name\":\"quantity\",\"type\":\"uint256\"}],\"name\":\"createNFT\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_tokenId\",\"type\":\"uint256\"}],\"name\":\"getNFTTokenURI\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_tokenId\",\"type\":\"uint256\"}],\"name\":\"getNFTOwner\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_tokenId\",\"type\":\"uint256\"}],\"name\":\"getNFTName\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"newOwner\",\"type\":\"address\"}],\"name\":\"transferOwnership\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"tokenId\",\"type\":\"uint256\"},{\"indexed\":true,\"name\":\"creator\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"owner\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"name\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"serialNumber\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"quantity\",\"type\":\"uint256\"}],\"name\":\"NFTCreated\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"tokenId\",\"type\":\"uint256\"},{\"indexed\":true,\"name\":\"from\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"to\",\"type\":\"address\"}],\"name\":\"NFTtransfer\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"previousOwner\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"newOwner\",\"type\":\"address\"}],\"name\":\"OwnershipTransferred\",\"type\":\"event\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_GETTOKENIDBYTOKENURI = "getTokenIdByTokenURI";

    public static final String FUNC_CREATENFT = "createNFT";

    public static final String FUNC__CONTRACTOWNER = "_contractOwner";

    public static final String FUNC_GETNFTCREATOR = "getNFTCreator";

    public static final String FUNC_TOKENURITOTOKENID = "tokenURIToTokenId";

    public static final String FUNC_GETTOKENIDBYNAMEANDSERIALNUMBER = "getTokenIdByNameAndSerialNumber";

    public static final String FUNC_TOKENS = "tokens";

    public static final String FUNC_GETNFT = "getNFT";

    public static final String FUNC_TOKENNAMETOIDS = "tokenNameToIds";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_TRANSFERNFT = "transferNFT";

    public static final String FUNC_GENERATEID = "generateID";

    public static final String FUNC_GETNFTSERIALNUMBER = "getNFTSerialNumber";

    public static final String FUNC_GETTOKENIDSBYNAME = "getTokenIdsByName";

    public static final String FUNC_GETNFTQUANTITY = "getNFTQuantity";

    public static final String FUNC_GETNFTTOKENURI = "getNFTTokenURI";

    public static final String FUNC_GETNFTOWNER = "getNFTOwner";

    public static final String FUNC_GETNFTNAME = "getNFTName";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event NFTCREATED_EVENT = new Event("NFTCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event NFTTRANSFER_EVENT = new Event("NFTtransfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    protected BcosNFT(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public BigInteger getTokenIdByTokenURI(String _tokenURI) throws ContractException {
        final Function function = new Function(FUNC_GETTOKENIDBYTOKENURI, 
                Arrays.<Type>asList(new Utf8String(_tokenURI)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public TransactionReceipt createNFT(String creatorAddress, String _name, String baseURI, BigInteger quantity) {
        final Function function = new Function(
                FUNC_CREATENFT, 
                Arrays.<Type>asList(new Address(creatorAddress),
                new Utf8String(_name),
                new Utf8String(baseURI),
                new Uint256(quantity)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] createNFT(String creatorAddress, String _name, String baseURI, BigInteger quantity, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CREATENFT, 
                Arrays.<Type>asList(new Address(creatorAddress),
                new Utf8String(_name),
                new Utf8String(baseURI),
                new Uint256(quantity)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForCreateNFT(String creatorAddress, String _name, String baseURI, BigInteger quantity) {
        final Function function = new Function(
                FUNC_CREATENFT, 
                Arrays.<Type>asList(new Address(creatorAddress),
                new Utf8String(_name),
                new Utf8String(baseURI),
                new Uint256(quantity)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple4<String, String, String, BigInteger> getCreateNFTAddressStringStringUint256Input(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CREATENFT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple4<String, String, String, BigInteger>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (BigInteger) results.get(3).getValue()
                );
    }

    public TransactionReceipt createNFT(String creatorAddress, String _name, String baseURI) {
        final Function function = new Function(
                FUNC_CREATENFT, 
                Arrays.<Type>asList(new Address(creatorAddress),
                new Utf8String(_name),
                new Utf8String(baseURI)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] createNFT(String creatorAddress, String _name, String baseURI, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CREATENFT, 
                Arrays.<Type>asList(new Address(creatorAddress),
                new Utf8String(_name),
                new Utf8String(baseURI)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForCreateNFT(String creatorAddress, String _name, String baseURI) {
        final Function function = new Function(
                FUNC_CREATENFT, 
                Arrays.<Type>asList(new Address(creatorAddress),
                new Utf8String(_name),
                new Utf8String(baseURI)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple3<String, String, String> getCreateNFTAddressStringStringInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CREATENFT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<String, String, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue()
                );
    }

    public Tuple1<BigInteger> getCreateNFTAddressStringStringOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_CREATENFT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public String _contractOwner() throws ContractException {
        final Function function = new Function(FUNC__CONTRACTOWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public String getNFTCreator(BigInteger _tokenId) throws ContractException {
        final Function function = new Function(FUNC_GETNFTCREATOR, 
                Arrays.<Type>asList(new Uint256(_tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public BigInteger tokenURIToTokenId(String param0) throws ContractException {
        final Function function = new Function(FUNC_TOKENURITOTOKENID, 
                Arrays.<Type>asList(new Utf8String(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public BigInteger getTokenIdByNameAndSerialNumber(String _name, BigInteger serialNumber) throws ContractException {
        final Function function = new Function(FUNC_GETTOKENIDBYNAMEANDSERIALNUMBER, 
                Arrays.<Type>asList(new Utf8String(_name),
                new Uint256(serialNumber)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public Tuple7<String, String, String, String, BigInteger, BigInteger, BigInteger> tokens(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_TOKENS, 
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple7<String, String, String, String, BigInteger, BigInteger, BigInteger>(
                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (String) results.get(3).getValue(), 
                (BigInteger) results.get(4).getValue(), 
                (BigInteger) results.get(5).getValue(), 
                (BigInteger) results.get(6).getValue());
    }

    public Tuple4<String, String, String, BigInteger> getNFT(BigInteger _tokenId) throws ContractException {
        final Function function = new Function(FUNC_GETNFT, 
                Arrays.<Type>asList(new Uint256(_tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple4<String, String, String, BigInteger>(
                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (BigInteger) results.get(3).getValue());
    }

    public BigInteger tokenNameToIds(String param0, BigInteger param1) throws ContractException {
        final Function function = new Function(FUNC_TOKENNAMETOIDS, 
                Arrays.<Type>asList(new Utf8String(param0),
                new Uint256(param1)),
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

    public TransactionReceipt transferNFT(BigInteger _tokenId, String _to) {
        final Function function = new Function(
                FUNC_TRANSFERNFT, 
                Arrays.<Type>asList(new Uint256(_tokenId),
                new Address(_to)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] transferNFT(BigInteger _tokenId, String _to, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_TRANSFERNFT, 
                Arrays.<Type>asList(new Uint256(_tokenId),
                new Address(_to)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForTransferNFT(BigInteger _tokenId, String _to) {
        final Function function = new Function(
                FUNC_TRANSFERNFT, 
                Arrays.<Type>asList(new Uint256(_tokenId),
                new Address(_to)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<BigInteger, String> getTransferNFTInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_TRANSFERNFT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<BigInteger, String>(

                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
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

    public BigInteger getNFTSerialNumber(BigInteger _tokenId) throws ContractException {
        final Function function = new Function(FUNC_GETNFTSERIALNUMBER, 
                Arrays.<Type>asList(new Uint256(_tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public List getTokenIdsByName(String _name) throws ContractException {
        final Function function = new Function(FUNC_GETTOKENIDSBYNAME, 
                Arrays.<Type>asList(new Utf8String(_name)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
        List<Type> result = (List<Type>) executeCallWithSingleValueReturn(function, List.class);
        return convertToNative(result);
    }

    public BigInteger getNFTQuantity(BigInteger _tokenId) throws ContractException {
        final Function function = new Function(FUNC_GETNFTQUANTITY, 
                Arrays.<Type>asList(new Uint256(_tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public TransactionReceipt createNFT(String creatorAddress, String _name, String baseURI, BigInteger serialNumber, BigInteger quantity) {
        final Function function = new Function(
                FUNC_CREATENFT, 
                Arrays.<Type>asList(new Address(creatorAddress),
                new Utf8String(_name),
                new Utf8String(baseURI),
                new Uint256(serialNumber),
                new Uint256(quantity)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] createNFT(String creatorAddress, String _name, String baseURI, BigInteger serialNumber, BigInteger quantity, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CREATENFT, 
                Arrays.<Type>asList(new Address(creatorAddress),
                new Utf8String(_name),
                new Utf8String(baseURI),
                new Uint256(serialNumber),
                new Uint256(quantity)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForCreateNFT(String creatorAddress, String _name, String baseURI, BigInteger serialNumber, BigInteger quantity) {
        final Function function = new Function(
                FUNC_CREATENFT, 
                Arrays.<Type>asList(new Address(creatorAddress),
                new Utf8String(_name),
                new Utf8String(baseURI),
                new Uint256(serialNumber),
                new Uint256(quantity)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple5<String, String, String, BigInteger, BigInteger> getCreateNFTAddressStringStringUint256Uint256Input(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CREATENFT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple5<String, String, String, BigInteger, BigInteger>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (BigInteger) results.get(3).getValue(), 
                (BigInteger) results.get(4).getValue()
                );
    }

    public Tuple1<BigInteger> getCreateNFTAddressStringStringUint256Uint256Output(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_CREATENFT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public String getNFTTokenURI(BigInteger _tokenId) throws ContractException {
        final Function function = new Function(FUNC_GETNFTTOKENURI, 
                Arrays.<Type>asList(new Uint256(_tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public String getNFTOwner(BigInteger _tokenId) throws ContractException {
        final Function function = new Function(FUNC_GETNFTOWNER, 
                Arrays.<Type>asList(new Uint256(_tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public String getNFTName(BigInteger _tokenId) throws ContractException {
        final Function function = new Function(FUNC_GETNFTNAME, 
                Arrays.<Type>asList(new Uint256(_tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
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

    public List<NFTCreatedEventResponse> getNFTCreatedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(NFTCREATED_EVENT, transactionReceipt);
        ArrayList<NFTCreatedEventResponse> responses = new ArrayList<NFTCreatedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NFTCreatedEventResponse typedResponse = new NFTCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.creator = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.serialNumber = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.quantity = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeNFTCreatedEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(NFTCREATED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeNFTCreatedEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(NFTCREATED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<NFTtransferEventResponse> getNFTtransferEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(NFTTRANSFER_EVENT, transactionReceipt);
        ArrayList<NFTtransferEventResponse> responses = new ArrayList<NFTtransferEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NFTtransferEventResponse typedResponse = new NFTtransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.from = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeNFTtransferEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(NFTTRANSFER_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeNFTtransferEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(NFTTRANSFER_EVENT);
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

    public static BcosNFT load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new BcosNFT(contractAddress, client, credential);
    }

    public static BcosNFT deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(BcosNFT.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }

    public static class NFTCreatedEventResponse {
        public TransactionReceipt.Logs log;

        public BigInteger tokenId;

        public String creator;

        public String owner;

        public String name;

        public BigInteger serialNumber;

        public BigInteger quantity;
    }

    public static class NFTtransferEventResponse {
        public TransactionReceipt.Logs log;

        public BigInteger tokenId;

        public String from;

        public String to;
    }

    public static class OwnershipTransferredEventResponse {
        public TransactionReceipt.Logs log;

        public String previousOwner;

        public String newOwner;
    }
}
