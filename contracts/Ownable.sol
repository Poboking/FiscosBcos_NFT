// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.4.25;

/*
 * @dev: a20-nft-3_7 by poboking in 2024/4/2 11:17
 */
contract Ownable {
    address private _owner;


    event OwnershipTransferred(address indexed previousOwner, address indexed newOwner);

    /*
     * @dev Initializes the contract setting the address provided by the deployer as the initial owner.
     */
    constructor(address initialOwner) internal {
        require(initialOwner != address(0), "Only owner can call this function");
        _transferOwnership(initialOwner);
    }

    /**
     * @dev Throws if called by any account other than the owner.
     */
    modifier onlyOwner() {
        _checkOwner();
        _;
    }

    // function _msgSender() internal view virtual returns (address) {
    //     return msg.sender;
    // }

    // function _msgData() internal view virtual returns (bytes calldata) {
    //     return msg.data;
    // }

    /*
     * @dev Returns the address of the current owner.
     */
    // function owner() public view virtual returns (address) {
    //     return _owner;
    // }

    /*
     * @dev Throws if the sender is not the owner.
     */
    function _checkOwner() internal view  {
        require(_owner == msg.sender, "OwnableUnauthorizedAccount");
    }

    /*
     * @dev Leaves the contract without owner. It will not be possible to call
     * `onlyOwner` functions. Can only be called by the current owner.
     *
     * NOTE: Renouncing ownership will leave the contract without an owner,
     * thereby disabling any functionality that is only available to the owner.
     */
    function renounceOwnership() public  onlyOwner {
        _transferOwnership(address(0));
    }

    /*
     * @dev Transfers ownership of the contract to a new account (`newOwner`).
     * Can only be called by the current owner.
     */
    function transferOwnership(address newOwner) public  onlyOwner {
        require(newOwner != address(0), "OwnableInvalidOwner");
        _transferOwnership(newOwner);
    }

    /*
     * @dev Transfers ownership of the contract to a new account (`newOwner`).
     * Internal function without access restriction.
     */
    function _transferOwnership(address newOwner) internal  {
        address oldOwner = _owner;
        _owner = newOwner;
        emit OwnershipTransferred(oldOwner, newOwner);
    }
}
