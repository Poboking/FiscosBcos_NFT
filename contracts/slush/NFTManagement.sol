// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.4.25;

import "./openzeppelin/contracts/token/ERC721/ERC721.sol";
import "./openzeppelin/contracts/access/Ownable.sol";
import "./Utils.sol";


contract NFTManagement is ERC721, Ownable, Utils {

    constructor() ERC721("A20NFT", "A20") {}

    function mint(address to) public onlyOwner {
        uint256 id = generateID();
        _mint(to, id);
    }


    function burn(uint256 tokenId) public onlyOwner {
        _burn(tokenId);
    }

    /*
    * @dev Create NFTs
    */
    function createNFTs(string memory baseURI, uint256 numberOfTokens) public onlyOwner {
        for (uint256 i = 0; i < numberOfTokens; i++) {
            uint256 newTokenId = generateID();
            _safeMint(msg.sender, newTokenId);
            _setTokenURI(newTokenId, string(abi.encodePacked(baseURI, uint2str(newTokenId))));
            tokenCounter++;
        }
    }

    /*
    * @dev Transfer NFT to another address
    */
    function transferNFT(address receiver, uint256 tokenId) public {
        require(_isApprovedOrOwner(_msgSender(), tokenId), "ERC721: transfer caller is not owner nor approved");
        _transfer(_msgSender(), receiver, tokenId);
    }

    /*
    * @dev Transfer ownership of NFTManagement contract to another address
    */
    function transferOwnership(address newOwner, uint256 tokenId) public {
        require(_isApprovedOrOwner(_msgSender(), tokenId), "ERC721: transfer caller is not owner nor approved");
        transferOwnership(newOwner);
    }

    function uint2str(uint256 _i) internal pure returns (string memory str) {
        if (_i == 0) {
            return "0";
        }
        uint256 j = _i;
        uint256 length;
        while (j != 0) {
            length++;
            j /= 10;
        }
        bytes memory bstr = new bytes(length);
        uint256 k = length - 1;
        while (_i != 0) {
            bstr[k--] = bytes1(uint8(48 + _i % 10));
            _i /= 10;
        }
        str = string(bstr);
    }

}
