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
  public static Service service=new Service();

    public static void main(String[] args) throws Exception {
        Long t1=System.nanoTime();
            if(args.length!=1)
                throw new Exception("the arguments must be length of one");
       else {
          String dirName=args[0];
          
        // String  dirName="C:\\Users\\OdayY\\Desktop\\out3\\";

               service.setAbsoluteFileDirectory(dirName);

        try {

           service.displayDirectoryContents(dirName);

        } catch (IOException e) {
            e.printStackTrace();
        }


          }

        System.err.println(((System.nanoTime()-t1)/1000000000.0)/60);

        System.exit(0);
    }


}

