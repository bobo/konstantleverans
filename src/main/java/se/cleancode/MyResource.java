package se.cleancode;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.BuildResponseItem;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.BuildImageResultCallback;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.apache.maven.cli.MavenCli;
import org.eclipse.jgit.api.Git;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class MyResource {

    public String build(String path) {
        System.out.println("building: "+path);
        MavenCli cli = new MavenCli();
        cli.doMain(new String[]{"clean", "install"}, path, System.out, System.out);
        return "hello";
    }

    @RequestMapping("/build")
    public String cloneAndBuild() {
        String repo = git();
        build(repo);
        buildDockerImage(repo);
        return "done";
    }

    @RequestMapping("/clone")
    public String git() {

        try {

            Path git = Paths.get("git");
            Files.createDirectory(git);

            Git result = Git.cloneRepository()
                    .setURI("git@github.com:bobo/konstantleverans.git")
                    .setDirectory(git.toFile())
                    .call();
            // Note: the call() returns an opened repository already which needs to be closed to avoid file handle leaks!
            System.out.println("Having repository: " + result.getRepository().getDirectory());
            result.close();
            return result.getRepository().getDirectory().getParentFile().getAbsolutePath();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "mjao";
    }

    public void runSubsystemTests(){

    }

    @RequestMapping("/docker")
    public String buildDockerImage(@RequestBody String path){

        DockerClient dockerClient = DockerClientBuilder.getInstance().build();

        File baseDir = new File(path);

        BuildImageResultCallback callback = new BuildImageResultCallback() {
            @Override
            public void onNext(BuildResponseItem item) {
                System.out.println("" + item);
                super.onNext(item);
            }
        };

        String imageId = dockerClient.buildImageCmd(baseDir).withTag("konstantleverans").exec(callback).awaitImageId();
        return dockerClient.inspectImageCmd(imageId).exec().getRepoTags().get(0);

    }


    @RequestMapping("/deploy")
    public void deployu(){
        KubernetesClient client = new DefaultKubernetesClient();
        Container container = new Container();
        container.setImage("nginx");
        container.setName("nginx");
        PodSpec podspec = new PodSpecBuilder()
                .withContainers(container)
                .build();
        Pod pod = new PodBuilder()
                .withSpec(podspec)
                .withNewMetadata()
                    .withName("nginx")
                .endMetadata()
                .build();
        Pod pod1 = client.pods().inNamespace("default").create(pod);
        System.out.println(pod1.getStatus());
    }

}
