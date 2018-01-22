package org.tim.notes;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author TNasibullin
 * @since 2017-12-26
 */
@SpringBootApplication
public class NotesApp {
    public static void main(String[] args) {
        SpringApplication notesApp = new SpringApplication(NotesApp.class);
        notesApp.addListeners(new ApplicationPidFileWriter("notes.pid"));
        notesApp.setBannerMode(Banner.Mode.OFF);
        notesApp.run(args);
    }
}
