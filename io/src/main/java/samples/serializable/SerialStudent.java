package samples.serializable;

import java.io.*;

/**
 * @author wangyongtao
 * @date 2012-8-29 22:49:49
 */
@SuppressWarnings("all")
public class SerialStudent {

  /** 序列化Student实例 */
  public void test01() {
    Student student = new Student();
    student.setAge(10);
    student.setName("Jim");

    try {
      // 序列化student对象到student.obj文件
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("GenFiles/student.obj"));
      oos.writeObject(student);
      oos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** 反序列化student实例 */
  public void test02() {
    ObjectInputStream ois;
    try {
      // 反序列化
      ois = new ObjectInputStream(new FileInputStream("GenFiles/student.obj"));
      Student stuBak = (Student) ois.readObject();
      // print
      System.out.println("name: " + stuBak.getName() + ",age: " + stuBak.getAge());
      ois.close();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
