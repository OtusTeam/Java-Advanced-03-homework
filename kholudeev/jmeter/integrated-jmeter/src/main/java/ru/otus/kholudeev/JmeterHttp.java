package ru.otus.kholudeev;

import org.apache.jmeter.config.CSVDataSet;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.control.gui.LoopControlPanel;
import org.apache.jmeter.control.gui.TestPlanGui;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.control.Header;
import org.apache.jmeter.protocol.http.control.HeaderManager;
import org.apache.jmeter.protocol.http.control.gui.HttpTestSampleGui;
import org.apache.jmeter.protocol.http.gui.HeaderPanel;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testbeans.gui.TestBeanGUI;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.gui.ThreadGroupGui;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jmeter.visualizers.SummaryReport;
import org.apache.jmeter.visualizers.ViewResultsFullVisualizer;
import org.apache.jorphan.collections.HashTree;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jorphan.collections.ListedHashTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class JmeterHttp {
    private static final String DOMAIN = "localhost";
    private static final int PORT = 8080;
    private static final String CSV_PATH = "./users.csv";
    private static final String POST_BODY = "{\n  \"login\": \"${login}\",\n  \"name\": \"${name}\",\n  \"password\": \"${pass}\"\n}";

    public static void main(String[] args) {
        try {
            simpleRequest();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void simpleRequest() throws IOException {
        String jmeterHome = System.getenv("JMETER_HOME");
        if (jmeterHome == null) {
            throw new RuntimeException("JMETER_HOME environment variable is not set.");
        }

        JMeterUtils.loadJMeterProperties(jmeterHome + "\\bin\\jmeter.properties");

        JMeterUtils.setJMeterHome(jmeterHome);
        JMeterUtils.initLocale();
        StandardJMeterEngine jmeter = new StandardJMeterEngine();

        ListedHashTree testPlanTree = getTestPlanTree();
        SaveService.saveTree(testPlanTree, Files.newOutputStream(Paths.get( "./script.jmx")));

        jmeter.configure(testPlanTree);
        jmeter.run();
    }

    private static ListedHashTree getTestPlanTree() {
        TestPlan testPlan = getTestPlan();
        ListedHashTree testPlanTree = new ListedHashTree();
        testPlanTree.add(testPlan, getCsv(CSV_PATH, "id,login,pass,name"));
        testPlanTree.add(testPlan, getResultTree());
        testPlanTree.add(testPlan, getLogger("output-logs.jtl"));

        HTTPSamplerProxy httpSamplerGet = getHttpSamplerProxy("authorization/user/${id}", "GET", null, "Get User Request");
        HTTPSamplerProxy httpSamplerPost = getHttpSamplerProxy("authorization/user", "POST", POST_BODY, "Create User Request");
        LoopController loopController = getLoopController(100);
        ThreadGroup threadGroupGet = getThreadGroup(loopController, 1000, 2);
        ThreadGroup threadGroupPost = getThreadGroup(loopController, 100, 0);

        addThreadGroupWithRequest(testPlan, testPlanTree, threadGroupGet, httpSamplerGet);
        addThreadGroupWithRequest(testPlan, testPlanTree, threadGroupPost, httpSamplerPost);

        return testPlanTree;
    }

    private static HashTree addThreadGroupWithRequest(TestPlan testPlan, HashTree testPlanTree, ThreadGroup threadGroup, HTTPSamplerProxy http) {
        testPlan.addThreadGroup(threadGroup);
        HashTree threadGroupHashTree = testPlanTree.add(testPlan, threadGroup);
        HashTree requestHashTree = new HashTree();
        requestHashTree.add(http, getHeader());
        threadGroupHashTree.add(requestHashTree);
        return testPlanTree;
    }

    private static LoopController getLoopController(int loopCount) {
        LoopController loopController = new LoopController();
        loopController.setLoops(loopCount);
        loopController.setFirst(true);
        loopController.setProperty(TestElement.TEST_CLASS, LoopController.class.getName());
        loopController.setProperty(TestElement.GUI_CLASS, LoopControlPanel.class.getName());
        loopController.initialize();
        return loopController;
    }

    private static ThreadGroup getThreadGroup(LoopController loopController, int numThreads, int rumpUp) {
        ThreadGroup threadGroup = new ThreadGroup();
        threadGroup.setName("Sample Thread Group");
        threadGroup.setNumThreads(numThreads);
        threadGroup.setRampUp(rumpUp);
        threadGroup.setSamplerController(loopController);
        threadGroup.setProperty(TestElement.TEST_CLASS, ThreadGroup.class.getName());
        threadGroup.setProperty(TestElement.GUI_CLASS, ThreadGroupGui.class.getName());
        return threadGroup;
    }

    private static HTTPSamplerProxy getHttpSamplerProxy(String path, String method, String body, String name) {
        HTTPSamplerProxy httpSampler = new HTTPSamplerProxy();
        httpSampler.setDomain(DOMAIN);
        httpSampler.setPort(PORT);
        httpSampler.setPath(path);
        httpSampler.setMethod(method);
        httpSampler.setFollowRedirects(true);
        httpSampler.setUseKeepAlive(true);
        if (body != null && !Objects.equals(method, "GET")) {
            httpSampler.setPostBodyRaw(true);
            httpSampler.addNonEncodedArgument("", body, "");
        }
        httpSampler.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
        httpSampler.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());
        httpSampler.setName(name);
        return httpSampler;
    }

    private static HeaderManager getHeader() {
        Header header = new Header("Content-Type", "application/json");
        HeaderManager headerManager = new HeaderManager();
        headerManager.setProperty(TestElement.TEST_CLASS, HeaderManager.class.getName());
        headerManager.setProperty(TestElement.GUI_CLASS, HeaderPanel.class.getName());
        headerManager.setName(JMeterUtils.getResString("header_manager_title"));
        headerManager.add(header);
        headerManager.setEnabled(true);
        return headerManager;
    }

    private static TestPlan getTestPlan() {
        TestPlan testPlan = new TestPlan("Sample Test Plan");
        testPlan.setTearDownOnShutdown(true);
        testPlan.setProperty(TestElement.TEST_CLASS, TestPlan.class.getName());
        testPlan.setProperty(TestElement.GUI_CLASS, TestPlanGui.class.getName());
        return testPlan;
    }

    private static CSVDataSet getCsv(String path, String variables) {
        CSVDataSet csvDataSet = new CSVDataSet();
        csvDataSet.setName("CSV Data Set Config");
        csvDataSet.setProperty("filename", path);
        csvDataSet.setProperty("variableNames", variables);
        csvDataSet.setProperty("delimiter", ",");
        csvDataSet.setQuotedData(false);
        csvDataSet.setRecycle(true);
        csvDataSet.setStopThread(false);
        csvDataSet.setProperty("shareMode", "shareMode.all");
        csvDataSet.setProperty("fileEncoding", "UTF-8");
        csvDataSet.setProperty(TestElement.TEST_CLASS, CSVDataSet.class.getName());
        csvDataSet.setProperty(TestElement.GUI_CLASS, TestBeanGUI.class.getName());
        return csvDataSet;
    }

    private static ResultCollector getResultTree(){
        ResultCollector viewResultsTree = new ResultCollector();
        viewResultsTree.setName("View Results Tree");
        viewResultsTree.setProperty(TestElement.TEST_CLASS, ResultCollector.class.getName());
        viewResultsTree.setProperty(TestElement.GUI_CLASS, ViewResultsFullVisualizer.class.getName());
        return viewResultsTree;
    }

    private static ResultCollector getLogger(String logFile) {
        Summariser summer = null;
        String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
        if (!summariserName.isEmpty()) {
            summer = new Summariser(summariserName);
        }

        ResultCollector logger = new ResultCollector(summer);
        logger.setFilename(logFile);
        logger.setProperty(TestElement.TEST_CLASS, ResultCollector.class.getName());
        logger.setProperty(TestElement.GUI_CLASS, SummaryReport.class.getName());
        logger.setName("Summary Reposrt");
        return logger;
    }
}