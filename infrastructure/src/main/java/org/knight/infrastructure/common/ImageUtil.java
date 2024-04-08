package org.knight.infrastructure.common;

import cn.hutool.core.text.CharSequenceUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/8 15:15
 */
public class ImageUtil {

    public static String convertImageToBase64Str(String imageFileName) {
        ByteArrayOutputStream baos = null;
        try {
            //获取图片类型
            String suffix = imageFileName.substring(imageFileName.lastIndexOf(".") + 1);
            //构建文件
            File imageFile = new File(imageFileName);
            //通过ImageIO把文件读取成BufferedImage对象
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            //构建字节数组输出流
            baos = new ByteArrayOutputStream();
            //写入流
            ImageIO.write(bufferedImage, suffix, baos);
            //通过字节数组流获取字节数组
            byte[] bytes = baos.toByteArray();
            //更改访问前缀
            if ("jpg".equals(suffix)) {
                suffix = "jpeg";
            } else if ("png".equals(suffix)) {
                suffix = "png";
            } else if ("jpeg".equals(suffix)) {
                suffix = "jpeg";
            } else if ("gif".equals(suffix)) {
                suffix = "gif";
            } else if ("webp".equals(suffix)) {
                suffix = "webp";
            } else if ("bmp".equals(suffix)) {
                suffix = "bmp";
            } else if ("ico".equals(suffix)) {
                suffix = "x-icon";
            } else if ("svg".equals(suffix)) {
                suffix = "svg+xml";
            } else {
                suffix = "jpeg";

            }
            //获取JDK8里的编码器Base64.Encoder转为base64字符
            return CharSequenceUtil.format("data:image/{};base64,",suffix) + Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String convertToBase64(String imagePath) throws IOException {
        // 读取图片文件到字节数组
        byte[] imageData = readImageFile(imagePath);

        // 对字节数组进行 Base64 编码
        String base64Image = Base64.getEncoder().encodeToString(imageData);

        return base64Image;
    }

    private static byte[] readImageFile(String imagePath) throws IOException {
        FileInputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {
            File imageFile = new File(imagePath);
            inputStream = new FileInputStream(imageFile);
            outputStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            return outputStream.toByteArray();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
