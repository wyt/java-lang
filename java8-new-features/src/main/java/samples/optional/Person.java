package samples.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author wangyongtao
 * @date 2020/7/10
 */
public class Person {

  private String name;

  private int age;

  private String profession;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getProfession() {
    return profession;
  }

  public void setProfession(String profession) {
    this.profession = profession;
  }

  public static void main(String[] args) {
    Person person01 = new Person();
    person01.setName("wangyt");
    person01.setAge(19);
    person01.setProfession("Coder");

    List<Person> personList = new ArrayList<>();
    personList.add(person01);

    User user = new User();
    User user01 = setFields(null, user);

    System.out.println("username=" + user01.getUsername() + ", job=" + user01.getJob());
  }

  private static User setFields(Person person, User user) {
    user.setUsername(Optional.ofNullable(person).map(Person::getName).orElse(""));
    user.setJob(Optional.ofNullable(person).map(Person::getProfession).orElse(""));
    return user;
  }
}
