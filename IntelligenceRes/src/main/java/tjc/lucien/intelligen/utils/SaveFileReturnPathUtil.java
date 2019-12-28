package tjc.lucien.intelligen.utils;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

/**
 * Created by Administrator on 2017/4/1.
 */
public class SaveFileReturnPathUtil {

    public static Logger log = Logger.getLogger(SaveFileReturnPathUtil.class);

    public static String saveFile(MultipartFile srcFile, String rootPath) throws IOException {

        /* 步骤一：获取用户选择的头像图片和电子签名的图片 */
        String srcFileName = srcFile.getOriginalFilename();
        String tarFilePath = rootPath + Calendar.getInstance().get(Calendar.YEAR)
                + (Calendar.getInstance().get(Calendar.MONTH)+1);  //目标路径
		/* 通过UUID生成随机数，结合源文件的名称生成目标文件的文件名，线程安全*/
        String fileName = UUID.randomUUID() + srcFileName.substring(0, srcFileName.lastIndexOf("."));
        String fileType = srcFileName.substring(srcFileName.lastIndexOf("."));

		/* 步骤二：创建目标路径 */
        File file = new File(tarFilePath);
        file.mkdirs();
        File tarFile = File.createTempFile(fileName, fileType, file);
        MulFileCopyToUtil.copyTo(srcFile, tarFile);

        String picWay = tarFile.getAbsolutePath();
        log.info("图片保存地址：" + picWay);
        String[] pathStrs = picWay.split("\\\\");
        String returnPath = pathStrs[3] + "/" + pathStrs[4];
        log.info("返回的图片路径：" + returnPath);

        return returnPath;
    }
}
