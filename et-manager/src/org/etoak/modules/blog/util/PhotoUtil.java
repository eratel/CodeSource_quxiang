package org.etoak.modules.blog.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * <p>
 * Title: SavePhotoUtil
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author 孙继凯
 * @date 2017年4月28日上午10:54:22
 * @version 1.0
 */
public class PhotoUtil
{
    
    private static Logger logger = Logger.getLogger(PhotoUtil.class.getSimpleName());
    
    public static String savePhoto(MultipartFile fileCover, HttpServletRequest request)
        throws IOException
    {
        long size = fileCover.getSize();
        if (size <= 0)
        {
            return "暂无封面";
        }
        String fileName = fileCover.getOriginalFilename();
        String contentType = fileCover.getContentType();
        InputStream iStream = fileCover.getInputStream();
        String path = request.getServletContext().getRealPath("/pages/img");
        /*
         * UUID uuid = UUID.randomUUID();
         * 
         * @SuppressWarnings("static-access") String newName = uuid.randomUUID().toString();
         */
        String finalName = URLEncoder.encode(fileName, "utf-8");
        finalName = finalName.substring(fileName.lastIndexOf("."));
        String newName = IDUtils.genImageName();
        // newName = newName.replace("-", "");
        newName = newName + finalName;
        File newfile = new File(path, newName);
        OutputStream outputStream = new FileOutputStream(newfile);
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = iStream.read(buffer)) != -1)
        {
            outputStream.write(buffer, 0, len);
        }
        outputStream.flush();
        outputStream.close();
        iStream.close();
        
        return newName;
    }
    
    public static String deletePhotoByID(String phoneName,HttpServletRequest request)
    {
        String path = request.getServletContext().getRealPath("/pages/img");
        File file = new File(path+"/"+phoneName);
        if(file.exists())
        {
            logger.debug("file exits Y");
            boolean b = file.delete();
            if(b)
            {
                
                return Constant.SUCCESS;
            }
        }
        return Constant.ERROR;
    }
}
































