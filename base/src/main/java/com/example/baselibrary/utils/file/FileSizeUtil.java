package com.example.baselibrary.utils.file;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;

/**
 * @CreateBy HaiyuKing
 * @Used android 获取文件夹或文件的大小 以B、KB、MB、GB 为单位
 * @参考资料 http://blog.csdn.net/jiaruihua_blog/article/details/13622939
 */
public class FileSizeUtil {

    public static final int SIZETYPE_B = 1;//获取文件大小单位为B的double值
    public static final int SIZETYPE_KB = 2;//获取文件大小单位为KB的double值
    public static final int SIZETYPE_MB = 3;//获取文件大小单位为MB的double值
    public static final int SIZETYPE_GB = 4;//获取文件大小单位为GB的double值
    /**
     * 获取指定文件或指定文件夹的的指定单位的大小
     * @param filePath 文件路径
     * @param sizeType 获取大小的类型1为B、2为KB、3为MB、4为GB
     * @return double值的大小
     */
    public static double getFolderOrFileSize(String filePath,int sizeType){
        File file=new File(filePath);
        long blockSize=0;
        try {
            if(file.isDirectory()){
                blockSize = getFolderSize(file);
            }else{
                blockSize = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("获取文件大小","获取失败!");
        }
        return FormetFileSize(blockSize, sizeType);
    }
    /**
     * 调用此方法自动计算指定文件或指定文件夹的大小
     * @param filePath 文件路径
     * @return 计算好的带B、KB、MB、GB的字符串
     */
    public static String getAutoFolderOrFileSize(String filePath){
        File file=new File(filePath);
        long blockSize=0;
        try {
            if(file.isDirectory()){
                blockSize = getFolderSize(file);
            }else{
                blockSize = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("获取文件大小","获取失败!");
        }
        return FormetFileSize(blockSize);
    }
    /**
     * 获取指定文件的大小
     * @param file
     * @return
     * @throws Exception
     */
    private static long getFileSize(File file) throws Exception
    {
        long size = 0;
        if (file.exists()){
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
            fis.close();
        }
        else{
            file.createNewFile();
            Log.e("获取文件大小","文件不存在!");
        }

        return size;
    }

    /**
     * 获取指定文件夹的大小
     * @param file
     * @return
     * @throws Exception
     */
    private static long getFolderSize(File file) throws Exception
    {
        long size = 0;
        File flist[] = file.listFiles();
        for (int i = 0; i < flist.length; i++){
            if (flist[i].isDirectory()){
                size = size + getFolderSize(flist[i]);
            }
            else{
                size =size + getFileSize(flist[i]);
            }
        }
        return size;
    }
    /**
     * 转换文件大小
     * @param fileSize
     * @return
     */
    private static String FormetFileSize(long fileSize)
    {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize="0B";
        if(fileSize==0){
            return wrongSize;
        }
        if (fileSize < 1024){
            fileSizeString = df.format((double) fileSize) + "B";
        }
        else if (fileSize < 1048576){
            fileSizeString = df.format((double) fileSize / 1024) + "KB";
        }
        else if (fileSize < 1073741824){
            fileSizeString = df.format((double) fileSize / 1048576) + "MB";
        }
        else{
            fileSizeString = df.format((double) fileSize / 1073741824) + "GB";
        }
        return fileSizeString;
    }
    /**
     * 转换文件大小,指定转换的类型
     * @param fileSize
     * @param sizeType
     * @return
     */
    private static double FormetFileSize(long fileSize,int sizeType)
    {
        DecimalFormat df = new DecimalFormat("#.00");
        double fileSizeLong = 0;
        switch (sizeType) {
            case SIZETYPE_B:
                fileSizeLong=Double.valueOf(df.format((double) fileSize));
                break;
            case SIZETYPE_KB:
                fileSizeLong=Double.valueOf(df.format((double) fileSize / 1024));
                break;
            case SIZETYPE_MB:
                fileSizeLong=Double.valueOf(df.format((double) fileSize / 1048576));
                break;
            case SIZETYPE_GB:
                fileSizeLong=Double.valueOf(df.format((double) fileSize / 1073741824));
                break;
            default:
                break;
        }
        return fileSizeLong;
    }
}
