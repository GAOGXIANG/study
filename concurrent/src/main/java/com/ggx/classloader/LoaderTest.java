package com.ggx.classloader;

import java.net.URL;
import java.net.URLClassLoader;

public class LoaderTest {

    public static void main(String[] args) {
        try{
            System.out.println(ClassLoader.getSystemClassLoader());
            System.out.println(ClassLoader.getSystemClassLoader().getParent());
            System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
            try {
                URL[] extURLs = ((URLClassLoader) ClassLoader.getSystemClassLoader().getParent()).getURLs();
                for (int i = 0; i < extURLs.length; i++) {
                    System.out.println(extURLs[i]);
                }
            } catch (Exception e) {
                //…
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
