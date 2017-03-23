package se.cleancode;

import org.apache.maven.cli.MavenCli;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyResource {

    @RequestMapping("/hello")
    public String mjao() {
        MavenCli cli = new MavenCli();
        cli.doMain(new String[]{"clean", "install"}, "/Users/mikael.sundberg/IdeaProjects/hemma/konstantleverans/", System.out, System.out);
        return "hello";
    }


}
