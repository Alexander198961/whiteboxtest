/*
 * FileSorter.java
 *
 * Created on 21 �������� 2006 �., 9:02
 */

package searchtools;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;

/**
 * ���� ����� ������������ ��� ���������� ������ ������
 *
 * @author �������� ��������
 * http://www.vova-prog.narod.ru
 */
public class FileSorter implements Comparator {
    
    //����� ��� ������ � ����������� �����������
    Pattern p = null;
    //����� ��� ������ �� �������� �� ������ ������
    Collator collator = null;
    
    /** ������� ����� ���������� FileSorter */
    public FileSorter() {
        //���������� ��������� ������ ����������� � �������
        //������ �� ��� ������
        String separator = File.separator;
        if(separator.equals("\\")) {
            separator = "\\\\";
        }
        //������� ������ �� ������ �������-�����������
        p = Pattern.compile(separator,
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        //�������� ��������� ��������� (���� � ������)
        String country = System.getProperty("user.country");
        String language = System.getProperty("user.language");
        //������� ��������� ������ ��� ��������� ����� ��
        //������ ������������ ��������
        collator = Collator.getInstance(new Locale(country, language));
    }

    /**
     * ���� ����� ��������� ��������� ���� ���� ������.
     * ����������:
     *     1 ���� ������ �������� (�1) ������ ������� (�2),
     *    -1 ���� ������ �������� (�1) ������ ������� (�2),
     *     0 ���� ��� �����.
     * ��� ������� ����� ��������� ������ ������� �����, ����
     * ������ ���� ��������� ����� � ����� ������ �����.
     * ���� ����� ��������� � ����� �����, �� ������ �� ���,
     * ������� ���� ������ �� ��������.
     * @param o1 ������ ���� File
     * @param o2 ������ ���� File
     * @return ��������� ���������
     */
    public int compare(Object o1, Object o2) {
        //���� ������� �� ����� null � ����� ��� File
        if(o1 != null && o2 != null &&
                o1 instanceof File && o2 instanceof File) {
            //�������� � ���� File
            File f1 = (File)o1;
            File f2 = (File)o2;
            //�������� ������ ���� � ����� �����
            String fullPath1 = f1.getAbsolutePath();
            String fullPath2 = f2.getAbsolutePath();
            //��������� ��������� ����
            if(fullPath1.equals(fullPath2)) {
                //���������� 0, �.�. ����� ���������
                return 0;
            }
            //���������� ������� ���������� ����� � ������ �����
            //��� ����� ��������� ������ ���� � ����� ��
            //�������, � ���������� �� ����������
            String[] res1 = p.split(fullPath1);
            String[] res2 = p.split(fullPath2);
            if(res1.length > res2.length) {
                //���������� 1, ���� ������� �������� �������
                //����� ������ ������� �������� �������
                return 1;
            }
            if(res1.length < res2.length) {
                //���������� "-1" � ��������� ������
                return -1;
            }
            if(res1.length == res2.length) {
                //���� ����� ��������� �� ���������� �������,
                //��������� �� � ������������ � ���������
                return collator.compare(fullPath1, fullPath2);
            }
        }
        //����� �� ���������� 0, �.�. ��������� ��������
        //��������� ���������� (�.�. �������, ��� �������
        //����������, �� ������ ������, ����������� ��
        //��� ������)
        return 0;
    }

    /**
     * ���� ����� ��������� ���������� ������ ������
     * @param fileList ����������������� ������ ������
     * @return ��������������� ������ ������
     */
    public List sort(List fileList) {
        //������� ������ ��� ����������� (������ �� �������
        //��� � �������� ������)
        ArrayList res = new ArrayList(fileList.size());
        //�������� ������
        res.addAll(fileList);
        //��������� ����������
        Collections.sort(res, this);
        //���������� ���������
        return res;
    }
}
