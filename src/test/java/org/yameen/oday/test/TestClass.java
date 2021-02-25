package org.yameen.oday.test;

import org.junit.Assert;
import org.junit.Test;
import org.yameen.oday.OdayYameen;
import org.yameen.oday.Service;

import java.io.BufferedReader;
import java.io.FileReader;

public class TestClass {

    @Test(expected = RuntimeException.class)
    public void testNoArgument() throws Exception {
        String args[]={};
        OdayYameen.main(args);

    }
    @Test(expected = RuntimeException.class)
    public void testMoreThanOneArg() throws Exception {

        String args[]={"111111","222222"};
        OdayYameen.main(args);

    }

    @Test(expected = RuntimeException.class)
    public void testOneArgButNotValidLocation() throws Exception {

        String args[]={"fwsfcafgrweqgrfv"};
        OdayYameen.main(args);



    }

    @Test
    public void CorrectLocation() throws Exception {
        String args[]={"C:\\Users\\OdayY\\Desktop\\out3\\"};
        OdayYameen.main(args);

    }
    @Test
    public void TestCountingInFile() throws Exception {
        String args[]={"C:\\Users\\OdayY\\Desktop\\out3\\file-111.txt"};/////note with file
        OdayYameen.main(args);
        BufferedReader input=new BufferedReader(new FileReader(args[0]));
        int Count[]=new int[26];
        int value;
        while ((value = input.read()) != -1) {
            char c=(char)value;

            if((c >='a') && (c<='z')) {
                Count[c -'a' ]++;

            }
            /// change ends.
        }
      int [] calcCount= OdayYameen.service.count;
        Assert.assertArrayEquals(Count,calcCount);


    }

}
