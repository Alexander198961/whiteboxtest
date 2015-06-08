/*
 * SearchListener.java
 *
 * Created on 16 ������� 2006 �., 20:15
 *
 */

package searchtools;

/**
 * ���� ��������� ������ ����������� ��� ������, ������� �����
 * ������� �� ���������� �������� ������, ������� �����������
 * ������� <CODE>FileFinder</CODE>
 * @author �������� ��������
 * http://www.vova-prog.narod.ru
 */
public interface SearchListener {
    /** ���� ����� ���������� ����� ������� ������
     */
    public void onSearchStart();
    
    /**
     * ���� ����� ���������� � �������� ������. � ���
     * ���������� ���������� ���������� � ����������
     * ��������� ��������, �� ������� � �.�.
     *
     * @param totalLength ����� ������ ��������� ������
     * @param filesNumber ����� ���������� ��������� ������
     * @param directoriesNumber ����� ���������� ������������� ����������
     */
    public void onSearchProgressChange(long totalLength,
            long filesNumber, long directoriesNumber);
    
    /** ���� ����� ���������� � ����� ������
     */
    public void onSearchEnd();
}
