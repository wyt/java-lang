package samples.pipe;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 管道流用于线程之间的通信
 *
 * @author wangyongtao
 */
public class TestPiped {

  public static void main(String[] args) {

    Sender sender = new Sender();
    Recive recive = new Recive();
    PipedInputStream pi = recive.getPipedInputputStream();
    PipedOutputStream po = sender.getPipedOutputStream();
    try {
      pi.connect(po);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    sender.start();
    recive.start();
  }
}

class Sender extends Thread {
  PipedOutputStream out = null;

  public PipedOutputStream getPipedOutputStream() {
    out = new PipedOutputStream();
    return out;
  }

  @Override
  public void run() {
    try {
      out.write("Hello , Reciver!".getBytes());
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    try {
      out.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}

class Recive extends Thread {
  PipedInputStream in = null;

  public PipedInputStream getPipedInputputStream() {
    in = new PipedInputStream();
    return in;
  }

  @Override
  public void run() {
    byte[] bys = new byte[1024];
    try {
      in.read(bys);
      System.out.println("读取到的信息：" + new String(bys).trim());
      in.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
