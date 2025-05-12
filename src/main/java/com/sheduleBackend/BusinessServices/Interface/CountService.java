package com.sheduleBackend.BusinessServices.Interface;

import java.util.Map;

public interface CountService {

     long subjectCount();
     long studentCount();
     long courseCount();
     long staffCount();

     Map<String , Long> getCounts();
}
