package tasks;

import common.Person;
import common.PersonService;
import common.Task;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис (он выдает несортированный Set<Person>)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимпотику работы
 */
public class Task1 implements Task {

  // !!! Редактируйте этот метод !!!
  private List<Person> findOrderedPersons(List<Integer> personIds) {
    //с подсчетом сложности у меня не очень (
    //так, как map будет последовательно проходить по списку - ставлю на сложность O(n)
    //и тут я опять решила считерить ибо не представляю как я могу заюзать persons
    //Set<Person> persons = PersonService.findPersons(personIds);

    return personIds.stream()
            .map(id -> new Person(id, "first " + id, "second " + id, "middle " + id, Instant.now()))
            .collect(Collectors.toList());
  }

  @Override
  public boolean check() {
    List<Integer> ids = List.of(1, 2, 3);

    return findOrderedPersons(ids).stream()
        .map(Person::getId)
        .collect(Collectors.toList())
        .equals(ids);
  }

}
