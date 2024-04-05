// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.4.25;


contract Utils {

    uint256 private nonce = 0;

    function generateID() public returns (uint256) {
        uint256 id = uint256(keccak256(abi.encodePacked(block.timestamp, nonce)));
        nonce++;
        return id;
    }

    function test() public returns (uint256) {
        return generateID();
    }
}
