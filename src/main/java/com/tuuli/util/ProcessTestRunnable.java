package com.tuuli.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessTestRunnable implements Runnable {
    Process p;
    BufferedReader br;

    public ProcessTestRunnable(Process p) {
        this.p = p;
    }

    @Override
    public void run() {
        try {
            InputStreamReader isr = new InputStreamReader(p.getInputStream(), "GBK");
            br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}