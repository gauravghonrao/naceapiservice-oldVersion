package com.luxoft.proj.naceapi.controller;

import com.luxoft.proj.naceapi.NaceApiApplication;
import com.luxoft.proj.naceapi.configuration.DBConfig;
import com.luxoft.proj.naceapi.dto.NaceDTO;
import com.luxoft.proj.naceapi.exception.NaceRecordNotSavedException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NaceApiApplication.class)
@ContextConfiguration(classes = { DBConfig.class })
@Sql(scripts = { "classpath:initData.sql" })
@Transactional
public class NaceApiControllerIntegrationTest
{
    @Autowired
    private NaceApiController naceApiController;

    @Test
    public void shouldReturnObjectAfterCallingGet() throws Exception, NaceRecordNotSavedException
    {
        NaceDTO response = naceApiController.getNaceDetails("0");
        Assert.assertEquals(0, response.getOrderId());
    }

    @Test
    public void shouldReturnObjectAfterCallingPut() throws Exception, NaceRecordNotSavedException
    {
        NaceDTO naceDto = new NaceDTO();
        naceDto.setOrderId(0);
        naceDto.setCode("1234");
        ResponseEntity<NaceDTO> response = naceApiController.putNaceDetails(naceDto);
        Assert.assertEquals(0, response.getBody().getOrderId());
        Assert.assertEquals("1234", response.getBody().getCode());
    }
}
