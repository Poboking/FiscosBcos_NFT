package org.knight.infrastructure.fisco.config;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/4 17:13
 */
public class ContractAddressContext {
    private static String ownableAddress;

    private static String utilsAddress;

    private static String bcosUserAddress;

    private static String bcosNFTAddress;

    public static String getOwnableAddress() {
        return ownableAddress;
    }

    public static void setOwnableAddress(String ownableAddress) {
        ContractAddressContext.ownableAddress = ownableAddress;
    }

    public static String getUtilsAddress() {
        return utilsAddress;
    }

    public static void setUtilsAddress(String utilsAddress) {
        ContractAddressContext.utilsAddress = utilsAddress;
    }

    public static String getBcosUserAddress() {
        return bcosUserAddress;
    }

    public static void setBcosUserAddress(String bcosUserAddress) {
        ContractAddressContext.bcosUserAddress = bcosUserAddress;
    }

    public static String getBcosNFTAddress() {
        return bcosNFTAddress;
    }

    public static void setBcosNFTAddress(String bcosNFTAddress) {
        ContractAddressContext.bcosNFTAddress = bcosNFTAddress;
    }
}
