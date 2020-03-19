package cn.huanji.utils;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * 根据表明生成model
 */
public class GreneratorServiceEntity {

    /**
     * 文件生成路径
     */
    private static String OUTPUTDIR = "e:\\codeGen";

    @Test
    public void generateCode(){
        String packageName = "cn.huanji.Security";
        boolean flag = false;
        String tableName = "us_power";
        generateByTables(flag,packageName,tableName);
    }

    private void generateByTables(boolean serviceNameStarWithI,
                                  String packageName,
                                  String... tableNames){
        File file = new File(OUTPUTDIR);
        //如果文件已经存在，就删除
        if (file.exists()){
            FileUtil.del(OUTPUTDIR);
        }

        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://localhost:3306/info?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("123456")
                .setDriverName("com.mysql.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true);
        if (tableNames != null){
            strategyConfig.setInclude(tableNames);
        }

        config.setActiveRecord(false)
                .setAuthor("猪肉佬")
                .setOutputDir(OUTPUTDIR)
                .setFileOverride(true)
                .setIdType(IdType.UUID);

        if (!serviceNameStarWithI){
            config.setServiceName("%sService");
        }

        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                        .setParent(packageName)
                        .setController("controller")
                        .setEntity("model")
                        .setMapper("dao")
                        .setXml("dao.mapper")
                ).execute();
    }
}
