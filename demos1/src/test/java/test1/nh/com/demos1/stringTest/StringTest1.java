package test1.nh.com.demos1.stringTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 15-12-30.
 */
public class StringTest1 {


    @Before
    public void before(){
    }

    @After
    public void after(){
    }


    @Test
    public void test1(){
        String str1="012345654321";
        int indexOf3=str1.indexOf("3");
        System.out.println(""+indexOf3);
    }

    @Test
    public void test2(){
        String str1="012345654321";
        int indexOfA=str1.indexOf("A");
        System.out.println(""+indexOfA);  // gives -1
    }

    @Test
    public void test3(){
        String str1="012345654321";
        char char1=str1.toCharArray()[0];
        System.out.println(""+(char1==(""+0).toCharArray()[0]));
    }


}
