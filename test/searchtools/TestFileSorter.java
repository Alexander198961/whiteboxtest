/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchtools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import static org.junit.Assert.*;
import org.junit.Rule;

/**
 *
 * @author akovtunenko
 */
public class TestFileSorter {
    private FileSorter fs;
    private File file1,file2,file3,file4;
    private List<File> fileList;
    @Rule
    public TemporaryFolder tempFolder=new  TemporaryFolder();
   
   /***
    *  create file1 with absolute PATH equivalent /tempFolder/file1  
    *  create file2 with absolute PATH equivalent  
    */
    @Before
    public void setUp() throws Throwable  {
   
       fs=new FileSorter();
       file1=tempFolder.newFile("file1");
       File folder=tempFolder.newFolder();
       new File(folder.getAbsolutePath().concat(File.separator).concat("mydir")).mkdirs();
           
       file3=new File(folder.getAbsolutePath().concat(File.separator).concat("mydir").concat(File.separator).concat("file3"));
       file2=new File(folder.getAbsolutePath().concat(File.separator).concat("file2"));
       new File(file3.getParent().concat(File.separator).concat("testfolder")).mkdirs();
       file4=new File(file3.getParent().concat(File.separator).concat("testfolder").concat(File.separator).concat("file4"));
       
       fileList= new ArrayList<File>();
       fileList.add(file4);
       fileList.add(file2);
       fileList.add(file3);
       fileList.add(file1);
       fileList.add(file1);
       fileList.add(file3);
       
       
    }
   
    /***
    *  test for sort the files in ascending order by count directories which was included in temporary folder  
    *  
    */
    @Test
    public void testSortTheFilesInAscendingOrderByDirectoriesCount()
    {
        String[] expectedFileNames={"file1","file1","file2","file3","file3","file4"};
       
        
        
        List<File> files=fs.sort(fileList);
        List<String> fileNames = new ArrayList<String>();
        for(File file:files)
        {
            fileNames.add(file.getName());
         
          
        }
        String[] actualFileNames= fileNames.toArray(new String[fileNames.size()]);
        assertArrayEquals("Check for equals expected and actual array",expectedFileNames,actualFileNames);
        
    }
    
   
    
}
