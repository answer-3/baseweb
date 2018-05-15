package com.lizhan.core.util;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MBGUtils {


    public static List<Context> createModels() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
//        File configFile = new File(MBGUtils.class.getResource("/MBGConfig.xml").getPath());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(MBGUtils.class.getResourceAsStream("/MBGConfig.xml"));
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        return config.getContexts();
    }


    public static void createServices(List<Context> contexts) throws IOException, TemplateException {
        for (Context context : contexts) {

            String modelPackage = context.getJavaModelGeneratorConfiguration().getTargetPackage();
            String modelProject = context.getJavaModelGeneratorConfiguration().getTargetProject();

            String mapperPackage = context.getJavaClientGeneratorConfiguration().getTargetPackage();
            String mapperProject = context.getJavaClientGeneratorConfiguration().getTargetProject();



            String mapperPackageParent;
            if (mapperPackage.lastIndexOf(".") > 0) {
                mapperPackageParent = mapperPackage.substring(0, mapperPackage.lastIndexOf("."));
            } else {
                mapperPackageParent = mapperPackage;
            }
            for (TableConfiguration tableConfig : context.getTableConfigurations()) {
                String modelName = tableConfig.getDomainObjectName();

                String modelPath = modelProject + modelPackage.replaceAll("\\.", "/");

                File modelKey = new File(modelPath + "/" + modelName + "Key.java");

                Map<String, Object> valueMap = new HashMap<>();
                valueMap.put("modelName", modelName);
                valueMap.put("modelPackage", modelPackage);
                valueMap.put("hasKeyEntity", modelKey.exists());
                valueMap.put("iServicePackage", mapperPackageParent + ".iservice");

                createJavaByFreeMarker(mapperProject,
                        mapperPackageParent + ".service",
                        modelName + "Service",
                        "Service.FTL",
                        valueMap
                );

                createJavaByFreeMarker(mapperProject,
                        mapperPackageParent + ".controller",
                        modelName + "Controller",
                        "Controller.FTL",
                        valueMap
                );

                createJavaByFreeMarker(mapperProject,
                        mapperPackage,
                        modelName + "Mapper",
                        "Mapper.FTL",
                        valueMap
                );

                createJavaByFreeMarker(mapperProject,
                        mapperPackageParent + ".iservice",
                        "I" + modelName + "Service",
                        "IService.FTL",
                        valueMap
                );

            }


        }


    }


    public static void createJavaByFreeMarker(String dir, String packageName, String objectName, String templateFileName, Map<String, Object> valueMap) throws IOException, TemplateException {

        String javaFilePath = dir + "/" + packageName.replaceAll("\\.", "/") + "/" + objectName + ".java";

        valueMap.put("package", packageName);

        createFileByFreeMarker(new File(javaFilePath), templateFileName, valueMap);

    }

    public static void createFileByFreeMarker(File createFile, String templateFileName, Map<String, Object> valueMap) throws IOException, TemplateException {
        Version version = new Version("2.3.28");
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(version);
        configuration.setDirectoryForTemplateLoading(new File(MBGUtils.class.getResource("/template").getPath()));
        Template template = configuration.getTemplate(templateFileName);
        if (createFile.exists()) {
            createFile.delete();
        } else {
            File parent = new File(createFile.getParent());
            if (!parent.exists()) {
                parent.mkdirs();
            }
        }
        Writer writer = new BufferedWriter(new FileWriter(createFile));
        template.process(valueMap, writer);
        writer.flush();
        writer.close();
    }


    public static void main(String[] args) throws InterruptedException, SQLException, InvalidConfigurationException, XMLParserException, IOException, TemplateException {
        createServices(createModels());
    }
}
