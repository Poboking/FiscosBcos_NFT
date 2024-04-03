// SPDX-License-Identifier: MIT

pragma solidity ^0.4.25;

contract BadBeaconNoImpl {}

contract BadBeaconNotContract {
    function implementation() external pure returns (address) {
        return address(0x1);
    }
}
