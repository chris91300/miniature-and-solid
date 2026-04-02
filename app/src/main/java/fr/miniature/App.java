
package fr.miniature;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.tomcat.util.descriptor.web.ErrorPage;

import jakarta.servlet.http.HttpServletResponse;

public class App {

    public static void main(String[] args) {
        
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();
        File publicFolder = new File("src/main/webapp/");
        if(!publicFolder.exists()){
            publicFolder.mkdirs();
        }

        Context ctx = tomcat.addWebapp("", publicFolder.getAbsolutePath());
        ctx.setReloadable(true);
        File classFolder = new File("build/classes/java/main");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(
            resources,
            "/WEB-INF/classes",
            classFolder.getAbsolutePath(),
            "/"
        ));
        
        ctx.setResources(resources);
        ErrorPage error404 = new ErrorPage();
        error404.setLocation("/404.html");
        error404.setErrorCode(HttpServletResponse.SC_NOT_FOUND);
        ctx.addErrorPage(error404);
        try{
            tomcat.start();
        }catch(LifecycleException e){
            e.printStackTrace();
        }
        tomcat.getServer().await();
    }
}
