/*
 * SearchListener.java
 *
 * Created on 16 Октябрь 2006 г., 20:15
 *
 */

package searchtools;

/**
 * Этот интерфейс должны реализовать все классы, которые хотят
 * следить за состоянием процесса поиска, который выполняется
 * классом <CODE>FileFinder</CODE>
 * @author Стаценко Владимир
 * http://www.vova-prog.narod.ru
 */
public interface SearchListener {
    /** Этот метод вызывается перед началом поиска
     */
    public void onSearchStart();
    
    /**
     * Этот метод вызывается в процессе поиска. В его
     * параметрах передается информация о количестве
     * найденных объектов, их размере и т.п.
     *
     * @param totalLength общий размер найденных файлов
     * @param filesNumber общее количество найденных файлов
     * @param directoriesNumber общее количество просмотренных директорий
     */
    public void onSearchProgressChange(long totalLength,
            long filesNumber, long directoriesNumber);
    
    /** Этот метод вызывается в конце поиска
     */
    public void onSearchEnd();
}
