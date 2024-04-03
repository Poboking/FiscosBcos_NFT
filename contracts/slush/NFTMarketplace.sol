// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.4.25;

import "openzeppelin/contracts/token/ERC721/IERC721.sol";
import "openzeppelin/contracts/utils/Address.sol";


contract NFTMarketplace {
    struct Trade {
        address buyer;
        address seller;
        uint256 price;
    }

    mapping(bytes32 => Trade) public trades;

    event TradeCreated(bytes32 tradeId, address indexed buyer, address indexed seller, uint256 price);

    function createTrade(
        address _nftContract,
        uint256 _tokenId,
        uint256 _price,
        address _seller
    ) external payable returns (bytes32) {
        bytes32 tradeId = keccak256(abi.encodePacked(block.timestamp, msg.sender, _nftContract, _tokenId, _price));

        require(msg.value == _price, "Insufficient funds");

        IERC721(_nftContract).transferFrom(_seller, address(this), _tokenId);

        Trade memory newTrade = Trade({
            buyer: msg.sender,
            seller: _seller,
            price: _price
        });
        trades[tradeId] = newTrade;

        emit TradeCreated(tradeId, msg.sender, _seller, _price);

        return tradeId;
    }

    function fulfillTrade(bytes32 _tradeId) external {
        Trade storage trade = trades[_tradeId];
        require(trade.buyer == msg.sender, "Only the buyer can fulfill the trade");

        payable(trade.seller).transfer(trade.price);
        delete trades[_tradeId];

        IERC721(_nftContract).transferFrom(address(this), msg.sender, _tokenId);
    }

    function cancelTrade(bytes32 _tradeId) external {
        Trade storage trade = trades[_tradeId];
        require(trade.seller == msg.sender, "Only the seller can cancel the trade");

        IERC721(_nftContract).transferFrom(address(this), trade.seller, _tokenId);
        delete trades[_tradeId];
    }
}