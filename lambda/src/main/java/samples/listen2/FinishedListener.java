package samples.listen2;

/**
 * Automator执行完脚本返回消息的监听器.
 *
 * <p>1. 声明监听器，声明接口定义；
 *
 * <p>2. 注册监听器，定义参数为监听器的方法；
 *
 * <p>3. 实现监听器，实现监听器接口(lambda)；
 *
 * <p>4. 触发监听器，监听器实例调用onScriptFinished方法；
 *
 * @author wangyongtao
 * @date 2018/5/9
 */
@FunctionalInterface
public interface FinishedListener {

  void onFinished(String msg);
}
