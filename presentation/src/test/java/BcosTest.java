import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.knight.infrastructure.fisco.service.biz.BcosTestService;
import org.knight.infrastructure.fisco.service.biz.ChainService;
import org.knight.infrastructure.fisco.service.biz.DeployService;
import org.knight.infrastructure.init.service.InitDataService;
import org.knight.infrastructure.repository.impl.CollectionRepositoryImpl;
import org.knight.infrastructure.repository.impl.IssuedCollectionRepositoryImpl;
import org.knight.infrastructure.repository.impl.MemberHoldCollectionRepositoryImpl;
import org.knight.infrastructure.repository.impl.MemberRepositoryImpl;
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

    private String userAddress;

    private String nftAddress;

    private final BcosTestService bcosTestService;

    private final InitDataService initDataService;

    private final DeployService deployService;

    private final ChainService chainService;

    private final MemberRepositoryImpl memberRepositoryImpl;

    private final CollectionRepositoryImpl collectionRepository;

    private final IssuedCollectionRepositoryImpl issuedCollectionRepository;

    private final MemberHoldCollectionRepositoryImpl memberHoldCollectionRepository;



    @Autowired
    BcosTest(BcosTestService bcosTestService, InitDataService initDataService, DeployService deployService, ChainService chainService, MemberRepositoryImpl memberRepositoryImpl, CollectionRepositoryImpl collectionRepository, IssuedCollectionRepositoryImpl issuedCollectionRepository, MemberHoldCollectionRepositoryImpl memberHoldCollectionRepository) {
        this.bcosTestService = bcosTestService;
        this.initDataService = initDataService;
        this.deployService = deployService;
        this.chainService = chainService;
        this.memberRepositoryImpl = memberRepositoryImpl;
        this.collectionRepository = collectionRepository;
        this.issuedCollectionRepository = issuedCollectionRepository;
        this.memberHoldCollectionRepository = memberHoldCollectionRepository;
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
    void blockNumber() {
        bcosTestService.getBlockNumber();
    }

    @Test
    void firstInit(){
        initDataService.initCollectionStock();
    }

    @Test
    void dataTest(){
    initDataService.initIssuedCollection();
    }

    @Test
    void testDeploy() throws ContractException {
        System.err.println("BCOSUSER");
        System.err.println(deployService.deployBcosUserContract());
        System.err.println("BCOSNFT");
        System.err.println(deployService.deployBcosNFTContract());
    }

    @Test
    void testInit() throws ContractException {
        initDataService.initCollectionCover();
//        initDataService.initCreator();
//        chainService.initCollectionData(collectionRepository,issuedCollectionRepository);
//        chainService.issuedCollection("test","test","test", 1);
//        chainService.issuedCollection("test","test","test", 2, 2);
//        chainService.initIssuedCollectionData(issuedCollectionRepository, collectionRepository);
//        chainService.initUserData(memberRepositoryImpl, memberHoldCollectionRepository, issuedCollectionRepository, collectionRepository);
//        chainService.initChain(memberHoldCollectionRepository,memberRepositoryImpl,collectionRepository);
    }

    @Test
    void getDeployAddress() {
        System.err.println(chainService.getDeployAddress());
    }

    @Test
    void getNewPrivateKey() {
        System.err.println(chainService.getNewDeployAccount());
    }


    @Test
    void testTransaction() throws ContractException {
        userAddress = "0xa56cba84f9fa3a2ce524dceeee5bb46eaa9094c6";
        nftAddress = "0xb8ca726a438d3d4703a1c7735ddef8afaea9880e";
        chainService.testNFTTransaction(nftAddress);
        chainService.testUserTransaction(userAddress);
    }


    @Test
    void quickTest() throws ContractException {
        chainService.quickTest();
    }
}
