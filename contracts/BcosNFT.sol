// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.4.25;

import "./Utils.sol";
import "./Ownable.sol";

contract BcosNFT is Utils, Ownable {

    address public _contractOwner;

    constructor() {
        _contractOwner = msg.sender; // 将合约部署者设置为合约拥有者
    }

    // 定义结构体表示 NFT
    struct Token {
        address creator;  // NFT 创建者地址
        address owner;    // NFT 拥有者地址
        string name;      // NFT 名称
        string tokenURI;  // NFT Data
        uint256 tokenId;  // NFT 唯一 ID
        uint256 serialNumber;  // NFT 序列号
        uint256 quantity; //发行数量
    }

    // 通过 tokenId 获取 NFT
    mapping(uint256 => Token)public tokens;
    mapping(string => uint256[])public tokenNameToIds;
    mapping(string => uint256)public tokenURIToTokenId;
    uint256 tokenLength;

    // 事件通知 NFT 发行
    event NFTCreated(uint256 indexed tokenId, address indexed creator, address indexed owner, string name, uint256 serialNumber, uint256 quantity);

    // 事件通知 NFT交易
    event NFTtransfer(uint256 indexed tokenId, address indexed from, address indexed to);

    // 修饰符：限制只有 NFT 拥有者或经过授权的地址可以调用
    modifier isApprovedOrOwner(uint256 _tokenId) {
        require(_tokenId > 0, "Invalid token ID");
        require(tokens[_tokenId].creator != address(0), "Token does not exist");
        require(tokens[_tokenId].owner == msg.sender || msg.sender == address(this),
            "You are not approved or the owner of this token");
        _;
    }

    // 发行指定数量新的 NFT
    function createNFT(address creatorAddress, string _name, string baseURI, uint256 quantity) public onlyOwner {
        uint256[] memory tokenIds = new uint256[](quantity);
        for (uint256 i = 0; i < quantity; i++) {
            uint256 tokenId = generateID();
            Token memory newToken = Token(creatorAddress, _contractOwner, _name, baseURI, tokenId, i, quantity);
            tokenURIToTokenId[baseURI] = tokenId;
            tokens[tokenId] = newToken;
            tokenIds[i] = tokenId;
            tokenLength++;
            emit NFTCreated(tokenId, msg.sender, _contractOwner, _name, i + 1, quantity);
        }
        tokenNameToIds[_name] = tokenIds;
    }

    //发行指定序列号的NFT
    function createNFT(address creatorAddress, string _name, string baseURI, uint256 serialNumber, uint256 quantity) public onlyOwner returns (uint256){
        require(serialNumber > 0 && serialNumber <= quantity, "Invalid serial number");
        require(tokenNameToIds[_name][serialNumber - 1] == 0, "Token already exists");
        uint256 tokenId = generateID();
        Token memory newToken = Token(creatorAddress, _contractOwner, _name, baseURI, tokenId, serialNumber, quantity);
        tokens[tokenId] = newToken;
        tokenURIToTokenId[baseURI] = tokenId;
        //如果没有初始化过
        if (tokenNameToIds[_name].length == 0) {
            uint256[] memory _Ids_1 = new uint256[](quantity);
            _Ids_1[serialNumber - 1] = tokenId;
            tokenNameToIds[_name] = _Ids_1;
        //如果初始化过,却预计发行数量小于序列号
        } else if (tokenNameToIds[_name].length < serialNumber) {
            uint256[] memory _Ids_2 = new uint256[](serialNumber);
            _Ids_2[serialNumber - 1] = tokenId;
            tokenNameToIds[_name] = _Ids_2;
        } else {
            tokenNameToIds[_name][serialNumber - 1] = tokenId;
        }
        tokenLength++;
        emit NFTCreated(tokenId, msg.sender, _contractOwner, _name, serialNumber, quantity);
        return tokenId;
    }

    // 发行单个NFT
    function createNFT(address creatorAddress, string _name, string baseURI) public onlyOwner returns (uint256){
        uint256 tokenId = generateID();
        Token memory newToken = Token(creatorAddress, _contractOwner, _name, baseURI, tokenId, 0, 1);
        tokens[tokenId] = newToken;
        tokenURIToTokenId[baseURI] = tokenId;
        uint256[] memory Ids = new uint256[](1);
        Ids[0] = tokenId;
        tokenNameToIds[_name] = Ids;
        tokenLength++;
        emit NFTCreated(tokenId, msg.sender, _contractOwner, _name, 1, 1);
        return tokenId;
    }

    // 交易 NFT
    function transferNFT(uint256 _tokenId, address _to) public isApprovedOrOwner(_tokenId) {
        require(tokens[_tokenId].owner == msg.sender, "You are not the owner of this token");
        address _from = tokens[_tokenId].owner;
        tokens[_tokenId].owner = _to;
        emit NFTtransfer(_tokenId, _from, _to);
    }

    // 获取指定 NFT 的信息
    function getNFT(uint256 _tokenId) public view returns (address, address, string memory, uint256) {
        require(_tokenId > 0 && tokens[_tokenId].creator != address(0), "Invalid token ID");
        return (tokens[_tokenId].creator, tokens[_tokenId].owner, tokens[_tokenId].name, tokens[_tokenId].tokenId);
    }

    function getNFTCreator(uint256 _tokenId) public view returns (address) {
        require(_tokenId > 0 && tokens[_tokenId].creator != address(0), "Invalid token ID");
        return tokens[_tokenId].creator;
    }

    function getNFTName(uint256 _tokenId) public view returns (string) {
        require(_tokenId > 0 && tokens[_tokenId].creator != address(0), "Invalid token ID");
        return tokens[_tokenId].name;
    }

    function getNFTOwner(uint256 _tokenId) public view returns (address) {
        require(_tokenId > 0 && tokens[_tokenId].creator != address(0), "Invalid token ID");
        return tokens[_tokenId].owner;
    }

    function getNFTTokenURI(uint256 _tokenId) public view returns (string) {
        require(_tokenId > 0 && tokens[_tokenId].creator != address(0), "Invalid token ID");
        return tokens[_tokenId].tokenURI;
    }

    function getNFTSerialNumber(uint256 _tokenId) public view returns (uint256)  {
        require(_tokenId > 0 && tokens[_tokenId].creator != address(0), "Invalid token ID");
        return tokens[_tokenId].serialNumber;
    }

    function getNFTQuantity(uint256 _tokenId) public view returns (uint256) {
        require(_tokenId > 0 && tokens[_tokenId].creator != address(0), "Invalid token ID");
        return tokens[_tokenId].quantity;
    }

    //这里根据IssuedCollectionID充当tokenURI
    function getTokenIdByTokenURI(string _tokenURI) public view returns (uint256) {
        return tokenURIToTokenId[_tokenURI];
    }

    function getTokenIdsByName(string _name) public view returns (uint256[]) {
        return tokenNameToIds[_name];
    }

    // 从1开始, 通过名称和序列号获取 NFT 的唯一 ID
    function getTokenIdByNameAndSerialNumber(string _name, uint256 serialNumber) public view returns (uint256) {
        return tokenNameToIds[_name][serialNumber - 1];
    }

}
