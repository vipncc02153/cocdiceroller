import java.io.*;
import java.net.Socket;
public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 9527);
            System.out.println("客户端开始连接");
            //一直读取控制台
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                //包体
                byte[] content = br.readLine().getBytes();
                //包头,固定4个字节,包含包体长度信息
                byte[] head = intToByteArray1(content.length);
                BufferedOutputStream bis = new BufferedOutputStream(socket.getOutputStream());
                bis.write(head);
                bis.flush();
                bis.write(content);
                bis.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static byte[] intToByteArray1(int i) {
        byte[] result = new byte[4];
        result[0] = (byte) ((i >> 24) & 0xFF);
        result[1] = (byte) ((i >> 16) & 0xFF);
        result[2] = (byte) ((i >> 8) & 0xFF);
        result[3] = (byte) (i & 0xFF);
        return result;
    }
    public static byte[] intToByteArray2(int i) throws Exception {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(buf);
        out.writeInt(i);
        byte[] b = buf.toByteArray();
        out.close();
        buf.close();
        return b;
    }
    //字节数组转int
    public static int byteArrayToInt(byte[] b) {
        int intValue = 0;
        for (int i = 0; i < b.length; i++) {
            intValue += (b[i] & 0xFF) << (8 * (3 - i));
        }
        return intValue;
    }
}