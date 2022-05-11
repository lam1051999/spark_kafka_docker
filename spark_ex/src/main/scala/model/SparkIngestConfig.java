package model;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class SparkIngestConfig {
    private Integer id;
    private String appName;
    private String type;
    private Integer order;
    private String topic;
    private String status;
    private String fields;
    private String tempViewFirst;
    private String sqlParser;
    private String prdId;
    private String keys;
    private String pathHdfs;
    private String tableDest;
    private String impalaDriver;
    private String impalaUrl;
    private String kafkaMsgType;
    private String jsonSchema;
    private Integer repartition_des;
    private String msgType;
    private String created;
    private String modified;

    public SparkIngestConfig() {
    }

    public SparkIngestConfig(int id, String appName, String type, int order, String topic, String status, String fields, String tempViewFirst, String sqlParser, String prdId, String keys, String pathHdfs, String tableDest, String impalaDriver, String impalaUrl, String kafkaMsgType, String jsonSchema, int repartition_des, String msgType, String created, String modified) {
        this.id = id;
        this.msgType = msgType;
        this.appName = appName;
        this.type = type;
        this.order = order;
        this.topic = topic;
        this.status = status;
        this.fields = fields;
        this.tempViewFirst = tempViewFirst;
        this.sqlParser = sqlParser;
        this.prdId = prdId;
        this.keys = keys;
        this.pathHdfs = pathHdfs;
        this.tableDest = tableDest;
        this.impalaDriver = impalaDriver;
        this.impalaUrl = impalaUrl;
        this.kafkaMsgType = kafkaMsgType;
        this.jsonSchema = jsonSchema;
        this.repartition_des = repartition_des;
        this.created = created;
        this.modified = modified;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFields() {
        return this.fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getTempViewFirst() {
        return this.tempViewFirst;
    }

    public void setTempViewFirst(String tempViewFirst) {
        this.tempViewFirst = tempViewFirst;
    }

    public String getSqlParser() {
        return this.sqlParser;
    }

    public void setSqlParser(String sqlParser) {
        this.sqlParser = sqlParser;
    }

    public String getPrdId() {
        return this.prdId;
    }

    public void setPrdId(String prdId) {
        this.prdId = prdId;
    }

    public String getKeys() {
        return this.keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public String getPathHdfs() {
        return this.pathHdfs;
    }

    public void setPathHdfs(String pathHdfs) {
        this.pathHdfs = pathHdfs;
    }

    public String getTableDest() {
        return this.tableDest;
    }

    public void setTableDest(String tableDest) {
        this.tableDest = tableDest;
    }

    public String getImpalaDriver() {
        return this.impalaDriver;
    }

    public void setImpalaDriver(String impalaDriver) {
        this.impalaDriver = impalaDriver;
    }

    public String getImpalaUrl() {
        return this.impalaUrl;
    }

    public void setImpalaUrl(String impalaUrl) {
        this.impalaUrl = impalaUrl;
    }

    public String getCreated() {
        return this.created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return this.modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getKafkaMsgType() {
        return this.kafkaMsgType;
    }

    public void setKafkaMsgType(String kafkaMsgType) {
        this.kafkaMsgType = kafkaMsgType;
    }

    public String getJsonSchema() {
        return this.jsonSchema;
    }

    public void setJsonSchema(String jsonSchema) {
        this.jsonSchema = jsonSchema;
    }

    public int getRepartition_des() {
        return this.repartition_des;
    }

    public void setRepartition_des(int repartition_des) {
        this.repartition_des = repartition_des;
    }

    public String getMsgType() {
        return this.msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}