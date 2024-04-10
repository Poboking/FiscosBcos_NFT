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
    public static final String[] BINARY_ARRAY = {"60806040526000805534801561001457600080fd5b5033600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610ef5806100656000396000f3006080604052600436106100c5576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806317b9ebec146100ca5780632bb3b114146101015780634b593e4c1461015857806369c212f6146101a357806390d976e2146101fa57806394116d4614610267578063aa83647b14610292578063b0467deb146102d3578063b5cb15f71461034e578063cdd8761814610379578063dc91f954146103bc578063ef57563f14610409578063f5537ede14610440575b600080fd5b3480156100d657600080fd5b506100ff60048036038101908080359060200190929190803590602001909291905050506104ad565b005b34801561010d57600080fd5b50610116610646565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561016457600080fd5b5061018d600480360381019080803590602001909291908035906020019092919050505061066c565b6040518082815260200191505060405180910390f35b3480156101af57600080fd5b506101e4600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061069e565b6040518082815260200191505060405180910390f35b34801561020657600080fd5b50610225600480360381019080803590602001909291905050506106e7565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561027357600080fd5b5061027c610727565b6040518082815260200191505060405180910390f35b34801561029e57600080fd5b506102bd600480360381019080803590602001909291905050506107d4565b6040518082815260200191505060405180910390f35b3480156102df57600080fd5b506102fe600480360381019080803590602001909291905050506107f4565b604051808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001838152602001828152602001935050505060405180910390f35b34801561035a57600080fd5b5061036361086b565b6040518082815260200191505060405180910390f35b34801561038557600080fd5b506103ba600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610875565b005b3480156103c857600080fd5b50610407600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506109cf565b005b34801561041557600080fd5b5061043e6004803603810190808035906020019092919080359060200190929190505050610ac3565b005b34801561044c57600080fd5b506104ab600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610d08565b005b3373ffffffffffffffffffffffffffffffffffffffff166003600084815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415156105ac576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260238152602001807f596f7520617265206e6f7420746865206f776e6572206f66207468697320746f81526020017f6b656e000000000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b600360008381526020019081526020016000206003016000815480929190600101919050555080600360008481526020019081526020016000206002016000600360008681526020019081526020016000206003015481526020019081526020016000208190555080827f7713a460c4ef3baceee6275b7dab936881047949b93f0559ce44de5c0a28e59660405160405180910390a35050565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600060036000848152602001908152602001600020600201600083815260200190815260200160002054905092915050565b6000600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b60006003600083815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b6000804260005460405160200180838152602001828152602001925050506040516020818303038152906040526040518082805190602001908083835b6020831015156107895780518252602082019150602081019050602083039250610764565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060019004905060008081548092919060010191905055508091505090565b600060036000838152602001908152602001600020600301549050919050565b60008060006003600085815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16600360008681526020019081526020016000206001015460036000878152602001908152602001600020600301549250925092509193909250565b6000600454905090565b600061087f610e91565b610887610727565b91506060604051908101604052808473ffffffffffffffffffffffffffffffffffffffff1681526020018381526020016000815250905081600260008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550806003600084815260200190815260200160002060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506020820151816001015560408201518160030155905050600460008154809291906001019190505550818373ffffffffffffffffffffffffffffffffffffffff167fa27642fccf9a9a0a8a8e31188dfec60a6565c30a20ef3ed1eb3a779a79a36b2e60405160405180910390a3505050565b60008090506109dd8361069e565b905060008114151515610a58576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f546f207573657220646f6573206e6f742065786973740000000000000000000081525060200191505060405180910390fd5b610a6281836104ad565b818373ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff167f9c8515990fd8c61431c4ac8db9b81475f90c292a1dda77731e56c22e64fc764360405160405180910390a4505050565b60003373ffffffffffffffffffffffffffffffffffffffff166003600085815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161480610b8257503373ffffffffffffffffffffffffffffffffffffffff16600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16145b1515610c1c576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260238152602001807f596f7520617265206e6f7420746865206f776e6572206f66207468697320746f81526020017f6b656e000000000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b600190505b600360008481526020019081526020016000206003015481111515610d035781600360008581526020019081526020016000206002016000838152602001908152602001600020541415610cf65760006003600085815260200190815260200160002060020160008381526020019081526020016000208190555060036000848152602001908152602001600020600301600081548092919060019003919050555081837fcd0c497f299ca733a5ed81b411f9a240c11b363935e7a7f934d26690dc8109ca60405160405180910390a3610d03565b8080600101915050610c21565b505050565b6000806000915060009050610d1c8561069e565b9150610d278461069e565b905060008214151515610da2576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260188152602001807f46726f6d207573657220646f6573206e6f74206578697374000000000000000081525060200191505060405180910390fd5b60008114151515610e1b576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f546f207573657220646f6573206e6f742065786973740000000000000000000081525060200191505060405180910390fd5b610e258284610ac3565b610e2f81846104ad565b828473ffffffffffffffffffffffffffffffffffffffff168673ffffffffffffffffffffffffffffffffffffffff167f9c8515990fd8c61431c4ac8db9b81475f90c292a1dda77731e56c22e64fc764360405160405180910390a45050505050565b606060405190810160405280600073ffffffffffffffffffffffffffffffffffffffff168152602001600081526020016000815250905600a165627a7a723058206ca193acb14d6806aece799a7de3bd208e6c65244c0990eed1a9d06666e19f760029"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"60806040526000805534801561001457600080fd5b5033600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610ef5806100656000396000f3006080604052600436106100c5576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680631614608b146100ca5780631da27aa5146101215780633abae6e51461018e5780633e8c9b07146101b95780634717476c146101fc5780634fcaf9ca1461024957806358ddd9de146102745780636a1bff12146102ef5780639274b07214610346578063ae58d3941461037d578063dc9e7f1a146103b4578063e8a22630146103ff578063f5317cc71461046c575b600080fd5b3480156100d657600080fd5b506100df6104ad565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561012d57600080fd5b5061014c600480360381019080803590602001909291905050506104d3565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561019a57600080fd5b506101a3610513565b6040518082815260200191505060405180910390f35b3480156101c557600080fd5b506101fa600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061051d565b005b34801561020857600080fd5b50610247600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610677565b005b34801561025557600080fd5b5061025e61076b565b6040518082815260200191505060405180910390f35b34801561028057600080fd5b5061029f60048036038101908080359060200190929190505050610818565b604051808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001838152602001828152602001935050505060405180910390f35b3480156102fb57600080fd5b50610330600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061088f565b6040518082815260200191505060405180910390f35b34801561035257600080fd5b5061037b60048036038101908080359060200190929190803590602001909291905050506108d8565b005b34801561038957600080fd5b506103b26004803603810190808035906020019092919080359060200190929190505050610b1d565b005b3480156103c057600080fd5b506103e96004803603810190808035906020019092919080359060200190929190505050610cb6565b6040518082815260200191505060405180910390f35b34801561040b57600080fd5b5061046a600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610ce8565b005b34801561047857600080fd5b5061049760048036038101908080359060200190929190505050610e71565b6040518082815260200191505060405180910390f35b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60006003600083815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b6000600454905090565b6000610527610e91565b61052f61076b565b91506060604051908101604052808473ffffffffffffffffffffffffffffffffffffffff1681526020018381526020016000815250905081600260008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550806003600084815260200190815260200160002060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506020820151816001015560408201518160030155905050600460008154809291906001019190505550818373ffffffffffffffffffffffffffffffffffffffff167f01f4110f63a66e89e86285eae69edb8f88cc331e76c51cc452e16f3deb0a61fc60405160405180910390a3505050565b60008090506106858361088f565b905060008114151515610700576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f546f207573657220646f6573206e6f742065786973740000000000000000000081525060200191505060405180910390fd5b61070a8183610b1d565b818373ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff167f0c66b8685ad624497c8da9c1fc84ebdb266d65077415d85eb433e16e4d5e79a860405160405180910390a4505050565b6000804260005460405160200180838152602001828152602001925050506040516020818303038152906040526040518082805190602001908083835b6020831015156107cd57805182526020820191506020810190506020830392506107a8565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060019004905060008081548092919060010191905055508091505090565b60008060006003600085815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16600360008681526020019081526020016000206001015460036000878152602001908152602001600020600301549250925092509193909250565b6000600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b60003373ffffffffffffffffffffffffffffffffffffffff166003600085815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16148061099757503373ffffffffffffffffffffffffffffffffffffffff16600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16145b1515610a31576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260238152602001807f596f7520617265206e6f7420746865206f776e6572206f66207468697320746f81526020017f6b656e000000000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b600190505b600360008481526020019081526020016000206003015481111515610b185781600360008581526020019081526020016000206002016000838152602001908152602001600020541415610b0b5760006003600085815260200190815260200160002060020160008381526020019081526020016000208190555060036000848152602001908152602001600020600301600081548092919060019003919050555081837f9aae29b1a029e50ac79a202b22fcb394cb48a58b2d80b389a45be464b22b86be60405160405180910390a3610b18565b8080600101915050610a36565b505050565b3373ffffffffffffffffffffffffffffffffffffffff166003600084815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515610c1c576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260238152602001807f596f7520617265206e6f7420746865206f776e6572206f66207468697320746f81526020017f6b656e000000000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b600360008381526020019081526020016000206003016000815480929190600101919050555080600360008481526020019081526020016000206002016000600360008681526020019081526020016000206003015481526020019081526020016000208190555080827fc1c698637da07ec737897c38369dd50569ab1f632320dfb3cabaf1fe8f21598160405160405180910390a35050565b600060036000848152602001908152602001600020600201600083815260200190815260200160002054905092915050565b6000806000915060009050610cfc8561088f565b9150610d078461088f565b905060008214151515610d82576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260188152602001807f46726f6d207573657220646f6573206e6f74206578697374000000000000000081525060200191505060405180910390fd5b60008114151515610dfb576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f546f207573657220646f6573206e6f742065786973740000000000000000000081525060200191505060405180910390fd5b610e0582846108d8565b610e0f8184610b1d565b828473ffffffffffffffffffffffffffffffffffffffff168673ffffffffffffffffffffffffffffffffffffffff167f0c66b8685ad624497c8da9c1fc84ebdb266d65077415d85eb433e16e4d5e79a860405160405180910390a45050505050565b600060036000838152602001908152602001600020600301549050919050565b606060405190810160405280600073ffffffffffffffffffffffffffffffffffffffff168152602001600081526020016000815250905600a165627a7a72305820214b3c67269b4c7351e9e1857df05888b3f9b2cf52f3492928b2f51d0244ddef0029"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"_userId\",\"type\":\"uint256\"},{\"name\":\"_tokenId\",\"type\":\"uint256\"}],\"name\":\"addToken\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"_contractOwner\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_userId\",\"type\":\"uint256\"},{\"name\":\"_index\",\"type\":\"uint256\"}],\"name\":\"getUserToken\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_userAddress\",\"type\":\"address\"}],\"name\":\"getUserByAddress\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_userId\",\"type\":\"uint256\"}],\"name\":\"getUserAddress\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[],\"name\":\"generateID\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_userId\",\"type\":\"uint256\"}],\"name\":\"getUserTokenCount\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_userId\",\"type\":\"uint256\"}],\"name\":\"getUser\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getUserCount\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_userAddress\",\"type\":\"address\"}],\"name\":\"createUser\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_to\",\"type\":\"address\"},{\"name\":\"_tokenId\",\"type\":\"uint256\"}],\"name\":\"initToken\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_userId\",\"type\":\"uint256\"},{\"name\":\"_tokenId\",\"type\":\"uint256\"}],\"name\":\"delToken\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_from\",\"type\":\"address\"},{\"name\":\"_to\",\"type\":\"address\"},{\"name\":\"_tokenId\",\"type\":\"uint256\"}],\"name\":\"transferToken\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"userAddress\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"userId\",\"type\":\"uint256\"}],\"name\":\"UserCreated\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"userId\",\"type\":\"uint256\"},{\"indexed\":true,\"name\":\"tokenId\",\"type\":\"uint256\"}],\"name\":\"TokenAdded\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"userId\",\"type\":\"uint256\"},{\"indexed\":true,\"name\":\"tokenId\",\"type\":\"uint256\"}],\"name\":\"TokenDeleted\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"from\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"to\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"tokenId\",\"type\":\"uint256\"}],\"name\":\"TokenTransferred\",\"type\":\"event\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_ADDTOKEN = "addToken";

    public static final String FUNC__CONTRACTOWNER = "_contractOwner";

    public static final String FUNC_GETUSERTOKEN = "getUserToken";

    public static final String FUNC_GETUSERBYADDRESS = "getUserByAddress";

    public static final String FUNC_GETUSERADDRESS = "getUserAddress";

    public static final String FUNC_GENERATEID = "generateID";

    public static final String FUNC_GETUSERTOKENCOUNT = "getUserTokenCount";

    public static final String FUNC_GETUSER = "getUser";

    public static final String FUNC_GETUSERCOUNT = "getUserCount";

    public static final String FUNC_CREATEUSER = "createUser";

    public static final String FUNC_INITTOKEN = "initToken";

    public static final String FUNC_DELTOKEN = "delToken";

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
}
