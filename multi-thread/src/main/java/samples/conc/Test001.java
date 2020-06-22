package samples.conc;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wangyongtao
 * @date 2020/5/12
 */
public class Test001 {

  private static final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

  public static void main(String[] args) {
    System.out.println(rwl.readLock());
    System.out.println(rwl.readLock());
    System.out.println(rwl.readLock());
    System.out.println(rwl.readLock());
    System.out.println(rwl.readLock());
    System.out.println(rwl.readLock());
    System.out.println(rwl.writeLock());
    System.out.println(rwl.writeLock());
    System.out.println(rwl.writeLock());
    System.out.println(rwl.writeLock());
    System.out.println(rwl.writeLock());
    System.out.println(rwl.writeLock());
  }
}
