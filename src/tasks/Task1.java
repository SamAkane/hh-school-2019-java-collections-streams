package tasks;

import common.Person;
import common.PersonService;
import common.Task;

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
    //ассимптотика работы n^2:
    //в каждой итерации внешнего цикла может быть n итераций внутреннего цикла

    Set<Person> persons = PersonService.findPersons(personIds);
    List<Person> sorted = new ArrayList<>();

    for(int i = 0; i < personIds. size(); i++) {
      Iterator iterator = persons.iterator();

      while(iterator.hasNext()) {
        Person person = (Person) iterator.next();

        if(personIds.get(i).equals(person.getId())) {
          sorted.add(person);
          break;
        }
      }
    }
    return sorted;
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
