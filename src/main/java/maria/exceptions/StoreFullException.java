package maria.exceptions;

/*
Данный класс также описывает проверяемое исключение, так как проявление такой ошибке не зависит от программиста;
т.е. в коде может быть все верно, а ошибка произойдет если пользователь попробует добавить продуктов больше
чем размер магазина.
 */

public class StoreFullException extends Exception {

    public StoreFullException(int size){
        super("Store is full; cant add more than " + size + " maria.learnup.products!");
    }
}
