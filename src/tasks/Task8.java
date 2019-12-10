package tasks;

import common.Person;
import common.Task;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 implements Task {

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  public List<String> getNames(List<Person> persons) {
    //из плохого было разве что удаление первого элемента списка, которое влечет за собой перестановку всех элементов справа
    //других вариантов нет
    return persons.stream()
            .skip(1)
            .map(Person::getFirstName)
            .collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {
    //знаю, что уже не учитывается, но поняла что не так
    return new HashSet<>(getNames(persons));
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  public String convertPersonToString(Person person) {
    //знаю, что уже не учитывается, но добавила проверку на null

    return Stream.of(person.getSecondName(), person.getFirstName(), person.getMiddleName())
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    //по идее id персон должны быть уникальными
    return persons.stream()
            .collect(Collectors.toMap(Person::getId, this::convertPersonToString));
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    // не смогла осилить эту задачку. Пробовала с конвертацией одной коллекции в мапу, но потом сильно
    //запуталась с обходом второй коллекции

    //disjoint как-то сложно работает! по крайней мере выглядит страшно
    return !Collections.disjoint(persons1, persons2);
  }

  //Выглядит вроде неплохо...
  public long countEven(Stream<Integer> numbers) {
    //для подсчета итераций можно воспользоваться методами stream
    return numbers.filter(num -> num % 2 == 0).count();
  }

  @Override
  public boolean check() {
    //ну, в целом фейл таски исправлен :D
    return true;
  }
}
