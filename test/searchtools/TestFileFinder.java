/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchtools;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author akovtunenko
 */
public class TestFileFinder {
    
    final FileFinder ff;
    private File file1,file2,file3,file4;
    @Rule
    public TemporaryFolder tempFolder=new  TemporaryFolder();
    public TestFileFinder() {
        ff=new FileFinder();
    }
    /***
     *  Creates directory
     * @param path
     * @param subDirectoryName
     * @return 
     */
    private String createSubDirectory(String path, String subDirectoryName)
    {
       path=path+File.separator+subDirectoryName;
       tempFolder.newFolder(path);
       return path; 
    }
    
    /***
     * Sets up file size in bytes
     * @param file the file Object
     * @param size the  file size in bytes
     * @throws Throwable 
     */
    private void setUpFileSize(File file, long size) throws Throwable
    {
        RandomAccessFile f = new RandomAccessFile(file, "rw");
        f.setLength(size);
    }
    @Before
    public void setUp() throws Throwable {

        
       
       String path="subfolder"; 
       tempFolder.newFolder("alex");
       tempFolder.newFolder(path);
        
       file1=tempFolder.newFile(path.concat(File.separator).concat("file2"));
      
       setUpFileSize(file1,1024 * 1024);
       path=createSubDirectory(path,"mydir");
       
       file2=tempFolder.newFile(path.concat(File.separator).concat("file32f"));
       setUpFileSize(file2,512);
       
       file3=tempFolder.newFile(path.concat(File.separator).concat("alexfil32etesters"));
       setUpFileSize(file3,256);
       
       path=createSubDirectory(path,"testfolder");
       File folder=tempFolder.newFolder(path);
       file4=tempFolder.newFile(path.concat(File.separator).concat("file4"));
       setUpFileSize(file4,1024);
       createSubDirectory(path,"own");
       
    }
    /***
    *  find all files and directories in tempFolder and compare obtained array with
    *  expected array 
    */
    @Test
    public void testFindAllFilesAndDirectories() throws Throwable
    {
        List<String> fileNames=new ArrayList<String>();
        String[] expectedAllNames = {  "alex" , "subfolder","file2", "mydir" , "file32f" , "testfolder" , "file4" , "own", "alexfil32etesters" };

        List<File> files=ff.findAll(tempFolder.getRoot().getAbsolutePath());
        
        for(File myFile:files)
        {
            
            fileNames.add(myFile.getName());
                    
        }
        String[] actualAllNames= fileNames.toArray(new String[fileNames.size()]);
        assertArrayEquals("Compare expected files and directies array with obtained files and directories array ",expectedAllNames,actualAllNames);
    }
    
    /***
    *  find all files and directories in tempFolder  which name is matched regexp and compare obtained array with
    *  expected array 
    */
    @Test
    public void testFindAllFilesAndDirectoriesWhichNameIsMatchedRegexp() throws Throwable
    {
        List<String> fileNames=new ArrayList<String>();
        String[] expectedAllNames = {"alex","alexfil32etesters" };

        List<File> files=ff.findAll(tempFolder.getRoot().getAbsolutePath(),"^al.*");
        
        for(File myFile:files)
        {
            
            fileNames.add(myFile.getName());
          
        }
        String[] actualAllNames= fileNames.toArray(new String[fileNames.size()]);
        assertArrayEquals("Compare expected files and directies array with obtained files and directories array ",expectedAllNames,actualAllNames);
    }
    /***
    *  find all files in tempFolder  which file names is matched regexp  and compare obtained files with
    *  expectedFiles array 
    */
    @Test
    public void testFindFilesWichNameIsMatchedRegexp() throws Throwable
    {
        List<String> fileNames=new ArrayList<String>();
        String[] expectedFileNames = {"file2","file32f","file4" };

        List<File> files=ff.findFiles(tempFolder.getRoot().getAbsolutePath(),"^file.*");
        
        for(File myFile:files)
        {
            
            fileNames.add(myFile.getName());
          
        }
        String[] actualFileNames= fileNames.toArray(new String[fileNames.size()]);
        assertArrayEquals("Compare expected files array with obtained files array",expectedFileNames,actualFileNames);
    }
    /***
    *  find all files in tempFolder  and compare obtained files with
    *  expectedFiles array 
    */
    @Test
    public void testFindFiles() throws Throwable
    {
        List<String> fileNames=new ArrayList<String>();
        String[] expectedFiles = {"file2","file32f","file4", "alexfil32etesters"  };

        List<File> files=ff.findFiles(tempFolder.getRoot().getAbsolutePath());
        
        for(File myFile:files)
        {
            
            fileNames.add(myFile.getName());
          
        }
        String[] actualFiles= fileNames.toArray(new String[fileNames.size()]);
        assertArrayEquals("Compare expected files array with obtained files array",expectedFiles,actualFiles);
    } 
    /***
    *  find all directories in tempFolder  which directory name is matched regexp ".*ld.*" and compare obtained directories with
    *  expectedDirectories array 
    */
    @Test
    public void testFindAllDirectoiesWichNameIsMatchedRegexp() throws Throwable
    {
        List<String> fileNames=new ArrayList<String>();
        String[] expectedDirectories = {"subfolder","testfolder"  };

        List<File> files=ff.findDirectories(tempFolder.getRoot().getAbsolutePath(), ".*ld.*");
        
        for(File myFile:files)
        {
            
            fileNames.add(myFile.getName());
            
        }
        String[] actualDirectories= fileNames.toArray(new String[fileNames.size()]);
        assertArrayEquals("Compare expected directories with obtained directories",expectedDirectories,actualDirectories);
    }
    
    /***
    *  find all directories in tempFolder  and compare obtained directories with
    *  expectedDirectories array 
    */
    @Test
    public void testFindAllDirectories() throws Throwable
    {
        List<String> fileNames=new ArrayList<String>();
        String[] expectedDirectories = { "alex", "subfolder","mydir","testfolder" , "own" };

        List<File> files=ff.findDirectories(tempFolder.getRoot().getAbsolutePath());
        for(File myFile:files)
        {
           
            fileNames.add(myFile.getName());
          
        }
        String[] actualDirectories =fileNames.toArray(new String[fileNames.size()]);
        assertArrayEquals("Compare expected directories with obtained directories",expectedDirectories,actualDirectories);
    }
    /***
    *   count all directories in tempFolder  
    *  
    */
    @Test
    public void testCountAllDirectories() throws Throwable
    {
       
        long expectedCountDirectories=5;
        ff.findDirectories(tempFolder.getRoot().getAbsolutePath());
        long actualCountDirectories=ff.getDirectoriesNumber();
        assertEquals(expectedCountDirectories,actualCountDirectories);
        
    }
    /***
    *   count all files in tempFolder  
    *  
    */
    @Test
    public void testCountAllFiles() throws Throwable
    {
       
        long expectedCountFiles=4;
        ff.findFiles(tempFolder.getRoot().getAbsolutePath());
        long actualCountFiles=ff.getFilesNumber();
        assertEquals(expectedCountFiles,actualCountFiles);
        
    }
   /***
    *  count all files size in tempFolder in my case file
    */
    @Test
    public void testCountFilesSize() throws Throwable
    {
       
        long expectedFilesSize=file1.length()+file2.length()+file3.length()+file4.length();
        ff.findFiles(tempFolder.getRoot().getAbsolutePath());
        long actualFilesSize=ff.getDirectorySize();
        assertEquals(expectedFilesSize,actualFilesSize);
        
    }

}
