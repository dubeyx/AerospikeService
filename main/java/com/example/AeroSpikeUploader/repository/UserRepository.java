package com.example.AeroSpikeUploader.repository;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.WritePolicy;
import com.example.AeroSpikeUploader.config.AerospikeConfigurationProperties;
import com.example.AeroSpikeUploader.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final AerospikeClient aerospikeClient;
    private final AerospikeConfigurationProperties properties;

    public UserRepository(AerospikeClient aerospikeClient, AerospikeConfigurationProperties properties) {
        this.aerospikeClient = aerospikeClient;
        this.properties = properties;
    }

    public void saveUserList(List<String> l,String id){
        WritePolicy writePolicy = new WritePolicy();
        Key key = new Key(properties.getNamespace(), "Akhil_Temp", id);
        Bin bin1 = new Bin("List", l);
        aerospikeClient.put(writePolicy, key, bin1);

    }

    public void saveUser(User user) {
        WritePolicy writePolicy = new WritePolicy();
        Key key = new Key(properties.getNamespace(), "Akhil_Temp", user.getId());
        Bin bin1 = new Bin("name", user.getName());
        Bin bin2 = new Bin("age", user.getAge());
        aerospikeClient.put(writePolicy, key, bin1, bin2);
    }

    public User getUser(String id) {
        Key key = new Key(properties.getNamespace(), "Akhil_Temp", id);
        Record record = aerospikeClient.get(null, key);
        if (record != null) {
            return new User(id, record.getString("name"), record.getInt("age"));
        }
        return null;
    }
}
