/**
 * 
 */
package hu.infokristaly.homework.ejb3client;

import hu.infokristaly.ejb3fileindexserver.interfaces.IFileSystemIndexService;
import hu.infokristaly.homework.ejb3client.tasks.UploadFileIndexTask;

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.Parser;
import org.jboss.ejb.client.ContextSelector;
import org.jboss.ejb.client.EJBClientConfiguration;
import org.jboss.ejb.client.EJBClientContext;
import org.jboss.ejb.client.PropertiesBasedEJBClientConfiguration;
import org.jboss.ejb.client.remoting.ConfigBasedEJBClientContextSelector;

/**
 * @author pzoli
 *         https://docs.jboss.org/author/display/AS71/EJB+invocations+from+
 *         a+remote+server+instance
 * 
 */
public class Homework4EJB3Client {

    @SuppressWarnings("static-access")
    public static Options getOprions() {
        Options options = new Options();
        options.addOption(OptionBuilder.isRequired(true).withArgName("rootPath").withLongOpt("rootPath").hasArg(true).withDescription("directory for indexing").create());
        options.addOption(OptionBuilder.isRequired(true).withArgName("fileSystemName").withLongOpt("fileSystemName").hasArg(true).withDescription("name of target").create());
        options.addOption(OptionBuilder.isRequired(true).withArgName("mediaName").withLongOpt("mediaName").hasArg(true).withDescription("media type like DVD/HDD/CD").create());
        options.addOption(OptionBuilder.isRequired(true).withArgName("place").withLongOpt("place").hasArg(true).withDescription("location of media").create());
        return options;
    }

    /**
     * Looks up and returns the proxy to remote stateless calculator bean
     * 
     * @return
     * @throws NamingException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static Object lookupRemoteStatelessFileIndexServer() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put("jboss.naming.client.ejb.context", true);
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);
        // The app name is the application name of the deployed EJBs. This is
        // typically the ear name
        // without the .ear suffix. However, the application name could be
        // overridden in the application.xml of the
        // EJB deployment on the server.
        // Since we haven't deployed the application as a .ear, the app name for
        // us will be an empty string
        final String appName = "";
        // This is the module name of the deployed EJBs on the server. This is
        // typically the jar name of the
        // EJB deployment, without the .jar suffix, but can be overridden via
        // the ejb-jar.xml
        // In this example, we have deployed the EJBs in a
        // jboss-as-ejb-remote-app.jar, so the module name is
        // jboss-as-ejb-remote-app
        final String moduleName = "Homework4EJB3FileIndexServer";
        // AS7 allows each deployment to have an (optional) distinct name. We
        // haven't specified a distinct name for
        // our EJB deployment, so this is an empty string
        final String distinctName = "";
        // The EJB name which by default is the simple class name of the bean
        // implementation class
        final String beanName = "FileSystemIndexService";
        // the remote view fully qualified class name
        final String viewClassName = "hu.infokristaly.ejb3fileindexserver.interfaces.IFileSystemIndexService";
        // let's do the lookup
        return context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
    }

    private static Object getFileIndexService() {
        Properties clientProp = new Properties();
        clientProp.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false");
        clientProp.put("remote.connections", "default");
        clientProp.put("remote.connection.default.port", "8080"); //Wildfly 8.2 use 8080 instead Jboss AS 4447
        clientProp.put("remote.connection.default.host", "dell-wifi"); // "amilo-wifi"
        clientProp.put("remote.connection.default.username", "quickstartUser");
        clientProp.put("remote.connection.default.password", "quickstart!1Password"); //quickstartPassword
        clientProp.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", "false");

        EJBClientConfiguration cc = new PropertiesBasedEJBClientConfiguration(clientProp);
        ContextSelector<EJBClientContext> selector = new ConfigBasedEJBClientContextSelector(cc);
        EJBClientContext.setSelector(selector);

        final String appName = "";
        final String moduleName = "Homework4EJB3FileIndexServer";
        final String distinctName = "";
        final String beanName = "FileSystemIndexService";
        final String viewClassName = "hu.infokristaly.ejb3fileindexserver.interfaces.IFileSystemIndexService";

        Properties props = new Properties();
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        Object result = null;
        try {
            Context ctx = new InitialContext(props);
            result = ctx.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void main(String[] args) {
        Options options = getOprions();
        try {
            CommandLine commandLine = null;
            Parser parser = new GnuParser();

            commandLine = parser.parse(options, args);
            String rootPath = (String) commandLine.getOptionValue("rootPath");
            String fileSystemName = (String) commandLine.getOptionValue("fileSystemName");
            String mediaName = (String) commandLine.getOptionValue("mediaName");
            String place = (String) commandLine.getOptionValue("place");

            // Object o = lookupRemoteStatelessFileIndexServer();
            IFileSystemIndexService fileInfoService = (IFileSystemIndexService) getFileIndexService();
            UploadFileIndexTask task = new UploadFileIndexTask(fileInfoService);
            task.setFileSystemName(fileSystemName);
            task.setMediaName(mediaName);
            task.setPlace(place);
            task.setRootPath(rootPath);
            task.startIndexing();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar Homework4EJBClient.jar", options);
        }
    }
}
