package samples.conc.app;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 缓存中锁降级的示例,提高并发性<code>ReentrantReadWriteLock</code>官方示例
 *
 * @author WANG YONG TAO
 * @date 2019/09/24
 */
class CachedData {

  Object data;
  volatile boolean cacheValid;
  final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

  void processCachedData() {
    rwl.readLock().lock();
    if (!cacheValid) {
      // Must release read lock before acquiring write lock
      // 申请写锁之前必须释放读锁
      rwl.readLock().unlock();
      rwl.writeLock().lock();
      try {
        // Recheck state because another thread might have
        // acquired write lock and changed state before we did.
        // 重新检查状态,因为在此之前另外的线程可能申请了写锁并且更改了状态
        if (!cacheValid) {
          data = "获取数据";
          cacheValid = true;
        }
        // Downgrade by acquiring read lock before releasing write lock
        // 在释放写锁之前通过申请读锁降级
        // 同一个线程之前获取了写锁，后面又申请了读锁，接着又释放了写锁，即持有写锁，到持有读锁，即发生了锁降级
        rwl.readLock().lock();
      } finally {
        // Unlock write, still hold read
        // 释放写锁,仍然持有读锁
        rwl.writeLock().unlock();
      }
    }
    try {
      // 由于上面已经锁降级,此时不会阻塞拥有读锁的其他线程,提高并发性
      use(data);
    } finally {
      rwl.readLock().unlock();
    }
  }

  void use(Object data) {
    //
  }
}
