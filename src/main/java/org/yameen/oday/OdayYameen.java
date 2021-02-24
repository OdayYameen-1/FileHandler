package org.yameen.oday;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OdayYameen {


    public static void main(String[] args) {

           /* if(args.length!=1)
                System.err.println("You must enter the dir name only");
       else {*/
          // String dirName=args[0];
          
         String  dirName="G:\\Homworks";
                Service service=new Service();
               service.setAbsoluteFileDirectory(dirName);

        try {

           service.displayDirectoryContents(dirName);

        } catch (IOException e) {
            e.printStackTrace();
        }


        //  }




    }


}

