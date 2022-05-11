package utils;

import model.SparkIngestConfig;
import model.SparkLauncherConfig;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DatabaseUtils {
    private final  String jdbcUrl;

    public DatabaseUtils(String jdbcUrl){
        this.jdbcUrl = jdbcUrl;
    }
    public SparkLauncherConfig getSparkLauncherConfig(String app) throws SQLException, IOException, org.json.simple.parser.ParseException {
        SparkLauncherConfig config = null;
        Connection connection = DriverManager.getConnection(this.jdbcUrl);
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM spark_launcher_config WHERE app_name='" + app + "'";
        ResultSet resultSet = statement.executeQuery(query);

        if(resultSet.next()){
            int id = resultSet.getInt("id");
            String desc = resultSet.getString("desc");
            String appName = resultSet.getString("app_name");
            String properties = resultSet.getString("properties");
            String created = resultSet.getString("created");
            String modified = resultSet.getString("modified");
            Map <String, String> mapProperties = new HashMap<>();
            Object obj = (new JSONParser()).parse(properties);
            JSONObject jo = (JSONObject) obj;
            Iterator itr1 = jo.entrySet().iterator();

            while (itr1.hasNext()){
                Map.Entry pair = (Map.Entry) itr1.next();
                mapProperties.put(pair.getKey().toString(), pair.getValue().toString());
            }

            SparkLauncherConfig launcher = new SparkLauncherConfig(id, desc, appName, mapProperties, created, modified);
            config = launcher;
        }
        statement.close();
        connection.close();
        return config;
    }
    public Map<String, SparkIngestConfig> readSparkIngestConfig(String app) throws SQLException, IOException {
        Map<String, SparkIngestConfig> map = new HashMap();
        Connection connection = DriverManager.getConnection(this.jdbcUrl);
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM spark_ingest_config WHERE app_name = '" + app + "' AND status = 1";
        ResultSet rs = statement.executeQuery(query);

        while(rs.next()) {
            int id = rs.getInt("id");
            String appName = rs.getString("app_name");
            String type = rs.getString("type");
            int order = rs.getInt("order");
            String topic = rs.getString("topic");
            String status = rs.getString("status");
            String fields = rs.getString("fields");
            String tempViewFirst = rs.getString("temp_view_first");
            String sqlParser = rs.getString("sql_parser");
            String prdId = rs.getString("prd_id");
            String keys = rs.getString("keys");
            String pathHdfs = rs.getString("path_hdfs");
            String tableDest = rs.getString("table_dest");
            String impalaDriver = rs.getString("impala_driver");
            String impalaUrl = rs.getString("impala_url");
            String msgType = rs.getString("msg_type");
            String kafkaMsgType = rs.getString("kafka_msg_type");
            String jsonSchema = rs.getString("json_schema");
            int repartition_des = rs.getInt("repartition_des");
            String created = rs.getString("created");
            String modified = rs.getString("modified");
            SparkIngestConfig ingestConfig = new SparkIngestConfig(id, appName, type, order, topic, status, fields, tempViewFirst, sqlParser, prdId, keys, pathHdfs, tableDest, impalaDriver, impalaUrl, kafkaMsgType, jsonSchema, repartition_des, msgType, created, modified);
            map.put(ingestConfig.getTopic(), ingestConfig);
        }

        statement.close();
        connection.close();
        return map;
    }
}
