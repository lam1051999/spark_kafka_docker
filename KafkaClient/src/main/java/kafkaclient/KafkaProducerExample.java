package kafkaclient;

import com.github.javafaker.Faker;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class KafkaProducerExample {
    private final static String TOPIC = "personinformation";
    private final static String BOOTSTRAP_SERVERS = "10.110.81.178:9092,10.110.81.179:9092,10.110.81.180:9092";
    private final static String SCHEMA_REGISTRY_URL = "http://10.110.81.178:8081";
    private final static String LOCAL_SCHEMA_PATH = "src/main/resources/person.avsc";
    private final static Schema schema;

    private final static int nPersons = 1000;

    static {
        try {
            schema = new Schema.Parser().parse(new File(LOCAL_SCHEMA_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Producer<String, GenericRecord> createProducer(){
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, SCHEMA_REGISTRY_URL);

        return new KafkaProducer<>(props);
    }

    static void runProducer() {
        final Producer<String, GenericRecord> producer = createProducer();
        Faker faker = new Faker();

        for (int i = 0; i < nPersons; i ++){
            String id = faker.idNumber().valid();
            String firstName = faker.name().firstName();
            String nickName = faker.name().username();
            String lastName = faker.name().lastName();
            int age = faker.number().numberBetween(18, 90);
//            ArrayList<String> emails = new ArrayList<String>();
//            int nEmails = 3;
//            for(int k = 0; k < nEmails; k++){
//                emails.add(faker.internet().safeEmailAddress());
//            }
            String emails = faker.internet().safeEmailAddress();
            String areaCode = String.valueOf(faker.number().numberBetween(200, 500));
            String countryCode = String.valueOf(faker.number().numberBetween(80, 85));
            String prefix = String.valueOf(faker.number().numberBetween(400, 600));
            String number = String.valueOf(faker.number().numberBetween(1234, 6789));

            GenericRecord phoneNumber = new GenericData.Record(schema.getField("phoneNumber").schema());
            phoneNumber.put("areaCode", areaCode);
            phoneNumber.put("countryCode", countryCode);
            phoneNumber.put("prefix", prefix);
            phoneNumber.put("number", number);

            StatusEnum status = StatusEnum.getRandomStatus();

            GenericRecord personInfo = new GenericData.Record(schema);
            personInfo.put("id", id);
            personInfo.put("firstName", firstName);
            personInfo.put("nickName", nickName);
            personInfo.put("lastName", lastName);
            personInfo.put("age", age);
            personInfo.put("emails", emails);
            personInfo.put("phoneNumber", phoneNumber);
            personInfo.put("status", status.toString());

            ProducerRecord<String, GenericRecord> data = new ProducerRecord<String, GenericRecord>(TOPIC, String.format("%s %s %s", firstName, lastName, nickName), personInfo);
            producer.send(data);
            System.out.println("Send successfully!!!");
            try {
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            runProducer();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
