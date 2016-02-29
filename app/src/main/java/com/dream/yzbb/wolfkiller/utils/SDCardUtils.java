package com.dream.yzbb.wolfkiller.utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * Created by liuzhaofeng on 2016/2/25.
 */
public class SDCardUtils {

    public static boolean isSDCardEnable()
    {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);

    }

    public static String getSDCardPath()
    {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator;
    }


    public static long getSDCardAllSize()
    {
        if (isSDCardEnable())
        {
            StatFs stat = new StatFs(getSDCardPath());
            // Get available data blocks
            long availableBlocks = (long) stat.getAvailableBlocks() - 4;
            // Get size of a single data block in bytes
            long freeBlocks = stat.getAvailableBlocks();
            return freeBlocks * availableBlocks;
        }
        return 0;
    }

    /**
     * Get available storage in bytes by filePath
     *
     * @param filePath
     * @return internal available storage in bytes
     */
    public static long getFreeBytes(String filePath)
    {
        // Get available capacity of sd card if it is a SD card path
        if (filePath.startsWith(getSDCardPath()))
        {
            filePath = getSDCardPath();
        } else
        {
            //Get available capacity of internal storage
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }

    /**
     * Get system storage path
     *
     * @return
     */
    public static String getRootDirectoryPath()
    {
        return Environment.getRootDirectory().getAbsolutePath();
    }
}
