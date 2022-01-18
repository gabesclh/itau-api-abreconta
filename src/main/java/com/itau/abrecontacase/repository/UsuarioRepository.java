package com.itau.abrecontacase.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.itau.abrecontacase.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public UsuarioEntity save(UsuarioEntity usuarioEntity) {
        dynamoDBMapper.save(usuarioEntity);
        return usuarioEntity;
    }

    public PaginatedScanList<UsuarioEntity> getUsuarioByCpf(String cpf) {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        scanExpression.addFilterCondition("cpf", new Condition()
                .withComparisonOperator(ComparisonOperator.EQ)
                .withAttributeValueList(new AttributeValue().withS(cpf)));
        return dynamoDBMapper.scan(UsuarioEntity.class,
                scanExpression);
    }

    public UsuarioEntity delete(String uid) {
        UsuarioEntity del = dynamoDBMapper.load(UsuarioEntity.class, uid);
        dynamoDBMapper.delete(del);
        return del;
    }

    public UsuarioEntity update(String uid, UsuarioEntity usuarioEntity) {
        dynamoDBMapper.save(usuarioEntity, new DynamoDBSaveExpression()
                .withExpectedEntry("uid", new ExpectedAttributeValue(new AttributeValue().withS(uid))));
        return usuarioEntity;
    }
}
