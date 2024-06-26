import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.knight.infrastructure.fisco.service.biz.BcosTestService;
import org.knight.infrastructure.fisco.service.biz.ChainService;
import org.knight.infrastructure.fisco.service.biz.DeployService;
import org.knight.presentation.PresentationApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/2 14:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PresentationApplication.class)
class BcosTest {

    private final BcosTestService bcosTestService;

    private final DeployService deployService;
    
    private final ChainService chainService;

    @Autowired
    BcosTest(BcosTestService bcosTestService, DeployService deployService, ChainService chainService) {
        this.bcosTestService = bcosTestService;
        this.deployService = deployService;
        this.chainService = chainService;
    }

    @Test
    void helloWorld() throws ContractException {
        String key = bcosTestService.getPublicAndPrivateKey();
        System.err.println(key);
        CryptoKeyPair address = bcosTestService.getCreateKeyPairByKey();
        System.err.println(address);
        System.err.println("deploy");
        String deployAddress = bcosTestService.deploy(address);
//        deployAddress = BcosTestService.convert(deployAddress);
        System.err.println(deployAddress + " deploy");
        System.err.println("get");
        bcosTestService.getByAddress(deployAddress, address);
        System.err.println("set cnm by address");
        bcosTestService.setByAddress(deployAddress, "cnm", address);
        System.err.println("get by address");
        bcosTestService.getByAddress(deployAddress, address);
        blockNumber();
    }

    @Test
    void blockNumber(){
        bcosTestService.getBlockNumber();
    }

    @Test
    void testDeploy() throws ContractException {
        System.err.println(deployService.deployOwnable());
        System.err.println(deployService.deployUtils());
        System.err.println(deployService.deployBcosUserContract());
        System.err.println(deployService.deployBcosNFTContract());
    }
    
    @Test
    void testInit(){
        // TODO: 2024/4/8 待测试 ChainService  
    }


}
