package tjc.lucien.intelligen.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class MulFileCopyToUtil {

    /**
     * @param srcFile
     * @param tarFile
     * @throws IOException
     * @Date 2016-9-8
     * @author Lucien
     * @Describe 将MultipartFile类型的文件上传到File类型的磁盘
     */
    public static void copyTo(MultipartFile srcFile, File tarFile) throws IOException {

		/* 步骤一：获取源文件的输入流和目标文件的输出流 */
        FileInputStream fis = (FileInputStream) srcFile.getInputStream();
        FileOutputStream fos = new FileOutputStream(tarFile);

		/* 步骤二：定义转存时需要的byte[1024]，每次传送1024和字节，即1K */
        byte[] buffer = new byte[1024];
        int len = 0;                   //记录每次转存数据的长度，当长度为0时，转存完成

		/* 步骤三：读取输入流，并且写入到目标文件流中 */
        while ((len = fis.read(buffer)) > 0) {
            fos.write(buffer);
        }
    }

    /**
     * 将文件流转化成字节流
     * @param srcFile
     * @return
     * @throws IOException
     */
    public static InputStream returnStream(MultipartFile srcFile) throws IOException {
		/* 步骤一：获取源文件的输入流和目标文件的输出流 */
        FileInputStream fis = (FileInputStream) srcFile.getInputStream();

        byte[] bys = IOUtils.toByteArray(fis);
        fis.close();
        InputStream stream = new ByteArrayInputStream(bys);

        /*BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        StringBuffer sb = new StringBuffer();
        String readLine = "";
        while ((readLine = bufferedReader.readLine()) != null) {
            sb.append(readLine);
        }
        return sb.toString();*/
        return stream;
    }
}
