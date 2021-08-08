package Exceptions;

/*
Данный класс описывает проверяемое исключение, так как проявление такой ошибке не зависит от программиста;
т.е. в коде может быть все верно, а ошибка произойдет если пользователь введет не правельную строку и
попробует удалить продукты которых нет.
 */

public class NoSuchProductException extends Exception {

    public NoSuchProductException(String sample) {
        super("No products deleted; zero products like: " + sample);
    }

}
