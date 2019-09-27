package samples.listen2;

/**
 * @author WANG YONG TAO
 * @date 2019/09/27
 */
@FunctionalInterface
public interface FinishedListener {

  void onFinished(String msg);
}
