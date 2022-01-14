package com.graduation.mesume.controller;

import com.graduation.mesume.entity.SysCategory;
import com.graduation.mesume.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 图片操作控制层
 */
@Controller
@RequestMapping("/image")
@CrossOrigin
@ResponseBody
public class ImageController {

    /**
     * 显示图片
     * @param path
     * @param name
     * @param response
     */
    @GetMapping("/showImg")
    public void getById(String path,String name, HttpServletResponse response) {
        File imageFile = new File(Constants.IMG_BASE_PATH + "/" + path + "/" + name);
        if(StringUtils.isEmpty(path) || StringUtils.isEmpty(name)){
            return;
        }
        if (imageFile.exists()) {
            FileInputStream fis = null;
            OutputStream os = null;
            try {
                fis = new FileInputStream(imageFile);
                os = response.getOutputStream();
                int count = 0;
                byte[] buffer = new byte[1024 * 8];
                while ((count = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, count);
                    os.flush();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
