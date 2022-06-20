package com.luxoft.proj.naceapi.controller;

import com.luxoft.proj.naceapi.dto.NaceDTO;
import com.luxoft.proj.naceapi.exception.NaceRecordNotSavedException;
import com.luxoft.proj.naceapi.services.NaceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/nace-api")
public class NaceApiController
{
    @Autowired
    private NaceService naceService;

    @GetMapping(value = "/{orderId}/get",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public NaceDTO getNaceDetails(
            @PathVariable
                    String orderId
    )
    {
        return naceService.getNaceById(Integer.valueOf(orderId));
    }

    @PutMapping(value = "/add",
                consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NaceDTO> putNaceDetails(
            @RequestBody
                    NaceDTO naceDTO
    ) throws NaceRecordNotSavedException
    {
        return naceService.addNace(naceDTO);
    }
}