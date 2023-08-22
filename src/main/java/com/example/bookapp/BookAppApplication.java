package com.example.bookapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class BookAppApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }

    public static void main(String[] args) {
        SpringApplication.run(BookAppApplication.class, args);
//        try {
//            ProcessBuilder processBuilder = new ProcessBuilder(
//                    "python3",
//                    "/media/shakib/Projects/Book App/src/main/java/com/example/bookapp/predict_test.py",
//                    "predict","2"
//
//            );
//            processBuilder.redirectErrorStream(true);
//            Process process = processBuilder.start();
//
//            final BufferedReader reader = new BufferedReader(
//                    new InputStreamReader(process.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//            reader.close();
//
//            //System.out.println(result.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }

}
