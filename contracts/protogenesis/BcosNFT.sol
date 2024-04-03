// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.4.25;

import "./Utils.sol";
import "./Ownable.sol";

contract BcosNFT is Utils, Ownable {
    // 定义结构体表示 NFT
    struct Token {
        address creator;  // NFT 创建者地址
        address owner;    // NFT 拥有者地址
        string name;      // NFT 名称
        string tokenURI;  // NFT Data
        uint256 tokenId;  // NFT 唯一 ID
    }

    // 通过 tokenId 获取 NFT
    mapping(uint256 => Token)public tokens;
    uint256 tokenLength;

    // 事件通知 NFT 发行
    event NFTCreated(uint256 indexed tokenId, address indexed creator, address indexed owner, string name);

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

    // 发行一个新的 NFT
    function createNFT(string creator, string memory _name, string memory baseURI, uint256 numberOfTokens) public onlyOwner {
        for (uint256 i = 0; i < numberOfTokens; i++) {
            uint256 tokenId = generateID();
            Token memory newToken = Token(creator, address(0), _name, baseURI, tokenId);
            tokens.push(tokenId, newToken);
            tokenLength++;

            emit NFTCreated(tokenId, msg.sender, address(0), _name);
        }
    }

    // 交易 NFT
    function transferNFT(uint256 _tokenId, address _to) public isApprovedOrOwner {
        require(tokenMapping[_tokenId].owner == msg.sender, "You are not the owner of this token");
        address _form = tokens[_tokenId].owner;
        tokens[_tokenId].owner = _to;

        emit NFTtransfer(_tokenId, _form, _to);
    }

    // 获取指定 NFT 的信息
    function getNFT(uint256 _tokenId) public view returns (address, address, string memory, string memory, uint256) {
        require(_tokenId > 0 && tokens[_tokenId].creator != address(0), "Invalid token ID");
        return (tokens[_tokenId].creator, tokens[_tokenId].owner, tokens[_tokenId].name,, tokens[_tokenId].tokenId);
    }

    function getNFTCreator(uint256 _tokenId) public view returns (address) {
        require(_tokenId > 0 && tokens[_tokenId].creator != address(0), "Invalid token ID");
        return tokens[_tokenId].creator;
    }

    function getNFTName(uint256 _tokenId) public view returns (address) {
        require(_tokenId > 0 && tokens[_tokenId].creator != address(0), "Invalid token ID");
        return tokens[_tokenId].name;
    }

    function getNFTOwner(uint256 _tokenId) public view returns (address) {
        require(_tokenId > 0 && tokens[_tokenId].creator != address(0), "Invalid token ID");
        return tokens[_tokenId].owner;
    }

    function getNFTTokenURI(uint256 _tokenId) public view returns (address) {
        require(_tokenId > 0 && tokens[_tokenId].creator != address(0), "Invalid token ID");
        return tokens[_tokenId].tokenURI;
    }
}
