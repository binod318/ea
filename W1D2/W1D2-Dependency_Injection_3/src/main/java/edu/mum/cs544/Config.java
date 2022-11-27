package edu.mum.cs544;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan("edu.mum.cs544")
public class Config {

    @Bean
    public BookService bookService(){
        return new BookService();
    }

    @Bean
    public IBookSupplier amazon(){
        return new Amazon();
    }

    @Bean
    public IBookSupplier eBooks(){
        return new EBooks();
    }

    @Bean
    public IBookSupplier barnesAndNoble(){
        return new BarnesAndNoble();
    }

    @Bean
    public IBookSupplier borders(){
        return new Borders();
    }
}
