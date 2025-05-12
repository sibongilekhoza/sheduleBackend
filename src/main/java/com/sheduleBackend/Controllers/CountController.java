package com.sheduleBackend.Controllers;


import com.sheduleBackend.BusinessServices.Interface.CountService;
import com.sheduleBackend.BusinessServices.impl.CountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin()
@RestController
@RequestMapping("/api/count")
public class CountController {
    @Autowired
    private CountService countService;


    @GetMapping
    public ResponseEntity<Map<String , Long> >getCounts(){

        return ResponseEntity.ok(countService.getCounts());
    }


}
