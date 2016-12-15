var ioc = {
    conf : {
        type : "org.nutz.ioc.impl.PropertiesProxy",
        fields : {
            paths : ["custom/db.properties"]
        }
    },
    dataSource : {
        type : "com.alibaba.druid.pool.DruidDataSource",
        events : {
            create : "init",
            depose : 'close'
        },
        fields : {
            url : {java:"$conf.get('db.url')"},
            username : {java:"$conf.get('db.username')"},
            password : {java:"$conf.get('db.password')"},
            testWhileIdle : true,
            validationQuery : {java:"$conf.get('db.validationQuery')"},
            maxActive : {java:"$conf.get('db.maxActive')"},
            //filters是druid定义的一些过滤器,mergeStat是带合并的sql状态过滤器, 而connectionProperties配置中的2000代表如果sql执行超过2秒,就输出日志
            filters : "mergeStat",
            connectionProperties : "druid.stat.slowSqlMillis=2000"
        }
    },
    dao : {
        type : "org.nutz.dao.impl.NutDao",
        args : [{refer:"dataSource"}]
    }
};